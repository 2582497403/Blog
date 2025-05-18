package com.fanqie.blog.entity;

import java.util.Date;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * (TbCategory)实体类
 *
 * @author makejava
 * @since 2025-05-08 11:06:05
 */
@Data
public class TbCategory implements Serializable {

    private static final long serialVersionUID = 951002445693894137L;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;

    private String name;

    private String slug;

    private String description;

    private Date createTime;

    private Date updateTime;

    private String color;

    private Integer count;

}

