package com.fanqie.blog.controller;

import com.fanqie.blog.entity.TbArticle;
import com.fanqie.blog.entity.TbUser;
import com.fanqie.blog.service.TbArticleService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fanqie.blog.service.TbArticleTagService;
import com.fanqie.blog.service.TbUserService;
import com.fanqie.blog.utils.Result;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

/**
 * (TbArticle)表控制层
 *
 * @author makejava
 * @since 2025-05-08 11:06:20
 */
@RestController
@RequestMapping("tbArticle")
public class TbArticleController {
    /**
     * 服务对象
     */
    @Autowired
    private TbArticleService tbArticleService;
    @Autowired
    private TbUserService tbUserService;
    @Autowired
    private TbArticleTagService tbArticleTagService;

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
    public Result<Page<TbArticle>> queryByPage(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String orderBy,
            @RequestParam(defaultValue = "true") boolean isAsc,
            @RequestParam(required = false) String status) {

        Map<String, Object> params = new HashMap<>();
        params.put("status", status);
        Page<TbArticle> articlePage = this.tbArticleService.queryByPage(page, size, params, orderBy, isAsc);
        return Result.ok(tbArticleTagService.setTagToArticleList(articlePage));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public Result<TbArticle> queryById(@PathVariable("id") Long id) {
        return Result.ok(this.tbArticleService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param tbArticle 实体
     * @return 新增结果
     */
    @PostMapping
    public Result<TbArticle> add(@RequestBody TbArticle tbArticle) {
        TbUser currentUser = tbUserService.getCurrentUser();
        tbArticle.setAuthorId(currentUser.getId());
        return Result.ok(this.tbArticleService.addTbArticle(tbArticle));
    }

    /**
     * 编辑数据
     *
     * @param tbArticle 实体
     * @return 编辑结果
     */
    @PutMapping
    public Result<TbArticle> edit(@RequestBody TbArticle tbArticle) {
        return Result.ok(this.tbArticleService.update(tbArticle));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping("/{id}")
    public Result<Boolean> deleteById(@PathVariable("id") Long id) {
        return Result.ok(this.tbArticleService.deleteById(id));
    }

}

