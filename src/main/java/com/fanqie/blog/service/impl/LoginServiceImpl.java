package com.fanqie.blog.service.impl;

import com.fanqie.blog.entity.LoginUser;
import com.fanqie.blog.entity.TbUser;
import com.fanqie.blog.exception.BusinessException;
import com.fanqie.blog.service.LoginService;
import com.fanqie.blog.utils.JwtUtil;
import com.fanqie.blog.utils.RedisCache;
import com.fanqie.blog.utils.Result;
import io.jsonwebtoken.lang.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Objects;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RedisCache redisCache;

    @Override
    public Result<HashMap<String,String>> login(TbUser user) {
        HashMap<String,String> map = new HashMap<>();
        if(Objects.isNull(user)|| !Strings.hasText(user.getUsername()) || !Strings.hasText(user.getPassword())){
            throw new BusinessException("账号数据异常");
        }
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword());
        try {
            Authentication authenticate = authenticationManager.authenticate(authenticationToken);
            if(Objects.isNull(authenticate)){
                return Result.error("账号或密码错误"); // 这个判断在正常情况下不会执行到，因为认证失败会抛异常
            }
            //使用userid生成token
            LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
            String userId = loginUser.getUser().getId().toString();
            String jwt = JwtUtil.createJWT(userId);
            //authenticate存入redis
            redisCache.setCacheObject("blog:login:"+userId,loginUser);
            //把token响应给前端
            map.put("token",jwt);
            return Result.ok(map);
        } catch (AuthenticationException e) {
            // 捕获认证失败异常
            return Result.error("账号或密码错误");
        }
    }
}
