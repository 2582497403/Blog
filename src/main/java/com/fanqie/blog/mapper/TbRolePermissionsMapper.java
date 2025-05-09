package com.fanqie.blog.mapper;

import com.fanqie.blog.entity.TbRolePermissions;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * (TbRolePermissions)表数据库访问层
 *
 * @author makejava
 * @since 2025-05-08 08:49:46
 */
public interface TbRolePermissionsMapper extends BaseMapper<TbRolePermissions> {

    /**
     * 通过ID查询单条数据
     *
     * @param roleId 主键
     * @return 实例对象
     */
    TbRolePermissions queryById(Long roleId);

    /**
     * 查询指定行数据
     *
     * @param tbRolePermissions 查询条件
     * @param pageable          分页对象
     * @return 对象列表
     */
    List<TbRolePermissions> queryAllByLimit(TbRolePermissions tbRolePermissions, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param tbRolePermissions 查询条件
     * @return 总行数
     */
    long count(TbRolePermissions tbRolePermissions);

    /**
     * 新增数据
     *
     * @param tbRolePermissions 实例对象
     * @return 影响行数
     */
    int insert(TbRolePermissions tbRolePermissions);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<TbRolePermissions> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<TbRolePermissions> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<TbRolePermissions> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<TbRolePermissions> entities);

    /**
     * 修改数据
     *
     * @param tbRolePermissions 实例对象
     * @return 影响行数
     */
    int update(TbRolePermissions tbRolePermissions);

    /**
     * 通过主键删除数据
     *
     * @param roleId 主键
     * @return 影响行数
     */
    int deleteById(Long roleId);

}

