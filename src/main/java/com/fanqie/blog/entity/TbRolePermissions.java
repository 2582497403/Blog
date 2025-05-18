package com.fanqie.blog.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * (TbRolePermissions)实体类
 *
 * @author makejava
 * @since 2025-05-08 11:06:38
 */
@Data
public class TbRolePermissions implements Serializable {
    private static final long serialVersionUID = -96119052212529229L;

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

