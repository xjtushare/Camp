package com.nowcoder.weibo.controller;

import com.nowcoder.weibo.service.ToutiaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;

/**
 * Created by soyo pu on 2017/7/18.
 */
//入口，指定这是一个Controller
@Controller
public class IndexController {
    //    @Autowired:将创建好的ToutiaoService复制到toutiaoService成员变量上。

    @Autowired
    private ToutiaoService toutiaoService;

    /*java中一般这么写
    public IndexController(){
        toutiaoService = new ToutiaoService();
    }
    */
    //spring 中用控制反转






    //RequestMapping是一个用来处理请求地址映射的注解，可用于类或方法上。
    /*
    * 在使用@RequestMapping后，返回值通常解析为跳转路径，
加上@Responsebody后返回结果不会被解析为跳转路径，而是直接写入HTTP response body中。
比如异步获取json数据，加上@Responsebody后，会直接返回json数据*/
    //指定url,访问url地址
//    @RequestMapping("/")
    //指定path多样性
    @RequestMapping(path = {"/", "/index"})
    @ResponseBody
    public String index(HttpSession session) {

        //打印session值

//        return "Hello NowCoder";
//        return "Hello NowCoder"+session.getAttribute("msg");
        return "Hello NowCoder"+session.getAttribute("msg")+"<br> Say:"+toutiaoService.say();
    //再去访问http://localhost:8080/
        // 可以看到直接调用say()方法Hello NowCodernull Say:This is from ToutiaoService

    }

    @RequestMapping(value = "/profile/{groupId}/{userId}")
    @ResponseBody
    //用户信息
    /*
    * value = "/profile/{groupId}/{userId}"可以看作是path,
    * @PathVariable是path里面的一个变量*/
    public String profile(@PathVariable("groupId") String groupId,
                          @PathVariable("userId") int userId,
                          //Request参数
                          @RequestParam(value = "type", defaultValue = "1") int type,
                          @RequestParam(value = "key", defaultValue = "nowcoder") String key) {
        //显示到浏览器上http://127.0.0.1:8080/profile/11/22?type=99&key=xx
        //GroupId{11},userId{22},type{99},key{xx}
        return String.format("GroupId{%s},userId{%d},type{%d},key{%s}", groupId, userId, type, key);

    }

    @RequestMapping(value = {"/request"})
    @ResponseBody
    //将http请求中request,header等等包装在ServletRequest
    public String request(HttpServletRequest request,
                          HttpServletResponse response,
                          HttpSession session) {
        StringBuilder sb = new StringBuilder();
        //取得全部头信息127.0.0.1:8080/request
        Enumeration<String> headerNames = request.getHeaderNames();
        //迭代
        while (headerNames.hasMoreElements()) {
            String name = headerNames.nextElement();
            sb.append(name + ":" + request.getHeader(name) + "<br>");
        }

//host:127.0.0.1:8080
//connection:keep-alive
//cache-control:max-age=0
//upgrade-insecure-requests:1
//user-agent:Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36
//accept:text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8
//accept-encoding:gzip, deflate, sdch, br
//accept-language:zh-CN,zh;q=0.8
//cookie:JSESSIONID=83075C61EB86006BE225C12DCC634FD6
//        浏览器第一次不显示cookie，刷新显示

//        session:我第一次打开网站，在我浏览器关闭之前，浏览器和服务器都会有一个唯一的sessionID,可以通过sessionID知道两次请求的是不是一个人
//                sessionID是放在cookie里面的，
        //用cookie类来获取cookie
        for (Cookie cookie : request.getCookies()) {
            sb.append("Cookie:");
            sb.append(cookie.getName());
            sb.append(":");
            sb.append(cookie.getValue());
            sb.append("<br>");

//    效果
//            host:127.0.0.1:8080
//            connection:keep-alive
//            cache-control:max-age=0
//            upgrade-insecure-requests:1
//            user-agent:Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36
//            accept:text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8
//accept-encoding:gzip, deflate, sdch, br
//accept-language:zh-CN,zh;q=0.8
//cookie:JSESSIONID=83075C61EB86006BE225C12DCC634FD6
//Cookie:JSESSIONID:83075C61EB86006BE225C12DCC634FD6

            sb.append("getMethod:" + request.getMethod() + "<br>");
            //url
            sb.append("getPathInfo:" + request.getPathInfo() + "<br>");
            //参数
            sb.append("getQueryString:" + request.getQueryString() + "<br>");
            sb.append("getRequestURI:" + request.getRequestURL() + "<br>");
//          显示
//            getMethod:GET
//            getPathInfo:null
//            getQueryString:null
//            getRequestURI:http://127.0.0.1:8080/request
        }
        return sb.toString();
    }

    //Response
    @RequestMapping(value = {"/response"})
    @ResponseBody
    //  可以加一个cookie参数，可以读出COOkie的值。
    public String response(
            //请求  NowcoderId From Cookie:a
            @CookieValue(value = "nowcoderid", defaultValue = "a") String nowcoderid,
            //key,value
            @RequestParam(value = "key", defaultValue = "key") String key,
            @RequestParam(value = "value", defaultValue = "value") String value,
            HttpServletResponse response) {
        //将key,value写入到cookie
        response.addCookie(new Cookie(key, value));
        response.addHeader(key, value);
        return "NowcoderId From Cookie:" + nowcoderid;
        /*
* 在一个网页的请求过程中，通过request能够获取到所有的请求的数据，
* 也可以通过response的 HttpServletResponse response){
        //将key,value写入到cookie
        response.addCookie(new Cookie(key,value));
        response.addHeader(key,value);把数据写回去，

       一次请求过来，request把原始的数据作解析，包括参数解析，cookie读取，
       http请求，文件上传，

       response主要负责把内容下发给用户的请求，可以设置cookie,header,

        */

    }

    //重定向,从跳转页到首页，
    // 从跳转页到首页只要浏览器不关闭，其实所有的访问是同一个http的session,可以通过session传递数据
    /*
    * */
    @RequestMapping("/redirect/{code}")
    public   RedirectView redirect(@PathVariable("code") int code,HttpSession session) {
        RedirectView red = new RedirectView("/", true);
        //永久性转移，强制性跳转
        if (code == 301) {
            red.setStatusCode(HttpStatus.MOVED_PERMANENTLY);
        }

        //session表示用户一次打开浏览器访问网页，所有的回话表示一个session,
        //这里设置了值，在首先显示出来http://127.0.0.1:8080/redirect/302
        // Hello NowCoderJump from redirect.
        session.setAttribute("msg","Jump from redirect.");
        return red;
//        return "redirect:/";

    }
    //管理员页面，密码key,带一个请求required
    //http://localhost:8080/admin?key=admin显示 hello admin
    @RequestMapping("/admin")
    @ResponseBody
    public String admin(@RequestParam(value="key",required = false)String key){
    if("admin".equals(key)){
        return "hello admin";
    }
    throw new IllegalArgumentException("KEY错误");
    }
    //自己定义处理错误，自定义error
    //http://localhost:8080/admin?key=2显示error:KEY错误
    @ExceptionHandler
    @ResponseBody
    public String error(Exception e){
        return "error:"+e.getMessage();
    }
}