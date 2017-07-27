package com.nowcoder.weibo.util;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Created by soyo pu on 2017/7/26.
 */
//工具类，和业务无关，把数据转给前端看
public class WeiboUtil {
    private static final Logger logger= LoggerFactory.getLogger(WeiboUtil.class);




    //code为0时正确，为1时错误
    public  static String getJSONString(int code){
        //生成json串的过程
        JSONObject json=new JSONObject();
        json.put("code",code);
        return json.toJSONString();
    }
    //返回code,以及用来干什么msg
    public  static String getJSONString(int code, String msg){
        JSONObject json=new JSONObject();
        json.put("code",code);
        json.put("msg",msg);
        return json.toJSONString();
    }

    public  static String getJSONString(int code, Map<String,Object> map){
        JSONObject json=new JSONObject();
        json.put("code",code);
        //遍历Map
        for(Map.Entry<String,Object> entry:map.entrySet()){
            //json保存key,value
            json.put(entry.getKey(),entry.getValue());
        }
        return json.toJSONString();
    }
}
