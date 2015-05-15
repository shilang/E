/**
 * @Title:  AppTest.java
 * @Package:  com.cloud.erp.entities
 * @Description:  TODO
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年4月20日 下午2:06:39
 * @version:  v1.0
 *
 * History:
 * Date		Author		Version
 * ---------------------------------------------
 * <reasons>
 */
package com.cloud.erp.entities;

import junit.framework.TestCase;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cloud.erp.entities.table.Log;

/**
 * @ClassName  AppTest
 * @Description  TODO
 * @author  bollen bollen@live.cn
 * @date  2015年4月20日 下午2:06:39
 *
 */
public class AppTest extends TestCase {

	private SessionFactory sessionFactory ;
	
	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:applicationContext*.xml");
		sessionFactory = ctx.getBean(SessionFactory.class);
	}

	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		super.tearDown();
	}

	public void test(){
		
		Session session = sessionFactory.openSession();
		Transaction transaction =  session.beginTransaction();
		
		transaction.commit();
		
	}
}
