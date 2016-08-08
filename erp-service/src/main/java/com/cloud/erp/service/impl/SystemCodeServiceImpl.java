/**
 * @Title:  SystemCodeServiceImpl.java
 * @Package:  com.cloud.erp.service.impl
 * @Description:  
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
 * @Description  
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
	
	@Override
	public List<SystemCode> findSystemCodeList(Integer id) {
		
		return systemCodeDao.findSystemCodeList(id);
	}

	
	@Override
	public List<TreeModel> findSystemCodeList() {
		
		return systemCodeDao.findSystemCodeList();
	}

	
	@Override
	public boolean persistenceSystemCodeDig(SystemCode systemCode,
			String permissionName, Integer codePid) {
		
		return systemCodeDao.persistenceSystemCodeDig(systemCode, permissionName, codePid);
	}

	
	@Override
	public boolean delSystemCode(Integer codeId) {
		
		return systemCodeDao.delSystemCode(codeId);
	}

	
	@Override
	public List<SystemCode> findSystemCodeByType(String codeMyid) {
		
		return systemCodeDao.findSystemCodeByType(codeMyid);
	}

}
