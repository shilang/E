package com.cloud.erp.activiti.utils;

import java.util.ArrayList;
import java.util.List;

import org.activiti.engine.task.Task;

import com.cloud.erp.activiti.model.TaskInfoModel;

public class TaskUtils {
	
	public static TaskInfoModel Task2TaskModel(Task task){
		TaskInfoModel taskInfoModel = new TaskInfoModel();
		taskInfoModel.setId(task.getId());
		taskInfoModel.setName(task.getName());
		taskInfoModel.setDescription(task.getDescription());
		taskInfoModel.setPriority(task.getPriority());
		taskInfoModel.setOwner(task.getOwner());
		taskInfoModel.setAssignee(task.getAssignee());
		taskInfoModel.setProcessInstanceId(task.getProcessInstanceId());
		taskInfoModel.setExecutionId(task.getExecutionId());
		taskInfoModel.setProcessDefinitionId(task.getProcessDefinitionId());
		taskInfoModel.setCreateTime(task.getCreateTime());
		taskInfoModel.setTaskDefinitionKey(task.getTaskDefinitionKey());
		taskInfoModel.setDueDate(task.getDueDate());
		taskInfoModel.setCategory(task.getCategory());
		taskInfoModel.setParentTaskId(task.getParentTaskId());
		taskInfoModel.setTenantId(task.getTenantId());
		taskInfoModel.setFormKey(task.getFormKey());
		taskInfoModel.setTaskLocalVariables(task.getTaskLocalVariables());
		taskInfoModel.setProcessVariables(task.getProcessVariables());
		return taskInfoModel;
	}
	
	public static List<TaskInfoModel> Task2TaskModel(List<Task> tasks){
		List<TaskInfoModel> taskInfoModels = new ArrayList<TaskInfoModel>();
		for(Task task : tasks){
			taskInfoModels.add(Task2TaskModel(task));
		}
		return taskInfoModels;
	}
		
}
