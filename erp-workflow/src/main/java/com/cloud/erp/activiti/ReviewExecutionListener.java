package com.cloud.erp.activiti;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.activiti.engine.delegate.Expression;

import com.cloud.erp.activiti.model.AuditModel;
import com.cloud.erp.utils.Constants;

/**
 * 
 * @author Bollen
 *
 */
public class ReviewExecutionListener implements ExecutionListener{

	private static final long serialVersionUID = 1L;
	
	private Expression taskBusinessType;
	private Expression path;

	@Override
	public void notify(DelegateExecution execution) throws Exception {
		AuditModel auditModel = (AuditModel) execution.getVariable(Constants.AUDIT_MODEL);
		auditModel.setTaskBusinessType(taskBusinessType.getValue(execution).toString());
		auditModel.setPath(path.getValue(execution).toString());
		execution.setVariable(Constants.AUDIT_MODEL, auditModel);
	}

}
