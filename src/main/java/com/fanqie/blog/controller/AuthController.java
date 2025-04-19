package com.fanqie.blog.controller;

import com.fanqie.blog.entity.TbUser;
import com.fanqie.blog.service.LoginService;
import com.fanqie.blog.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public Result<HashMap<String,String>> login(@RequestBody TbUser user) {
        System.out.println("sdadasda");
        return loginService.login(user);
    }

    @PostMapping("/register")
    public Result<String> register(@RequestBody TbUser user) {
        return Result.ok();
    }
}
