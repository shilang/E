package com.cloud.erp.activiti;

import org.activiti.engine.delegate.DelegateExecution;

public class CommitJavaDelegate extends BusinessServiceJavaDelegate{

	@Override
	public void doExecute(DelegateExecution execution) {
		commit();
	}
}
