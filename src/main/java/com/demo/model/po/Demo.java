package com.demo.model.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * description：测试
 *
 * @author jiac
 * @date 2020/12/18 10:25
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("测试")
public class Demo extends BasePo {

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "描述")
    private String description;
}
