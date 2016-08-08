/**
 * @Title:  CompanyInfoServiceImpl.java
 * @Package:  com.cloud.erp.service.impl
 * @Description:  
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
 * @Description 
 * @author bollen bollen@live.cn
 * @date 2015年4月28日 上午10:40:55
 *
 */
@Service("companyInfoService")
public class CompanyInfoServiceImpl implements CompanyInfoService {

	@Autowired
	private CompanyInfoDao companyInfoDao;

	@Override
	public List<CompanyInfo> findCompanyInfos() {
		return companyInfoDao.findAll(null, null);
	}

	@Override
	public long getCount() {
		return companyInfoDao.getCount(null);
	}

	@Override
	public List<CompanyInfo> findAll(Map<String, Object> params,
			PageUtil pageUtil) {
		return companyInfoDao.findAll(params, pageUtil);
	}

	@Override
	public long getCount(Map<String, Object> params) {
		return companyInfoDao.getCount(params);
	}

	@Override
	public CompanyInfo get(Integer id) {
		return companyInfoDao.get(id);
	}

	@Override
	public void update(CompanyInfo master) {
		companyInfoDao.update(master);
	}

	@Override
	public boolean persistence(CompanyInfo master) throws Exception {
		return companyInfoDao.persistence(master);
	}

	@Override
	public boolean deleteToUpdate(Integer pid) {
		return companyInfoDao.deleteToUpdate(pid);
	}

}
