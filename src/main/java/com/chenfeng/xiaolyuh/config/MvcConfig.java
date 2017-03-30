package com.chenfeng.xiaolyuh.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * MVC 配置类。这里我们配置了一个jsp的ViewResolver，用来映射路径和实际页面的位置，
 * 其中@EnableWebMvc注解会开启一些默认的配置，如一些ViewResolver或者MessageConverter
 * @author yuhao.wang
 * @Date 2017年3月29日 下午3:41:20
 */
@Configuration
@EnableWebMvc
@ComponentScan("com.chenfeng.xiaolyuh")
public class MvcConfig {
	
	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/classes/views/");// 运行时代码会将页面自动编译到/WEB-INF/classes/views/下
		viewResolver.setSuffix(".jsp");
		viewResolver.setViewClass(JstlView.class);
		return viewResolver;
	}
}
