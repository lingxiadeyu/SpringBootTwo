package com.imooc.handle;

/*
此类主要用来接收和处理异常Exception
 */

import com.imooc.domain.Result;
import com.imooc.exception.GirlException;
import com.imooc.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

//@ControllerAdvice注解，包含@component组件，可以被扫描到统一捕获和处理异常
@ControllerAdvice
public class ExceptionHandle {

    //@ExceptionHandler（Exception.class）注解：用在方法上面表示遇到这个异常就执行以下方法
    //@ResponseBody因为处理的是返回结果的异常，所以加一个@ResponseBody

//    @ExceptionHandler(value = Exception.class)
//    @ResponseBody
//    public Result Handle(Exception e){
//        //返回统一日志处理类中的error方法，错误码是100，错误信息是e.getMessage(),获取异常信息
//        //ResultUtil.error方法返回的类型就是Result类型，所以Handle方法返回的类型也是Result
//        return ResultUtil.error(100,e.getMessage());
//    }

    /*
    重新定义了GirlException，判断捕获的异常是不是GirlExce要重写handle方法

     */
    //通过日志的方式记录异常
    private  final static Logger logger=LoggerFactory.getLogger(ExceptionHandle.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result Handle(Exception e){



        //如果异常e 包含GirlException类 就把e强制类型转换为GirlException类
        if (e instanceof GirlException){
            GirlException girlException=(GirlException) e;
            //获取GirlException的code和msg，然后返回给ResultUtil的error方法
            return ResultUtil.error(girlException.getCode(),girlException.getMessage());
        }else {
            logger.error("【系统异常】{}",e);
            return ResultUtil.error(-1,"未知错误");
        }
    }

}
