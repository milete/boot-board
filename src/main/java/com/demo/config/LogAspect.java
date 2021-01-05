package com.demo.config;

import com.demo.annotation.Log;
import com.demo.common.JsonResult;
import com.demo.mapper.sys.SysLogMapper;
import com.demo.model.enums.ResultCode;
import com.demo.model.po.sys.SysLog;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * description：日志记录切面
 *
 * @author jiac
 * @date 2020/12/21 11:02
 */
@Aspect
@Component
public class LogAspect {

    @Autowired
    private SysLogMapper sysLogMapper;

    @AfterReturning(returning = "result", pointcut = "@annotation(com.demo.annotation.Log)")
    public Object afterReturning(JoinPoint point, Object result) {
        //只记录返回成功的操作日志
        if (Objects.equals(ResultCode.SUCCESS.getCode(), ((JsonResult) result).getCode())) {
            MethodSignature methodSignature = (MethodSignature) point.getSignature();
            Method method = methodSignature.getMethod();
            Log logAnnotation = method.getAnnotation(Log.class);
            SysLog sysLog = new SysLog();
            sysLog.setModule(logAnnotation.module());
            sysLog.setOperationType(logAnnotation.operationType());
            this.sysLogMapper.insert(sysLog);
        }
        return result;
    }
}
