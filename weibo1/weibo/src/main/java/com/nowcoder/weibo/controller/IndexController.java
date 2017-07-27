package com.nowcoder.weibo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by soyo pu on 2017/7/25.
 */
@Controller
public class IndexController {
    @RequestMapping(path={"/","/index"})
    @ResponseBody
    public String index(){
        return "hello kitty";
    }
}
