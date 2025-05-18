package com.fanqie.blog.mapper;

import com.fanqie.blog.entity.TbArticleTag;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * (TbArticleTag)表数据库访问层
 *
 * @author makejava
 * @since 2025-05-08 11:06:27
 */
public interface TbArticleTagMapper extends BaseMapper<TbArticleTag> {

    /**
     * 通过ID查询单条数据
     *
     * @param articleId 主键
     * @return 实例对象
     */
    TbArticleTag queryById(Long articleId);

    /**
     * 查询指定行数据
     *
     * @param tbArticleTag 查询条件
     * @param pageable     分页对象
     * @return 对象列表
     */
    List<TbArticleTag> queryAllByLimit(TbArticleTag tbArticleTag, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param tbArticleTag 查询条件
     * @return 总行数
     */
    long count(TbArticleTag tbArticleTag);

    /**
     * 新增数据
     *
     * @param tbArticleTag 实例对象
     * @return 影响行数
     */
    int insert(TbArticleTag tbArticleTag);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<TbArticleTag> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<TbArticleTag> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<TbArticleTag> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<TbArticleTag> entities);

    /**
     * 修改数据
     *
     * @param tbArticleTag 实例对象
     * @return 影响行数
     */
    int update(TbArticleTag tbArticleTag);

    /**
     * 通过主键删除数据
     *
     * @param articleId 主键
     * @return 影响行数
     */
    int deleteById(Long articleId);

}

