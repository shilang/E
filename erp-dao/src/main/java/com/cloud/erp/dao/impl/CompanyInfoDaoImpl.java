/**
 * @Title:  CompanyInfoDaoImpl.java
 * @Package:  com.cloud.erp.dao.impl
 * @Description:  
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年4月28日 上午11:16:47
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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cloud.erp.dao.CompanyInfoDao;
import com.cloud.erp.dao.common.BaseDao;
import com.cloud.erp.dao.common.GeneralDaoSupport;
import com.cloud.erp.dao.common.StatusFields;
import com.cloud.erp.entities.table.CompanyInfo;
import com.cloud.erp.utils.PageUtil;

/**
 * @ClassName  CompanyInfoDaoImpl
 * @Description  
 * @author  bollen bollen@live.cn
 * @date  2015年4月28日 上午11:16:47
 *
 */
@Repository("companyInfoDao")
public class CompanyInfoDaoImpl implements CompanyInfoDao {
	
	private final StatusFields statusFields = new StatusFields();
	
	{
		statusFields.setInterId("companyId");
	}
	
	@Resource
	private GeneralDaoSupport<CompanyInfo> generalDao;

	@Autowired
	private BaseDao<CompanyInfo> baseDao;

	@Override
	public List<CompanyInfo> findAll(Map<String, Object> params, PageUtil pageUtil) {
		
		return generalDao.findAll(CompanyInfo.class, params, pageUtil);
	}

	@Override
	public long getCount(Map<String, Object> params) {
		
		return generalDao.getCount(CompanyInfo.class, params);
	}

	@Override
	public CompanyInfo get(Integer id) {
		return generalDao.get(CompanyInfo.class, id);
	}

	@Override
	public void update(CompanyInfo master) {
		generalDao.update(master);
	}

	@Override
	public boolean persistence(CompanyInfo master) throws Exception {
		return generalDao.persistence(master, statusFields);
	}

	@Override
	public boolean deleteToUpdate(Integer pid) {
		return generalDao.deleteToUpdate(CompanyInfo.class, pid, statusFields);
	}

}
