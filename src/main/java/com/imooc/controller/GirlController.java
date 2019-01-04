package com.imooc.controller;

import com.imooc.aspect.HttpAspect;
import com.imooc.domain.Girl;
import com.imooc.domain.Result;
import com.imooc.repository.GirlRep;
import com.imooc.service.GirlService;
import com.imooc.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
/*
1、本类是controller 控制类。添加、修改、查询数据库信息
2、本方法中不能写构造方法，因为spring启动时就已经把类实例化了，当http请求过来时就不会再请求构造方法，所以不能写构造方法
 */

//@RestController 从浏览器中请求该接口
@RestController
public class GirlController {

    //定义一个私有的常量，用来存储日志
    private final static Logger logger= LoggerFactory.getLogger(GirlController.class);

    //通过@Autowired把GirlRep接口自动注入到SpringBoot容器中，同时带入Girl实体类
    @Autowired
    private GirlRep girlRep;

//    /**
//    添加女生方法一：add girl
//    @param cupSize
//     @param age
//     */
//
//    //定义一个post方法，设置两个参数，与数据库中参数值要对应
//    @RequestMapping(value = "/addgirl",method = RequestMethod.POST)
//    public Girl addgirl(@RequestParam("cupSize") String cupSize,
//                        @RequestParam("age") Integer age){
//        //定义一个gir对象
//        Girl girl=new Girl();
//        //设置cupsize和age的值
//        girl.setCupSize(cupSize);
//        girl.setAge(age);
//        //调girlRep接口的save方法，往数据库中插入值,并同时返回插入的值信息,如果数据库表不存在会先建表再插入数据
//        return girlRep.save(girl);
//    }



//    /**
//     * 添加女生方法二：添加女生，使用@Valid注解添加校验方法，获取默认的错误信息
//     * @param girl
//     * @return
//     */
//
//    //定义一个post方法，设置两个参数，与数据库中参数值要对应
//    @RequestMapping(value = "/addgirl",method = RequestMethod.POST)
//    public Girl addgirl(@Valid Girl girl, BindingResult bindingResult){
//        //不写获取参数的@RequestParma注解，直接写一个对象，通过对象的get方法获取参数值。如果属性值很多就写入girl实体类中就行。
//        //@Valid注解 验证age小于18时会报错，接收并打印错误，同时返回null
//        if(bindingResult.hasErrors()){
//            System.out.println(bindingResult.getFieldError().getDefaultMessage());
//            return null;
//        }
//        //设置cupsize和age的值
//        girl.setCupSize(girl.getCupSize());
//        girl.setAge(girl.getAge());
//        //调girlRep接口的save方法，往数据库中插入值,并同时返回插入的值信息
//        return girlRep.save(girl);
//    }


//    /**
//     * 添加女生方法三：添加女生，使用@Valid注解添加校验方法，获取默认的错误信息，返回错误信息到response中
//     * 方法的返回类型修改为Object
//     * @param girl
//     * @return
//     */
//    //定义一个post方法，设置两个参数，与数据库中参数值要对应
//    @RequestMapping(value = "/addgirl",method = RequestMethod.POST)
//    public Object addgirl(@Valid Girl girl, BindingResult bindingResult){
//        //不写获取参数的@RequestParma注解，直接写一个对象，通过对象的get方法获取参数值。如果属性值很多就写入girl实体类中就行。
//        //@Valid注解 验证age小于18时会报错，接收并打印错误，同时返回null
//        if(bindingResult.hasErrors()){
//            return bindingResult.getFieldError().getDefaultMessage();
//        }
//        //设置cupsize和age的值
//        girl.setCupSize(girl.getCupSize());
//        girl.setAge(girl.getAge());
//        //调girlRep接口的save方法，往数据库中插入值,并同时返回插入的值信息
//        return girlRep.save(girl);
//    }

//    /**
//     * 添加女生方法四：添加女生，使用统一异常处理，统一返回code、msg、responsebody
//     *
//     * @param girl
//     * @return
//     */
//    //定义一个post方法，设置两个参数，与数据库中参数值要对应
//    @RequestMapping(value = "/addgirl",method = RequestMethod.POST)
//    public Result<Girl> addgirl(@Valid Girl girl, BindingResult bindingResult){
//        if(bindingResult.hasErrors()){
//            //定义Result对象，给code、Msg赋值
//            Result result=new Result();
//            //code为1代表失败
//            result.setCode(1);
//            //把获取到的错误信息赋值给msg
//            result.setMsg(bindingResult.getFieldError().getDefaultMessage());
//            //不设置data信息，就会默认返回null
//            //返回result
//            return result;
//        }
//
//        //设置cupsize和age的值
//        girl.setCupSize(girl.getCupSize());
//        girl.setAge(girl.getAge());
//        //定义result对象，给code、msg赋值
//        Result result=new Result();
//        //成功返回0
//        result.setCode(0);
//        //接口调成功返回成功
//        result.setMsg("成功");
//        //调girlRep接口的save方法，往数据库中插入值,并同时返回插入的值信息，
//        // 把girlRep.save(girl)返回的结果赋值给data
//        result.setData(girlRep.save(girl));
//        return result;
//    }

    /**
     * 添加女生方法五：添加女生，使用统一异常处理，创建ResultUtil类，把result代码封装起来
     *
     * @param girl
     * @return
     */
    //定义一个post方法，设置两个参数，与数据库中参数值要对应
    @RequestMapping(value = "/addgirl",method = RequestMethod.POST)
    public Result<Girl> addgirl(@Valid Girl girl, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ResultUtil.error(1,bindingResult.getFieldError().getDefaultMessage());
        }

        //设置cupsize和age的值
        girl.setCupSize(girl.getCupSize());
        girl.setAge(girl.getAge());
        return ResultUtil.success(girlRep.save(girl));
    }

    /*


     */
    @GetMapping(value = "/getgirlsage/{id}")
    public void getgirlsage(@PathVariable("id") Integer id){

    }


    /**
    方法名:getgirlslist
    描述：从数据库中获取女生数据，返回类型为List,是返回所有的女生信息
     */
    @RequestMapping(value = "/getgirlslist",method = RequestMethod.GET)
    public List<Girl> getgirlslist(){
//        System.out.println("测试AOP统一日志打印的先后顺序，先打印AOP日志再打印本语句");
        logger.info("测试AOP统一日志打印的先后顺序，先打印AOP日志再打印本语句");
        return girlRep.findAll();
    }

    /**
     * update girls
     * @param id
     * @param cupSize
     * @param age
     * @return girl update
     */

    @PostMapping(value = "/updategirls/{id}")
    public Girl girlupdate(@PathVariable("id") Integer id,   //用户输入id后获取该id值
                           @RequestParam("cupSize") String cupSize,
                           @RequestParam("age") Integer age){
        Girl girl=new Girl();
        //更改女生信息，如果不存在就新增一个女生信息
        girl.setAge(age);
        girl.setCupSize(cupSize);
        girl.setId(id);

        return girlRep.save(girl);

    }

    /**
     * delete girl by id
     * @param id
     */

    @GetMapping(value = "/deletegirls/{id}")
    public String deletegirls(@PathVariable("id") Integer id){
//        girlRep.delete(id);
        girlRep.deleteById(id);
        return "删除成功";
    }

    /**
     * get one girl by cupsize
     * 在GirlRep方法中定制查询方法
     * @param cupSize
     */

    @GetMapping(value = "/girlfindbycupSize/{cupSize}")
    public List<Girl> girlfindbycupSize(@PathVariable("cupSize") String cupSize){
        return girlRep.findBycupSize(cupSize);
    }
    //也可以用findByAge方法根据age获取女生信息


    /**
     * 验证@Transactional
     *
     */

    //注入GirlService类
    @Autowired
    public GirlService girlService;

    //定义get方法，验证transactional注解
    @GetMapping(value = "/transactional")
    public void testtranactional(){
        //如果把girlone.setAge(34);中参数值写成girlone.setAge(3);
        // 因为age值不能小于18，这样就会报错，这时transactional注解就会起作用，这样第一条数据也不会插入数据库中
        girlService.interTwo();
    }

    /**
     * get one girl by cupsize，
     * 课程中讲的用girlRep.findOne(id)此方法，因为SpringBoot版本过高的问题，此方法报错，用findById方法就可以
     * @param id
     */

    @GetMapping(value = "/girlfindbyid/{id}")
    public Girl girlfindbyid(@PathVariable("id") Integer id){
        return girlRep.findById(id).get();
    }

    /**
     * 获取女生的年龄并判断，小于10，返回正在上小学；大于10且小于16，返回正在上初中
     * 调girlService中的getage方法并把id传过去，判断都放在service层
     *
     * @param id
     */

    @GetMapping(value = "/jugegirlsage/{id}")
    public Result<Girl> jugegirlsage(@PathVariable("id") Integer id){
        return ResultUtil.error(1,girlService.getage(id));
    }

    /**
     * 获取女生的年龄并判断，小于10，返回正在上小学；大于10且小于16，返回正在上初中
     * 调girlService中的getageuseException方法并把id传过去，判断都放在service层
     * getageuseException方法中通过抛出异常的方式返回错误信息
     * jugegirlsageuesException方法也接收异常
     *
     * @param id
     */

    @GetMapping(value = "/jugegirlsageuesException/{id}")
    public void jugegirlsageuesException(@PathVariable("id") Integer id) throws Exception{
        //直接调getageuseException方法就行，自动会通过抛异常的方法把信息传递出来
        //handle包中的ExceptionHandle类就是为了捕获所有的异常类返回的信息，然后通过Result类统一日志处理方法打印出来，要不然就是按异常的格式打印出来结果。
        girlService.getageuseException(id);
    }

    /*
    自定义异常类
     */

    @GetMapping(value = "/jugegirlsageuesCustomException/{id}")
    public void jugegirlsageuesCustomException(@PathVariable("id") Integer id) throws Exception{
        //直接调getageuseException方法就行，自动会通过抛异常的方法把信息传递出来
        //handle包中的ExceptionHandle类就是为了捕获所有的异常类返回的信息，然后通过Result类统一日志处理方法打印出来，要不然就是按异常的格式打印出来结果。
        girlService.getageuseCustomException(id);
    }

        /*
    使用枚举值自定义错误编码和错误信息
     */

    @GetMapping(value = "/getageuseCustomcodemsg/{id}")
    public void getageuseCustomcodemsg(@PathVariable("id") Integer id) throws Exception{
        //直接调getageuseException方法就行，自动会通过抛异常的方法把信息传递出来
        //handle包中的ExceptionHandle类就是为了捕获所有的异常类返回的信息，然后通过Result类统一日志处理方法打印出来，要不然就是按异常的格式打印出来结果。
        girlService.getageuseCustomcodemsg(id);
    }



}
