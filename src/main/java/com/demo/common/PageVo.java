package com.demo.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

/**
 * description：基础分页对象
 *
 * @author jiac
 * @date 2020/12/22 9:39
 */
@ApiModel("基础分页对象")
@Getter
public class PageVo {

    @ApiModelProperty(value = "当前页，不传默认为第一页", example = "1")
    protected int page = 1;

    @ApiModelProperty(value = "每页记录数，不传默认为20条", example = "20")
    protected int size = 20;

    public void setPage(int page) {
        if (page < 1) {
            page = 1;
        }
        this.page = page;
    }

    public void setSize(int size) {
        if (size < 20) {
            size = 20;
        }
        this.size = size;
    }
}
