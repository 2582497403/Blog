package com.fanqie.blog.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * (TbUserRoles)实体类
 *
 * @author makejava
 * @since 2025-05-08 11:06:51
 */
@Data
public class TbUserRoles implements Serializable {
    private static final long serialVersionUID = -71056575566366894L;

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

