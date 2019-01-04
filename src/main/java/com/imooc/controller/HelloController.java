package com.imooc.controller;

import com.imooc.properties.GirlProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/testtwo") //写到类前面，controller类中的每个方法请求时url都要加上/testtwo
public class HelloController {
//    //使用value注解把cupSize的参数值从yaml文件中获取过来，然后赋值给cupSize参数
//    @Value("${cupSize}")
//    private String cupSize;
//
//    @Value("${age}")
//    private String age;
//
//    //获取组合参数content的值赋值给content
//    @Value("${content}")
//    private String content;

    //使用@Autowired自动注入函数
    @Autowired
    private GirlProperties girlProperties;

    //请求方式注解，value是指请求路径，method是请求方法
    //value = {"/hello","/hi"} 是指请求url时用/hello或者/hi都可以
    @RequestMapping(value = {"/hello","/hi"},method = RequestMethod.GET)
    public String say(){
//        return "hello Spring Boot hahha";
////        return cupSize+" "+age;
//        return content;
        return girlProperties.getCupSize()+girlProperties.getAge();
    }


    //value = "/hello/{idnum}" 输入url时带上参数 如/hello/111
    //使用@PathVariable("idnum") Integer id ，从浏览器输入时获取参数值，Integer id是自定义的参数，
    @RequestMapping(value = "/hello/{idnum}",method =RequestMethod.GET)
    public String saytwo(@PathVariable("idnum") Integer id){
        return "输入的id是:"+id;

    }

    //可以把参数值写到url的中间
    @RequestMapping(value = "/{idnum}/hello",method =RequestMethod.GET)
    public String saythree(@PathVariable("idnum") Integer id){
        return "输入的id是:"+id;
    }

    //使用@RequestParam("id")，在url后面以问号的方式携带参数，比如/hellosayfour?id=666,获取666的值并返回到浏览器中
    @RequestMapping(value = "/hellosayfour",method =RequestMethod.GET)
    public String sayfour(@RequestParam("id") Integer myid){
        return "id:"+myid;
    }

    //@RequestParam(value="id",required = false,defaultValue = "5")是指没有输入值时取默认的值，默认值为5
    @RequestMapping(value = "/hellosayfive",method =RequestMethod.GET)
    public String sayfive(@RequestParam(value="id",required = false,defaultValue = "5") Integer myid){
        return "id:"+myid;
    }

    //@RequestMapping注解可以用@GetMapping(value = "/hellosaysix")代替，post注解可以用@PostMapping
    @GetMapping(value = "/hellosaysix")
    public String saysix(@RequestParam(value="id",required = false,defaultValue = "6") Integer myid){
        return "id:"+myid;
    }


}
