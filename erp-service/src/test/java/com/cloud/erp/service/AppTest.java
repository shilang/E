package com.cloud.erp.service;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import com.cloud.erp.dao.impl.DepartmentDaoImpl;

import junit.framework.TestCase;

public class AppTest extends TestCase {
	
	private SessionFactory sessionFactory = null;
	
	{
		Configuration configuration = new Configuration().configure();
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);
	}

	public void test(){

		TestDepartmentService testDepartmentService = new TestDepartmentService();
		
		DepartmentDaoImpl departmentDaoImpl = (DepartmentDaoImpl)testDepartmentService.getDepartmentDao();
		departmentDaoImpl.setSessionFactory(sessionFactory);
		
		assertNotNull(testDepartmentService.getAllDepartments());
	}
	
}
