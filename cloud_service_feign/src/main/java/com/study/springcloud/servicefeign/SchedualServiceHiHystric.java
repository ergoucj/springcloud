package com.study.springcloud.servicefeign;

import org.springframework.stereotype.Component;

/**
 * Created by Administratior on 2017/11/2.
 */
@Component
public class SchedualServiceHiHystric implements SchedualServiceHi {

    @Override
    public String sayHiFromClientOne(String name) {
        return "sorry "+name;
    }
}
