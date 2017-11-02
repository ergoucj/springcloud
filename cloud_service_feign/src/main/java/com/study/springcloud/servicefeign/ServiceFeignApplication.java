package com.study.springcloud.servicefeign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * @EnableFeignClients 注解开启Feign的功能（消费者）
 *  * @EnableHystrix  注解开启Hystrix 断路器
 * @EnableHystrixDashboard  注解，开启hystrixDashboard仪表盘
 * @EnableCircuitBreaker注解，但要使用Dashboard则必须加，如果不加，Dashboard无法接收到来自Feign内部断路器的监控数据
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableCircuitBreaker
@EnableHystrixDashboard
public class ServiceFeignApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceFeignApplication.class, args);
	}


}
