package com.cloud.erp.dao.common;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.cloud.erp.utils.PageUtil;

public interface BaseDao<T> {

	public Serializable save(T object);

	public void delete(T object);

	public void update(T object);

	public void saveOrUpdate(T object);

	public T get(Class<T> clazz, Serializable id);

	public T get(String hql, Map<String, Object> params);

	public List<?> findBySQL(String sql);
	
	public List<?> findBySQL(String sql, Map<String, Object> params);

	public void deleteToUpdate(T object);

	public List<T> find(String hql);

	public List<T> find(String hql, Map<String, Object> params);
	
	public List<T> find(String hql, Map<String, Object> params, PageUtil pageUtil);

	public List<T> find(String hql, Map<String, Object> params, Integer page,
			Integer rows);

	public Long count(String hql, Map<String, Object> params);
	
	public int executeUpdate(String hql);

}
