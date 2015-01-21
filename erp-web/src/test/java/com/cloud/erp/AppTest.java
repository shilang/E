package com.cloud.erp;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import junit.framework.TestCase;

public class AppTest extends TestCase {

	public void testDataSource(){
		
		ApplicationContext ctx = new FileSystemXmlApplicationContext("classpath:applicationContext.xml");
		
	
		
		System.out.println(ctx.getBean("dataSource"));
		
		System.out.println(ctx.getBean("sessionFactory"));
	}
	
}
