package site.keyu.askme.utils;

import com.alibaba.fastjson.JSONObject;

/**
 * @Author:Keyu
 */
public class JsonUtil {

    public static String getJsonString(int code,String msg){
        JSONObject json = new JSONObject();
        json.put("code",code);
        json.put("msg",msg);
        return json.toJSONString();
    }
}
