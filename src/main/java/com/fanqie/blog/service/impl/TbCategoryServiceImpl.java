package com.fanqie.blog.service.impl;

import com.fanqie.blog.entity.TbCategory;
import com.fanqie.blog.mapper.TbCategoryMapper;
import com.fanqie.blog.service.TbCategoryService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.util.StringUtils;

import java.util.Map;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * (TbCategory)表服务实现类
 *
 * @author makejava
 * @since 2025-05-08 11:06:05
 */
@Service("tbCategoryService")
public class TbCategoryServiceImpl extends ServiceImpl<TbCategoryMapper, TbCategory> implements TbCategoryService {
    @Autowired
    private TbCategoryMapper tbCategoryMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public TbCategory queryById(Long id) {
        return this.tbCategoryMapper.queryById(id);
    }

    /**
     * 分页查询
     */
    @Override
    public Page<TbCategory> queryByPage(int page, int size, Map<String, Object> params, String orderBy, boolean isAsc) {
        QueryWrapper<TbCategory> queryWrapper = new QueryWrapper<>();

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
        return this.page(new Page<TbCategory>(page, size), queryWrapper);
    }

    /**
     * 新增数据
     *
     * @param tbCategory 实例对象
     * @return 实例对象
     */
    @Override
    public TbCategory insert(TbCategory tbCategory) {
        this.tbCategoryMapper.insert(tbCategory);
        return tbCategory;
    }

    /**
     * 修改数据
     *
     * @param tbCategory 实例对象
     * @return 实例对象
     */
    @Override
    public TbCategory update(TbCategory tbCategory) {
        this.tbCategoryMapper.update(tbCategory);
        return this.queryById(tbCategory.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.tbCategoryMapper.deleteById(id) > 0;
    }
}
