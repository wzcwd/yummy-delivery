package com.yummy.aspect;

import com.yummy.annotation.AutoFill;
import com.yummy.enumeration.OperationType;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class AutoFillAspect {

    @Pointcut("execution(* com.yummy.mapper.*.*(..)) && @annotation(com.yummy.annotation.AutoFill)")
    public void autoFillPointcut() {}

    @Before("autoFillPointcut()")
    public void autoFill(JoinPoint joinPoint) {
        log.info("start auto fill... ");
        // get method signature
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        // get annotation on method
        AutoFill autoFill = signature.getMethod().getAnnotation(AutoFill.class);
        // get database operation type
        OperationType operationType = autoFill.value();






    }


}
