package com.springboot.study.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.MessageFormat;
import java.util.Map;

/**
 * LogInterceptor拦截器 http请求响应过程负责记录日志。 <br>
 * @author Administratior
 */
public class LogInterceptor extends HandlerInterceptorAdapter {

    private static Logger logger = LoggerFactory.getLogger(LogInterceptor.class);

    /**
     * 执行顺序: 1、preHandle
     * 在业务处理器处理请求之前被调用
     * 
     * 如果返回false
     * 从当前的拦截器往回执行所有拦截器的afterCompletion(),再退出拦截器链
     * 
     * 如果返回true
     * 执行下一个拦截器,直到所有的拦截器都执行完毕
     * 再执行被拦截的Controller
     * 然后进入拦截器链,
     * 从最后一个拦截器往回执行所有的postHandle()
     * 接着再从最后一个拦截器往回执行所有的afterCompletion()
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object target) throws Exception {

//        // 跨域设置
//        response.setHeader("Access-Control-Allow-Origin", "*");
//        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
//        response.setHeader("Access-Control-Max-Age", "3600");
//        response.setHeader("Access-Control-Allow-Headers", "x-requested-with");

//        logger.info(MessageFormat.format("Http Request:[ip - {0}] [url - {1}] {2}.", request.getRemoteAddr(),
//                request.getRequestURL()));
        return true;
    }

    // 执行顺序: 2、postHandle
    /**
     * 在业务处理器处理请求执行完成后,生成视图之前执行的动作
     * 可在modelAndView中加入数据，比如当前时间
     */
    // @Override
    // public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
    // ModelAndView modelAndView) throws Exception {
    //
    // }

    // 执行顺序: 3、afterCompletion
    /**
     * 在DispatcherServlet完全处理完请求后被调用,可用于清理资源等
     * 
     * 当有拦截器抛出异常时,会从当前拦截器往回执行所有的拦截器的afterCompletion()
     */
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
//            throws Exception {
//
//        if ((ex != null) || (response.getStatus() == 500)) {
//            logger.info(MessageFormat.format("Http Response:[ip - {0}] [url - {1}] Exception.", request.getRemoteAddr(),
//                    request.getRequestURL()));
//
//        } else {
//            logger.info(MessageFormat.format("Http Response:[ip - {0}] [url - {1}] Success.", request.getRemoteAddr(),
//                    request.getRequestURL()));
//        }
//    }

}