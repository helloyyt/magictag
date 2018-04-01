package com.bootdo.system.extstags.processor;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.thymeleaf.Arguments;
import org.thymeleaf.dom.Element;
import org.thymeleaf.dom.Node;
import org.thymeleaf.processor.AbstractProcessor;
import org.thymeleaf.processor.IProcessorMatcher;
import org.thymeleaf.processor.ProcessorMatchingContext;
import org.thymeleaf.processor.ProcessorResult;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

/**
 * @Author:yangyt
 * @Description
 * @Date:Created in 22:52 2018/3/31
 */
public class BaseProcessor extends AbstractProcessor {
    @Override
    public int getPrecedence() {
        return 100;
    }

    @Override
    protected ProcessorResult doProcess(Arguments arguments, ProcessorMatchingContext processorMatchingContext, Node node) {
        return null;
    }
    @Override
    public IProcessorMatcher<? extends Node> getMatcher() {
        return null;
    }

    /**
     * 根据名称获取doc 子级element
     * @param arguments
     * @param name
     * @return
     */
    public Element getElementByName(Arguments arguments,String name) {
        org.thymeleaf.dom.Document doc = arguments.getDocument();
        List<Element> childrenElements = doc.getFirstElementChild().getElementChildren();
        Element rtnElement = null;
        for(Element e:childrenElements) {
            String tempElementName = e.getOriginalName();
            if(StringUtils.isNotEmpty(tempElementName) && name.equals(tempElementName.toLowerCase())) {
                rtnElement = e;
                break;
            }
        }
        return rtnElement;
    }
    @Bean
    public Properties getPluginsConfig() throws IOException{
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        propertiesFactoryBean.setLocation(new ClassPathResource("/config/frontplugins.properties"));
        propertiesFactoryBean.afterPropertiesSet();
        return propertiesFactoryBean.getObject();
    }

}
