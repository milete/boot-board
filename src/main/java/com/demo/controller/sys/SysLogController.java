package com.demo.controller.sys;

import com.demo.annotation.Log;
import com.demo.common.PageVo;
import com.demo.common.JsonResult;
import com.demo.model.enums.OperationType;
import com.demo.service.sys.SysLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
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
        return JsonResult.success(this.sysLogService.selectList(pageVo));
    }

    @ApiOperation("导出")
    @PostMapping("export")
    @Log(module = "测试", operationType = OperationType.EXPORT)
    public JsonResult export() {
        String msg = this.sysLogService.export();
        if (StringUtils.isNotBlank(msg)) {
            return JsonResult.failed(msg);
        }
        return null;
    }
}
