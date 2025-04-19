package com.fanqie.blog.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fanqie.blog.entity.TbPermissions;
import com.fanqie.blog.service.TbPermissionsService;
import com.fanqie.blog.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

/**
 * (TbPermissions)表控制层
 *
 * @author makejava
 * @since 2025-04-17 10:20:48
 */
@RestController
@RequestMapping("tbPermissions")
public class TbPermissionsController {
    /**
     * 服务对象
     */
    @Autowired
    private TbPermissionsService tbPermissionsService;

    /**
     * 分页查询所有数据
     *
     * @param page          分页对象
     * @param tbPermissions 查询实体
     * @return 所有数据
     */
    @GetMapping
    public Result<Page<TbPermissions>> selectAll(Page<TbPermissions> page, TbPermissions tbPermissions) {
        return Result.ok(this.tbPermissionsService.page(page, new QueryWrapper<>(tbPermissions)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public Result<TbPermissions> selectOne(@PathVariable Serializable id) {
        return Result.ok(this.tbPermissionsService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param tbPermissions 实体对象
     * @return 新增结果
     */
    @PostMapping
    public Result<Boolean> insert(@RequestBody TbPermissions tbPermissions) {
        return Result.ok(this.tbPermissionsService.save(tbPermissions));
    }

    /**
     * 修改数据
     *
     * @param tbPermissions 实体对象
     * @return 修改结果
     */
    @PutMapping
    public Result<Boolean> update(@RequestBody TbPermissions tbPermissions) {
        return Result.ok(this.tbPermissionsService.updateById(tbPermissions));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public Result<Boolean> delete(@RequestParam("idList") List<Long> idList) {
        return Result.ok(this.tbPermissionsService.removeByIds(idList));
    }
}

