package com.dayu.aop;

import com.dayu.mapper.OperateLogMapper;
import com.dayu.pojo.OperateLog;
import com.dayu.utils.CurrentHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class OperationLogAspect {

//    @Autowired
//    private OperateLogMapper operateLogMapper;
    private final OperateLogMapper operateLogMapper;

    public OperationLogAspect(OperateLogMapper operateLogMapper) {
        this.operateLogMapper = operateLogMapper;
    }

    // 环绕通知
    @Around("@annotation(com.dayu.anno.Log)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Integer operateEmpId = getCurrentUserId();

        // 记录开始时间
        long startTime = System.currentTimeMillis();
        // 执行方法
        Object result = joinPoint.proceed();
        // 当前时间
        long endTime = System.currentTimeMillis();
        // 耗时
        long costTime = endTime - startTime;

        // 构建日志对象
        OperateLog operateLog = new OperateLog();
        operateLog.setOperateEmpId(operateEmpId); // 需要实现 getCurrentUserId 方法
        operateLog.setOperateTime(LocalDateTime.now());
        operateLog.setClassName(joinPoint.getTarget().getClass().getName());
        operateLog.setMethodName(joinPoint.getSignature().getName());
        operateLog.setMethodParams(Arrays.toString(joinPoint.getArgs()));
        operateLog.setReturnValue(result!= null ? result.toString() : "void");
        operateLog.setCostTime(costTime);

        // 保存日志
        log.info("操作日志:{}", operateLog);

        // 插入日志
        operateLogMapper.insert(operateLog);
        return result;
    }
    
    // 示例方法，获取当前用户ID
    private Integer getCurrentUserId() {
        // 这里应该根据实际情况从认证信息中获取当前登录用户的ID
        return CurrentHolder.getCurrentId();
    }
}