package com.study.springcloud.configclient.servicefeign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administratior on 2017/11/2.
 * Feign客户端SchedualServiceHi 来消费服务
 */
@RestController
public class LucyController {

    @Autowired
    private SchedualServiceLucy schedualServiceLucy;


    /**
     * controller层，对外暴露一个”/lucy”的API接口，通过接口定义的Feign客户端SchedualServiceHi 来消费服务
     * @param name
     * @return
     */

    @RequestMapping(value = "/lucy",method = RequestMethod.GET)
    public String sayLucyFromClientLucy(@RequestParam String name){
        return schedualServiceLucy.sayLucyFromClientLucy(name);
    }


}
