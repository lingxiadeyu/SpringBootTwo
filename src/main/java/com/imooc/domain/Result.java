package com.imooc.domain;

/*
统一异常处理,定义最外层要返回的内容
Result类的属性是要用private私有的，其他的类不可以调用该属性，只能是本类专用
其他类可以通过get和set方法去调用，get和set方法是public
 */
public class Result<T> {


    //错误码
    private Integer code;
    //错误信息
    private String msg;
    //返回具体内容,定义泛型T，T可以是随意的大写字母，
    private T data;


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }





}
