package com.lp.aop;

import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AllAspect {

    /**
     * 定义切入点，切入点为com.example.demo.aop.AopController中的所有函数
     *通过@Pointcut注解声明频繁使用的切点表达式
     */
    @Pointcut("execution(* com.lp.controller.AopController.*(..)))")
    public void BrokerAspect(){

    }

    @Before("BrokerAspect()")
    public void doBeforeGame(){
        System.out.println("Before！");
    }

    @After("BrokerAspect()")
    public void doAfterGame(){
        System.out.println("After！");
    }

    @AfterReturning("BrokerAspect()")
    public void doAfterReturningGame(){
        System.out.println("AfterReturning！");
    }

    @AfterThrowing("BrokerAspect()")
    public void doAfterThrowingGame(){
        System.out.println("AfterThrowing！");
    }

}
