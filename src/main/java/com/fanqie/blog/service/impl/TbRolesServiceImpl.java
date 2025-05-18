package com.fanqie.blog.service.impl;

import com.fanqie.blog.entity.TbRoles;
import com.fanqie.blog.mapper.TbRolesMapper;
import com.fanqie.blog.service.TbRolesService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.util.StringUtils;
import java.util.Map;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * (TbRoles)表服务实现类
 *
 * @author makejava
 * @since 2025-05-08 11:06:42
 */
@Service("tbRolesService")
public class TbRolesServiceImpl extends ServiceImpl<TbRolesMapper, TbRoles> implements TbRolesService {
    @Autowired
    private TbRolesMapper tbRolesMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public TbRoles queryById(Long id) {
        return this.tbRolesMapper.queryById(id);
    }

    /**
     * 分页查询
     *
     */
    @Override
    public Page<TbRoles> queryByPage(int page, int size, Map<String, Object> params, String orderBy, boolean isAsc) {
        QueryWrapper<TbRoles> queryWrapper = new QueryWrapper<>();

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
        return this.page(new Page<TbRoles>(page, size), queryWrapper);
    }

    /**
     * 新增数据
     *
     * @param tbRoles 实例对象
     * @return 实例对象
     */
    @Override
    public TbRoles insert(TbRoles tbRoles) {
        this.tbRolesMapper.insert(tbRoles);
        return tbRoles;
    }

    /**
     * 修改数据
     *
     * @param tbRoles 实例对象
     * @return 实例对象
     */
    @Override
    public TbRoles update(TbRoles tbRoles) {
        this.tbRolesMapper.update(tbRoles);
        return this.queryById(tbRoles.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.tbRolesMapper.deleteById(id) > 0;
    }
}
