/**
 * @Title:  AuxiliaryResTypeServiceImpl.java
 * @Package:  com.cloud.erp.service.impl
 * @Description:  TODO
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年4月30日 下午2:33:53
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

import com.cloud.erp.dao.AuxiliaryResTypeDao;
import com.cloud.erp.entities.table.AuxiliaryResType;
import com.cloud.erp.service.AuxiliaryResTypeService;

/**
 * @ClassName  AuxiliaryResTypeServiceImpl
 * @Description  TODO
 * @author  bollen bollen@live.cn
 * @date  2015年4月30日 下午2:33:53
 *
 */
@Service("auxiliaryResTypeService")
public class AuxiliaryResTypeServiceImpl implements AuxiliaryResTypeService {

	private AuxiliaryResTypeDao auxiliaryResTypeDao;
	
	/**
	 * @param auxiliaryResTypeDao the auxiliaryResTypeDao to set
	 */
	@Autowired
	public void setAuxiliaryResTypeDao(AuxiliaryResTypeDao auxiliaryResTypeDao) {
		this.auxiliaryResTypeDao = auxiliaryResTypeDao;
	}
	/* (non-Javadoc)
	 * @see com.cloud.erp.service.AuxiliaryResTypeService#findAuxiliaryResTypes(java.util.Map)
	 */
	@Override
	public List<AuxiliaryResType> findAuxiliaryResTypes() {
		// TODO Auto-generated method stub
		return auxiliaryResTypeDao.findAuxiliaryResTypes();
	}

	/* (non-Javadoc)
	 * @see com.cloud.erp.service.AuxiliaryResTypeService#persistenceAuxiliaryResType(com.cloud.erp.entities.table.AuxiliaryResType)
	 */
	@Override
	public boolean persistenceAuxiliaryResType(AuxiliaryResType auxiliaryResType) {
		// TODO Auto-generated method stub
		return auxiliaryResTypeDao.persistenceAuxiliaryResType(auxiliaryResType);
	}

	/* (non-Javadoc)
	 * @see com.cloud.erp.service.AuxiliaryResTypeService#delAuxiliaryResType(java.lang.Integer)
	 */
	@Override
	public boolean delAuxiliaryResType(Integer id) {
		// TODO Auto-generated method stub
		return auxiliaryResTypeDao.delAuxiliaryResType(id);
	}

}
