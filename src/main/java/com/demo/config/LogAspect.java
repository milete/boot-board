package com.demo.config;

import com.demo.annotation.Log;
import com.demo.mapper.sys.SysLogMapper;
import com.demo.model.po.sys.SysLog;
import com.demo.utils.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * description：日志记录切面
 *
 * @author jiac
 * @date 2020/12/21 11:02
 */
@Slf4j
@Aspect
@Component
public class LogAspect {

    @Autowired
    private SysLogMapper sysLogMapper;

    @AfterReturning(returning = "jsonResult", pointcut = "@annotation(com.demo.annotation.Log)")
    public void afterReturning(JoinPoint point, Object jsonResult) {
        try {
            MethodSignature methodSignature = (MethodSignature) point.getSignature();
            Method method = methodSignature.getMethod();
            Log logAnnotation = method.getAnnotation(Log.class);
            SysLog sysLog = new SysLog();
            sysLog.setModule(logAnnotation.module());
            sysLog.setOperationType(logAnnotation.operationType());
            HttpServletRequest request = HttpUtil.getRequest();
            sysLog.setOperationUrl(request.getRequestURI());
            sysLog.setOperationMethod(request.getMethod());
            sysLog.setOperationIp(request.getRemoteAddr());
            this.sysLogMapper.insert(sysLog);
        } catch (Exception e) {
            log.error("操作日志记录失败：{}", e.getMessage());
        }
    }
}
