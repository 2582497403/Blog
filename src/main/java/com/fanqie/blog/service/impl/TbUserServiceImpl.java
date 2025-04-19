package com.fanqie.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fanqie.blog.mapper.TbUserMapper;
import com.fanqie.blog.entity.TbUser;
import com.fanqie.blog.service.TbUserService;
import org.springframework.stereotype.Service;

/**
 * (TbUser)表服务实现类
 *
 * @author makejava
 * @since 2025-04-17 10:20:24
 */
@Service("tbUserService")
public class TbUserServiceImpl extends ServiceImpl<TbUserMapper, TbUser> implements TbUserService {

}

