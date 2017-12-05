package com.springboot.study.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Administratior
 */
@RestController
public class IndexController extends BaseController{

//    @RequestMapping(value = { "/", "index" }, method = RequestMethod.GET)
//    public ModelAndView index(HttpServletRequest request, HttpServletResponse response){
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("/index");
//        //利用openid实现自动登陆
//        String openId = reGetOpenId(request, response);
//         //根据openid去查询用户，执行登录逻辑
//
//        System.out.println(openId);
//        return modelAndView;
//    }
}
