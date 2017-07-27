package com.nowcoder.weibo.interceptor;

import com.nowcoder.weibo.dao.LoginTicketDAO;
import com.nowcoder.weibo.dao.UserDAO;
import com.nowcoder.weibo.model.HostHolder;
import com.nowcoder.weibo.model.LoginTicket;
import com.nowcoder.weibo.model.User;
import org.apache.ibatis.plugin.Intercepts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import sun.security.krb5.internal.Ticket;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * Created by soyo pu on 2017/7/26.
 *
 * //也可以做一些登录注册时候敏感词过滤，密码强度是否够大等等工作
 *这里面的ticket是从cookie中来的
 */
/*而拦截器是每次访问之前，先插进来，这是最早的入口，第三就是做些判断，把数据展现出来
* 拦截器写好之后需要注册到MVC里面去，可以通过配置的方式，加了一个configuration包，把用户
* 注册进来*/
//认证用户是否已经登录
//    1.从ticket里面查用户  读取cookie，里面request里面有没有这个字段即可
public class PassportInterceptor implements HandlerInterceptor {
    @Autowired
    private UserDAO userDAO;
    @Autowired
    LoginTicketDAO loginTicketDAO;
    //通过依赖注入的方式传进来
    @Autowired
    HostHolder hostHolder;
    @Autowired
    User user;
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        //默认是null
        //第一步走这里，第二步走首先HomeController
        String ticket = null;
//        1.从ticket里面查用户  读取cookie，里面request里面有没有这个字段即可
        if (httpServletRequest.getCookies()!=null){
            for (Cookie cookie:httpServletRequest.getCookies()){
                if (cookie.getName().equals("ticket")){
                    ticket = cookie.getValue();
                    break;
                }
            }
        }
        //如果ticket！=null说明用户已经登陆过，但是还是需要查看下是否这个用户还是有效期的
        if (ticket!=null){
            LoginTicket loginTicket = loginTicketDAO.selectByTicket(ticket);
            //用户没登录/或者在今天之前，说明已经过期，
            if (loginTicket==null||loginTicket.getExpired().before(new Date())){
                return true;
            }
            //现在知道用户是谁，如果把用户保存起来，别人也可以知道,由于我需要随时知道，当前调用这个
            //线程的人是谁，所以，不能保存在request里，而是单独建立一个类，HostHolder
            //先判断用户是否有效，有效之后，这里在再数据库中去查这个用户，
            User user = userDAO.selectById(loginTicket.getUser_id());
            //保存
//            httpServletRequest.setAttribute();

            //保存用户(通过前面我去查这个用户是否有效，到这个用户是谁，现在保存起来)，保存到当前线程里
            hostHolder.setUser(user);
        }
        return true;
    }
    //处理结束了之后的工作
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
    //如果这个用户是登录用户，就需要把这个用户加进来
        if (modelAndView!=null&&HostHolder.getUser()!=null){
            modelAndView.addObject("user",HostHolder.getUser());

        }
    }

    //afterCompletion是用来做收尾工作的
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        //把这个用户清掉，不然每次登录都有用户放入。
        hostHolder.clear();
    }
}
