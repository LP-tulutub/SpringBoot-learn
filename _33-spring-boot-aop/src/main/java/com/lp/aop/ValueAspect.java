package com.lp.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ValueAspect {

    @Pointcut("execution(public * com.lp.controller.Aop2Controller.test3(int)) && args(value))")
    public void test3(int value){
    }
    @Pointcut("execution(public * com.lp.controller.Aop2Controller.test4(int)) && args(value))")
    public void test4(int value){
    }

    @Around("test3(value)")
    public void doAroundGameData(ProceedingJoinPoint pjp, int value) throws Throwable {
        System.out.println("Around: " + value);
        pjp.proceed();
        System.out.println("Around2: " + value);
    }

    @Around("test4(value)")
    public Object deAround(ProceedingJoinPoint joinPoint, int value) throws Throwable{
        System.out.println("Around: " + value);
        Object obj = joinPoint.proceed();
        System.out.println("Around2: " + value);
        return obj;
    }


}
