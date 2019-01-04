package com.imooc.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
/*
创建Girl实体类，用来存放数据库的字段信息。
Girl类的属性是要用private私有的，其他的类不可以调用该属性，只能是本类专用
其他类可以通过get和set方法去调用，get和set方法是public
 */


//@Entity:@Table(name="") 表明这是一个实体类，一般和jpa配合着使用，如果实体类名称和数据库名称一致，@Table注解可以省略
//此注解代表要生成一个Girls数据表
@Entity
@Table(name = "Girls")
public class Girl {


    //@Id注解表示该属性为主键，此注解放在private Integer id;前面，代表id为该表的主键。
    //@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = “repair_seq”)：表示主键生成策略是sequence（可以为Auto、IDENTITY、native等，Auto表示可在多个数据库间切换），指定sequence的名字是repair_seq。
    @Id
    @GeneratedValue
    private Integer id;
    private String cupSize;
    private Integer age;

    //生成一个构造函数
    public Girl(){

    }

//以下是getter和setter方法
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCupSize() {
        return cupSize;
    }

    public void setCupSize(String cupSize) {
        this.cupSize = cupSize;
    }

    //@Min注解是给属性值一个最小值，如果小于最小值给出一个message提示
    @Min(value = 18,message = "未成年少女不可入内")
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    //重写toString()方法
    @Override
    public String toString() {
        return "Girl{" +
                "id=" + id +
                ", cupSize='" + cupSize + '\'' +
                ", age=" + age +
                '}';
    }



}
