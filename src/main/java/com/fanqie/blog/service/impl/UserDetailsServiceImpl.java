package com.fanqie.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fanqie.blog.entity.LoginUser;
import com.fanqie.blog.entity.TbUser;
import com.fanqie.blog.mapper.TbUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private TbUserMapper tbUserMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //username = "admin";
        //查询用户
        TbUser user = tbUserMapper.selectOne(new LambdaQueryWrapper<TbUser>().eq(TbUser::getUsername, username));
        if (Objects.isNull(user)) {
            throw new RuntimeException("账号不存在");
        }
        return new LoginUser(user);
    }

    public UserDetails loadUserById(String userId) {
        TbUser user = tbUserMapper.selectById(Integer.valueOf(userId));
        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException("用户不存在");
        }
        return new LoginUser(user);
    }

}
