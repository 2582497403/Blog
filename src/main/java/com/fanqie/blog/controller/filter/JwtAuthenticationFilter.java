package com.fanqie.blog.controller.filter;

import com.fanqie.blog.config.exception.BusinessException;
import com.fanqie.blog.entity.LoginUser;
import com.fanqie.blog.service.impl.UserDetailsServiceImpl;
import com.fanqie.blog.utils.JwtUtil;
import com.fanqie.blog.utils.RedisCache;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.MimeType;
import org.springframework.util.MimeTypeUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.Objects;

@Component // 标记为 Spring 组件，使其能够被 Spring 容器管理
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired // 自动注入 UserDetailsServiceImpl 实例，用于加载用户详细信息
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private RedisCache redisCache;

    @Override // 重写 OncePerRequestFilter 中的 doFilterInternal 方法，该方法会在每个请求处理过程中执行一次
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // 跳过 OPTIONS 请求
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            filterChain.doFilter(request, response);
            return;
        }

        // 从 HTTP 请求中获取 JWT 字符串
        String jwt = getJwtFromRequest(request);

        // 判断 JWT 字符串是否有效（不为空且不是空白字符）
        if (StringUtils.hasText(jwt)) {
            Claims claims = null;
            try {
                // 使用 JwtUtil 工具类解析 JWT 字符串，获取 Claims 对象（包含了 JWT 的声明信息）
                claims = JwtUtil.parseJWT(jwt);
            }catch (Exception e){
                logger.error("JWT 解析失败: ", e);
                sendErrorResponse(response, HttpServletResponse.SC_UNAUTHORIZED, "登录失效，请重新登录");
                return;
            }
            // 判断 Claims 对象是否存在且 Token 未过期
            if(Objects.isNull(claims) && isTokenExpired(claims)){
                logger.error("Token失效: ");
                sendErrorResponse(response, HttpServletResponse.SC_UNAUTHORIZED, "登录失效，请重新登录");
                return;
            }
            // 从 Claims 中获取用户标识，这里假设 subject 存储的是用户 ID
            String userId = claims.getSubject();
            LoginUser loginUser =  (LoginUser) redisCache.getCacheObject("blog:login:" + userId);
            if(Objects.isNull(loginUser)){
                logger.error(userId+"账号已退出登录 ");
                sendErrorResponse(response, HttpServletResponse.SC_UNAUTHORIZED, "登录失效，请重新登录");
                return;
            }
            // 使用从 JWT 中提取的用户 ID 加载用户的详细信息
            UserDetails userDetails = userDetailsService.loadUserById(userId);
            // 创建一个 UsernamePasswordAuthenticationToken 对象，用于 Spring Security 的认证
            // 参数分别为：userDetails（用户信息）、null（密码，因为 JWT 认证不需要再次验证密码）、userDetails.getAuthorities()（用户拥有的权限）
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities());
            // 设置认证信息的详细信息，例如请求信息
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            // 将创建的认证对象设置到 Spring Security 的安全上下文（SecurityContextHolder）中
            // 这表示当前用户已通过认证，后续的请求可以直接访问受保护的资源
            SecurityContextHolder.getContext().setAuthentication(authentication);

        }

        // 将请求传递给过滤器链中的下一个过滤器
        filterChain.doFilter(request, response);
    }

    // 从 HttpServletRequest 中提取 JWT 字符串的方法
    private String getJwtFromRequest(HttpServletRequest request) {
        // 从请求头中获取名为 "Authorization" 的 Header 值
        String bearerToken = request.getHeader("Authorization");
        // 判断 Authorization Header 是否有效且以 "Bearer " 开头（这是 JWT 的标准格式）
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            // 如果符合 Bearer 格式，则截取 "Bearer " 之后的 Token 字符串（"Bearer " 的长度是 7）
            return bearerToken.substring(7);
        }
        // 如果 Authorization Header 不符合 Bearer 格式，则返回整个 bearerToken (可能为空或包含其他认证信息)
        // 注意：这里的返回逻辑可能需要根据实际需求调整，如果只接受 Bearer Token，可以返回 null。
        return bearerToken;
    }

    // 判断 JWT 是否已过期的方法
    private boolean isTokenExpired(Claims claims) {
        // 从 Claims 中获取过期时间（Expiration Time）
        Date expirationDate = claims.getExpiration();
        // 判断过期时间是否在当前时间之前，如果在之前则表示已过期
        return expirationDate.before(new Date());
    }

    /**
     * 发送请求错误
     * @param response
     * @param status
     * @param message
     * @throws IOException
     */
    private void sendErrorResponse(HttpServletResponse response, int status, String message) throws IOException {
        response.setStatus(status);
        response.setContentType(MimeTypeUtils.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        String json = "{\"success\": false, \"message\": \"" + message + "\", \"code\": " + status + "}";
        response.getWriter().write(json);
    }

}