package com.fanqie.blog.mapper;

import com.fanqie.blog.entity.TbArticle;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * (TbArticle)表数据库访问层
 *
 * @author makejava
 * @since 2025-05-08 11:06:20
 */
public interface TbArticleMapper extends BaseMapper<TbArticle> {

    /**
     * 查询指定行数据
     *
     * @param tbArticle 查询条件
     * @param pageable  分页对象
     * @return 对象列表
     */
    List<TbArticle> queryAllByLimit(TbArticle tbArticle, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param tbArticle 查询条件
     * @return 总行数
     */
    long count(TbArticle tbArticle);



    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<TbArticle> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<TbArticle> entities);

    /**
     * 修改数据
     *
     * @param tbArticle 实例对象
     * @return 影响行数
     */
    int update(TbArticle tbArticle);


    Integer selectView();

    Integer selectLike();

}

