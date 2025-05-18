package com.fanqie.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fanqie.blog.mapper.TbUserMapper;
import com.fanqie.blog.entity.TbUser;
import com.fanqie.blog.service.TbArticleService;
import com.fanqie.blog.service.TbUserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * (TbUser)表服务实现类
 *
 * @author makejava
 * @since 2025-04-17 10:20:24
 */
@Service("tbUserService")
public class TbUserServiceImpl extends ServiceImpl<TbUserMapper, TbUser> implements TbUserService {

    public TbUser getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException("未登录");
        }
        String username = authentication.getName();
        return this.lambdaQuery()
                .eq(TbUser::getUsername, username)
                .one();
    }
}

