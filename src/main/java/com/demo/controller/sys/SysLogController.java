package com.demo.controller.sys;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.demo.common.PageVo;
import com.demo.common.JsonResult;
import com.demo.model.po.sys.SysLog;
import com.demo.service.sys.SysLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * description：系统日志
 *
 * @author jiac
 * @date 2020/12/21 15:17
 */
@RestController
@RequestMapping("sys/log")
@Api(tags = "系统日志")
public class SysLogController {

    @Autowired
    private SysLogService sysLogService;

    @ApiOperation("列表")
    @GetMapping("list")
    public JsonResult list(PageVo pageVo) {
        IPage<SysLog> list = this.sysLogService.selectList(pageVo);
        return JsonResult.success(list);
    }
}
