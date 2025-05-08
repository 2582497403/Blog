package com.fanqie.blog.service;

import com.fanqie.blog.entity.TbArticle;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;


/**
 * (TbArticle)表服务接口
 *
 * @author makejava
 * @since 2025-05-07 12:56:18
 */
public interface TbArticleService extends IService<TbArticle> {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TbArticle queryById(Long id);

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
    List<TbArticle> queryByPage(int page, int size, Map<String, Object> params, String orderBy, boolean isAsc);

    /**
     * 新增数据
     *
     * @param tbArticle 实例对象
     * @return 实例对象
     */
    TbArticle insert(TbArticle tbArticle);

    /**
     * 修改数据
     *
     * @param tbArticle 实例对象
     * @return 实例对象
     */
    TbArticle update(TbArticle tbArticle);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
