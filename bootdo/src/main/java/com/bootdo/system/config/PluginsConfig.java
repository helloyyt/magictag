package com.bootdo.system.config;

import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Properties;

/**
 *前端组件引入配置
 * @author yangytian
 */
@Component
@Configuration
public class PluginsConfig {
	/**
	 * frontplugins.properties
	 * @return
	 * @throws IOException
	 */
	@Bean
	public Properties pluginProperties() throws IOException {
		PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
		propertiesFactoryBean.setLocation(new ClassPathResource("/config/frontplugins.properties"));
		propertiesFactoryBean.afterPropertiesSet();
		return propertiesFactoryBean.getObject();
	}
}
