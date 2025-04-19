package com.fanqie.blog.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fanqie.blog.entity.TbRoles;
import com.fanqie.blog.service.TbRolesService;
import com.fanqie.blog.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

/**
 * (TbRoles)表控制层
 *
 * @author makejava
 * @since 2025-04-17 10:20:38
 */
@RestController
@RequestMapping("tbRoles")
public class TbRolesController {
    /**
     * 服务对象
     */
    @Autowired
    private TbRolesService tbRolesService;

    /**
     * 分页查询所有数据
     *
     * @param page    分页对象
     * @param tbRoles 查询实体
     * @return 所有数据
     */
    @GetMapping
    public Result<Page<TbRoles>> selectAll(Page<TbRoles> page, TbRoles tbRoles) {
        return Result.ok(this.tbRolesService.page(page, new QueryWrapper<>(tbRoles)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public Result<TbRoles> selectOne(@PathVariable Serializable id) {
        return Result.ok(this.tbRolesService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param tbRoles 实体对象
     * @return 新增结果
     */
    @PostMapping
    public Result<Boolean> insert(@RequestBody TbRoles tbRoles) {
        return Result.ok(this.tbRolesService.save(tbRoles));
    }

    /**
     * 修改数据
     *
     * @param tbRoles 实体对象
     * @return 修改结果
     */
    @PutMapping
    public Result<Boolean> update(@RequestBody TbRoles tbRoles) {
        return Result.ok(this.tbRolesService.updateById(tbRoles));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public Result<Boolean> delete(@RequestParam("idList") List<Long> idList) {
        return Result.ok(this.tbRolesService.removeByIds(idList));
    }
}

