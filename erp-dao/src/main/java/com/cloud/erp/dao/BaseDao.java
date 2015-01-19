package com.cloud.erp.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface BaseDao<T> {
	
	public Serializable save(T object);
	
	public void saveOrUpdate(T object);
	
	public void delete(T object);
	
	public void update(T object);
	
	public T get(String hql, Map<String, Object> params);
	
	public List<T> getForList(String hql, Map<String, Object> params);
	
}
