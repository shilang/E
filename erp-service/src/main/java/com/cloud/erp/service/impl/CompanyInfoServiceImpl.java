/**
 * @Title:  CompanyInfoServiceImpl.java
 * @Package:  com.cloud.erp.service.impl
 * @Description:  TODO
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年4月28日 上午10:40:55
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

import com.cloud.erp.dao.CompanyInfoDao;
import com.cloud.erp.entities.table.CompanyInfo;
import com.cloud.erp.service.CompanyInfoService;
import com.cloud.erp.utils.PageUtil;

/**
 * @ClassName CompanyInfoServiceImpl
 * @Description TODO
 * @author bollen bollen@live.cn
 * @date 2015年4月28日 上午10:40:55
 *
 */
@Service("companyInfoService")
public class CompanyInfoServiceImpl implements CompanyInfoService {

	private CompanyInfoDao companyInfoDao;

	/**
	 * @param companyInfoDao
	 *            the companyInfoDao to set
	 */
	@Autowired
	public void setCompanyInfoDao(CompanyInfoDao companyInfoDao) {
		this.companyInfoDao = companyInfoDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cloud.erp.service.CompanyInfoService#findCompanyInfos()
	 */
	@Override
	public List<CompanyInfo> findCompanyInfos() {
		// TODO Auto-generated method stub
		return companyInfoDao.findCompanyInfos();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cloud.erp.service.CompanyInfoService#getCount()
	 */
	@Override
	public long getCount() {
		// TODO Auto-generated method stub
		return companyInfoDao.getCount();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cloud.erp.service.CompanyInfoService#persistenceCompanyInfo(com.cloud
	 * .erp.entities.table.CompanyInfo)
	 */
	@Override
	public boolean persistenceCompanyInfo(CompanyInfo companyInfo) {
		// TODO Auto-generated method stub
		return companyInfoDao.persistenceCompanyInfo(companyInfo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cloud.erp.service.CompanyInfoService#delCompanyInfo(java.lang.Integer
	 * )
	 */
	@Override
	public boolean delCompanyInfo(Integer companyId) {
		// TODO Auto-generated method stub
		return companyInfoDao.delCompanyInfo(companyId);
	}

}
