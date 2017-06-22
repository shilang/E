package com.cloud.erp.activiti;

import org.activiti.engine.delegate.DelegateExecution;

/**
 * 
 * @author Bollen
 *
 */
public class CommitJavaDelegate extends BusinessServiceJavaDelegate{

	@Override
	public void doExecute(DelegateExecution execution) {
		commit();
	}
}
