package com.cloud.erp.activiti;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.ActivitiException;
import org.activiti.engine.ActivitiObjectNotFoundException;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloud.erp.activiti.model.AuditModel;
import com.cloud.erp.utils.Commons;
import com.cloud.erp.utils.Constants;

/**
 * 
 * @author Bollen
 *
 */
@Service("processManager")
public class ProcessManager {
	
	private static final Logger log = LoggerFactory.getLogger(ProcessManager.class);
	
	private static final String DELETE_REASON = "记录被删除";

	@Autowired
	private RuntimeService runtimeService;
	
	@Autowired
	private IdentityService identityService;
	
	@Autowired
	private RepositoryService repositoryService;
	
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
		deleteProcessInstance(processInstanceId, DELETE_REASON);
	}
	
	public boolean hasProcessInstance(String processInstanceId){
		long count = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).count();
		if(count == 0){
			return false;
		}
		return true;
	}
	
	public void deleteProcessInstance(String processInstanceId, String deleteReason){
		
		if(!hasProcessInstance(processInstanceId)){
			if(log.isInfoEnabled()){
				log.info("no process instance [{}] exists", processInstanceId);
			}
			return;
		}
		
		try {
			runtimeService.deleteProcessInstance(processInstanceId, deleteReason);
		} catch (ActivitiObjectNotFoundException e) {
			if(log.isInfoEnabled()){
				log.info("Process instance id[{}] isn't exists.");
			}
		}
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
	
	public void signalEventReceived(String signalName/*, String executionId*/){
		try {
			runtimeService.signalEventReceived(signalName/*, executionId*/);
		} catch (ActivitiException e) {
			if(log.isInfoEnabled()){
				log.info("signalEventReceived:{}", e.getMessage());
			}
		}
	}

	
	private String getUserId(){
		return Commons.getCurrentUser().getAccount();
	}
	
}
