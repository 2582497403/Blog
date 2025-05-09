package com.fanqie.blog.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (TbTag)实体类
 *
 * @author makejava
 * @since 2025-05-08 08:49:58
 */
public class TbTag implements Serializable {
    private static final long serialVersionUID = -27728951122356498L;

    private Long id;

    private String name;

    private String slug;

    private Date createdAt;

    private Date updatedAt;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

}

