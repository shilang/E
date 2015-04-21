package com.cloud.erp.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cloud.erp.dao.BaseDao;

@Repository("baseDao")
public class BaseDaoImpl<T> implements BaseDao<T> {

	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public Serializable save(T object) {
		// TODO Auto-generated method stub
		return getSession().save(object);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cloud.erp.dao.BaseDao#delete(java.lang.Class,
	 * java.io.Serializable)
	 */
	@Override
	public void delete(T object) {
		// TODO Auto-generated method stub
		getSession().delete(object);
	}

	@Override
	public void update(T object) {
		// TODO Auto-generated method stub
		getSession().update(object);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T get(Class<T> clazz, Serializable id) {
		// TODO Auto-generated method stub
		return (T) getSession().get(clazz, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> find(String hql, Map<String, Object> params) {
		Query query = getSession().createQuery(hql);
		if (params != null && params.size() > 0) {
			query.setProperties(params);
		}

		return query.list();
	}

	@Override
	public List<?> findBySQL(String sql) {
		return getSession().createSQLQuery(sql).list();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cloud.erp.dao.BaseDao#saveOrUpdate(java.lang.Object)
	 */
	@Override
	public void saveOrUpdate(T object) {
		// TODO Auto-generated method stub
		getSession().saveOrUpdate(object);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cloud.erp.dao.BaseDao#get(java.lang.String, java.util.Map)
	 */
	@Override
	public T get(String hql, Map<String, Object> params) {
		// TODO Auto-generated method stub
		List<T> list = this.find(hql, params);
		if (list != null && list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cloud.erp.dao.BaseDao#deleteToUpdate(java.lang.Object)
	 */
	@Override
	public void deleteToUpdate(T object) {
		// TODO Auto-generated method stub
		getSession().update(object);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> find(String hql, Map<String, Object> params, Integer page,
			Integer rows) {
		// TODO Auto-generated method stub
		if (page == null || page < 1) {
			page = 1;
		}
		if (rows == null || rows < 1) {
			rows = 10;
		}
		Query q = this.getSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
	}

	@Override
	public Long count(String hql, Map<String, Object> params) {
		// TODO Auto-generated method stub
		Query q = this.getSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return (Long) q.uniqueResult();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cloud.erp.dao.BaseDao#find(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<T> find(String hql) {
		// TODO Auto-generated method stub
		return this.getSession().createQuery(hql).list();
	}
}
