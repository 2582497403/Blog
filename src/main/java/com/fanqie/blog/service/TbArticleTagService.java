package com.fanqie.blog.service;

import com.fanqie.blog.entity.TbArticleTag;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;


/**
 * (TbArticleTag)表服务接口
 *
 * @author makejava
 * @since 2025-05-08 08:49:05
 */
public interface TbArticleTagService extends IService<TbArticleTag> {

    /**
     * 通过ID查询单条数据
     *
     * @param articleId 主键
     * @return 实例对象
     */
    TbArticleTag queryById(Long articleId);

    /**
     * 分页查询，支持排序与条件过滤
     *
     * @param page    当前页
     * @param size    每页大小
     * @param params  查询条件 (key: 字段名, value: 查询值)
     * @param orderBy 排序字段
     * @param isAsc   是否升序
     * @return 分页结果
     */
    Page<TbArticleTag> queryByPage(int page, int size, Map<String, Object> params, String orderBy, boolean isAsc);

    /**
     * 新增数据
     *
     * @param tbArticleTag 实例对象
     * @return 实例对象
     */
    TbArticleTag insert(TbArticleTag tbArticleTag);

    /**
     * 修改数据
     *
     * @param tbArticleTag 实例对象
     * @return 实例对象
     */
    TbArticleTag update(TbArticleTag tbArticleTag);

    /**
     * 通过主键删除数据
     *
     * @param articleId 主键
     * @return 是否成功
     */
    boolean deleteById(Long articleId);

}
