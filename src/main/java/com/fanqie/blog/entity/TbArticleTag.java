package com.fanqie.blog.entity;

import java.io.Serializable;

/**
 * (TbArticleTag)实体类
 *
 * @author makejava
 * @since 2025-05-08 08:49:05
 */
public class TbArticleTag implements Serializable {
    private static final long serialVersionUID = -15286369466497236L;

    private Long articleId;

    private Long tagId;


    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public Long getTagId() {
        return tagId;
    }

    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }

}

