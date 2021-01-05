package com.demo.controller;

import com.demo.annotation.Log;
import com.demo.common.JsonResult;
import com.demo.model.enums.OperationType;
import com.demo.model.po.Demo;
import com.demo.service.DemoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * description：测试
 *
 * @author jiac
 * @date 2020/12/15 15:01
 */
@RestController
@RequestMapping("demo")
@Api(tags = "测试")
public class DemoController {

    @Autowired
    private DemoService demoService;

    @ApiOperation("列表")
    @GetMapping("list")
    public JsonResult selectList() {
        return JsonResult.success(this.demoService.selectList());
    }

    @ApiOperation("新增")
    @PostMapping("insert")
    @Log(module = "测试", operationType = OperationType.INSERT)
    public JsonResult insert(@RequestBody Demo demo) {
        this.demoService.insert(demo);
        return JsonResult.success();
    }

    @ApiOperation("删除")
    @PostMapping("delete")
    @Log(module = "测试", operationType = OperationType.DELETE)
    public JsonResult delete(String id) {
        this.demoService.deleteById(id);
        return JsonResult.success();
    }
}
