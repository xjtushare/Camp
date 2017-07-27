//package com.nowcoder.weibo.controller;
//
//import com.nowcoder.weibo.util.WeiboUtil;
//import org.apache.ibatis.annotations.Param;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.multipart.MultipartFile;
//
///**
// * Created by soyo pu on 2017/7/27.
// */
//@Controller
//public class UploadController {
//    private static Logger logger = LoggerFactory.getLogger(RegisterController.class);
//
//    //存到二进制流中，用Post方式，上传参数就file
//    @RequestMapping(path ={"/uploadImage/"},method ={RequestMethod.POST})
//    @ResponseBody
//    //存到本地服务器，比如自己电脑上
//    public String uploadImage(@RequestParam("file")MultipartFile file){
//        try {
//            //先加上service
//            String fileUrl =
//
//        }catch(Exception e){
//            //如果异常，打印出信息
//            logger.error("上传图片失败" + e.getMessage());
//            return WeiboUtil.getJSONString(1,"上传图片失败");
//
//        }
//
//
//        }
//    }
//
//}
