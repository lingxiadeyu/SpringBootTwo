package com.imooc.enums;
/*
把错误编码定义为枚举值
 */
public enum ResultEnum {
    UNKONW_ERROR(-1,"未知错误"),
    SUCCESS(100,"正在上小学"),
    PRIMARY_SCHOOL(101,"正在上初中"),
    MIDDLE_SCHOOL(102,"年龄很大")
    ;



    //定义一个code和msg
    private Integer code;
    private String msg;

    //定义构造函数
    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    //只定义get方法就行，枚举值不会涉及到修改枚举值
    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
