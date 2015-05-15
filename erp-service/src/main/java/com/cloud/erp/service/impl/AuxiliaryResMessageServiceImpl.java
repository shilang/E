/**
 * @Title:  AuxiliaryResMessageServiceImpl.java
 * @Package:  AuxiliaryResMessageService
 * @Description:  TODO
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年5月5日 上午11:14:16
 * @version:  v1.0
 *
 * History:
 * Date		Author		Version
 * ---------------------------------------------
 * <reasons>
 */
package com.cloud.erp.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloud.erp.dao.AuxiliaryResMessageDao;
import com.cloud.erp.entities.table.AuxiliaryResMessage;
import com.cloud.erp.service.AuxiliaryResMessageService;

/**
 * @ClassName  AuxiliaryResMessageServiceImpl
 * @Description  TODO
 * @author  bollen bollen@live.cn
 * @date  2015年5月5日 上午11:14:16
 *
 */
@Service("auxiliaryResMessageService")
public class AuxiliaryResMessageServiceImpl implements
		AuxiliaryResMessageService {

	private AuxiliaryResMessageDao auxiliaryResMessageDao;
	
	/**
	 * @param auxiliaryResMessageDao the auxiliaryResMessageDao to set
	 */
	@Autowired
	public void setAuxiliaryResMessageDao(
			AuxiliaryResMessageDao auxiliaryResMessageDao) {
		this.auxiliaryResMessageDao = auxiliaryResMessageDao;
	}
	/* (non-Javadoc)
	 * @see com.cloud.erp.service.AuxiliaryResMessageService#findAuxiliaryResMessages(java.util.Map)
	 */
	@Override
	public List<AuxiliaryResMessage> findAuxiliaryResMessages(Integer resId) {
		// TODO Auto-generated method stub
		return auxiliaryResMessageDao.findAuxiliaryResMessages(resId);
	}

	/* (non-Javadoc)
	 * @see com.cloud.erp.service.AuxiliaryResMessageService#persistenceAuxiliaryResMessage(com.cloud.erp.entities.table.AuxiliaryResMessage)
	 */
	@Override
	public boolean persistenceAuxiliaryResMessage(
			AuxiliaryResMessage auxiliaryResMessage) {
		// TODO Auto-generated method stub
		return auxiliaryResMessageDao.persistenceAuxiliaryResMessage(auxiliaryResMessage);
	}

	/* (non-Javadoc)
	 * @see com.cloud.erp.service.AuxiliaryResMessageService#delAuxiliaryResMessage(java.lang.Integer)
	 */
	@Override
	public boolean delAuxiliaryResMessage(Integer id) {
		// TODO Auto-generated method stub
		return auxiliaryResMessageDao.delAuxiliaryResMessage(id);
	}
	/* (non-Javadoc)
	 * @see com.cloud.erp.service.AuxiliaryResMessageService#getCount(java.lang.Integer)
	 */
	@Override
	public Long getCount(Integer resId) {
		// TODO Auto-generated method stub
		return auxiliaryResMessageDao.getCount(resId);
	}

}
