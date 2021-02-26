package com.demo.model.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * description：操作类型
 *
 * @author jiac
 * @date 2020/12/21 10:32
 */
@Getter
@AllArgsConstructor
public enum OperationType {
    OTHER("0", "其他"),
    INSERT("1", "新增"),
    UPDATE("2", "修改"),
    DELETE("3", "删除"),
    IMPORT("4", "导入"),
    EXPORT("5", "导出");

    /**
     * 数据库存该字段，字段类型要与数据库字段类型对应
     */
    @EnumValue
    private String code;

    /**
     * 序列化返回该字段
     */
    @JsonValue
    private String desc;
}
