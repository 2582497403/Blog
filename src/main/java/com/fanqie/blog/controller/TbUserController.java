package com.fanqie.blog.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fanqie.blog.entity.TbUser;
import com.fanqie.blog.service.TbUserService;
import com.fanqie.blog.utils.Result;
import com.fasterxml.jackson.databind.util.ObjectBuffer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.io.Serializable;
import java.util.List;

/**
 * (TbUser)表控制层
 *
 * @author makejava
 * @since 2025-04-17 10:20:24
 */
@RestController
@RequestMapping("tbUser")
public class TbUserController {
    /**
     * 服务对象
     */
    @Autowired
    private TbUserService tbUserService;

    @RequestMapping("getUsers")
    public Result<List<TbUser>> getUsers() {
        return Result.ok(tbUserService.list());
    }

    /**
     * 分页查询所有数据
     *
     * @param page   分页对象
     * @param tbUser 查询实体
     * @return 所有数据
     */
    @GetMapping
    public Result<Page<TbUser>> selectAll(Page<TbUser> page, TbUser tbUser) {
        return Result.ok(this.tbUserService.page(page, new QueryWrapper<>(tbUser)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public Result<TbUser> selectOne(@PathVariable Serializable id) {
        return Result.ok(this.tbUserService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param tbUser 实体对象
     * @return 新增结果
     */
    @PostMapping
    public Result<Boolean> insert(@RequestBody TbUser tbUser) {
        return Result.ok(this.tbUserService.save(tbUser));
    }

    /**
     * 修改数据
     *
     * @param tbUser 实体对象
     * @return 修改结果
     */
    @PutMapping
    public Result<Boolean> update(@RequestBody TbUser tbUser) {
        return Result.ok(this.tbUserService.updateById(tbUser));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public Result<Boolean> delete(@RequestParam("idList") List<Long> idList) {
        return Result.ok(this.tbUserService.removeByIds(idList));
    }
}

