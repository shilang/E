package org.activiti.designer.test;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.ActivitiRule;
import org.junit.Rule;
import org.junit.Test;

public class ProcessTestLeavedynamicfrom {

	private String filename = "E:\\Develop\\OBTCloud\\J2EE\\erp-workflow\\src\\main\\resources\\com\\cloud\\erp\\activiti\\autodeployment\\leave-dynamic-from.bpmn";

	@Rule
	public ActivitiRule activitiRule = new ActivitiRule();

	@Test
	public void startProcess() throws Exception {
		RepositoryService repositoryService = activitiRule.getRepositoryService();
		repositoryService.createDeployment().addInputStream("leave-dynamic-from.bpmn20.xml",
				new FileInputStream(filename)).deploy();
		RuntimeService runtimeService = activitiRule.getRuntimeService();
		Map<String, Object> variableMap = new HashMap<String, Object>();
		variableMap.put("name", "Activiti");
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("leave-dynamic-from", variableMap);
		assertNotNull(processInstance.getId());
		System.out.println("id " + processInstance.getId() + " "
				+ processInstance.getProcessDefinitionId());
	}
	
	@Test
	 public  void testProcessDefinition() throws Exception{
	  // 创建 Activiti流程引擎
/*	  ProcessEngine processEngine=
	      ProcessEngineConfiguration.createStandaloneInMemProcessEngineConfiguration()
	      .buildProcessEngine();*/
	    // 取得 Activiti 服务 
		
		ProcessEngine processEngine = activitiRule.getProcessEngine();
		
	  RepositoryService repositoryService=activitiRule.getRepositoryService();
	  // 部署流程定义
	  Deployment deploy=repositoryService.createDeployment().addClasspathResource("MyProcess.bpmn20.xml").deploy();
	   
	  ProcessDefinition processDefinition=repositoryService.createProcessDefinitionQuery().deploymentId(deploy.getId())
	    .singleResult();
	  System.out.println(processDefinition.getKey());
	  // 启动流程实例
	  RuntimeService runtimeService=processEngine.getRuntimeService();
	  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey(processDefinition.getKey());
	  System.out.println("pid="+processInstance.getId()+",pdid="+
	    processInstance.getProcessDefinitionId());
	 }
	  
	 @Test
	 public void testStartProcess() throws Exception{
	  // 1.创建 Activiti流程引擎（此处使用的是h2数据库，并启用的是内存模式，即数据保存在内存中，程序运行完数据就丢失）
	  ProcessEngine processEngine=  activitiRule.getProcessEngine();
	   
	  // 2.部署流程定义
	  //取得 Activiti 服务 
	  RepositoryService repositoryService=processEngine.getRepositoryService();
	  //获得流程定义的相关数据
	  String fileName="E:/workspace/activiti-demo/src/main/resources/MyProcess.bpmn20.xml";
	  File f =new File(fileName);
	  InputStream fileInputStream = new FileInputStream(f);
	  //创建Deployment，同时部署流程定义
	  repositoryService.createDeployment()
	   .addInputStream(fileName, fileInputStream)
	   .deploy();
	  //查看定义的流程（非必须，只是验证流程定义是否部署成功）
	  ProcessDefinition processDefinition=repositoryService.createProcessDefinitionQuery()
	    .singleResult();
	  System.out.println("流程定义文件的key为："+processDefinition.getKey());
	  // 3.启动流程实例
	  RuntimeService runtimeService=processEngine.getRuntimeService();
	  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey(processDefinition.getKey());
	  System.out.println("流程实例的id="+processInstance.getId()+",流程实例的definitionId="+
	    processInstance.getProcessDefinitionId());
	   
	  //4.取得当前任务
	  //获得任务服务
	  TaskService taskService=processEngine.getTaskService();
	  //取得当前任务(由于流程定义中没有指明任务指派给谁，所以这里不用申明任务由谁获得)
	  Task task=taskService.createTaskQuery().singleResult();
	  System.out.println("当前任务的名称是："+task.getName());
	  //5.认领任务（由于流程定义中没有指明任务指派给谁，任何人都可以认领该任务，如这里的“leaderUser”）
	  taskService.claim(task.getId(), "leaderUser");
	  //查看"leaderUser"现在是否能够获取到该任务(非必须)
	  task=taskService.createTaskQuery().taskAssignee("leaderUser").singleResult();
	  System.out.println("认领任务的人是："+task.getAssignee());
	  //6.完成任务
	  taskService.complete(task.getId());
	   
	  //以下是验证任务是否完成，通过重新查找任务（为空），查询历史记录（有），说明之前的任务完成了(非必须)
	  task=taskService.createTaskQuery().singleResult();
	  System.out.println("当前的任务是："+task);
	  HistoryService historyService=processEngine.getHistoryService();
	  long count=historyService.createHistoricProcessInstanceQuery().finished().count();
	  System.out.println("已完成的任务数是："+count);
	 }
	  
	  
	  
	 @Test
	 public void testStartProcess2() throws Exception{
		 
		 RepositoryService repositoryService = activitiRule.getRepositoryService();
			repositoryService.createDeployment().addInputStream("leave-dynamic-from.bpmn",
					new FileInputStream(filename)).deploy();
			RuntimeService runtimeService = activitiRule.getRuntimeService();
			Map<String, Object> variableMap = new HashMap<String, Object>();
			variableMap.put("name", "Activiti");
			ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("leave-dynamic-from", variableMap);
			assertNotNull(processInstance.getId());
			System.out.println("id " + processInstance.getId() + " "
					+ processInstance.getProcessDefinitionId());
		 
		 
	  // 1.创建 Activiti流程引擎（此处使用的是h2数据库，并启用的是内存模式，即数据保存在内存中，程序运行完数据就丢失）
	 // ProcessEngine processEngine = activitiRule.getProcessEngine();
	   
	  // 2.部署流程定义
	  //取得 Activiti 服务 
	  //RepositoryService repositoryService=processEngine.getRepositoryService();
	  //获得流程定义的相关数据
/*	  String fileName="E:/workspace/activiti-demo/src/main/resources/pool.bpmn";
	  File f =new File(fileName);
	  InputStream fileInputStream = new FileInputStream(f);
	  //创建Deployment，同时部署流程定义
	  repositoryService.createDeployment()
	   .addInputStream(fileName, fileInputStream)
	   .deploy();*/
	  //查看定义的流程（非必须，只是验证流程定义是否部署成功）
	  ProcessDefinition processDefinition=repositoryService.createProcessDefinitionQuery()
	    .processDefinitionKey("leave-dynamic-from")
	    .singleResult();
	  System.out.println("流程定义文件的key为："+processDefinition.getKey());
	   
	  //设置当前用户
	  String currentUserId="hen";
	  IdentityService identityService=activitiRule.getIdentityService();
	  identityService.setAuthenticatedUserId(currentUserId);
	   
	  //设置表单变量
	  SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	  Map<String,String> variables =new HashMap<String,String>();
	  Calendar ca=Calendar.getInstance();
	  String startDate = sdf.format(ca.getTime());
	  ca.add(Calendar.DAY_OF_MONTH, 2);
	  String endDate=sdf.format(ca.getTime());
	  variables.put("startDate", startDate);
	  variables.put("endDate", endDate);
	  variables.put("reason", "年假");
	   
	  // 3.当前用户启动流程实例（类似RuntimeService，只是这里带上了表单数据）
	  FormService formService=activitiRule.getFormService();
	 processInstance=
	    formService.submitStartFormData(processDefinition.getId(), variables);
	  System.out.println("流程实例的id="+processInstance.getId()+",流程实例的definitionId="+
	    processInstance.getProcessDefinitionId());
	   
	  //取得deptLeader的任务
	  TaskService taskService=activitiRule.getTaskService();
	  Task leaderTask=taskService.createTaskQuery().taskCandidateGroup("deptLeader").singleResult();
	  System.out.println("当前任务的名称是："+leaderTask.getName());
	  //审批当前任务（类似于TaskService的complete）
	  variables=new HashMap<String,String>();
	  variables.put("deptLeaderApprove", "true");
	  formService.submitTaskFormData(leaderTask.getId(), variables);
	   
	  //取得hr的任务
	  Task hrTask=taskService.createTaskQuery().taskCandidateGroup("hr").singleResult();
	  System.out.println("当前任务的名称是："+hrTask.getName());
	  //审批当前任务（类似于TaskService的complete）
	  variables=new HashMap<String,String>();
	  variables.put("hrApprove", "true");
	  formService.submitTaskFormData(hrTask.getId(), variables);
	   
	  //取得用户的销假任务
	  Task reportBackTask=taskService.createTaskQuery().taskAssignee(currentUserId).singleResult();
	  //提交任务
	  variables=new HashMap<String,String>();
	  variables.put("reportBackDate", endDate);
	  formService.submitTaskFormData(reportBackTask.getId(),variables);
	   
	  //以下是验证流程是否结束（非必须）
	  HistoryService historyService=activitiRule.getHistoryService();
	  HistoricProcessInstance historicProcessInstance=historyService
	    .createHistoricProcessInstanceQuery().finished().singleResult();
	  System.out.println("已执行的流程id："+historicProcessInstance.getId());
	  Map<String,Object> historyVariables=packageVariables(historyService,processInstance);
	  System.out.println(historyVariables.get("result"));
	   
	 }
	  
	 private Map<String,Object> packageVariables(HistoryService historyService,ProcessInstance processInstance){
	   
	  Map<String,Object> historyVariables=new HashMap<String,Object>();
	   
	  //在保存表单字段的同时会复制一份经过类型转换的值，并以字段的ID为变量名保存到数据库中，当然前提是要设置引擎的“history”属性为“full”级别
	  /*List<HistoricDetail> list=
	  historyService.createHistoricDetailQuery().processInstanceId(processInstance.getId()).list();
	  System.out.println("历史记录数"+list.size());
	  for(HistoricDetail historicDetail:list){
	   if(historicDetail instanceof HistoricFormProperty){
	    HistoricFormProperty field=(HistoricFormProperty) historicDetail;
	    historyVariables.put(field.getPropertyId(), field.getPropertyValue());
	    System.out.println("form field: taskId="+field.getTaskId()+", "
	      +field.getPropertyId()+"="+field.getPropertyValue());
	   }else if(historicDetail instanceof HistoricVariableUpdate){
	    HistoricVariableUpdate variable=(HistoricVariableUpdate) historicDetail;
	    historyVariables.put(variable.getVariableName(),variable.getValue());
	    System.out.println("variable: "+variable.getVariableName()+"="+variable.getValue());
	   }
	  }*/
	   
	   
	  /* 查询所有保存的变量:5.11版本之后，从表act_hi_varinst*/
	  List<HistoricVariableInstance> list = 
	    historyService.createHistoricVariableInstanceQuery().processInstanceId(processInstance.getId()).list();
	  for (HistoricVariableInstance variable : list) 
	  { 
	   historyVariables.put(variable.getVariableName(),variable.getValue());
	   System.out.println("variable: " + variable.getVariableName() + " = " + variable.getValue());
	   
	  }
	   
	   
	  /* 只读取表单变量:从表act_hi_detail*/
	  /*List<HistoricDetail> formProperties = 
	    historyService.createHistoricDetailQuery().processInstanceId(processInstance.getId())
	    .formProperties().list();
	  for (HistoricDetail historicDetail : formProperties) {  
	   HistoricFormProperty field = (HistoricFormProperty) historicDetail;  
	   historyVariables.put(field.getPropertyId(), field.getPropertyValue());
	   System.out.println("field id: " + field.getPropertyId() + ", value: " + field.getPropertyValue());
	  } */
	 
	  return historyVariables;
	 }
}
