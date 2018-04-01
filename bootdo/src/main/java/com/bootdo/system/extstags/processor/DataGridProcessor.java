package com.bootdo.system.extstags.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.thymeleaf.Arguments;
import org.thymeleaf.dom.Node;
import org.thymeleaf.processor.*;

/** 表格处理
 * @author yangyt
 */
public class DataGridProcessor extends BaseProcessor {
	private final IElementNameProcessorMatcher matcher;
	@Autowired
	private Logger logger = LoggerFactory.getLogger(getClass());
	public DataGridProcessor() {
		super();
		this.matcher = new ElementNameProcessorMatcher("datagrid");
	}

	@Override
	public IProcessorMatcher<? extends Node> getMatcher() {
		return matcher;
	}

	@Override
	protected ProcessorResult doProcess(Arguments arguments,
                                        ProcessorMatchingContext processorMatchingContext, Node node) {
		System.out.println("1111111111111");
		// 父节点
//		Element currentElement = (Element) node;
//		Element headElement = getElementByName(arguments,"head");
//		Element bodyElement = getElementByName(arguments,"body");
//		final IStandardExpressionParser parser = new StandardExpressionParser();
//		String pluginsNames = currentElement.getAttributeValue("names");
//		if (StringUtils.isEmpty(pluginsNames)) {
//			return ProcessorResult.OK;
//		}
		return ProcessorResult.OK;
	}

}