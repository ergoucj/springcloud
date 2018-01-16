package com.springboot.study.common.exceptions;

import com.springboot.study.common.beans.ResultBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/***
 * Created by IntelliJ IDEA.
 *@author :Administratior
 *@date:2017/11/30
 *To change this template use File|Settings|File Templates.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    /**
     * 主动异常
     * @param req
     * @param e
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = CheckException.class)
    @ResponseBody
    public ResultBean defaultErrorHandler(HttpServletRequest req, CheckException e) throws Exception {
        ResultBean<?> result = new ResultBean();
        // 已知异常
        logger.error( " error ", e);
            result.setMsg(e.getLocalizedMessage());
            result.setCode(ResultBean.FAIL);
        return result;
    }

//    /**
//     * 非主动异常
//     * @param req
//     * @param e
//     * @return
//     * @throws Exception
//     */
//    @ExceptionHandler(value = Exception.class)
//    public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
//        ModelAndView mav = new ModelAndView();
//        mav.addObject("exception", e);
//        mav.addObject("url", req.getRequestURL());
//        mav.setViewName("error");
//        return mav;
//    }
}