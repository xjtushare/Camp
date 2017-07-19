package com.nowcoder.weibo.service;

import org.springframework.stereotype.Service;

/**
 * Created by soyo pu on 2017/7/18.
 */
//Ioc控制反转，依赖注入  这个类包含很多功能，（写一个类，定义@Service）
    //@Service 一旦定义Service，每次系统自动创建一个ToutiaoService 对象

    /*控制反转思想：写代码时候，能够将
    * 具体的实现写在一个单独的地方，用到的时候，通过关键词* @Autowired注入进来
    *
    * 实现给其他人写，需要的时候注入进来就行。
    *
    *
    * spring通过注解的方式将对象关联起来，也可以通过配置文件xml，
    *
    * spring通过控制反转，依赖注入的方式，能够把所有的变量初始化。
    * 根据这个类来创建一个对象，这个对象里面的引用ref来自另一个对象，
    *
    * 通过一个服务的方式，把定义好的这个实现的一些业务，能够通过一些注解的方式，也可以通过配置文件的方式直接注入进来，然后就
    * 可以直接用这个对象里面的一些方法。
    * */
    @Service
public class ToutiaoService {
    public String say(){
        return "This is from ToutiaoService";
    }
}
