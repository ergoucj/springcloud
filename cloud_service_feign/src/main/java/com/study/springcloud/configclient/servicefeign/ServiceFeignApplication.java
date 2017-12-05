package com.study.springcloud.configclient.servicefeign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * @EnableFeignClients 注解开启Feign的功能（消费者）
 *
 * @EnableHystrix  注解开启Hystrix 断路器
 * @EnableHystrixDashboard  注解，开启hystrixDashboard仪表盘
 * @EnableCircuitBreaker注解，但要使用Dashboard则必须加，如果不加，Dashboard无法接收到来自Feign内部断路器的监控数据
 *
 * http://localhost:8765/hystrix 访问监控界面
 *  Feign默认集成了Ribbon，并和Eureka结合，默认实现了负载均衡的效果。
 * Feign 采用的是基于接口的注解
 *Feign 整合了ribbon
 *
 * Feign是自带断路器的，在D版本的Spring Cloud中，它没有默认打开。需要在配置文件中配置打开它，在配置文件加以下代码：
 * 只需要在FeignClient的SchedualServiceHi接口的注解中加上fallback的指定类
 *
 * 在微服务架构中，根据业务来拆分成一个个的服务，服务与服务之间可以相互调用（RPC），在Spring Cloud可以用RestTemplate+Ribbon和Feign来调用。
 * 为了保证其高可用，单个服务通常会集群部署。由于网络原因或者自身的原因，服务并不能保证100%可用，如果单个服务出现问题，调用这个服务就会出现线程阻塞
 * 此时若有大量的请求涌入，Servlet容器的线程资源会被消耗完毕，导致服务瘫痪。服务与服务之间的依赖性，故障会传播，会对整个微服务系统造成灾难性的严重后果
 * 这就是服务故障的“雪崩”效应。为了解决这个问题，业界提出了断路器模型。
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableHystrix
@EnableHystrixDashboard
@EnableCircuitBreaker
public class ServiceFeignApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceFeignApplication.class, args);
	}


}
