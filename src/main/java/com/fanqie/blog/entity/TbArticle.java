package com.fanqie.blog.entity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
public class TbArticle implements Serializable {
    private static final long serialVersionUID = -64841424849046729L;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;                    // 文章ID

    private String title;               // 文章标题

    private String slug;                // 文章别名（用于URL优化）

    private String content;             // 文章内容

    private String summary;             // 文章摘要
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long categoryId;            // 分类ID
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long authorId;              // 作者ID

    private Integer views;              // 查看次数

    private Integer likes;               //点赞

    private String status;              // 文章状态（例如：draft、published）

    private Date createTime;            // 创建时间

    private Date updateTime;            // 更新时间

    private LocalDateTime publishedAt;           // 发布时间

    private Boolean isDeleted;          // 是否删除

    private String coverImage;          // 封面图片URL

    private Boolean isTop;               // 是否置顶（建议改为 Boolean 类型以更规范）

    @TableField(exist = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private List<Long> tags;

    @TableField(exist = false)
    private List<String> tagNames;

    @TableField(exist = false)
    private String category;
}