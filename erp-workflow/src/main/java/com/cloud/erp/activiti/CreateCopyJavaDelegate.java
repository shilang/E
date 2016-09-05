package com.cloud.erp.activiti;

import org.activiti.engine.delegate.DelegateExecution;

public class CreateCopyJavaDelegate extends CreateCopyServiceJavaDelegate{

	@Override
	public void doExecute(DelegateExecution execution) {
		save();
	}
}
