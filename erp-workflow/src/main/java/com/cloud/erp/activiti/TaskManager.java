package com.cloud.erp.activiti;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.FormService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloud.erp.activiti.model.AuditModel;
import com.cloud.erp.activiti.model.TaskInfoModel;
import com.cloud.erp.activiti.utils.TaskUtils;
import com.cloud.erp.activiti.utils.VariableUtils;
import com.cloud.erp.utils.Constants;

@Service("taskManager")
public class TaskManager {

	@Autowired
	private TaskService taskService;
	
	@Autowired
	private RuntimeService runtimeService;
	
	@Autowired
	private RepositoryService repositoryService;
	
	@Autowired
	private FormService formService;
	
	private int firstResultConverter(Integer page, Integer rows){
		if (page == null || page < 1) {
			page = 1;
		}
		return (page - 1) * rows;
	}
	
	private int maxResultsConverter(Integer rows){
		if (rows == null || rows < 1) {
			rows = 10;
		}
		return rows;
	}
	
	private List<TaskInfoModel> taskModelConverter(List<Task> tasks){
		List<TaskInfoModel> taskInfoModels = TaskUtils.Task2TaskModel(tasks);
	    for(TaskInfoModel taskInfoModel : taskInfoModels){
	    	AuditModel auditModel = (AuditModel) taskService.getVariable(taskInfoModel.getId(), Constants.AUDIT_MODEL);
	    	taskInfoModel.setAuditModel(auditModel);
	    	Object renderedTaskForm = formService.getRenderedTaskForm(taskInfoModel.getId());
	    	if(renderedTaskForm != null){
	    		taskInfoModel.setRenderedTaskForm(renderedTaskForm.toString());
	    	}
	    }
	    return taskInfoModels;
	}
	
	private TaskQuery unsignedTasksQuery(String userId){
		return taskService.createTaskQuery().taskCandidateUser(userId);
	}
	
	public List<TaskInfoModel> unsignedTasksList(String userId, Integer page, Integer rows){
		List<Task> tasks = unsignedTasksQuery(userId).listPage(firstResultConverter(page, rows)
				, maxResultsConverter(rows));
		return taskModelConverter(tasks);
	}
	
	public long unsignedTasksCount(String userId){
		return unsignedTasksQuery(userId).count();
	}
	
	private TaskQuery toDoTasksQuery(String userId){
		return taskService.createTaskQuery().taskAssignee(userId);
	}
	
	public List<TaskInfoModel> toDoTasksList(String userId, Integer page, Integer rows){
		List<Task> tasks = toDoTasksQuery(userId).listPage(firstResultConverter(page, rows),
				maxResultsConverter(rows));
		return taskModelConverter(tasks);
	}
	
	public long  toDoTasksCount(String userId){
		return toDoTasksQuery(userId).count();
	}
	
	private TaskQuery taskCandidateOrAssignedQuery(String userId){
		return taskService.createTaskQuery().taskCandidateOrAssigned(userId).orderByTaskCreateTime().desc();
	}
	
	public List<TaskInfoModel> taskCandidateOrAssignedList(String userId, Integer page, Integer rows){
		List<Task> tasks = taskCandidateOrAssignedQuery(userId).listPage(firstResultConverter(page, rows),
				maxResultsConverter(rows));
		return taskModelConverter(tasks);
	}
	
	public long taskCandidateOrAssignedCount(String userId){
		return taskCandidateOrAssignedQuery(userId).count();
	}
	
	public void signedTask(String taskId, String userId){
		taskService.claim(taskId, userId);
	}
	
	public void submitTask(String taskId, AuditModel auditModel){
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put(Constants.AUDIT_MODEL, auditModel);
		taskService.complete(taskId, variables);
	}
	
	public void submitTaskWithForm(String taskId, AuditModel auditModel){
		Map<String, String> properties = VariableUtils.getProperties(auditModel);
		formService.submitTaskFormData(taskId, properties);
	}
	
	public void submitTaskWithForm(String procInstId, String assignee, AuditModel auditModel){
		Map<String, String> properties = VariableUtils.getProperties(auditModel);
		Task task = taskService.createTaskQuery().processInstanceId(procInstId).taskAssignee(assignee).singleResult();
		if(task != null){
			formService.submitTaskFormData(task.getId(), properties);
		}
	}
	
	public String getTaskIdByProcInstIdAndDefKey(String processInstanceId, String key){
		Task task = taskService.createTaskQuery().processInstanceId(processInstanceId).
				taskDefinitionKey(key).singleResult();
		if(task != null){
			return task.getId();
		}
		return "";
	}
}
