package com.imooc.service;

import com.imooc.domain.Girl;
import com.imooc.enums.ResultEnum;
import com.imooc.exception.GirlException;
import com.imooc.repository.GirlRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/*
service层写业务逻辑
 */
@Service
public class GirlService {

    //通过@Autowired注解把GirlRep接口注入Spirng容器中，同时带入Girl实体类
    @Autowired
    public GirlRep girlRep;

    //在service层添加@Transactional注解可以有效控制事务
    @Transactional
    public void interTwo(){
        Girl girlone=new Girl();
        girlone.setAge(33);
        girlone.setCupSize("BBBBBBBBBB");

        Girl girltwo=new Girl();
        girltwo.setAge(34);
        girltwo.setCupSize("D");

        girlRep.save(girlone);
        girlRep.save(girltwo);
    }

    /*
    根据id获取女生信息并判断女生的年龄
     */

    public String getage(Integer id){
        //获取女生信息
        Girl girl=girlRep.findById(id).get();
        //获取女生年龄
        int age=girl.getAge();
        //判断女生年龄，并返回相应的信息
        if(age<10){
            return "正在上小学";
        }else if (10<age && age<16){
            return "正在上初中";
        }else{
            return "年龄很大";
        }
    }

        /*
    优化getage方法，使用抛出异常的方法抛出错误信息
     */

    public void getageuseException (Integer id) throws Exception{
        //获取女生信息
        Girl girl=girlRep.findById(id).get();
        //获取女生年龄
        int age=girl.getAge();
        //判断女生年龄，并返回相应的信息
        if(age<10){
            //通过抛出异常的方式返回错误信息，方法体中写throw new Exception,方法头中也要写throws Exception
            throw new Exception("正在上小学");
        }else if (10<age && age<16){
            throw new Exception("正在上初中");
        }else{
            throw new Exception("年龄很大");
        }
    }
    /*
    优化getage方法，使用自定义的异常类GirlException，自定义错误码
     */

    public void getageuseCustomException (Integer id) throws Exception{
        //获取女生信息
        Girl girl=girlRep.findById(id).get();
        //获取女生年龄
        int age=girl.getAge();
        //判断女生年龄，并返回相应的信息
        if(age<10){
            //通过抛出异常的方式返回错误信息，方法体中写throw new Exception,方法头中也要写throws Exception
            throw new GirlException(100,"正在上小学");
        }else if (10<age && age<16){
            throw new GirlException(101,"正在上初中");
        }else{
            throw new GirlException(102,"年龄很大");
        }
    }
        /*
    优化getage方法，使用枚举值自定义错误编码和错误信息
     */

    public void getageuseCustomcodemsg (Integer id) throws Exception{
        //获取女生信息
        Girl girl=girlRep.findById(id).get();
        //获取女生年龄
        int age=girl.getAge();
        //判断女生年龄，并返回相应的信息
        if(age<10){
            //使用枚举值
            throw new GirlException(ResultEnum.SUCCESS);
        }else if (10<age && age<16){
            throw new GirlException(ResultEnum.PRIMARY_SCHOOL);
        }else{
            throw new GirlException(ResultEnum.MIDDLE_SCHOOL);
        }
    }

    /*
测试GirlService类,根据id去查询，判断返回值是否与预期值一致。
 */
    public Girl findone(Integer id){
        return girlRep.findById(id).get();
    }



}
