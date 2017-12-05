package com.springboot.study.controller;

import com.springboot.study.common.beans.ResultBean;
import com.springboot.study.util.WxJsUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * *@author Administratior
 */
@RestController
public class WechatController {

    @RequestMapping("/getWxConfig")
    public ResultBean getWxConfig(ServletRequest request) {
        String jsapiTicket;
        //获取Unix时间戳
        long timestamp = System.currentTimeMillis() / 1000L;
        //获取appId
        //获取页面路径(前端获取时采用location.href.split('#')[0]获取url)
        String url = request.getParameter("url");

        //获取ticket
        jsapiTicket = WxJsUtils.getTickect();


        String noncestr = UUID.randomUUID().toString();
        //获取签名
        String signature = WxJsUtils.signature(jsapiTicket, timestamp, noncestr, url);

        //创建Map用于创建签名串
        Map<String, Object> params = new HashMap<>(16);
        params.put("jsapi_ticket", jsapiTicket);
        params.put("timestamp", timestamp);
        params.put("noncestr", noncestr);
        params.put("url", url);

        //得到签名再组装到Map里
        params.put("signature", signature);
        //传入对应的appId
        params.put("appId", WxJsUtils.getAPPID());
        return new ResultBean(params);
    }
}
