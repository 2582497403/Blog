package com.fanqie.blog.controller;

import com.fanqie.blog.entity.TbRoles;
import com.fanqie.blog.service.TbRolesService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

/**
 * (TbRoles)表控制层
 *
 * @author makejava
 * @since 2025-05-08 08:52:59
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
    public Page<TbRoles> queryByPage(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String orderBy,
            @RequestParam(defaultValue = "true") boolean isAsc,
            @RequestParam(required = false) String status) {

        Map<String, Object> params = new HashMap<>();
        params.put("status", status);

        return this.tbRolesService.queryByPage(page, size, params, orderBy, isAsc);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<TbRoles> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.tbRolesService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param tbRoles 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<TbRoles> add(TbRoles tbRoles) {
        return ResponseEntity.ok(this.tbRolesService.insert(tbRoles));
    }

    /**
     * 编辑数据
     *
     * @param tbRoles 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<TbRoles> edit(TbRoles tbRoles) {
        return ResponseEntity.ok(this.tbRolesService.update(tbRoles));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(this.tbRolesService.deleteById(id));
    }

}

