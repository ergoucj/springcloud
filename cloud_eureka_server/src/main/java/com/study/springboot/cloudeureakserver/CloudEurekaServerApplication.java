package com.study.springboot.cloudeureakserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 *  启动一个服务注册中心，只需要一个注解@EnableEurekaServer
 *
 *  所有的服务端及访问服务的客户端都需要连接到注册管理器（eureka服务器）。
 *  服务在启动时会自动注册自己到eureka服务器，每一个服务都有一个名字，这个名字会被注册到eureka服务器。
 *  使用服务的一方只需要使用该名字加上方法名就可以调用到服务
 */

@EnableEurekaServer
@SpringBootApplication
public class CloudEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudEurekaServerApplication.class, args);
	}
}
