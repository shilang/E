package com.cloud.erp.activiti;

import org.activiti.engine.delegate.DelegateExecution;

public class ChangeCommitJavaDelegate extends CheckServiceJavaDelegate{

	@Override
	public void doExecute(DelegateExecution execution) {
		changeCommit();
	}
	
}
