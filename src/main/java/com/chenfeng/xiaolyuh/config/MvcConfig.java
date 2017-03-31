package com.chenfeng.xiaolyuh.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.chenfeng.xiaolyuh.interceptor.DemoInterceptor;

/**
 * MVC 配置类。这里我们配置了一个jsp的ViewResolver，用来映射路径和实际页面的位置，
 * 其中@EnableWebMvc注解会开启一些默认的配置，如一些ViewResolver或者MessageConverter。
 * 
 * Spring MVC的定制配置需要我们的配置类继承WebMvcConfigurerAdapter类（Adapter适配器），
 * 并在此类加上@EnableWebMvc注解，来开启对Spring MVC的配置支持，这样我们就可以重写这个类的方法，
 * 来完成我们的配置。如果不加@EnableWebMvc注解，重写这些方法也无效。
 * @author yuhao.wang
 * @Date 2017年3月29日 下午3:41:20
 */
@Configuration
@EnableWebMvc// 开启对Spring MVC的支持，如果不加@EnableWebMvc注解，重写这些方法也无效。
@ComponentScan("com.chenfeng.xiaolyuh")
public class MvcConfig extends WebMvcConfigurerAdapter {// 重写WebMvcConfigurerAdapter类的方法可以对Spring MVC
	
	@Autowired
	private DemoInterceptor demoInterceptor;
	
	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/classes/views/");// 运行时代码会将页面自动编译到/WEB-INF/classes/views/下
		viewResolver.setSuffix(".jsp");
		viewResolver.setViewClass(JstlView.class);
		return viewResolver;
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// addResourceLocations是指文件放置的目录，addResourceHandler是指对外暴露的地址
		registry.addResourceHandler("/assets/**").addResourceLocations("classpath:/assets/");
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 注册拦截器
		registry.addInterceptor(demoInterceptor);
	}
}
