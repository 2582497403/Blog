package com.fanqie.blog.entity;

import java.util.Date;
import java.io.Serializable;
import lombok.Data;

/**
 * (TbRoles)实体类
 *
 * @author makejava
 * @since 2025-05-08 11:06:42
 */
@Data
public class TbRoles implements Serializable {
    private static final long serialVersionUID = -13387838524860191L;

    private Long id;
/**
     * 角色名
     */
    private String name;
/**
     * 角色编码
     */
    private String code;
/**
     * 描述
     */
    private String description;

    private Date createTime;

    private Date updateTime;


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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

}

