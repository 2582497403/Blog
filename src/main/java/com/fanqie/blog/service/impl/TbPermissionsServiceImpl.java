package com.fanqie.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fanqie.blog.mapper.TbPermissionsMapper;
import com.fanqie.blog.entity.TbPermissions;
import com.fanqie.blog.service.TbPermissionsService;
import org.springframework.stereotype.Service;

/**
 * (TbPermissions)表服务实现类
 *
 * @author makejava
 * @since 2025-04-17 10:20:48
 */
@Service("tbPermissionsService")
public class TbPermissionsServiceImpl extends ServiceImpl<TbPermissionsMapper, TbPermissions> implements TbPermissionsService {

}

