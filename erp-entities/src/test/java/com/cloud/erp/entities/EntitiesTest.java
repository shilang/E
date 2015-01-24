package com.cloud.erp.entities;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import junit.framework.TestCase;

public class EntitiesTest extends TestCase  {
	
	private SessionFactory sessionFactory = null;
	
	{
		Configuration configuration = new Configuration().configure();
		ServiceRegistry serviceRegistry = 
				new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		
	}
	
	public void test(){
		
	}
	
}
