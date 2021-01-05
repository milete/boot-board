package com.demo.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * description：状态码
 *
 * @author jiac
 * @date 2020/12/21 11:29
 */
@Getter
@AllArgsConstructor
public enum ResultCode {
    SUCCESS(200, "成功"),
    FAILED(503, "失败"),
    PARAMS_ERROR(403, "参数错误");

    private Integer code;
    private String desc;
}
