package com.cloud.erp.activiti;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.FormService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloud.erp.activiti.model.AuditModel;
import com.cloud.erp.utils.Commons;
import com.cloud.erp.utils.Constants;

@Service("processManager")
public class ProcessManager {

	@Autowired
	private RuntimeService runtimeService;
	
	@Autowired
	private IdentityService identityService;
	
	@Autowired
	private RepositoryService repositoryService;
	
	@Autowired
	private FormService formService;
	
	public String startProcess(String processDefinitionKey, AuditModel auditModel){
		try {
			identityService.setAuthenticatedUserId(getUserId());
		    Map<String, Object> variables = new HashMap<String, Object>();
			variables.put(Constants.AUDIT_MODEL, auditModel);
			ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processDefinitionKey,auditModel.getBusinessKey().toString(), variables);
			return processInstance.getId();
		} finally {
			identityService.setAuthenticatedUserId(null);
		}
	}
	
	public void processDefinitionQeury(){
		repositoryService.createProcessDefinitionQuery();
	}
	
	public void deleteProcessInstance(String processInstanceId){
		runtimeService.deleteProcessInstance(processInstanceId, "");
	}
	
	public void deleteProcessDeployment(){
		List<Deployment> list = repositoryService.createDeploymentQuery().list();
		for(Deployment deployment : list){
			repositoryService.deleteDeployment(deployment.getId(), true);
		}
	}
	
	public void updateBusinessKey(String processInstanceId, String businessKey){
		runtimeService.updateBusinessKey(processInstanceId, businessKey);
	}
	
	private String getUserId(){
		return Commons.getCurrentUser().getAccount();
	}
	
}
