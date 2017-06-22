package com.cloud.erp.activiti;

import org.activiti.engine.delegate.DelegateExecution;

import com.cloud.erp.utils.SpringUtil;

/**
 * 
 * @author Bollen
 *
 */
public class CancelTaskServiceJavaDelegate extends BaseJavaDelegate{
	
	private static final String DELETE_REASON = "改单取消";

	@Override
	public void doExecute(DelegateExecution execution) throws Exception {
		getProcessManager().deleteProcessInstance(getAuditModel().getProcessInstanceId(),DELETE_REASON);
	}
	
	private ProcessManager getProcessManager(){
		return (ProcessManager) SpringUtil.getBean(ProcessManager.class);
	}
}
