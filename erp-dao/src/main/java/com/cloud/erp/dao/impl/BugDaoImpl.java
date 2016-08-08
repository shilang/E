/**
 * @Title:  BugDaoImpl.java
 * @Package:  com.cloud.erp.dao.impl
 * @Description:  
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年3月31日 上午10:21:03
 * @version:  v1.0
 *
 * History:
 * Date		Author		Version
 * ---------------------------------------------
 * <reasons>
 */
package com.cloud.erp.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.cloud.erp.dao.BugDao;
import com.cloud.erp.dao.common.GeneralDaoSupport;
import com.cloud.erp.dao.common.StatusFields;
import com.cloud.erp.entities.table.Bug;
import com.cloud.erp.utils.PageUtil;

/**
 * @ClassName BugDaoImpl
 * @Description 
 * @author bollen bollen@live.cn
 * @date 2015年3月31日 上午10:21:03
 *
 */
@Repository("bugDao")
public class BugDaoImpl implements BugDao {
	
    private final StatusFields statusFields = new StatusFields();
	
	{
		statusFields.setInterId("bugId");
	}
	
	@Resource
	private GeneralDaoSupport<Bug> generalDao;

	@Override
	public List<Bug> findAll(Map<String, Object> params, PageUtil pageUtil) {
		return generalDao.findAll(Bug.class, params, pageUtil);
	}

	@Override
	public long getCount(Map<String, Object> params) {
		return generalDao.getCount(Bug.class, params);
	}

	@Override
	public Bug get(Integer id) {
		return generalDao.get(Bug.class, id);
	}

	@Override
	public void update(Bug master) {
		generalDao.update(master);
	}

	@Override
	public boolean persistence(Bug master) throws Exception {
		return generalDao.persistence(master, statusFields);
	}

	@Override
	public boolean deleteToUpdate(Integer pid) {
		return generalDao.deleteToUpdate(Bug.class, pid, statusFields);
	}

}
