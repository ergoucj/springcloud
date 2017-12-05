package com.springboot.study.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.springboot.study.common.beans.EasyUIDataGrideResult;
import com.springboot.study.common.beans.PageResultBean;
import com.springboot.study.util.NetWorkHelper;
import com.springboot.study.util.WxJsUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author Administratior
 */
public class BaseController {

    private final Logger log = LoggerFactory.getLogger(BaseController.class);
    protected final static NetWorkHelper NET_WORK_HELPER = new NetWorkHelper();

    /**
     * 第一步通过当前url 获取微信openid
     *
     * @return
     */
    public String reGetOpenId(HttpServletRequest request, HttpServletResponse response) {
        //获取当前url
        String url = request.getRequestURL().toString();
        String queryString = request.getQueryString();
        if (queryString!=null){
            url+="?"+request.getQueryString();
        }
        String openid = "";

        //先要判断是否是获取code后跳转过来的
        String code = "code";
        if ("".equals(request.getParameter(code)) || request.getParameter(code) == null) {
            //Code为空时，先获取Code
            String getCodeUrls = getCodeUrl(url);
            try {
                //先跳转到微信的服务器，取得code后会跳回来的
                response.sendRedirect(getCodeUrls);
                return null;
            } catch (IOException e) {
                e.printStackTrace();
                return "";
            }
        } else {
            //Code非空，已经获取了code后跳回来啦，现在重新获取openid
            //重新取得用户的openid
            openid = getOauthAccessOpenId(request.getParameter("code").toString());
            request.getSession().setAttribute("openid",openid);

        }
        return openid;
    }

    /**
     * 获取code跳转地址
     *
     * @param url
     * @return
     */
    public String getCodeUrl(String url) {
        String codeUrl = "";
        //对回调url进行编码
        try {
            url = URLEncoder.encode(url,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
//        URLEncoder.encode("This~string~has~tildes","UTF-8")
        codeUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+ WxJsUtils.getAPPID()+"&redirect_uri=" + url + "&response_type=code&scope=snsapi_base&state=1#wechat_redirect";
        return codeUrl;

    }

    /**
     * 第二步 获取openid
     *
     * @param code
     * @return
     */
    public String getOauthAccessOpenId(String code) {
        String openid = "";
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="+WxJsUtils.getAPPID()+"&secret="+WxJsUtils.getSecret()+"&code=" + code + "&grant_type=authorization_code";
        log.info("拿到的url是：" + url);

        String getHtml = NET_WORK_HELPER.getHttpsResponse(url, "");

        log.info("获取到的结果" + getHtml);

        JSONObject jsonObject = JSON.parseObject(getHtml);
        openid = (String) jsonObject.get("openid");
        log.info("openid=" + openid);

        return openid;
    }

    /**
     * 转换为普通分页对象
     *
     * @param page 分页
     * @return PageResultBean
     * @author Administrator
     */
    public PageResultBean switchPageInfoResult(Page page) {
        PageInfo pageInfo = new PageInfo<>(page);
        return new PageResultBean(pageInfo);
    }

    /**
     * 转换为Easyui分页对象
     *
     * @param page 分页
     * @return EasyUIDataGrideResult
     * @author Administrator
     */
    public EasyUIDataGrideResult switchEasyUIResult(Page page) {
        return new EasyUIDataGrideResult(page.getTotal(),page.getResult());
    }
}
