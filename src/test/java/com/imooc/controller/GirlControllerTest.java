package com.imooc.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc //该注解是指使用mockmvc测试
public class GirlControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void getgirlslist() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/getgirlslist")).
                andExpect(MockMvcResultMatchers.status().isOk()).
                andExpect(MockMvcResultMatchers.content().string("777"));
        //andExpect方法中有很多判断的方法


        //在命令行中输入：mvn clean package 打包构建
        //mvn clean package -Dmaven.test.skip=true 打包时跳过单元测试

    }
}