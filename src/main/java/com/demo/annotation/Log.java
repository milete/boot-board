package com.demo.annotation;

import com.demo.model.enums.OperationType;

import java.lang.annotation.*;

/**
 * description：日志记录
 *
 * @author jiac
 * @date 2020/12/21 10:36
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {

    /**
     * 模块
     */
    String module() default "";

    /**
     * 操作类型
     */
    OperationType operationType() default OperationType.OTHER;
}
