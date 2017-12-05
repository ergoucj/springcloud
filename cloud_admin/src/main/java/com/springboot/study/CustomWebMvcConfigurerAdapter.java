package com.springboot.study;

import com.springboot.study.interceptor.LogInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * web 拦截器/参数解析器/消息转换的配置
 *
 * <br>
 * <mvc:interceptors> <mvc:argument-resolvers> <mvc:message-converters>
 *
 * @author Administratior
 */
@Configuration
public class CustomWebMvcConfigurerAdapter extends WebMvcConfigurerAdapter {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {

		// 多个拦截器组成一个拦截器链
		// addPathPatterns 用于添加拦截规则
		// excludePathPatterns 用户排除拦截
		// 注入http请求响应的拦截，记录日志
		registry.addInterceptor(new LogInterceptor());


	}



}
