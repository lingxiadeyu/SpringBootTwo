package com.imooc.aspect;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
/*
统一处理请求类
 */

//使用AOP技术需要使用@Aspect注解
//@Component 把类注入到spring容器中（泛指组件，当组件不好归类的时候，我们可以使用这个注解进行标注。）
@Aspect
@Component
public class HttpAspect {

    /*
    没有使用Logger方法的日志处理
     */

//    //使用Before注解，在执行log方法前先执行“execution(public * com.imooc.controller.GirlController.getgirlslist(..))”
//    //执行com.imooc.controller.GirlController.getgirlslist(..)的getgirlslist()方法
//    //getgirlslist(..)  括号中的..是指这个方法中不管什么参数都会拦截，其实什么都不加也可以
//    //com.imooc.controller.GirlController.*(..) 把方法名换成* 星号是指该类中所有的方法都会被拦截
////    @Before("execution(public * com.imooc.controller.GirlController.getgirlslist(..))")
//
    //为了使代码更简单，可以这样写,使用@Pointcut注解
//    @Before("execution(public * com.imooc.controller.GirlController.*(..))")
//    @Pointcut("execution(public * com.imooc.controller.GirlController.*(..))")
//    public void log(){
//    }
//
//    //@Before注解可以这样写，调用方法之前先调用log()方法
//    @Before("log()")
//    public void doBefore(){
//        System.out.println("执行方法前，使用Aop统一处理日志");
//    }
//
//    //在方法执行完后打印日志
////    @After("execution(public * com.imooc.controller.GirlController.*(..))")
//    @After("log()")
//    public void doAfter(){
//        System.out.println("执行方法后，使用Aop统一处理日志");
//    }



    /*
    定义一个私有的常量logger，用来存储日志
     */

    //定义一个私有的常量，用来存储日志
    private final static Logger logger= LoggerFactory.getLogger(HttpAspect.class);
    @Pointcut("execution(public * com.imooc.controller.GirlController.*(..))")
    public void log(){
    }

    //@Before注解可以这样写，调用方法之前先调用log()方法
    @Before("log()")
    public void doBefore(JoinPoint joinPoint){
        logger.info("执行方法前，使用Aop统一处理日志");

        //获取url、method、ip、类、方法等信息并打印成日志
        ServletRequestAttributes attributes= (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request=attributes.getRequest();

        //获取url
        logger.info("url={}",request.getRequestURI());
        //获取method
        logger.info("method={}",request.getMethod());
        //获取ip
        logger.info("ip={}",request.getRemoteAddr());
        //获取方法，需要用joinpoint方法，先获取类名，再获取方法名
        logger.info("class_method={}",joinPoint.getSignature().getDeclaringTypeName()+"."+
                joinPoint.getSignature().getName());
        //使用joinpoint方法获取参数名
        logger.info("args={}",joinPoint.getArgs());
    }

    //在方法执行完后打印日志
    @After("log()")
    public void doAfter(){
        logger.info("执行方法后，使用Aop统一处理日志");
    }

    //获取接口返回的内容
    //使用@AfterReturning注解，获取目标方法返回的参数，目标方法返回的是object对象
    @AfterReturning(returning = "object",pointcut = "log()")
    public void doAfterReturining(Object object){
        logger.info("returning={}",object);
    }




}
