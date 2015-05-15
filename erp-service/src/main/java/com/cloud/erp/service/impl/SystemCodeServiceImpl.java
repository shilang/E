/**
 * @Title:  SystemCodeServiceImpl.java
 * @Package:  com.cloud.erp.service.impl
 * @Description:  TODO
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年3月30日 上午10:59:39
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

import com.cloud.erp.dao.SystemCodeDao;
import com.cloud.erp.entities.table.SystemCode;
import com.cloud.erp.entities.viewmodel.TreeModel;
import com.cloud.erp.service.SystemCodeService;

/**
 * @ClassName  SystemCodeServiceImpl
 * @Description  TODO
 * @author  bollen bollen@live.cn
 * @date  2015年3月30日 上午10:59:39
 *
 */
@Service("systemCodeService")
public class SystemCodeServiceImpl implements SystemCodeService {

	private SystemCodeDao systemCodeDao;
	
	/**
	 * @param systemCodeDao the systemCodeDao to set
	 */
	@Autowired
	public void setSystemCodeDao(SystemCodeDao systemCodeDao) {
		this.systemCodeDao = systemCodeDao;
	}
	/* (non-Javadoc)
	 * @see com.cloud.erp.service.SystemCodeService#findSystemCodeList(java.lang.Integer)
	 */
	@Override
	public List<SystemCode> findSystemCodeList(Integer id) {
		// TODO Auto-generated method stub
		return systemCodeDao.findSystemCodeList(id);
	}

	/* (non-Javadoc)
	 * @see com.cloud.erp.service.SystemCodeService#findSystemCodeList()
	 */
	@Override
	public List<TreeModel> findSystemCodeList() {
		// TODO Auto-generated method stub
		return systemCodeDao.findSystemCodeList();
	}

	/* (non-Javadoc)
	 * @see com.cloud.erp.service.SystemCodeService#persistenceSystemCodeDig(com.cloud.erp.entities.SystemCode, java.lang.String, java.lang.Integer)
	 */
	@Override
	public boolean persistenceSystemCodeDig(SystemCode systemCode,
			String permissionName, Integer codePid) {
		// TODO Auto-generated method stub
		return systemCodeDao.persistenceSystemCodeDig(systemCode, permissionName, codePid);
	}

	/* (non-Javadoc)
	 * @see com.cloud.erp.service.SystemCodeService#delSystemCode(java.lang.Integer)
	 */
	@Override
	public boolean delSystemCode(Integer codeId) {
		// TODO Auto-generated method stub
		return systemCodeDao.delSystemCode(codeId);
	}

	/* (non-Javadoc)
	 * @see com.cloud.erp.service.SystemCodeService#findSystemCodeByType(java.lang.String)
	 */
	@Override
	public List<SystemCode> findSystemCodeByType(String codeMyid) {
		// TODO Auto-generated method stub
		return systemCodeDao.findSystemCodeByType(codeMyid);
	}

}
