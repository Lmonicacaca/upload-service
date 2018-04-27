package com.mbr.upload.common.configurer;

import com.mbr.upload.interceptor.LogInterceptor;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.MultipartConfigElement;


@Configuration
@EnableWebMvc
public class WebMvcConfigurer extends WebMvcConfigurerAdapter {
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(logInterceptor()).addPathPatterns("/**"); // 拦截所有请求
		super.addInterceptors(registry);
	}

	@Bean
	public LogInterceptor logInterceptor() {
		return new LogInterceptor();
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/file/**").addResourceLocations("classpath:/file/");
	}

	@Bean
	public MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		// 置文件大小限制 ,超出此大小页面会抛出异常信息
		factory.setMaxFileSize("100MB"); //KB,MB
		// 设置总上传数据总大小
		factory.setMaxRequestSize("100MB");
		// 设置文件临时文件夹路径
		// 如果文件大于这个值，将以文件的形式存储，如果小于这个值文件将存储在内存中，默认为0
		// factory.setMaxRequestSize(0);
		return factory.createMultipartConfig();
	}
}
