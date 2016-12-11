package com.cloud.erp.service.common;

public interface CheckService {
	
	boolean commit(Class<?> clazz, Integer id);
	
	boolean check(Class<?> clazz, Integer id);
	
	boolean changeCommit(Class<?> clazz, Integer id);
	
	boolean cancelCheck(Class<?> clazz, Integer id, String cancelReason);
	
}
