package com.fanqie.blog.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.fanqie.blog.entity.LoginUser;
import com.fanqie.blog.entity.TbUser;
import com.fanqie.blog.config.exception.BusinessException;
import com.fanqie.blog.mapper.TbUserMapper;
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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Objects;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RedisCache redisCache;
    @Autowired
    private TbUserMapper tbUserMapper;

    @Override
    public Result<HashMap<String,String>> login(TbUser user) {
        HashMap<String,String> map = new HashMap<>();
        if(Objects.isNull(user)|| !Strings.hasText(user.getUsername()) || !Strings.hasText(user.getPassword())){
            return Result.noauth("账号数据异常");
        }
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword());
        try {
            Authentication authenticate = authenticationManager.authenticate(authenticationToken);
            if(Objects.isNull(authenticate)){
                return Result.noauth("账号或密码错误"); // 这个判断在正常情况下不会执行到，因为认证失败会抛异常
            }
            //使用userid生成token
            LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
            String userId = loginUser.getUser().getId().toString();
            String jwt = JwtUtil.createJWT(userId);
            //authenticate存入redis
            redisCache.setCacheObject("blog:login:"+userId,loginUser);
            //把token响应给前端
            TbUser userInfo = loginUser.getUser();
            userInfo.setPassword("");
            userInfo.setLoginIp(user.getLoginIp());
            map.put("accessToken",jwt);
            map.put("userInfo", JSON.toJSONString(userInfo));
            return Result.ok(map);
        } catch (AuthenticationException e) {
            // 捕获认证失败异常
            return Result.noauth("账号或密码错误");
        }
    }

    @Override
    public Result<String> register(TbUser user) {
        TbUser tbUser = tbUserMapper.selectOne(new LambdaQueryWrapper<TbUser>().eq(TbUser::getUsername, user.getUsername()));
        if(!Objects.isNull(tbUser)){
            return Result.noauth("用户名已存在");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        tbUserMapper.insert(user);
        return Result.ok("注册成功");
    }

    @Override
    public Result<String> logout(TbUser user) {
        return redisCache.deleteObject("blog:login:" + user.getId())?Result.ok("退出登录"):Result.error("失败");
    }
}
