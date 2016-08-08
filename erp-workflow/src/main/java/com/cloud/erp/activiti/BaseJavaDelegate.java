package com.cloud.erp.activiti;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cloud.erp.activiti.model.AuditModel;
import com.cloud.erp.utils.Constants;

public abstract class BaseJavaDelegate implements JavaDelegate{
	
	private static final Logger log = LoggerFactory.getLogger(BaseJavaDelegate.class);
	
	private AuditModel auditModel;
	private Class<?> businessClass;
	private Integer businessKey;
	
	public AuditModel getAuditModel() {
		return auditModel;
	}

	public Class<?> getBusinessClass() {
		return businessClass;
	}

	public Integer getBusinessKey() {
		return businessKey;
	}

	private void init() throws ClassNotFoundException{
		businessClass = Class.forName(auditModel.getBusinessClass());
		businessKey = auditModel.getBusinessKey();
	}
	
	public Object getAuditModelVariable(DelegateExecution execution){
		Object variable = execution.getVariable(Constants.AUDIT_MODEL);
		if(log.isDebugEnabled()){
			log.debug("AuditModel variable: {}", variable);
		}
		return variable;
	}
	
	public void setAuditModelVariable(DelegateExecution execution, Object value){
		execution.setVariable(Constants.AUDIT_MODEL, value);
	}

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		
		auditModel = (AuditModel)getAuditModelVariable(execution);
		init();
		doExecute(execution);
	}
	
	public abstract void doExecute(DelegateExecution execution);
}
