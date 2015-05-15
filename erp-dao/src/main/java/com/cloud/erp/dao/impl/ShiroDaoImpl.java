/**
 * @Title:  ShiroDaoImpl.java
 * @Package:  com.cloud.erp.dao.impl
 * @Description:  TODO
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年2月3日  下午3:06:01
 * @version:  v1.0
 *
 * History:
 * Date		Author		Version
 * ---------------------------------------------
 * <reasons>
 */
package com.cloud.erp.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cloud.erp.dao.BaseDao;
import com.cloud.erp.dao.ShiroDao;
import com.cloud.erp.entities.table.User;

/**
 * @ClassName  ShiroDaoImpl
 * @Description  TODO
 * @author  bollen bollen@live.cn
 * @date  2015年2月3日  下午3:06:01
 *
 */

@SuppressWarnings("rawtypes")
@Repository("shiroDao")
public class ShiroDaoImpl implements ShiroDao {

	private BaseDao baseDao;
	
	/**
	 * @param baseDao the baseDao to set
	 */
	@Autowired
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}
	
	/* (non-Javadoc)
	 * @see com.cloud.erp.dao.ShiroDao#getPermissions()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getPermissions(boolean isAdmin, String username) {
		// TODO Auto-generated method stub
		String sql;
		if (isAdmin){
			sql = "SELECT p.PERMISSION_ID,p.MYID FROM PERMISSION AS p\n"
					+ "where p.STATUS='A' and p.TYPE='O' and p.ISUSED='Y'";
		}else {
			sql = "SELECT DISTINCT rp.PERMISSION_ID,p.MYID FROM\n"
					+ "ROLE_PERMISSION AS rp\n"
					+ "INNER JOIN ROLE AS r ON rp.ROLE_ID = r.ROLE_ID\n"
					+ "INNER JOIN USER_ROLE AS ur ON rp.ROLE_ID = ur.ROLE_ID\n"
					+ "INNER JOIN USERS AS u ON u.USER_ID = ur.USER_ID\n"
					+ "INNER JOIN PERMISSION AS p ON rp.PERMISSION_ID = p.PERMISSION_ID\n"
					+ "WHERE rp.STATUS='A' and r.STATUS='A' and ur.STATUS='A' and u.STATUS='A' and p.STATUS='A' and p.TYPE='O' and p.ISUSED='Y'\n"
					+ "and u.NAME ='" + username + "'";
		}
		return baseDao.findBySQL(sql);
	}

	/* (non-Javadoc)
	 * @see com.cloud.erp.dao.ShiroDao#getUser(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public User getUser(String username) {
		// TODO Auto-generated method stub
		String hql = "from User t where t.status='A' and t.name=:name";
		//return (User) getSession().createQuery(hql).setParameter("name", username).uniqueResult();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name", username);
		return (User)baseDao.get(hql, params);
	}

}
