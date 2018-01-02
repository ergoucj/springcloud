package com.springboot.study.common.beans;

/**
 * Created by kobe on 2017/3/6.
 */
public class UserSessionConstant {

    //pc
    public static final String USER_SESSION_PC = "user:session:pc";
    public static final String PC_SHIRO_REDIS_SESSION = "pc_shiro_redis_session:";

    //wx
    public static final String USER_SESSION_WX = "user:session:wx";
    public static final String WX_SHIRO_REDIS_SESSION = "wx_shiro_redis_session:";

    //mobile
    public static final String USER_SESSION_MOBILE = "user:session:mobile";
    public static final String USER_MD5_MOBILE = "user:md5:mobile";

    //    // session 在redis过期时间是30分钟30*60 单位秒
    public static final long expireTime = 18000;
}
