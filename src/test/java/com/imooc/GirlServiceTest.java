package com.imooc;

import com.imooc.domain.Girl;
import com.imooc.service.GirlService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

/*
测试GirlService类
 */

//@RunWith注解是指测试运行器，告诉程序是什么环境下的测试，目前参数是SpringRunner是指在Spring环境下的测试
//@SpringBootTest 启动整个SpringBoot的工程
@RunWith(SpringRunner.class)
@SpringBootTest
public class GirlServiceTest {
    //注入GirlService类，下面会使用该类中的方法
    @Autowired
    GirlService girlService;

    @Test
    public void findoneTest(){
        Girl girl=girlService.findone(8);
        //断言，判断age值是否与预期的值相等
        Assert.assertEquals(new Integer(31),girl.getAge());
    }
}
