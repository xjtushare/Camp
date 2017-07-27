package com.nowcoder.weibo.controller;


import com.nowcoder.weibo.service.UserService;
import com.nowcoder.weibo.util.WeiboUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.remoting.rmi.CodebaseAwareObjectInputStream;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by soyo pu on 2017/7/25.
 */
@Controller
public class RegisterController {
    private static Logger logger = LoggerFactory.getLogger(RegisterController.class);
    @Autowired
    UserService userService;

    @RequestMapping(path = {"/register/"}, method = {RequestMethod.GET})
    @ResponseBody
    //用户注册，首先提交他的用户名密码(主要是用户判断的注册,在UserService判断)
    public String register(Model model, @RequestParam("name") String name,
                           @RequestParam("password") String password,
                          @RequestParam(value = "remeber",defaultValue = "0")int remember
                        ){
        //从Controller层，调用service
    try{
        //注册用户名密码，，我希望通过一个json{}串的方式来返回注册信息，写到util里面
        Map<String,Object>map = userService.register(name,password);


//
//注册好了，把信息返回给前端一个json串
//        return WeiboUtil.getJSONString(0,map);

        //如果map是空的，说明没注册，可以注册
        if (map.isEmpty()){
//            return WeiboUtil.getJSONString(0,map);
            return WeiboUtil.getJSONString(0,"注册成功");
        }else {
            return WeiboUtil.getJSONString(1,map);
        }
    }catch (Exception e){
        //如果出现异常，记录下来
        logger.error("注册异常" + e.getMessage());
        return WeiboUtil.getJSONString(1,"注册异常");//正常情况返回0
    }
    }

}
