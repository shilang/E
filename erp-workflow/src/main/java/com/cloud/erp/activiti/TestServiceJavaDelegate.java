package com.cloud.erp.activiti;

import org.activiti.engine.delegate.DelegateExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestServiceJavaDelegate extends BaseJavaDelegate {
	
	private static final Logger log = LoggerFactory.getLogger(TestServiceJavaDelegate.class);

	@Override
	public void doExecute(DelegateExecution execution) throws Exception {
		if(log.isDebugEnabled()){
			log.debug("This is a TestServiceJavaDelegate.");
		}
	}

}
