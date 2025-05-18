package com.fanqie.blog.mapper;

import com.fanqie.blog.entity.TbTag;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * (TbTag)表数据库访问层
 *
 * @author makejava
 * @since 2025-05-08 08:49:58
 */
public interface TbTagMapper extends BaseMapper<TbTag> {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TbTag queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param tbTag    查询条件
     * @param pageable 分页对象
     * @return 对象列表
     */
    List<TbTag> queryAllByLimit(TbTag tbTag, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param tbTag 查询条件
     * @return 总行数
     */
    long count(TbTag tbTag);

    /**
     * 新增数据
     *
     * @param tbTag 实例对象
     * @return 影响行数
     */
    int insert(TbTag tbTag);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<TbTag> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<TbTag> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<TbTag> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<TbTag> entities);

    /**
     * 修改数据
     *
     * @param tbTag 实例对象
     * @return 影响行数
     */
    int update(TbTag tbTag);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}

