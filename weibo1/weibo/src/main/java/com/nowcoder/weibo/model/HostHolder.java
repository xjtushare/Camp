package com.nowcoder.weibo.model;

import org.springframework.stereotype.Component;

/**
 * Created by soyo pu on 2017/7/27.
 */

/*
HostHolder专门用来存储，这一次访问，用户是谁，
* 现在有个对象是HostHolder,是用来存用户的，可以通过setUser(User user)的方式存下来，
* 也可以通过 getUser(){的方式把它提取出来，但是服务器不是一个人在用，比如你在浏览，w
* 我也在浏览，我这个地方只有一个@Component，我应该存在哪里呢？所以说每条线程应该都存着自己
* 的东西，ThreadLocal是线程本地变量，所以说，每一条线程set进来，你在get 的时候只能get到自己的
* 这条线程，*/
@Component
public class HostHolder {
    private static ThreadLocal<User> users = new ThreadLocal<User>();

    //定义两个函数，get,set
    public static User getUser(){
        return users.get();
    }
    public void setUser(User user){
        users.set(user);
    }
    public void clear(){
        users.remove();
    }

}
