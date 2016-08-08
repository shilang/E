package com.cloud.erp.activiti;

import org.activiti.engine.delegate.DelegateExecution;

import com.cloud.erp.service.common.BaseService;
import com.cloud.erp.utils.Reflect;
import com.cloud.erp.utils.SpringUtil;

public class UpdateRelationJavaDelegate extends BaseJavaDelegate{
	
	private static final String PROCINSTID = "procInstId";

	@Override
	public void doExecute(DelegateExecution execution) {
		
		//update process instance id in business table.
		String processInstanceId = execution.getProcessInstanceId();
		
		BaseService baseService = (BaseService) SpringUtil.getBean(BaseService.class);
		Object object = baseService.get(getBusinessClass(), getBusinessKey());
		
		Reflect.invokeSetMethod(object, PROCINSTID, processInstanceId);
		baseService.update(object);
		
		//update business key
		ProcessManager processManager = (ProcessManager) SpringUtil.getBean(ProcessManager.class);
		processManager.updateBusinessKey(processInstanceId, getBusinessKey().toString());
	}
		
}
