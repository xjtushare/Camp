package com.nowcoder.weibo.interceptor;

import com.nowcoder.weibo.model.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by now on 2017/7/23.
 */
@Component
public class LoginRequiredInterceptor implements HandlerInterceptor{
    @Autowired
    HostHolder hostHolder;
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        //判断当前用户是否存在
        if (hostHolder.getUser()==null){
            httpServletResponse.sendRedirect("/?callback="+httpServletRequest.getRequestURI());
        }

        //拦截器可以做一个判断用户登录判断，权限认证，
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
