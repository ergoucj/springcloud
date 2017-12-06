package com.study.springcloud.configclient.servicefeign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Administratior on 2017/11/2.
 * 定义一个feign接口
 * 通过@ FeignClient（“服务名”），来指定调用哪个服务
 *
 * fallback:指定熔断方法
 *
 */
@Component
@FeignClient(value = "service-lucy",fallback = SchedualServiceLucyHystric.class)
public interface SchedualServiceLucy {

    /**
     * 在代码中调用了service-lucy服务的“/hi”接口
     * @param name
     * @return
     */
    @RequestMapping(value = "/hi",method = RequestMethod.GET)
    String sayLucyFromClientLucy(@RequestParam(value = "name") String name);

    @RequestMapping(value = "/info",method = RequestMethod.GET)
    String sayLucyFromClientLucyInfo(@RequestParam(value = "name") String name);

}
