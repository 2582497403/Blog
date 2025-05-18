package com.fanqie.blog.service.impl;

import com.fanqie.blog.entity.TbTag;
import com.fanqie.blog.mapper.TbTagMapper;
import com.fanqie.blog.service.TbTagService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.util.StringUtils;
import java.util.Map;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * (TbTag)表服务实现类
 *
 * @author makejava
 * @since 2025-05-08 11:06:47
 */
@Service("tbTagService")
public class TbTagServiceImpl extends ServiceImpl<TbTagMapper, TbTag> implements TbTagService {
    @Autowired
    private TbTagMapper tbTagMapper;


    /**
     * 分页查询
     *
     */
    @Override
    public Page<TbTag> queryByPage(int page, int size, Map<String, Object> params, String orderBy, boolean isAsc) {
        QueryWrapper<TbTag> queryWrapper = new QueryWrapper<>();

        // 动态查询条件
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            String field = entry.getKey();
            Object value = entry.getValue();
            if (value != null) {
                queryWrapper.like(field, value);
            }
        }

        // 排序
        if (StringUtils.hasText(orderBy)) {
            if (isAsc) {
                queryWrapper.orderByAsc(orderBy);
            } else {
                queryWrapper.orderByDesc(orderBy);
            }
        }

        // 分页查询
        return this.page(new Page<TbTag>(page, size), queryWrapper);
    }

    /**
     * 新增数据
     *
     * @param tbTag 实例对象
     * @return 实例对象
     */
    @Override
    public TbTag insert(TbTag tbTag) {
        this.tbTagMapper.insert(tbTag);
        return tbTag;
    }

    /**
     * 修改数据
     *
     * @param tbTag 实例对象
     * @return 实例对象
     */
    @Override
    public TbTag update(TbTag tbTag) {
        this.tbTagMapper.update(tbTag);
        return this.tbTagMapper.selectById(tbTag);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.tbTagMapper.deleteById(id) > 0;
    }
}
