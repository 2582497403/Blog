package com.fanqie.blog.entity;

import java.util.Date;
import java.io.Serializable;

import lombok.Data;

/**
 * (TbPermissions)实体类
 *
 * @author makejava
 * @since 2025-05-08 11:06:33
 */
@Data
public class TbPermissions implements Serializable {
    private static final long serialVersionUID = 450658695628668446L;

    private Long id;
    /**
     * 权限名
     */
    private String name;
    /**
     * 权限编码
     */
    private String code;
    /**
     * 对应接口
     */
    private String url;
    /**
     * 请求方法
     */
    private String method;
    /**
     * 类型
     */
    private String type;
    /**
     * 父级 ID
     */
    private Long parentId;
    /**
     * 创建时间
     */
    private Date createdAt;
    /**
     * 更新时间
     */
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
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

