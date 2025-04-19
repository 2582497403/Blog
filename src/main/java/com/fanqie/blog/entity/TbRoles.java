package com.fanqie.blog.entity;

import java.util.Date;

import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;

/**
 * (TbRoles)表实体类
 *
 * @author makejava
 * @since 2025-04-17 10:20:38
 */
@SuppressWarnings("serial")
public class TbRoles extends Model<TbRoles> {

    private Long id;
    //角色名
    private String name;
    //角色编码
    private String code;
    //描述
    private String description;
    //创建时间
    private Date createdAt;
    //更新时间
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

