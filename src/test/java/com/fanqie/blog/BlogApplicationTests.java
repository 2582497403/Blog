package com.fanqie.blog;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.fanqie.blog.entity.TbUser;
import com.fanqie.blog.mapper.TbUserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class BlogApplicationTests {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private TbUserMapper tbUserMapper;

    @Test
    void contextLoads() {
        TbUser user = tbUserMapper.selectOne(new LambdaQueryWrapper<TbUser>().eq(TbUser::getUsername, "admin"));
        user.setPassword(passwordEncoder.encode("123456"));
        tbUserMapper.updateById(user);
    }

}
