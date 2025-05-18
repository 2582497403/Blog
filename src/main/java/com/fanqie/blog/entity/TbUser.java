package com.fanqie.blog.entity;

import java.time.LocalDateTime;
import java.util.Date;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;

/**
 * (TbUser)表实体类
 *
 * @author makejava
 * @since 2025-04-17 10:20:24
 */
@SuppressWarnings("serial")
@Data
public class TbUser extends Model<TbUser> {

    private Long id;
    //用户名
    private String username;
    //密码
    private String password;
    //昵称
    private String nickname;
    //头像
    private String avatar;
    //邮箱
    private String email;
    private String bio;
    //角色
    private Integer role;
    //状态
    private Integer status;
    //创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;
    //盐值
    private String salt;
    //最近登录时间
    private LocalDateTime lastLoginTime;
    //最近登录ip
    private String loginIp;
    //删除
    private Integer deleted;

}

