//package com.nowcoder.weibo.service;
//
//import org.springframework.web.multipart.MultipartFile;
//
///**
// * Created by soyo pu on 2017/7/27.
// */
//public class UpdownloadService {
//    //把文件或者图片保存到服务器上，如果保存成功，就返回一个地址。
//    public String savaImage(MultipartFile file) throws Exception{
//        //判断后缀名（找到最后一个点的位置），是不是图片
//        int dosPot  = file.getOriginalFilename().lastIndexOf(".");
//        if (dosPot<0){
//            return  null;
//        }
//        String fileExt = file.getOriginalFilename().substring(dosPot+1);//不要点
//
//    }
//}
