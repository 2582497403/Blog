package com.fanqie.blog.entity;

import java.io.Serializable;

/**
 * (TbUserRoles)实体类
 *
 * @author makejava
 * @since 2025-05-08 08:50:08
 */
public class TbUserRoles implements Serializable {
    private static final long serialVersionUID = -36622338074648694L;

    private Integer userId;

    private Long roleId;


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

}

