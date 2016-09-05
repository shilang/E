package com.cloud.erp.activiti;

import org.activiti.engine.delegate.DelegateExecution;

public class CopyJavaDelegate extends CopyServiceJavaDelegate{

	@Override
	public void doExecute(DelegateExecution execution) throws Exception {
		save();
	}
}
