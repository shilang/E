package com.cloud.erp.activiti;

import org.activiti.engine.delegate.DelegateExecution;

public class UpdateSettleInfoForCheck extends BusinessServiceJavaDelegate {

	@Override
	public void doExecute(DelegateExecution execution) throws Exception {
		updateSettleInfo(false);
	}

}
