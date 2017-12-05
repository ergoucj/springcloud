package com.study.springcloud.configclient.cloud_servicezuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @EnableZuulProxy，开启zuul的功能（路由网关：主要功能是路由转发和过滤器）
 *
 *
 * 在Spring Cloud微服务系统中，一种常见的负载均衡方式是，客户端的请求首先经过负载均衡（zuul、Ngnix），
 * 再到达服务网关（zuul集群），然后再到具体的服。
 * 服务的所有的配置文件由配置服务管理，配置服务的配置文件放在git仓库，方便开发人员随时改配置。
 */
@EnableZuulProxy
@EnableEurekaClient
@SpringBootApplication
public class CloudServiceZuulApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudServiceZuulApplication.class, args);
	}
}
