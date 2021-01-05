package com.demo.service.sys;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.common.PageVo;
import com.demo.mapper.sys.SysLogMapper;
import com.demo.model.po.sys.SysLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * description：系统日志
 *
 * @author jiac
 * @date 2020/12/21 15:19
 */
@Service
public class SysLogService {

    @Autowired
    private SysLogMapper sysLogMapper;

    /**
     * 列表
     *
     * @return
     */
    public IPage<SysLog> selectList(PageVo pageVo) {
        Page<SysLog> page = new Page<>(pageVo.getPage(), pageVo.getSize());
        page.addOrder(OrderItem.desc("create_time"));
        return this.sysLogMapper.selectPage(page, new QueryWrapper<>());
    }
}
