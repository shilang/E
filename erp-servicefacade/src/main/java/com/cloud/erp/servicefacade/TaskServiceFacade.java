package com.cloud.erp.servicefacade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloud.erp.activiti.ProcessManager;
import com.cloud.erp.activiti.TaskManager;
import com.cloud.erp.activiti.model.AuditModel;
import com.cloud.erp.activiti.model.TaskInfoModel;
import com.cloud.erp.service.common.BaseService;
import com.cloud.erp.utils.Commons;
import com.cloud.erp.utils.PageUtil;

@Service("taskServiceFacade")
public class TaskServiceFacade {
	
	@Autowired
	private TaskManager taskManager;
	
	@Autowired
	private ProcessManager processManager;
	
	@Autowired
	private BaseService baseService;
	
	public List<TaskInfoModel> unsignedTasksList(PageUtil pageUtil){
		return taskManager.unsignedTasksList(getUserId(), pageUtil.getPage(), 
				pageUtil.getRows());
	}
	
	public Long unsignedTasksCount() {
		
		return taskManager.unsignedTasksCount(getUserId());
	}
	
	public List<TaskInfoModel> toDoTaskList(PageUtil pageUtil){
		return taskManager.toDoTasksList(getUserId(), pageUtil.getPage(), 
				pageUtil.getRows());
	}
	
	public Long toDoTaskCount() {
		
		return taskManager.toDoTasksCount(getUserId());
	}
	
	public List<TaskInfoModel> taskCandidateOrAssignedList(PageUtil pageUtil){
		return taskManager.taskCandidateOrAssignedList(getUserId(), pageUtil.getPage(), 
				pageUtil.getRows());
	}
	
	public Long taskCandidateOrAssignedCount() {
		
		return taskManager.taskCandidateOrAssignedCount(getUserId());
	}
	
	public void signedTask(String taskId){
		taskManager.signedTask(taskId, getUserId());
	}
	
	public void submitTask(String taskId, AuditModel auditModel){
		taskManager.submitTask(taskId, auditModel);
	}
	
	public void deleteProcessInstance(String processInstanceId){
		processManager.deleteProcessInstance(processInstanceId);
	}
	
	public void deleteProcessDeployment(){
		processManager.deleteProcessDeployment();
	}
	
	private String getUserId(){
		return Commons.getCurrentUser().getAccount();
	}

	public void submitTaskWithForm(String taskId, AuditModel auditModel) {
		
		taskManager.submitTaskWithForm(taskId, auditModel);
	}
	
	public void submitTaskWithFormByProcInstId(String procInstId, AuditModel auditModel) {
		
		taskManager.submitTaskWithForm(procInstId, getUserId(), auditModel);
	}
	
	public Object getEntity(String businessClass, Integer businessKey) throws ClassNotFoundException{
		Class<?> clazz = Class.forName(businessClass);
		return baseService.get(clazz, businessKey);
	}

	public void startProcess(String processDefinitionKey, AuditModel auditModel) {
		
		processManager.startProcess(processDefinitionKey, auditModel);
	}
	
	public String getTaskIdByProcInstIdAndDefKey(String processInstanceId, String key){
		return taskManager.getTaskIdByProcInstIdAndDefKey(processInstanceId, key);
	}

}