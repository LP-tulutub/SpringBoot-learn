package com.lp.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AnyAspect {

    @Pointcut("execution(* com.lp.controller.AopController.test1(..)))")
    public void test1(){
    }

    @Pointcut("execution(* com.lp.controller.AopController.test2(..)))")
    public void test2(){
    }

    @Before("test1()")
    public void doBeforeTest1(){
        System.out.println("Before--test1()！");
    }


    @Before("test2()")
    public void doBeforeTest2(){
        System.out.println("Before--test2()！");
    }
}
