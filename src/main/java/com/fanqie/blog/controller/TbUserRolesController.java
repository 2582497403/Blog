package com.fanqie.blog.controller;

import com.fanqie.blog.entity.TbUserRoles;
import com.fanqie.blog.service.TbUserRolesService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

/**
 * (TbUserRoles)表控制层
 *
 * @author makejava
 * @since 2025-05-08 08:50:08
 */
@RestController
@RequestMapping("tbUserRoles")
public class TbUserRolesController {
    /**
     * 服务对象
     */
    @Autowired
    private TbUserRolesService tbUserRolesService;

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
    public Page<TbUserRoles> queryByPage(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String orderBy,
            @RequestParam(defaultValue = "true") boolean isAsc,
            @RequestParam(required = false) String status) {

        Map<String, Object> params = new HashMap<>();
        params.put("status", status);

        return this.tbUserRolesService.queryByPage(page, size, params, orderBy, isAsc);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<TbUserRoles> queryById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.tbUserRolesService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param tbUserRoles 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<TbUserRoles> add(TbUserRoles tbUserRoles) {
        return ResponseEntity.ok(this.tbUserRolesService.insert(tbUserRoles));
    }

    /**
     * 编辑数据
     *
     * @param tbUserRoles 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<TbUserRoles> edit(TbUserRoles tbUserRoles) {
        return ResponseEntity.ok(this.tbUserRolesService.update(tbUserRoles));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Integer id) {
        return ResponseEntity.ok(this.tbUserRolesService.deleteById(id));
    }

}

