package org.activiti.designer.test;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.FormService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.ActivitiRule;
import org.junit.Rule;
import org.junit.Test;

import com.cloud.erp.activiti.model.AuditModel;
import com.cloud.erp.utils.Constants;

public class ProcessTestSalesOrderProcess {

	private String filename = "E:\\Develop\\OBTCloud\\cloud-erp\\erp-workflow\\src\\main\\resources\\diagrams\\sales-order-process\\sales-order-process.bpmn";

	@Rule
	public ActivitiRule activitiRule = new ActivitiRule();

	/**
	 * @throws Exception
	 */
	@Test
	public void startProcess() throws Exception {
		RepositoryService repositoryService = activitiRule.getRepositoryService();
		repositoryService.createDeployment().addInputStream("sales-order-process.bpmn20.xml",
				new FileInputStream(filename)).deploy();
		
		RuntimeService runtimeService = activitiRule.getRuntimeService();
		Map<String, Object> variables = new HashMap<>();
		variables.put(Constants.AUDIT_MODEL, new AuditModel());
		runtimeService.startProcessInstanceByKey("sales-order-process", variables);

		TaskService taskService = activitiRule.getTaskService();
		
		Task task =  taskService.createTaskQuery().singleResult();
		taskService.complete(task.getId());
		
		task =  taskService.createTaskQuery().singleResult();
		FormService formService = activitiRule.getFormService();
		Object renderedTaskForm = formService.getRenderedTaskForm(task.getId());
		
		System.out.println(renderedTaskForm);

	}
}