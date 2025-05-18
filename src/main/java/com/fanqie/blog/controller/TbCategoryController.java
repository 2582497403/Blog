package com.fanqie.blog.controller;

import com.fanqie.blog.entity.TbCategory;
import com.fanqie.blog.service.TbCategoryService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fanqie.blog.utils.Result;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (TbCategory)表控制层
 *
 * @author makejava
 * @since 2025-05-08 11:06:05
 */
@RestController
@RequestMapping("tbCategory")
public class TbCategoryController {
    /**
     * 服务对象
     */
    @Autowired
    private TbCategoryService tbCategoryService;

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
    public Result<Page<TbCategory>> queryByPage(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String orderBy,
            @RequestParam(defaultValue = "true") boolean isAsc,
            @RequestParam(required = false) String status) {

        Map<String, Object> params = new HashMap<>();
        params.put("status", status);

        return Result.ok(this.tbCategoryService.queryByPage(page, size, params, orderBy, isAsc));
    }

    @GetMapping()
    public Result<List<TbCategory>> queryAll() {
        return Result.ok(this.tbCategoryService.list());
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public Result<TbCategory> queryById(@PathVariable("id") Long id) {
        return Result.ok(this.tbCategoryService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param tbCategory 实体
     * @return 新增结果
     */
    @PostMapping
    public Result<TbCategory> add(@RequestBody TbCategory tbCategory) {
        return Result.ok(this.tbCategoryService.insert(tbCategory));
    }

    /**
     * 编辑数据
     *
     * @param tbCategory 实体
     * @return 编辑结果
     */
    @PutMapping
    public Result<Boolean> edit(@RequestBody TbCategory tbCategory) {
        return Result.ok(this.tbCategoryService.updateById(tbCategory));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping("/{id}")
    public Result<Boolean> deleteById(@PathVariable("id") Long id) {
        return Result.ok(this.tbCategoryService.deleteById(id));
    }

}

