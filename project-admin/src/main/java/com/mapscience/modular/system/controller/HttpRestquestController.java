package com.mapscience.modular.system.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.mapscience.core.common.ResponseVal;
import com.mapscience.core.util.AESUtil;
import com.mapscience.core.util.HttpUtil;
import com.mapscience.core.util.common.Base64Util;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/HttpRestquest")
public class HttpRestquestController {

    @Value("${get-url}")
    private String url;

    @RequestMapping("/remethod")
    @ResponseBody
    public ResponseVal HttpDemo(){
        //String url ="http://192.168.1.205:8081/gateway/gateway.ht";

        Map<String , String> par =Maps.newHashMap();
        par.put("method", "custom.index"); //方法名
        par.put("version", "1.0.2");    //版本号
        par.put("merchantId", "11");    //子系统标记
        String encrypt = Base64Util.encrypt(JSON.toJSONString(par));   //BASE64加密

        Map<String, String> mapBody = new HashMap<String, String>();
        mapBody.put("merchantId", "11");
        mapBody.put("backurl", "http://192.168.1.225:8081/menu/menuTree");  //后台回调地址
        mapBody.put("url", "123456");
        String Body = AESUtil.encrypt(JSON.toJSONString(mapBody),"121212"); //aesu加密

        //Map<String, Object> params = new HashMap<String, Object>();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("head", encrypt);
        jsonObject.put("body", Body);
        String s = HttpUtil.sendJson(url, jsonObject.toString());
        //解密
        //字符串转json
        JSONObject jSONObject = JSONObject.parseObject(s);
        String data = jSONObject.getString("data");
        String decrypt = AESUtil.decrypt(data, "121212");
        return new ResponseVal(200,"成功",decrypt);
    }
}
