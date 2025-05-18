package com.fanqie.blog.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * (TbArticleTag)实体类
 *
 * @author makejava
 * @since 2025-05-08 11:06:27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TbArticleTag implements Serializable {
    private static final long serialVersionUID = 102670295861973795L;

    private Long articleId;

    private Long tagId;

}

