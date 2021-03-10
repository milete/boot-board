package com.demo.model.po.sys;

import com.demo.model.enums.OperationType;
import com.demo.model.po.BasePo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * description：系统日志
 *
 * @author jiac
 * @date 2020/12/21 11:04
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "系统日志")
public class SysLog extends BasePo {

    @ApiModelProperty(value = "操作模块")
    private String module;

    @ApiModelProperty(value = "操作类型")
    private OperationType operationType;

    @ApiModelProperty(value = "请求地址")
    private String operationUrl;

    @ApiModelProperty(value = "请求方法")
    private String operationMethod;

    @ApiModelProperty(value = "请求Ip")
    private String operationIp;
}
