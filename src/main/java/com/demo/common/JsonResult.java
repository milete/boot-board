package com.demo.common;

import com.demo.model.enums.ResultCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * description：返回结果
 *
 * @author jiac
 * @date 2020/12/21 11:34
 */
@AllArgsConstructor
@Getter
public class JsonResult {

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 消息
     */
    private String message;

    /**
     * 数据
     */
    private Object data;

    public static JsonResult success() {
        return new JsonResult(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getDesc(), null);
    }

    public static JsonResult success(String message) {
        return new JsonResult(ResultCode.SUCCESS.getCode(), message, null);
    }

    public static JsonResult success(String key, Object object) {
        Map<String, Object> map = new HashMap<>();
        map.put(key, object);
        return new JsonResult(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getDesc(), map);
    }

    public static JsonResult success(Object data) {
        return new JsonResult(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getDesc(), data);
    }

    public static JsonResult failed() {
        return new JsonResult(ResultCode.FAILED.getCode(), ResultCode.FAILED.getDesc(), null);
    }

    public static JsonResult failed(String message) {
        return new JsonResult(ResultCode.FAILED.getCode(), message, null);
    }

    public static JsonResult failed(Object data) {
        return new JsonResult(ResultCode.FAILED.getCode(), ResultCode.FAILED.getDesc(), data);
    }
}
