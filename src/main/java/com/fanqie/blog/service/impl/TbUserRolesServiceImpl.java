package com.fanqie.blog.service.impl;

import com.fanqie.blog.entity.TbUserRoles;
import com.fanqie.blog.mapper.TbUserRolesMapper;
import com.fanqie.blog.service.TbUserRolesService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.util.StringUtils;

import java.util.Map;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * (TbUserRoles)表服务实现类
 *
 * @author makejava
 * @since 2025-05-08 08:50:09
 */
@Service("tbUserRolesService")
public class TbUserRolesServiceImpl extends ServiceImpl<TbUserRolesMapper, TbUserRoles> implements TbUserRolesService {
    @Autowired
    private TbUserRolesMapper tbUserRolesMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param userId 主键
     * @return 实例对象
     */
    @Override
    public TbUserRoles queryById(Integer userId) {
        return this.tbUserRolesMapper.queryById(userId);
    }

    /**
     * 分页查询
     */
    @Override
    public Page<TbUserRoles> queryByPage(int page, int size, Map<String, Object> params, String orderBy, boolean isAsc) {
        QueryWrapper<TbUserRoles> queryWrapper = new QueryWrapper<>();

        // 动态查询条件
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            String field = entry.getKey();
            Object value = entry.getValue();
            if (value != null) {
                queryWrapper.like(field, value);
            }
        }

        // 排序
        if (StringUtils.hasText(orderBy)) {
            if (isAsc) {
                queryWrapper.orderByAsc(orderBy);
            } else {
                queryWrapper.orderByDesc(orderBy);
            }
        }

        // 分页查询
        return this.page(new Page<TbUserRoles>(page, size), queryWrapper);
    }

    /**
     * 新增数据
     *
     * @param tbUserRoles 实例对象
     * @return 实例对象
     */
    @Override
    public TbUserRoles insert(TbUserRoles tbUserRoles) {
        this.tbUserRolesMapper.insert(tbUserRoles);
        return tbUserRoles;
    }

    /**
     * 修改数据
     *
     * @param tbUserRoles 实例对象
     * @return 实例对象
     */
    @Override
    public TbUserRoles update(TbUserRoles tbUserRoles) {
        this.tbUserRolesMapper.update(tbUserRoles);
        return this.queryById(tbUserRoles.getUserId());
    }

    /**
     * 通过主键删除数据
     *
     * @param userId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer userId) {
        return this.tbUserRolesMapper.deleteById(userId) > 0;
    }
}
