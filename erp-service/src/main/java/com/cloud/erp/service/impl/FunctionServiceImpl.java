/**
 * @Title:  FunctionServiceImpl.java
 * @Package:  com.cloud.erp.service.impl
 * @Description:  
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
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloud.erp.dao.FunctionDao;
import com.cloud.erp.entities.table.Permission;
import com.cloud.erp.service.FunctionService;
import com.cloud.erp.utils.PageUtil;

/**
 * @ClassName FunctionServiceImpl
 * @Description 
 * @author bollen bollen@live.cn
 * @date 2015年3月2日 下午5:05:26
 *
 */
@Service("functionService")
public class FunctionServiceImpl implements FunctionService {

	@Autowired
	private FunctionDao functionDao;

	@Override
	public List<Permission> findFunctionsById(Integer pid) {
		return functionDao.findFunctionsById(pid);
	}

	@Override
	public List<Permission> findFunctions() {
		return functionDao.findAllWithExtHql();
	}

	@Override
	public List<Permission> findAll(Map<String, Object> params,
			PageUtil pageUtil) {
		return functionDao.findAll(params, pageUtil);
	}

	@Override
	public long getCount(Map<String, Object> params) {
		return functionDao.getCount(params);
	}

	@Override
	public Permission get(Integer id) {
		return functionDao.get(id);
	}

	@Override
	public void update(Permission master) {
		functionDao.update(master);
	}

	@Override
	public boolean persistence(Permission master) throws Exception {
		return functionDao.persistence(master);
	}

	@Override
	public boolean deleteToUpdate(Integer pid) {
		return functionDao.deleteToUpdate(pid);
	}

}
