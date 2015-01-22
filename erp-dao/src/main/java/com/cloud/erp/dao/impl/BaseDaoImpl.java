package com.cloud.erp.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.cloud.erp.dao.IBaseDao;

public class BaseDaoImpl<T> implements IBaseDao<T> {

	private SessionFactory sessionFactory;
	
	private Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
	public Serializable save(T object) {
		return getSession().save(object);
	}

	public void saveOrUpdate(T object) {
		getSession().saveOrUpdate(object);
	}

	public void delete(T object) {
		getSession().delete(object);
	}

	public void update(T object) {
		getSession().update(object);
	}

	@SuppressWarnings("unchecked")
	public T get(String hql, Map<String, Object> params) {
		Query query = getSession().createQuery(hql);
		if(params != null && params.size() > 0){
			query.setProperties(params);
		}
		return (T) query.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<T> getForList(String hql, Map<String, Object> params) {
		Query query = getSession().createQuery(hql);
		if (params != null && params.size() > 0){
			query.setProperties(params);
		}
		return query.list();
	}

}
