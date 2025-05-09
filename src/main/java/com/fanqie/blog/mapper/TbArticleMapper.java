package com.fanqie.blog.mapper;

import com.fanqie.blog.entity.TbArticle;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * (TbArticle)表数据库访问层
 *
 * @author makejava
 * @since 2025-05-07 12:56:19
 */
@Repository
public interface TbArticleMapper extends BaseMapper<TbArticle> {

}

