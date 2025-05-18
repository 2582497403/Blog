package com.fanqie.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.fanqie.blog.entity.TbArticle;
import com.fanqie.blog.entity.TbArticleTag;
import com.fanqie.blog.entity.TbCategory;
import com.fanqie.blog.mapper.TbArticleMapper;
import com.fanqie.blog.mapper.TbArticleTagMapper;
import com.fanqie.blog.mapper.TbCategoryMapper;
import com.fanqie.blog.service.TbArticleService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.beans.Transient;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * (TbArticle)表服务实现类
 *
 * @author makejava
 * @since 2025-05-08 11:06:20
 */
@Service("tbArticleService")
public class TbArticleServiceImpl extends ServiceImpl<TbArticleMapper, TbArticle> implements TbArticleService {
    @Autowired
    private TbArticleMapper tbArticleMapper;
    @Autowired
    private TbArticleTagMapper tbArticleTagMapper;
    @Autowired
    private TbCategoryMapper tbCategoryMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public TbArticle queryById(Long id) {
        //查询标签
        TbArticle article = this.tbArticleMapper.selectById(id);
        List<TbArticleTag> articleTagList = tbArticleTagMapper.selectList(new LambdaQueryWrapper<TbArticleTag>().eq(TbArticleTag::getArticleId, id));
        if(articleTagList == null && articleTagList.isEmpty()){
            return null;
        }
        List<Long> tags = new ArrayList<Long>();
        for (TbArticleTag tbArticleTag : articleTagList) {
            tags.add(tbArticleTag.getTagId());
        }
        article.setTags(tags);
        return article;
    }

    /**
     * 分页查询
     */
    @Override
    public Page<TbArticle> queryByPage(int page, int size, Map<String, Object> params, String orderBy, boolean isAsc) {
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

        // 分页查询
        return this.page(new Page<TbArticle>(page, size), queryWrapper);
    }


    /**
     * 修改数据
     *
     * @param tbArticle 实例对象
     * @return 实例对象
     */
    @Override
    @Transactional
    public TbArticle update(TbArticle tbArticle) {
        int i = tbArticleMapper.updateById(tbArticle);
        if(i != 1){
            return null;
        }
        tbArticleTagMapper.delete(new LambdaQueryWrapper<TbArticleTag>().eq(TbArticleTag::getArticleId, tbArticle.getId()));
        for (Long tag : tbArticle.getTags()) {
            tbArticleTagMapper.insert(new TbArticleTag(tbArticle.getId(),tag));
        }
        return tbArticleMapper.selectById(tbArticle.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    @Transactional
    public boolean deleteById(Long id) {
        TbArticle article = tbArticleMapper.selectById(id);
        TbCategory tbCategory = tbCategoryMapper.selectById(article.getCategoryId());
        tbCategory.setCount(tbCategory.getCount()-1);
        tbCategoryMapper.updateById(tbCategory);
        boolean result = this.tbArticleMapper.deleteById(id) > 0 && this.tbArticleTagMapper.delete(new LambdaQueryWrapper<TbArticleTag>().eq(TbArticleTag::getArticleId, id)) > 0;
        return result;
    }

    @Override
    @Transactional
    public TbArticle addTbArticle(TbArticle tbArticle) {
        if("published".equals(tbArticle.getStatus())){
            tbArticle.setPublishedAt(LocalDateTime.now());
        }
        //添加属性
        int insert = tbArticleMapper.insert(tbArticle);
        if (insert > 0) {
            //修改分类
            TbCategory tbCategory = tbCategoryMapper.selectById(tbArticle.getCategoryId());
            tbCategory.setCount(tbCategory.getCount()+1);
            tbCategoryMapper.updateById(tbCategory);
            //关联标签属性
            List<Long> tags = tbArticle.getTags();
            for (Long tag : tags) {
                tbArticleTagMapper.insert(new TbArticleTag(tbArticle.getId(),tag));
            }
            return tbArticle;
        }
        return null;
    }
}
