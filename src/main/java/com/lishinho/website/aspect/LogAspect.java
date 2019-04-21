package com.lishinho.website.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;

//AOP思想
@Aspect
@Component
public class LogAspect {
    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Before("execution(* com.lishinho.website.controller.*Controller.*(..))")
    public void beforeMethod(JoinPoint joinPoint){
        StringBuilder sb = new StringBuilder();
        for(Object arg : joinPoint.getArgs()){
            sb.append(" args: " + arg.toString() + "|");
        }
        logger.info("before method" + sb.toString());
    }

    @After("execution(* com.lishinho.website.controller.IndexController.*(..))")
    public void afterMethod(){
        logger.info("after method" + new Date());
    }
}
