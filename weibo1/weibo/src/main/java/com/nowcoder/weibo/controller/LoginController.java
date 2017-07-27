package com.nowcoder.weibo.controller;

import com.nowcoder.weibo.service.UserService;
import com.nowcoder.weibo.util.WeiboUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;
import java.util.Map;

/**
 * Created by soyo pu on 2017/7/26.
 */
@Controller
public class LoginController {

    private static Logger logger = LoggerFactory.getLogger(RegisterController.class);
    @Autowired
    UserService userService;

    @RequestMapping(path = {"/login/"}, method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    //用户注册，首先提交他的用户名密码(主要是用户判断的注册,在UserService判断)
    public String login(Model model, @RequestParam("name") String name,
                           @RequestParam("password") String password,
                           @RequestParam(value = "remeber",defaultValue = "0")int rememberme,
                        //@2  注册成功，需要用response写到cookie里面去
                        HttpServletResponse response
    ){
        //从Controller层，调用service
        try{
            //注册用户名密码，，我希望通过一个json{}串的方式来返回注册信息，写到util里面
            Map<String,Object> map = userService.register(name,password);


//
//注册好了，把信息返回给前端一个json串
//        return WeiboUtil.getJSONString(0,map);

            //如果map是空的，说明没注册，可以注册,如果注册成功，是包含ticket
//            if (map.isEmpty()){
////            return WeiboUtil.getJSONString(0,map);
//                return WeiboUtil.getJSONString(0,"注册成功");
//            }else {
//                return WeiboUtil.getJSONString(1,map);
//            }
//        }catch (Exception e){
//            //如果出现异常，记录下来
//            logger.error("注册异常" + e.getMessage());
//            return WeiboUtil.getJSONString(1,"注册异常");//正常情况返回0
//        }

            //@1  如果注册成功，是包含ticket,然后需要把数据写到cookie里面去，用response
            if (map.containsKey("ticket")){
//            return WeiboUtil.getJSONString(0,map);
                //@3 把ticket写到cookie里面,保存下来，同时response里面加上，这样后端就可以收到信息
                Cookie cookie = new Cookie("ticket",map.get("ticket").toString());//string类型
                cookie.setPath("/");//设置为全栈有效
                //如果rememberme,需要设置时间长些
                if (rememberme>0){
                    cookie.setMaxAge(3600*24*5);//5天有效期
                }
                response.addCookie(cookie);

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
    @RequestMapping(path={"/logout"},method={RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public String logout(@CookieValue("ticket") String ticket){
        userService.logout(ticket);
        //登出成功，跳转到首页
        return "redirect:/";

//        return "登出成功";
    }

}
