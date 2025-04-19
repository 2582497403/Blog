package com.fanqie.blog.config;

import com.fanqie.blog.controller.filter.JwtAuthenticationFilter;
import com.fanqie.blog.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration // 标记为配置类，用于定义 Spring Bean
@EnableWebSecurity // 启用 Spring Security 的 Web 安全功能
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    /**
     * @Bean
     * @Description 定义密码编码器 Bean。
     * Spring Security 推荐使用密码编码器对用户密码进行加密存储，而不是明文。
     * BCryptPasswordEncoder 是一种常用的强密码编码算法。
     * 将其声明为 Bean 后，可以在其他需要使用密码编码的地方进行依赖注入。
     * @return PasswordEncoder 密码编码器实例
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    /**
     * @Bean
     * @Description 配置 Spring Security 的安全过滤器链。
     * SecurityFilterChain 定义了哪些请求应该被拦截，以及如何进行认证和授权。
     *
     * @param http HttpSecurity 构建器，用于配置 HTTP 请求的安全规则
     * @return SecurityFilterChain 安全过滤器链实例
     * @throws Exception 配置过程中可能抛出的异常
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/login").permitAll()
                        .requestMatchers("/auth/register").permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .disable()
                        //.loginPage("/auth/login") // 指定你的自定义登录页面
                        //.loginProcessingUrl("/auth/login") // 处理登录请求的 URL (与 form action 相同)
                        //.defaultSuccessUrl("/", true) // 登录成功后重定向的 URL
                        //.failureUrl("/auth/login?error") // 登录失败后重定向的 URL
                )
                .authenticationProvider(authenticationProvider()) // 注册 DaoAuthenticationProvider
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class); // 将 JWT 验证过滤器添加到 UsernamePasswordAuthenticationFilter 之前;
        return http.build();
    }

    /**
     * @Bean
     * @Description 配置认证管理器 Bean。
     * AuthenticationManager 是 Spring Security 中负责认证的核心组件。
     * 通常情况下，你需要配置一个 AuthenticationProvider 来告诉 Spring Security 如何进行用户认证（例如，从数据库查询用户信息并验证密码）。
     * 通过 AuthenticationConfiguration 可以获取默认的 AuthenticationManager。
     *
     * @param authenticationConfiguration 认证配置对象
     * @return AuthenticationManager 认证管理器实例
     * @throws Exception 获取过程中可能抛出的异常
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    // 你可能还需要配置一个 UserDetailsService Bean 来告诉 Spring Security 如何加载用户信息。
    // 例如：
    // @Bean
    // public UserDetailsService userDetailsService() {
    //     return new CustomUserDetailsService(); // 你的自定义 UserDetailsService 实现
    // }
}