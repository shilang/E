package com.cloud.erp.activiti;

import org.activiti.engine.delegate.DelegateExecution;

public class UpdateSettleInfoForCancel extends BusinessServiceJavaDelegate {

	@Override
	public void doExecute(DelegateExecution execution) throws Exception {
		updateSettleInfo(true);
	}

}
