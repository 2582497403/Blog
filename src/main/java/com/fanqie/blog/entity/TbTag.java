package com.fanqie.blog.entity;

import java.util.Date;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * (TbTag)实体类
 *
 * @author makejava
 * @since 2025-05-08 11:06:47
 */
@Data
public class TbTag implements Serializable {
    private static final long serialVersionUID = 395932563881457867L;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;

    private String name;

    private String slug;

    private Date createTime;

    private Date updateTime;

    private String color;

    private String sort;

    private Integer count;
}

