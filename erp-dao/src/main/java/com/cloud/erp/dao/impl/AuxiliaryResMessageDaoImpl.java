/**
 * @Title:  AuxiliaryResMessageDaoImpl.java
 * @Package:  com.cloud.erp.dao.impl
 * @Description:  TODO
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年5月5日 上午11:17:18
 * @version:  v1.0
 *
 * History:
 * Date		Author		Version
 * ---------------------------------------------
 * <reasons>
 */
package com.cloud.erp.dao.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cloud.erp.dao.AuxiliaryResMessageDao;
import com.cloud.erp.dao.BaseDao;
import com.cloud.erp.entities.table.AuxiliaryResMessage;
import com.cloud.erp.utils.Constants;

/**
 * @ClassName  AuxiliaryResMessageDaoImpl
 * @Description  TODO
 * @author  bollen bollen@live.cn
 * @date  2015年5月5日 上午11:17:18
 *
 */
@Repository("auxiliaryResMessageDao")
public class AuxiliaryResMessageDaoImpl implements AuxiliaryResMessageDao {

	private BaseDao<AuxiliaryResMessage> baseDao;
	
	/**
	 * @param baseDao the baseDao to set
	 */
	@Autowired
	public void setBaseDao(BaseDao<AuxiliaryResMessage> baseDao) {
		this.baseDao = baseDao;
	}
	/* (non-Javadoc)
	 * @see com.cloud.erp.dao.AuxiliaryResMessageDao#findAuxiliaryResMessages(java.util.Map)
	 */
	@Override
	public List<AuxiliaryResMessage> findAuxiliaryResMessages(Integer resId) {
		// TODO Auto-generated method stub
		String hql = " from AuxiliaryResMessage t where t.status='A' and t.resId=" + resId;
		return baseDao.find(hql);
	}

	/* (non-Javadoc)
	 * @see com.cloud.erp.dao.AuxiliaryResMessageDao#persistenceAuxiliaryResMessage(com.cloud.erp.entities.table.AuxiliaryResMessage)
	 */
	@Override
	public boolean persistenceAuxiliaryResMessage(
			AuxiliaryResMessage auxiliaryResMessage) {
		// TODO Auto-generated method stub
		Integer userId = Constants.getCurrentUser().getUserId();
		if(null == auxiliaryResMessage.getMessageId() || "".equals(auxiliaryResMessage.getMessageId())){
			auxiliaryResMessage.setCreated(new Date());
			auxiliaryResMessage.setCreater(userId);
			auxiliaryResMessage.setLastmod(new Date());
			auxiliaryResMessage.setModifier(userId);
			auxiliaryResMessage.setStatus(Constants.PERSISTENCE_STATUS);
			baseDao.save(auxiliaryResMessage);
		}else {
			auxiliaryResMessage.setLastmod(new Date());
			auxiliaryResMessage.setModifier(userId);
			baseDao.update(auxiliaryResMessage);
		}
		
		return true;
	}

	/* (non-Javadoc)
	 * @see com.cloud.erp.dao.AuxiliaryResMessageDao#delAuxiliaryResMessage(java.lang.Integer)
	 */
	@Override
	public boolean delAuxiliaryResMessage(Integer id) {
		// TODO Auto-generated method stub
		AuxiliaryResMessage auxiliaryResMessage = baseDao.get(AuxiliaryResMessage.class, id);
		auxiliaryResMessage.setLastmod(new Date());
		auxiliaryResMessage.setModifier(Constants.getCurrentUser().getUserId());
		auxiliaryResMessage.setStatus(Constants.PERSISTENCE_DELETE_STATUS);
		baseDao.deleteToUpdate(auxiliaryResMessage);
		
		return true;
	}
	/* (non-Javadoc)
	 * @see com.cloud.erp.dao.AuxiliaryResMessageDao#getCount(java.lang.Integer)
	 */
	@Override
	public Long getCount(Integer resId) {
		// TODO Auto-generated method stub
		String hql = " select count(*) from AuxiliaryResMessage t where t.status='A' and t.resId=" + resId;
		return baseDao.count(hql, null);
	}

}
