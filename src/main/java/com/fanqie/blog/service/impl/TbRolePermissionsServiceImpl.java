package com.fanqie.blog.service.impl;

import com.fanqie.blog.entity.TbRolePermissions;
import com.fanqie.blog.mapper.TbRolePermissionsMapper;
import com.fanqie.blog.service.TbRolePermissionsService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.util.StringUtils;
import java.util.Map;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * (TbRolePermissions)表服务实现类
 *
 * @author makejava
 * @since 2025-05-08 11:06:38
 */
@Service("tbRolePermissionsService")
public class TbRolePermissionsServiceImpl extends ServiceImpl<TbRolePermissionsMapper, TbRolePermissions> implements TbRolePermissionsService {
    @Autowired
    private TbRolePermissionsMapper tbRolePermissionsMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param roleId 主键
     * @return 实例对象
     */
    @Override
    public TbRolePermissions queryById(Long roleId) {
        return this.tbRolePermissionsMapper.queryById(roleId);
    }

    /**
     * 分页查询
     *
     */
    @Override
    public Page<TbRolePermissions> queryByPage(int page, int size, Map<String, Object> params, String orderBy, boolean isAsc) {
        QueryWrapper<TbRolePermissions> queryWrapper = new QueryWrapper<>();

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
        return this.page(new Page<TbRolePermissions>(page, size), queryWrapper);
    }

    /**
     * 新增数据
     *
     * @param tbRolePermissions 实例对象
     * @return 实例对象
     */
    @Override
    public TbRolePermissions insert(TbRolePermissions tbRolePermissions) {
        this.tbRolePermissionsMapper.insert(tbRolePermissions);
        return tbRolePermissions;
    }

    /**
     * 修改数据
     *
     * @param tbRolePermissions 实例对象
     * @return 实例对象
     */
    @Override
    public TbRolePermissions update(TbRolePermissions tbRolePermissions) {
        this.tbRolePermissionsMapper.update(tbRolePermissions);
        return this.queryById(tbRolePermissions.getRoleId());
    }

    /**
     * 通过主键删除数据
     *
     * @param roleId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long roleId) {
        return this.tbRolePermissionsMapper.deleteById(roleId) > 0;
    }
}
