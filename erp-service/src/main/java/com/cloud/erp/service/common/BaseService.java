package com.cloud.erp.service.common;

import java.io.Serializable;


public interface BaseService{

	public Serializable save(Object object);

	public void delete(Object object);

	public void update(Object object);

	public void saveOrUpdate(Object object);
	
	public Object get(Class<?> clazz, Serializable id);
	
}
