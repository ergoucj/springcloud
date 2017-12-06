package com.study.springcloud.service.turbine.client;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

/***
 * Created by IntelliJ IDEA.
 *@author :Administratior
 *@date:2017/12/6
 *To change this template use File|Settings|File Templates.
 */
@SpringBootApplication
@EnableTurbine
public class ServiceTurbineApplication {

    public static void main(String[] args) {

        new SpringApplicationBuilder(ServiceTurbineApplication.class).web(true).run(args);
    }
}