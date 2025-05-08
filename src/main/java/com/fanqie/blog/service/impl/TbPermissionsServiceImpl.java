package com.fanqie.blog.service.impl;

import com.fanqie.blog.entity.TbPermissions;
import com.fanqie.blog.mapper.TbPermissionsMapper;
import com.fanqie.blog.service.TbPermissionsService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.util.StringUtils;

import java.util.Map;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * (TbPermissions)表服务实现类
 *
 * @author makejava
 * @since 2025-05-08 08:52:43
 */
@Service("tbPermissionsService")
public class TbPermissionsServiceImpl extends ServiceImpl<TbPermissionsMapper, TbPermissions> implements TbPermissionsService {
    @Autowired
    private TbPermissionsMapper tbPermissionsMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public TbPermissions queryById(Long id) {
        return this.tbPermissionsMapper.queryById(id);
    }

    /**
     * 分页查询
     */
    @Override
    public Page<TbPermissions> queryByPage(int page, int size, Map<String, Object> params, String orderBy, boolean isAsc) {
        QueryWrapper<TbPermissions> queryWrapper = new QueryWrapper<>();

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
        return this.page(new Page<TbPermissions>(page, size), queryWrapper);
    }

    /**
     * 新增数据
     *
     * @param tbPermissions 实例对象
     * @return 实例对象
     */
    @Override
    public TbPermissions insert(TbPermissions tbPermissions) {
        this.tbPermissionsMapper.insert(tbPermissions);
        return tbPermissions;
    }

    /**
     * 修改数据
     *
     * @param tbPermissions 实例对象
     * @return 实例对象
     */
    @Override
    public TbPermissions update(TbPermissions tbPermissions) {
        this.tbPermissionsMapper.update(tbPermissions);
        return this.queryById(tbPermissions.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.tbPermissionsMapper.deleteById(id) > 0;
    }
}
