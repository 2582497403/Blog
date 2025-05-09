package com.fanqie.blog.entity;

import java.io.Serializable;

/**
 * (TbRolePermissions)实体类
 *
 * @author makejava
 * @since 2025-05-08 08:49:45
 */
public class TbRolePermissions implements Serializable {
    private static final long serialVersionUID = 744538749301901543L;

    private Long roleId;

    private Long permissionId;


    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }

}

