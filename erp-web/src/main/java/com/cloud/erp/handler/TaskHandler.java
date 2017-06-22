package com.cloud.erp.handler;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cloud.erp.activiti.model.AuditModel;
import com.cloud.erp.activiti.model.TaskInfoModel;
import com.cloud.erp.common.BaseHandler;
import com.cloud.erp.entities.viewmodel.GridModel;
import com.cloud.erp.entities.viewmodel.Result;
import com.cloud.erp.servicefacade.TaskServiceFacade;
import com.cloud.erp.utils.PageUtil;

/**
 * 
 * @author Bollen
 *
 */
@Controller
@RequestMapping("/task")
public class TaskHandler extends BaseHandler{
	
	private static final String UNSIGNED = "unsigned";
	private static final String TODO = "toDo";
	
	@Resource
	private TaskServiceFacade taskServiceFacade;

	@ResponseBody
	@RequestMapping("/list")
	public GridModel findTasks(String type, PageUtil pageUtil){
		List<TaskInfoModel> list = null;
		Long total = 0L;
		if(UNSIGNED.equals(type)){
			list = taskServiceFacade.unsignedTasksList(pageUtil);
			total = taskServiceFacade.unsignedTasksCount();
		}
		else if(TODO.equals(type)){
			list = taskServiceFacade.toDoTaskList(pageUtil);
			total = taskServiceFacade.toDoTaskCount();
		}else {
			list = taskServiceFacade.taskCandidateOrAssignedList(pageUtil);
			total = taskServiceFacade.taskCandidateOrAssignedCount();
		}
		return new GridModel(list, total);
	}
	
	@ResponseBody
	@RequestMapping("/signed")
	public Result signedTask(String taskId){
		taskServiceFacade.signedTask(taskId);
		return getDefaultResult(true);
	}
	
	@ResponseBody
	@RequestMapping("/submit")
	public Result submitTask(String taskId, AuditModel auditModel){
		taskServiceFacade.submitTask(taskId, auditModel);
		return getDefaultResult(true);
	}
	
	@ResponseBody
	@RequestMapping("/submitWithForm")
	public Result submitTaskWithForm(String taskId, AuditModel auditModel){
		taskServiceFacade.submitTaskWithForm(taskId, auditModel);
		return getDefaultResult(true);
	}
	
	@ResponseBody
	@RequestMapping("/submitChangeApply")
	public Result submitChangeApply(String processDefinitionKey, AuditModel auditModel){
		taskServiceFacade.startProcess(processDefinitionKey, auditModel);
		return getDefaultResult(true);
	}
	
	@ResponseBody
	@RequestMapping("/submitWithFormByProcInstId")
	public Result submitTaskWithFormByProcInstId(String procInstId, AuditModel auditModel){
		taskServiceFacade.submitTaskWithFormByProcInstId(procInstId, auditModel);
		return getDefaultResult(true);
	}
	
	@ResponseBody
	@RequestMapping("/getEntity")
	public Object getEntity(String businessClass, Integer businessKey) throws Exception{
		return taskServiceFacade.getEntity(businessClass, businessKey);
	}
	
	@ResponseBody
	@RequestMapping("/deleteProcessInstance/{processInstanceId}")
	public Result deleteProcessInstance(@PathVariable("processInstanceId") String processInstanceId){
		taskServiceFacade.deleteProcessInstance(processInstanceId);
		return getDefaultResult(true);
	}
	
	@ResponseBody
	@RequestMapping("/deleteProcessDeployments")
	public Result deleteProcessDeployments(){
		taskServiceFacade.deleteProcessDeployment();
		return getDefaultResult(true);
	}
	
	@ResponseBody
	@RequestMapping("/getTaskId")
	public Result getTaskId(String processInstanceId, String key){
		return getResult(true, taskServiceFacade.getTaskIdByProcInstIdAndDefKey(processInstanceId, key));
	}
}
