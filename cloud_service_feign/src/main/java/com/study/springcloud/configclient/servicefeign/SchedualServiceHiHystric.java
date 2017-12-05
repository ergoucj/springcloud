package com.study.springcloud.configclient.servicefeign;

import org.springframework.stereotype.Component;

/**
 * Created by Administratior on 2017/11/2.
 * 断路器
 */
@Component
public class SchedualServiceHiHystric implements SchedualServiceHi {

    /**
     * 熔断方法
     * @param name
     * @return
     */
    @Override
    public String sayHiFromClientOne(String name) {
        return "sorry "+name;
    }
}
