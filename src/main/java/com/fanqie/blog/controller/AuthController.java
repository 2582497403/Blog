package com.fanqie.blog.controller;

import com.fanqie.blog.entity.TbUser;
import com.fanqie.blog.service.LoginService;
import com.fanqie.blog.utils.Result;
import com.fanqie.blog.utils.WebUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.ibatis.annotations.Options;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public Result<HashMap<String,String>> login(@RequestBody TbUser user, HttpServletRequest request) {
        String ip = WebUtils.getClientIp(request);
        user.setLoginIp(ip);
        return loginService.login(user);
    }

    @PutMapping("/register")
    public Result<String> register(@RequestBody TbUser user) {
        return loginService.register(user);
    }

    @PostMapping("/logout")
    public Result<String> logout(@RequestBody TbUser user) {
        return  loginService.logout(user);
    }
}
