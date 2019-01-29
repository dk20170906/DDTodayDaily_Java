package com.dingding.dddaily.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
public class HttpAspect {

@Pointcut("execution(public * com.dingding.dddaily.controller.*(..))")
    public void log(){

    }
    @Before("log()")
    public void doBefore(){
System.out.print("正在加载中，请稍候！！！");
    }
@After("log()")
    public void doAfter(){

    }
}
