package com.cloud.erp.activiti.test;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.test.ActivitiRule;
import org.junit.Rule;
import org.junit.Test;

public class AppTest {
	
	@Rule
	public ActivitiRule activitiRule;
	
	@Test
	public void test(){
		ProcessEngine processEngine = activitiRule.getProcessEngine();
		
		
		
		System.out.println(processEngine);
	}
}
