/**
 * @Title:  FunctionServiceImpl.java
 * @Package:  com.cloud.erp.service.impl
 * @Description:  TODO
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年3月2日  下午5:05:26
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

import com.cloud.erp.dao.FunctionDao;
import com.cloud.erp.entities.Permission;
import com.cloud.erp.entities.viewmodel.TreeGridModel;
import com.cloud.erp.entities.viewmodel.TreeModel;
import com.cloud.erp.service.FunctionService;

/**
 * @ClassName  FunctionServiceImpl
 * @Description  TODO
 * @author  bollen bollen@live.cn
 * @date  2015年3月2日  下午5:05:26
 *
 */
@Service("functionService")
public class FunctionServiceImpl implements FunctionService {

	private FunctionDao functionDao;
	
	/**
	 * @param functionDao the functionDao to set
	 */
	@Autowired
	public void setFunctionDao(FunctionDao functionDao) {
		this.functionDao = functionDao;
	}
	/* (non-Javadoc)
	 * @see com.cloud.erp.service.FunctionService#findAllFunctionList(java.lang.Integer)
	 */
	@Override
	public List<TreeGridModel> findAllFunctionList(Integer pid) {
		// TODO Auto-generated method stub
		return functionDao.findAllFunctionList(pid);
	}

	/* (non-Javadoc)
	 * @see com.cloud.erp.service.FunctionService#delFunction(java.lang.Integer)
	 */
	@Override
	public boolean delFunction(Integer id) {
		// TODO Auto-generated method stub
		return functionDao.delFunction(id);
	}

	/* (non-Javadoc)
	 * @see com.cloud.erp.service.FunctionService#persistenceFunction(java.util.List)
	 */
	@Override
	public boolean persistenceFunction(List<Permission> list) {
		// TODO Auto-generated method stub
		return functionDao.persistenceFunction(list);
	}

	/* (non-Javadoc)
	 * @see com.cloud.erp.service.FunctionService#findAllFunctionList()
	 */
	@Override
	public List<TreeModel> findAllFunctionList() {
		// TODO Auto-generated method stub
		return functionDao.findAllFunctionList();
	}

	/* (non-Javadoc)
	 * @see com.cloud.erp.service.FunctionService#persistenceFunction(com.cloud.erp.entities.Permission)
	 */
	@Override
	public boolean persistenceFunction(Permission permission) {
		// TODO Auto-generated method stub
		return functionDao.persistenceFunction(permission);
	}

}
