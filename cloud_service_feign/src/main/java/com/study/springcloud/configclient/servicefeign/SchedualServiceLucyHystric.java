package com.study.springcloud.configclient.servicefeign;

import org.springframework.stereotype.Component;

/**
 * Created by Administratior on 2017/11/2.
 * 断路器
 */
@Component
public class SchedualServiceLucyHystric implements SchedualServiceLucy {


    /**
     * 熔断方法
     * @param name
     * @return
     */
    @Override
    public String sayLucyFromClientLucy(String name) {
        return "sorry "+name;
    }

    @Override
    public String sayLucyFromClientLucyInfo(String name) {
        return "sorry "+name;
    }
}
