package com.springboot.study.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.springboot.study.domain.AccessToken;
import com.springboot.study.service.impl.CityServiceImpl;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

/**
 * @author Administratior
 * 申明为spring组件
 */
@Component
public class WxJsUtils {

    public static String APPID="wx618cb6f3645bb512";
    public static String secret="f6009214eb130453fb0da8ec766f2f32";

    public static String getAPPID() {
        return APPID;
    }

    public static String getSecret() {
        return secret;
    }

    @Autowired
    private RedisTemplate redisTemplate;

    private static final Logger log = LoggerFactory.getLogger(CityServiceImpl.class);

    protected final static NetWorkHelper NET_WORK_HELPER = new NetWorkHelper();

    /**
     *关键点1   静态初使化 一个工具类  这样是为了在spring初使化之前
     */
    private static WxJsUtils wxJsUtils;

    //关键二   通过@PostConstruct 和 @PreDestroy 方法 实现初始化和销毁bean之前进行的操作
    @PostConstruct
    public void init() {
        wxJsUtils = this;
        // 初使化时将已静态化的testService实例化
        wxJsUtils.redisTemplate = this.redisTemplate;
    }



    /**
     * 获取access_token
     *
     * @return AccessToken
     */
    public static AccessToken getAccessToken() {
        /**
         * 接口地址为https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET，其中grant_type固定写为client_credential即可。
         */
        String url = String.format("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s", APPID,secret );
        //此请求为https的get请求，返回的数据格式为{"access_token":"ACCESS_TOKEN","expires_in":7200}
        String result = NET_WORK_HELPER.getHttpsResponse(url, "");
        System.out.println("获取到的access_token=" + result);
        //使用FastJson将Json字符串解析成Json对象
        JSONObject json = JSON.parseObject(result);
        AccessToken token = new AccessToken();
        token.setAccessToken(json.getString("access_token"));
        token.setExpiresin(json.getInteger("expires_in"));

        ValueOperations operations = wxJsUtils.redisTemplate.opsForValue();
        // 插入缓存
        operations.set("access_token", token.getAccessToken(), token.getExpiresin(), TimeUnit.SECONDS);
        return token;
    }

    /**
     * 生成签名之前必须先了解一下jsapi_ticket，jsapi_ticket是公众号用于调用微信JS接口的临时票据。
     * 由于获取jsapi_ticket的api调用次数非常有限，频繁刷新jsapi_ticket会导致api调用受限，影响自身业务。
     * jsapi_ticket的有效期为7200秒，通过access_token来获取。
     * 开发者必须在自己的服务全局缓存jsapi_ticket
     */
    public static String getTickect() {
        String ticket = "";
        //判断ticket是否有效
        if (checkTicketExpiresin()) {
            //从缓存中获取ticket
            ticket = cacheTicket();
        } else {
            // 拼接请求地址
            String requestUrl = String.format("https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=%s&type=jsapi", cacheAccessToken());
            // 获取网页授权凭证
            String httpsResponse = NET_WORK_HELPER.getHttpsResponse(requestUrl, "");
            JSONObject json = JSON.parseObject(httpsResponse);
            if (null != json) {
                try {
                    ticket = json.getString("ticket");
                    Integer expiresIn = json.getInteger("expires_in");
                    ValueOperations operations = wxJsUtils.redisTemplate.opsForValue();
                    // 插入缓存
                    operations.set("ticket", ticket, expiresIn, TimeUnit.SECONDS);
                } catch (Exception e) {
                    Integer errorCode = json.getInteger("errcode");
                    String errorMsg = json.getString("errmsg");
                    log.error("获取jsapi_ticket失败 errcode:{} errmsg:{}", errorCode, errorMsg);
                }
            }
        }
        return ticket;
    }

    /**
     * 签名
     *
     * @param ticket
     * @param timestamp
     * @param nonceStr
     * @param url
     * @return
     */
    public static String signature(String ticket, long timestamp, String nonceStr, String url) {
        String str = "jsapi_ticket=" + ticket + "&noncestr=" + nonceStr + "&timestamp=" + timestamp + "&url=" + url;
        return DigestUtils.sha1Hex(str);
    }

    /**
     * 判断Ticket是否过期
     *
     * @return
     */
    public static boolean checkTicketExpiresin() {
        // 缓存存在
        boolean hasKey = wxJsUtils.redisTemplate.hasKey("ticket");
        return hasKey;
    }

    /**
     * 从缓存中获取 access_token
     *
     * @return
     */
    public static String cacheAccessToken() {
        ValueOperations operations = wxJsUtils.redisTemplate.opsForValue();
        String accessToken = (String) operations.get("access_token");
        return accessToken;
    }

    /**
     * 从缓存中获取tickext
     *
     * @return
     */
    public static String cacheTicket() {
        ValueOperations operations = wxJsUtils.redisTemplate.opsForValue();
        String ticket = (String) operations.get("ticket");
        return ticket;
    }

}
