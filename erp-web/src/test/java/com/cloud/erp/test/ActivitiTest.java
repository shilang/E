package com.cloud.erp.test;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.TaskService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class ActivitiTest {

	@Autowired
	private ProcessEngine processEngine;
	
	@Autowired
	private TaskService taskService;
	
	@Test
	public void testDeployment(){	
		
		
	}
	
}
