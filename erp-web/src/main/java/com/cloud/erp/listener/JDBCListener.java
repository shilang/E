package com.cloud.erp.listener;

import java.sql.DriverManager;
import java.util.Enumeration;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Application Lifecycle Listener implementation class JDBCListener
 *
 */
public class JDBCListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public JDBCListener() {
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent servletContextEvent)  { 
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent servletContextEvent)  { 
    	try{
    		com.mysql.jdbc.AbandonedConnectionCleanupThread.shutdown();
    	}catch(Throwable t){
    		Enumeration<java.sql.Driver> enumeration = DriverManager.getDrivers();
    		while(enumeration.hasMoreElements()){
    			try{
    				DriverManager.deregisterDriver(enumeration.nextElement());
    			}catch(Throwable e){
    				
    			}
    		}
    	}
    }
	
}
