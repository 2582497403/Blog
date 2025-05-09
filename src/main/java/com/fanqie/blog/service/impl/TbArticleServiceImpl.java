package com.fanqie.blog.service.impl;

import com.fanqie.blog.entity.TbArticle;
import com.fanqie.blog.mapper.TbArticleMapper;
import com.fanqie.blog.service.TbArticleService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * (TbArticle)表服务实现类
 *
 * @author makejava
 * @since 2025-05-07 12:56:19
 */
@Service("tbArticleService")
public class TbArticleServiceImpl extends ServiceImpl<TbArticleMapper, TbArticle> implements TbArticleService {
    @Autowired
    private TbArticleMapper tbArticleMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public TbArticle queryById(Long id) {
        return this.tbArticleMapper.selectById(id);
    }

    /**
     * 分页查询
     */
    @Override
    public List<TbArticle> queryByPage(int page, int size, Map<String, Object> params, String orderBy, boolean isAsc) {
        QueryWrapper<TbArticle> queryWrapper = new QueryWrapper<>();

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
        Page<TbArticle> pageParam = new Page<>(page, size);
        Page<TbArticle> selectPage = tbArticleMapper.selectPage(pageParam, null);
        List<TbArticle> resultPage = selectPage.getRecords();
        // 分页查询
        return resultPage;
    }

    /**
     * 新增数据
     *
     * @param tbArticle 实例对象
     * @return 实例对象
     */
    @Override
    public TbArticle insert(TbArticle tbArticle) {
        this.tbArticleMapper.insert(tbArticle);
        return tbArticle;
    }

    /**
     * 修改数据
     *
     * @param tbArticle 实例对象
     * @return 实例对象
     */
    @Override
    public TbArticle update(TbArticle tbArticle) {
        this.tbArticleMapper.updateById(tbArticle);
        return this.queryById(tbArticle.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.tbArticleMapper.deleteById(id) > 0;
    }
}
