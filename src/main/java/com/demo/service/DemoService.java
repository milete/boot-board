package com.demo.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.common.PageVo;
import com.demo.mapper.DemoMapper;
import com.demo.model.po.Demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * description：测试
 *
 * @author jiac
 * @date 2020/12/18 10:24
 */
@Service
public class DemoService {

    @Autowired
    private DemoMapper demoMapper;

    /**
     * 列表
     *
     * @return
     */
    public IPage<Demo> selectList(PageVo pageVo) {
        Page<Demo> page = new Page<>(pageVo.getPage(), pageVo.getSize());
        page.addOrder(OrderItem.desc("create_time"));
        return this.demoMapper.selectPage(page, new QueryWrapper<>());
    }

    /**
     * 新增
     *
     * @param demo
     */
    @Transactional(rollbackFor = Exception.class)
    public void insert(Demo demo) {
        this.demoMapper.insert(demo);
    }

    /**
     * 删除
     *
     * @param id
     */
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(String id) {
        this.demoMapper.deleteById(id);
    }
}
