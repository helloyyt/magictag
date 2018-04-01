package com.bootdo.system.extstags.processor;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.thymeleaf.Arguments;
import org.thymeleaf.dom.Element;
import org.thymeleaf.dom.Node;
import org.thymeleaf.dom.Text;
import org.thymeleaf.processor.*;
import org.thymeleaf.standard.expression.IStandardExpressionParser;
import org.thymeleaf.standard.expression.StandardExpressionParser;

import java.io.IOException;
import java.util.Properties;

/**
 * @author yangyt
 */
public class ImportProcessor extends BaseProcessor {
	private final IElementNameProcessorMatcher matcher;
	@Autowired
	private Logger logger = LoggerFactory.getLogger(getClass());
	public ImportProcessor() {
		super();
		this.matcher = new ElementNameProcessorMatcher("plugins");
	}

	@Override
	public IProcessorMatcher<? extends Node> getMatcher() {
		return matcher;
	}

	@Override
	protected ProcessorResult doProcess(Arguments arguments,
                                        ProcessorMatchingContext processorMatchingContext, Node node) {
		// 父节点
		Element currentElement = (Element) node;
		Element headElement = getElementByName(arguments,"head");
		Element bodyElement = getElementByName(arguments,"body");
		final IStandardExpressionParser parser = new StandardExpressionParser();
		String pluginsNames = currentElement.getAttributeValue("names");
		if (StringUtils.isEmpty(pluginsNames)) {
			return ProcessorResult.OK;
		}
		StringBuffer scriptSb = null;
		StringBuffer cssSb = null;

		try {
			scriptSb = new StringBuffer();
			cssSb = new StringBuffer();
			String [] pluginsNameArr = pluginsNames.split(",");
			for (String plugin:pluginsNameArr) {
				getjsAndCssStringBuffer(scriptSb,cssSb,plugin);
			}
			headElement.addChild(new Text(cssSb.toString(),"documentName",1,true));
			bodyElement.addChild(new Text(scriptSb.toString(),"documentName",1,true));
			bodyElement.removeChild(currentElement);
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("importProcessor中读取plugings.properties时发生错误",e);
		}
		return ProcessorResult.OK;
	}

	/**
	 * 根据插件名称自动生成html文本内容
	 * @param scriptSb
	 * @param cssSb
	 * @param name
	 * @throws IOException
	 */
	private void getjsAndCssStringBuffer(StringBuffer scriptSb,StringBuffer cssSb,String name) throws IOException {
		Properties pluginsPors = getPluginsConfig();
		String jss = pluginsPors.getProperty(name + ".js");
		String links = pluginsPors.getProperty(name + ".css");
		//添加js
		if(null != jss) {
			for (String js:jss.split(",")) {
				scriptSb.append("<script type=\"text/javascript\" src=\""+js+"\"></script>");
			}
		}
		//添加样式
		if(null != links) {
			for (String css:links.split(",")) {
				cssSb.append("<link rel=\"stylesheet\" href=\""+css+"\"></link>");
			}
		}
	}
}