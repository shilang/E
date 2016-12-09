package com.cloud.erp.activiti;

import org.activiti.engine.delegate.DelegateExecution;

import com.cloud.erp.activiti.model.AuditModel;
import com.cloud.erp.utils.Constants;

public class CancelCheckJavaDelegate extends BusinessServiceJavaDelegate{

	@Override
	public void doExecute(DelegateExecution execution) {
		
		AuditModel auditModel = getAuditModel();
		auditModel.setTaskBusinessType(Constants.BUSINESS_TYPE_COMMIT);
		setAuditModelVariable(execution, auditModel);
		cancelCheck();
	}

}
