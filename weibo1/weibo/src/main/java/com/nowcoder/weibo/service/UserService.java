package com.nowcoder.weibo.service;


import com.nowcoder.weibo.dao.LoginTicketDAO;
import com.nowcoder.weibo.dao.UserDAO;
import com.nowcoder.weibo.model.LoginTicket;
import com.nowcoder.weibo.model.User;
import com.nowcoder.weibo.util.MD5;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by soyo pu on 2017/7/25.
 * 注册就是把用户名和密码插入到数据库就完了
 */
@Service
public class UserService {
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private LoginTicketDAO loginTicketDAO;
/*如果用户名和密码都有问题会返回给前台，Controller在调用service时，把用户名和密码的合法性都返回，用
    Map<String,Object>来返回信息*/
   public Map<String, Object> register(String name, String password) {
       //如果用户的名字是空的，从用户体验上，给他说下，这个用户名是有问题的
       Map<String, Object> map = new HashMap<String, Object>();
       if (StringUtils.isBlank(name)) {
           //返回给前台信息，
           map.put("namemsg", "用户名不能为空");
           //如果用户名为空，直接返回map，就不再执行后面的验证
           return map;
       }
       if (StringUtils.isBlank(password)) {
           map.put("passwordmsg", "密码不能为空");
           return map;
       }
       //还有一点，用户要注册，需要看下用户在不在数据表里面，如何在，说明用户已经存在了
       //判断用户是否已经注册了
       User user = userDAO.selectByName(name);
       if (user != null) {
           map.put("namemsg", "用户已经注册了");

           return map;
       }

       //整个逻辑写完，就把用户插入进数据表,怎么插入呢，把数据更新下
       user = new User();
       //这个name就是刚才用户注册传进来的name
       user.setName(name);
       user.setPassword(password);//直接这样保存，是明文保存，不安全，需要通过盐的方式加强下。
       //所以为了更好的安全性，首先设置盐
       //会生成一个唯一的UUID，这里取他的前5位
       user.setSalt(UUID.randomUUID().toString().substring(0, 5));
       //随机生成一个默认用户头像
       user.setHeadUrl(String.format("http://images.nowcoder.com/head/%dt.png", new Random().nextInt(1000)));
       //加密：原有的password+生成的salt
       user.setPassword(MD5.getMd5(password + user.getSalt()));

       //完了之后，通过userDAO来调用用户，调用成功，就把返回map
       userDAO.addUser(user);
//       return map;

       //注册完成之后，下面就是登陆
//注册完成，就给他下发一个ticket
       String ticket = addLoginTicket(user.getId());
       map.put("ticket", ticket);
       return map;
   }
       public Map<String, Object> login(String name, String password){
           //如果用户的名字是空的，从用户体验上，给他说下，这个用户名是有问题的
           Map map = new HashMap<String, Object>();
           if (StringUtils.isBlank(name)) {
               //返回给前台信息，
               map.put("namemsg", "用户名不能为空");
               //如果用户名为空，直接返回map，就不再执行后面的验证
               return map;
           }
           if (StringUtils.isBlank(password)) {
               map.put("passwordmsg", "密码不能为空");
               return map;
           }
           //这里和注册不同，如果提交的用户名不存在，就说明不存在
           User user = userDAO.selectByName(name);
           if (user == null) {
               map.put("namemsg", "用户名不存在");

               return map;
           }

           //如果用户存在，就是验证密码
           if (!MD5.getMd5(password + user.getSalt()).equals(user.getPassword())) {
               map.put("msg", "密码错误");
               return map;
           }
               map.put("user_id",user.getId());
               String ticket=addLoginTicket(user.getId());
               map.put("ticket",ticket);

               return map;
           }
           //如果用户名密码都存在，则登录成功，给用户下发一个ticket
           /*有了注册，登录就好些了，
           都是用户名密码，不为空，
           如果用户不存在，返回不存在

           如果存在，验证密码
                   如果不等于服务器提交上来的密码说明密码有问题
           如果用户登录成功了，给用户下发一个ticket
           ticket有哪些属性id,和用户关联的userid，过期时间，状态（比如用0.1表示有效无效），字符串ticket

                   有了ticket很显然还要写一个他的dao
           ticket功能
                   用户去登录需要插入接口
           用户浏览需要查接口
           用户登出了。需要把状态改下*/
       private String addLoginTicket(int user_id){
           LoginTicket ticket=new LoginTicket();
           ticket.setUser_id(user_id);
           Date date=new Date();
           date.setTime(date.getTime()+1000*3600*24);
           ticket.setExpired(date);
           ticket.setStatus(0);
           ticket.setTicket(UUID.randomUUID().toString().replaceAll("-","."));
           loginTicketDAO.addTicket(ticket);
           return ticket.getTicket();

    }
    //登出
    //0表示正常，1表示过期
    public void logout(String ticket){
        loginTicketDAO.UpdateStatus(ticket,1);
    }

}





