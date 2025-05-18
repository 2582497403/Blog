package com.fanqie.blog.service.impl;

import com.fanqie.blog.entity.TbArticleTag;
import com.fanqie.blog.mapper.TbArticleTagMapper;
import com.fanqie.blog.service.TbArticleTagService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.util.StringUtils;

import java.util.Map;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * (TbArticleTag)表服务实现类
 *
 * @author makejava
 * @since 2025-05-08 08:49:05
 */
@Service("tbArticleTagService")
public class TbArticleTagServiceImpl extends ServiceImpl<TbArticleTagMapper, TbArticleTag> implements TbArticleTagService {
    @Autowired
    private TbArticleTagMapper tbArticleTagMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param articleId 主键
     * @return 实例对象
     */
    @Override
    public TbArticleTag queryById(Long articleId) {
        return this.tbArticleTagMapper.queryById(articleId);
    }

    /**
     * 分页查询
     */
    @Override
    public Page<TbArticleTag> queryByPage(int page, int size, Map<String, Object> params, String orderBy, boolean isAsc) {
        QueryWrapper<TbArticleTag> queryWrapper = new QueryWrapper<>();

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
        return this.page(new Page<TbArticleTag>(page, size), queryWrapper);
    }

    /**
     * 新增数据
     *
     * @param tbArticleTag 实例对象
     * @return 实例对象
     */
    @Override
    public TbArticleTag insert(TbArticleTag tbArticleTag) {
        this.tbArticleTagMapper.insert(tbArticleTag);
        return tbArticleTag;
    }

    /**
     * 修改数据
     *
     * @param tbArticleTag 实例对象
     * @return 实例对象
     */
    @Override
    public TbArticleTag update(TbArticleTag tbArticleTag) {
        this.tbArticleTagMapper.update(tbArticleTag);
        return this.queryById(tbArticleTag.getArticleId());
    }

    /**
     * 通过主键删除数据
     *
     * @param articleId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long articleId) {
        return this.tbArticleTagMapper.deleteById(articleId) > 0;
    }
}
