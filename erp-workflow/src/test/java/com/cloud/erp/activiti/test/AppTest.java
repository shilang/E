package com.cloud.erp.activiti.test;

import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.test.ActivitiRule;
import org.activiti.engine.test.Deployment;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

public class AppTest {
	
	@Rule
	public ActivitiRule activitiRule = new ActivitiRule("activiti.cfg.xml");
	
	
	@Test
	@Deployment(resources = {"com/cloud/erp/activiti/test/mybpn.bpmn","com/cloud/erp/activiti/test/mybpn.png"})
	public void testCompensateEvent() throws Exception{
		ProcessInstance processInstance = 
				activitiRule.getRuntimeService().startProcessInstanceByKey("mybpn");
		Assert.assertNotNull(processInstance);
		
		System.out.println(processInstance.getProcessDefinitionId());
		
		Thread.sleep(10000);
		
		System.out.println("done");
		
		
	}
}
