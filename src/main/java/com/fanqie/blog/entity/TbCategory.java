package com.fanqie.blog.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (TbCategory)实体类
 *
 * @author makejava
 * @since 2025-05-08 08:48:48
 */
public class TbCategory implements Serializable {
    private static final long serialVersionUID = -19567652591901251L;

    private Long id;

    private String name;

    private String slug;

    private String description;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

