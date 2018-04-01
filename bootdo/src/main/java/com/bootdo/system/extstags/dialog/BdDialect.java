package com.bootdo.system.extstags.dialog;

import com.bootdo.system.extstags.processor.DataColProcessor;
import com.bootdo.system.extstags.processor.DataGridProcessor;
import com.bootdo.system.extstags.processor.ImportProcessor;
import org.thymeleaf.dialect.AbstractDialect;
import org.thymeleaf.processor.IProcessor;

import java.util.HashSet;
import java.util.Set;

/**
 * 方言前缀
 */
public class BdDialect extends AbstractDialect {

	@Override
	public String getPrefix() {
		return "bd";
	}

	@Override
	public Set<IProcessor> getProcessors() {
		final Set<IProcessor> processors = new HashSet<IProcessor>();
		processors.add(new ImportProcessor());
		processors.add(new DataGridProcessor());
		processors.add(new DataColProcessor());
		return processors;
	}
}
