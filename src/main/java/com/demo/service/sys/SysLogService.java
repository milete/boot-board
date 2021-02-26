package com.demo.service.sys;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.common.PageVo;
import com.demo.mapper.sys.SysLogMapper;
import com.demo.model.po.sys.SysLog;
import com.demo.utils.ExcelUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * description：系统日志
 *
 * @author jiac
 * @date 2020/12/21 15:19
 */
@Service
@Slf4j
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

    /**
     * 导出
     */
    public String export() {
        List<SysLog> dataList = this.selectList(new PageVo()).getRecords();
        try {
            ExcelUtil.export(dataList, SysLog.class, "系统日志.xlsx");
        } catch (Exception e) {
            return "导出数据异常，请稍后重试！";
        }
        return "";
    }
}
