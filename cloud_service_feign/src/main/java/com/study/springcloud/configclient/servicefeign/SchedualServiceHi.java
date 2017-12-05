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
@FeignClient(value = "service-hi",fallback = SchedualServiceHiHystric.class)
public interface SchedualServiceHi {

    /**
     * 在代码中调用了service-hi服务的“/hi”接口
     * @param name
     * @return
     */
    @RequestMapping(value = "/hi",method = RequestMethod.GET)
    String sayHiFromClientOne(@RequestParam(value = "name") String name);

}
