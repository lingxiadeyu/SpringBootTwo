package com.imooc.exception;

import com.imooc.enums.ResultEnum;

/*
自定义一个Exception，用来处理本项目中的异常
一定要继承RuntimeException，不要继承Exception类，
因为在springBoot中Exception类不能对事务做回滚，RuntimeException类却可以，所以继承RuntimeException运行时异常类。
 */
public class GirlException extends RuntimeException{
    //定义一个code字段，因为父类中有msg字段，我们直接拿过来用就行
    private Integer code;

    public GirlException(Integer code,String msg){
        super(msg);//super(msg)使用父类的msg字段，把当前的msg传值过去。
        this.code=code;

    }
    //使用了枚举值，构造方法要改一下
    public GirlException(ResultEnum resultEnum){
        super(resultEnum.getMsg());//super(msg)使用父类的msg字段，把当前的msg传值过去。
        this.code=resultEnum.getCode();

    }
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }




}
