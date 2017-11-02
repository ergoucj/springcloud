package com.study.springcloud.cloud_configserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @EnableConfigServer 注解开启配置服务器的功能
 */
@EnableConfigServer
@SpringBootApplication
public class CloudConfigServerApplication {


	public static void main(String[] args) {
		SpringApplication.run(CloudConfigServerApplication.class, args);
	}
}
