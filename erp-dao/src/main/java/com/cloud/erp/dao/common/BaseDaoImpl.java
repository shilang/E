package com.cloud.erp.dao.common;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cloud.erp.utils.PageUtil;

/**
 * 
 * @author Bollen
 *
 * @param <T>
 */
@Repository("baseDao")
public class BaseDaoImpl<T> implements BaseDao<T> {

	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	// TODO sessionFactory configuration
	private Session getSession() throws HibernateException{
		return sessionFactory.getCurrentSession();
		//return sessionFactory.openSession();
	}
	
	private Query createHqlQuery(String hql, Map<String, Object> params){
		Query query = getSession().createQuery(hql);
		if (params != null && params.size() > 0) {
			query.setProperties(params);
		}
		return query;
	}

	@Override
	public Serializable save(T object) throws HibernateException{
		
		return getSession().save(object);
	}

	@Override
	public void delete(T object) throws HibernateException{
		getSession().delete(object);
	}

	@Override
	public void update(T object) throws HibernateException{
		getSession().update(object);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T get(Class<T> clazz, Serializable id) throws HibernateException{
		return (T) getSession().get(clazz, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> find(String hql, Map<String, Object> params) throws HibernateException{
		return createHqlQuery(hql, params).list();
	}

	@Override
	public List<?> findBySQL(String sql) throws HibernateException{
		return getSession().createSQLQuery(sql).list();
	}
	
	@Override
	public List<?> findBySQL(String sql, Map<String, Object> params) throws HibernateException{
		SQLQuery sqlQuery = getSession().createSQLQuery(sql);
		if(null != params && !params.isEmpty()){
			sqlQuery.setProperties(params);
		}
		return sqlQuery.list();
	}

	@Override
	public void saveOrUpdate(T object) throws HibernateException{
		getSession().saveOrUpdate(object);
	}

	@Override
	public T get(String hql, Map<String, Object> params) throws HibernateException{
		
		List<T> list = this.find(hql, params);
		if (list != null && list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

	@Override
	public void deleteToUpdate(T object) throws HibernateException{
		getSession().update(object);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> find(String hql, Map<String, Object> params, Integer page,
			Integer rows) throws HibernateException{
		Query q = createHqlQuery(hql, params);
		if(null != page && null != rows){
			if(page < 1){page = 1;}
			if(rows < 1){rows = 20;}
			q.setFirstResult((page - 1) * rows).setMaxResults(rows);
		}
		return q.list();
	}
	
	@Override
	public List<T> find(String hql, Map<String, Object> params,
			PageUtil pageUtil) throws HibernateException{
		if(null == pageUtil){
			pageUtil = new PageUtil();
		}
		return find(hql, params, pageUtil.getPage(), pageUtil.getRows());
	}

	@Override
	public Long count(String hql, Map<String, Object> params) throws HibernateException{
		return (Long) createHqlQuery(hql, params).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> find(String hql) throws HibernateException{
		return this.getSession().createQuery(hql).list();
	}

	@Override
	public int executeUpdate(String hql) throws HibernateException{
		return this.getSession().createQuery(hql).executeUpdate();
	}

}
