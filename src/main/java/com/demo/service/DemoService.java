package com.demo.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.demo.mapper.DemoMapper;
import com.demo.model.po.Demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public List<Demo> selectList() {
        return this.demoMapper.selectList(new QueryWrapper<>());
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
