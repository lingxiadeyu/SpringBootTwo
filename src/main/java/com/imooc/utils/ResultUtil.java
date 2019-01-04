package com.imooc.utils;

import com.imooc.domain.Result;

/*
统一异常处理，把处理成功和失败的结果封装起来，写成工具类
工具类可以定义成static 静态的，这样其他类调用时就不用先创建该类的对象再调用，直接用类去调用方法就行。
 */
public class ResultUtil {

    //定义静态的方法，在其他类中不需要创建类的对象直接用类名.方法名就可以调用

    public static Result success(Object object){
        //定义Result对象
        Result result=new Result();
        //设置code值为0，0是成功的意思
        result.setCode(0);
        //设置msg为成功
        result.setMsg("成功");
        //设置data值为返回的object，返回的可能是girl实体类的信息，所以要定义个object类型
        result.setData(object);
        //然后result
        return result;
    }
    //成功的时候可能会返回null，比如删除一个女生接口，成功是没返回内容，所以要调上面的succes方法，传值为null
    //此方法不需要传参数
    public static Result success(){
        return success(null);
    }


    //定义error
    public static Result error(Integer code,String msg){
        //定义Result对象
        Result result=new Result();
        //code和msg需要传值过来，不同的code值会对应不同的msg值
        result.setCode(code);
        result.setMsg(msg);
        //失败的话data为null，不设置data信息，就会默认返回null
        return result;
    }
}
