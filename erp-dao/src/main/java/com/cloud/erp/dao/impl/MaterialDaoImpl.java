/**
 * @Title:  MaterialDaoImpl.java
 * @Package:  com.cloud.erp.dao.impl
 * @Description:  
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年5月20日 下午4:24:04
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

import com.cloud.erp.dao.MaterialDao;
import com.cloud.erp.dao.common.GeneralDaoSupport;
import com.cloud.erp.dao.common.StatusFields;
import com.cloud.erp.entities.table.ICItemCore;
import com.cloud.erp.utils.PageUtil;

/**
 * @ClassName MaterialDaoImpl
 * @Description 
 * @author bollen bollen@live.cn
 * @date 2015年5月20日 下午4:24:04
 *
 */
@Repository("materialDao")
public class MaterialDaoImpl implements MaterialDao {
	
    private final StatusFields statusFields = new StatusFields();
	
	{
		statusFields.setInterId("itemId");
	}

	@Resource
	private GeneralDaoSupport<ICItemCore> generalDao;

	@Override
	public List<ICItemCore> findAll(Map<String, Object> params, PageUtil pageUtil) {
		return generalDao.findAll(ICItemCore.class, params, pageUtil);
	}

	@Override
	public long getCount(Map<String, Object> params) {
		return generalDao.getCount(ICItemCore.class, params);
	}

	@Override
	public ICItemCore get(Integer id) {
		return generalDao.get(ICItemCore.class, id);
	}

	@Override
	public void update(ICItemCore master) {
		generalDao.update(master);
	}

	@Override
	public boolean persistence(ICItemCore master) throws Exception {
		return generalDao.persistence(master, statusFields);
	}

	@Override
	public boolean deleteToUpdate(Integer pid) {
		return generalDao.deleteToUpdate(ICItemCore.class, pid, statusFields);
	}

}
