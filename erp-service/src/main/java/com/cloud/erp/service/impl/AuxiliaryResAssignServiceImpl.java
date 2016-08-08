/**
 * @Title:  AuxiliaryResAssignServiceImpl.java
 * @Package:  com.cloud.erp.service.impl
 * @Description:  
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年5月15日 上午11:10:47
 * @version:  v1.0
 *
 * History:
 * Date		Author		Version
 * ---------------------------------------------
 * <reasons>
 */
package com.cloud.erp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloud.erp.dao.AuxiliaryResAssignDao;
import com.cloud.erp.entities.table.AuxiliaryResMessage;
import com.cloud.erp.service.AuxiliaryResAssignService;

/**
 * @ClassName  AuxiliaryResAssignServiceImpl
 * @Description  
 * @author  bollen bollen@live.cn
 * @date  2015年5月15日 上午11:10:47
 *
 */
@Service("auxiliaryResAssignService")
public class AuxiliaryResAssignServiceImpl implements AuxiliaryResAssignService {

	private AuxiliaryResAssignDao auxiliaryResAssignDao;
	
	/**
	 * @param auxiliaryResAssignDao the auxiliaryResAssignDao to set
	 */
	@Autowired
	public void setAuxiliaryResAssignDao(
			AuxiliaryResAssignDao auxiliaryResAssignDao) {
		this.auxiliaryResAssignDao = auxiliaryResAssignDao;
	}

	@Override
	public List<AuxiliaryResMessage> findAuxiliaryResMessages(Integer resId) {
		return auxiliaryResAssignDao.findAuxiliaryResMessages(resId);
	}

	@Override
	public long getCount(Integer resId) {
		return auxiliaryResAssignDao.getCount(resId);
	}

}
