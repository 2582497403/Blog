package com.fanqie.blog.mapper;

import com.fanqie.blog.entity.TbUserRoles;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * (TbUserRoles)表数据库访问层
 *
 * @author makejava
 * @since 2025-05-08 11:06:51
 */
public interface TbUserRolesMapper extends BaseMapper<TbUserRoles>{

    /**
     * 通过ID查询单条数据
     *
     * @param userId 主键
     * @return 实例对象
     */
    TbUserRoles queryById(Integer userId);

    /**
     * 查询指定行数据
     *
     * @param tbUserRoles 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<TbUserRoles> queryAllByLimit(TbUserRoles tbUserRoles, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param tbUserRoles 查询条件
     * @return 总行数
     */
    long count(TbUserRoles tbUserRoles);

    /**
     * 新增数据
     *
     * @param tbUserRoles 实例对象
     * @return 影响行数
     */
    int insert(TbUserRoles tbUserRoles);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<TbUserRoles> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<TbUserRoles> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<TbUserRoles> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<TbUserRoles> entities);

    /**
     * 修改数据
     *
     * @param tbUserRoles 实例对象
     * @return 影响行数
     */
    int update(TbUserRoles tbUserRoles);

    /**
     * 通过主键删除数据
     *
     * @param userId 主键
     * @return 影响行数
     */
    int deleteById(Integer userId);

}

