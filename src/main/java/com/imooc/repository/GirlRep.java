package com.imooc.repository;

import com.imooc.domain.Girl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.io.Serializable;
import java.util.List;
/*
创建一个接口，继承JpaRepository类，Girl是创建的实体类，Integer是参数id的类型
SpringBoot会自动将接口类自动注解到Spring容器中，不需要我们做任何配置
 */

public interface GirlRep extends JpaRepository<Girl, Serializable> {
    public List<Girl> findBycupSize(String cupSize);
    public List<Girl> findByAge(Integer age);
    //自定义方法和sql语句，根据id去查询女生信息
    @Query(value = "select * from girls where id=?1",nativeQuery=true)
    Girl findEntityById(Integer id);
//    public List<Girl> findById(Integer id); 此方法不可用


}
