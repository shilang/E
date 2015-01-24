package com.cloud.erp.logging;

import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;

public class LogUtil{
	
	public static Logger getLogger(Class<?> clazz){
		return Logger.getLogger(clazz);
	}
	
	public static Logger getLogger(String name){
		return Logger.getLogger(name);
	}
	
	public static Logger getLogger(String name, LoggerFactory factory){
		return Logger.getLogger(name,factory);
	}
}
