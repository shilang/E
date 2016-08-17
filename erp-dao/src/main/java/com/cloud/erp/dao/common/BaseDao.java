package com.cloud.erp.dao.common;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;

import com.cloud.erp.utils.PageUtil;

public interface BaseDao<T> {

	public Serializable save(T object) throws HibernateException;

	public void delete(T object) throws HibernateException;

	public void update(T object) throws HibernateException;

	public void saveOrUpdate(T object) throws HibernateException;

	public T get(Class<T> clazz, Serializable id) throws HibernateException;

	public T get(String hql, Map<String, Object> params) throws HibernateException;

	public List<?> findBySQL(String sql) throws HibernateException;
	
	public List<?> findBySQL(String sql, Map<String, Object> params) throws HibernateException;

	public void deleteToUpdate(T object) throws HibernateException;

	public List<T> find(String hql) throws HibernateException;

	public List<T> find(String hql, Map<String, Object> params) throws HibernateException;
	
	public List<T> find(String hql, Map<String, Object> params, PageUtil pageUtil) throws HibernateException;

	public List<T> find(String hql, Map<String, Object> params, Integer page,
			Integer rows) throws HibernateException;

	public Long count(String hql, Map<String, Object> params) throws HibernateException;
	
	public int executeUpdate(String hql) throws HibernateException;

}
