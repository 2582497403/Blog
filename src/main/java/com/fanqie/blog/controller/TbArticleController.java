package com.fanqie.blog.controller;

import com.fanqie.blog.entity.TbArticle;
import com.fanqie.blog.service.TbArticleService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fanqie.blog.utils.Result;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (TbArticle)表控制层
 *
 * @author makejava
 * @since 2025-05-07 12:56:18
 */
@RestController
@RequestMapping("tbArticle")
public class TbArticleController {
    /**
     * 服务对象
     */
    @Autowired
    private TbArticleService tbArticleService;

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
    public Result<List<TbArticle>> queryByPage(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String orderBy,
            @RequestParam(defaultValue = "true") boolean isAsc,
            @RequestParam(required = false) String status) {

        Map<String, Object> params = new HashMap<>();
        params.put("status", status);

        return Result.ok(tbArticleService.queryByPage(page, size, params, orderBy, isAsc));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<TbArticle> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.tbArticleService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param tbArticle 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<TbArticle> add(TbArticle tbArticle) {
        return ResponseEntity.ok(this.tbArticleService.insert(tbArticle));
    }

    /**
     * 编辑数据
     *
     * @param tbArticle 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<TbArticle> edit(TbArticle tbArticle) {
        return ResponseEntity.ok(this.tbArticleService.update(tbArticle));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(this.tbArticleService.deleteById(id));
    }

}

