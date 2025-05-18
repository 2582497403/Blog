package com.fanqie.blog.controller;

import com.fanqie.blog.entity.TbPermissions;
import com.fanqie.blog.service.TbPermissionsService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fanqie.blog.utils.Result;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

/**
 * (TbPermissions)表控制层
 *
 * @author makejava
 * @since 2025-05-08 11:06:33
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
     * 通用分页查询接口
     *
     * @param page    当前页
     * @param size    每页大小
     * @param orderBy 排序字段
     * @param isAsc   是否升序
     * @param status  状态
     * @return 分页结果
     */
    @GetMapping("/page")
    public Result<Page<TbPermissions>> queryByPage(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String orderBy,
            @RequestParam(defaultValue = "true") boolean isAsc,
            @RequestParam(required = false) String status) {

        Map<String, Object> params = new HashMap<>();
        params.put("status", status);

        return Result.ok(this.tbPermissionsService.queryByPage(page, size, params, orderBy, isAsc));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public Result<TbPermissions> queryById(@PathVariable("id") Long id) {
        return Result.ok(this.tbPermissionsService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param tbPermissions 实体
     * @return 新增结果
     */
    @PostMapping
    public Result<TbPermissions> add(TbPermissions tbPermissions) {
        return Result.ok(this.tbPermissionsService.insert(tbPermissions));
    }

    /**
     * 编辑数据
     *
     * @param tbPermissions 实体
     * @return 编辑结果
     */
    @PutMapping
    public Result<TbPermissions> edit(TbPermissions tbPermissions) {
        return Result.ok(this.tbPermissionsService.update(tbPermissions));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public Result<Boolean> deleteById(Long id) {
        return Result.ok(this.tbPermissionsService.deleteById(id));
    }

}

