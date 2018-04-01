package com.bootdo.system.config;

import com.bootdo.system.extstags.dialog.BdDialect;
import com.google.common.collect.Lists;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.nio.charset.Charset;

@Configuration
public class WebTagConfig extends WebMvcConfigurerAdapter {
	/**
	 * 默认的编码
	 * **/
	protected static final String DEFAULT_CHARSET_NAME = "UTF-8";
	
	/**
	 * 默认的编码
	 * **/
	protected static final Charset DEFAULT_CHARSET = Charset.forName(DEFAULT_CHARSET_NAME);
	@Bean
	@ConditionalOnMissingBean(BdDialect.class)
	public BdDialect bdDialect(){
		return new BdDialect();
	}
	/**
	 * 编码过滤器
	 * **/
	@Bean
	@ConditionalOnMissingBean(FilterRegistrationBean.class)
    public FilterRegistrationBean registerCharacterEncodingFilter() {
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding(DEFAULT_CHARSET_NAME);
        
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(characterEncodingFilter);
        registrationBean.setUrlPatterns(Lists.newArrayList("/*"));
        return registrationBean;
    }
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
		//registry.addResourceHandler("*.css").addResourceLocations("classpath:/static/css/");
	}
}
