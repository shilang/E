/**
 * @Title:  SystemParameterServiceImpl.java
 * @Package:  com.cloud.erp.service.impl
 * @Description:  TODO
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年3月31日 上午9:48:30
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

import com.cloud.erp.dao.SystemParameterDao;
import com.cloud.erp.entities.table.Parameter;
import com.cloud.erp.entities.viewmodel.ParameterModel;
import com.cloud.erp.service.SystemParameterService;

/**
 * @ClassName  SystemParameterServiceImpl
 * @Description  TODO
 * @author  bollen bollen@live.cn
 * @date  2015年3月31日 上午9:48:30
 *
 */
@Service("systemParameterService")
public class SystemParameterServiceImpl implements SystemParameterService {

	private SystemParameterDao systemParameterDao;
	
	/**
	 * @param systemParameterDao the systemParameterDao to set
	 */
	@Autowired
	public void setSystemParameterDao(SystemParameterDao systemParameterDao) {
		this.systemParameterDao = systemParameterDao;
	}
	/* (non-Javadoc)
	 * @see com.cloud.erp.service.SystemParameterService#findParameterList(java.lang.String)
	 */
	@Override
	public List<ParameterModel> findParameterList(String type) {
		// TODO Auto-generated method stub
		return systemParameterDao.findParameterList(type);
	}

	/* (non-Javadoc)
	 * @see com.cloud.erp.service.SystemParameterService#persistenceParameter(java.util.Map)
	 */
	@Override
	public boolean persistenceParameter(Map<String, List<Parameter>> map) {
		// TODO Auto-generated method stub
		return systemParameterDao.persistenceParameter(map);
	}

}
