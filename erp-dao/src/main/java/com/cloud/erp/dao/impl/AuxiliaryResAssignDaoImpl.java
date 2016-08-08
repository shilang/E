/**
 * @Title:  AuxiliaryResAssignDaoImpl.java
 * @Package:  com.cloud.erp.dao.impl
 * @Description:  
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年5月15日 上午11:06:40
 * @version:  v1.0
 *
 * History:
 * Date		Author		Version
 * ---------------------------------------------
 * <reasons>
 */
package com.cloud.erp.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cloud.erp.dao.AuxiliaryResAssignDao;
import com.cloud.erp.dao.common.BaseDao;
import com.cloud.erp.entities.table.AuxiliaryResMessage;

/**
 * @ClassName  AuxiliaryResAssignDaoImpl
 * @Description  
 * @author  bollen bollen@live.cn
 * @date  2015年5月15日 上午11:06:40
 *
 */
@Repository("auxiliaryResAssignDao")
public class AuxiliaryResAssignDaoImpl implements AuxiliaryResAssignDao {

	private BaseDao<AuxiliaryResMessage> baseDao;
	
	/**
	 * @param baseDao the baseDao to set
	 */
	@Autowired
	public void setBaseDao(BaseDao<AuxiliaryResMessage> baseDao) {
		this.baseDao = baseDao;
	}
	@Override
	public List<AuxiliaryResMessage> findAuxiliaryResMessages(Integer resId) {
		String hql = "from AuxiliaryResMessage t where t.status='A' and t.resId=" + resId;
		return baseDao.find(hql);
	}
	@Override
	public long getCount(Integer resId) {
		String hql = "select count(*) from AuxiliaryResMessage t where t.status='A' and t.resId=" + resId;
		return baseDao.count(hql, null);
	}

}
