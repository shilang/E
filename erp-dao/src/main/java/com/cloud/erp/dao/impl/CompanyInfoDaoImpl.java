/**
 * @Title:  CompanyInfoDaoImpl.java
 * @Package:  com.cloud.erp.dao.impl
 * @Description:  TODO
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

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cloud.erp.dao.BaseDao;
import com.cloud.erp.dao.CompanyInfoDao;
import com.cloud.erp.entities.table.CompanyInfo;
import com.cloud.erp.utils.Constants;
import com.cloud.erp.utils.PageUtil;

/**
 * @ClassName  CompanyInfoDaoImpl
 * @Description  TODO
 * @author  bollen bollen@live.cn
 * @date  2015年4月28日 上午11:16:47
 *
 */
@Repository("companyInfoDao")
public class CompanyInfoDaoImpl implements CompanyInfoDao {

	private BaseDao<CompanyInfo> baseDao;
	
	/**
	 * @param baseDao the baseDao to set
	 */
	@Autowired
	public void setBaseDao(BaseDao<CompanyInfo> baseDao) {
		this.baseDao = baseDao;
	}

	/* (non-Javadoc)
	 * @see com.cloud.erp.dao.CompanyInfoDao#findCompanyInfos()
	 */
	@Override
	public List<CompanyInfo> findCompanyInfos() {
		// TODO Auto-generated method stub
		String hql = "from CompanyInfo t where t.status='A'";
		return baseDao.find(hql);
	}

	/* (non-Javadoc)
	 * @see com.cloud.erp.dao.CompanyInfoDao#getCount()
	 */
	@Override
	public long getCount() {
		// TODO Auto-generated method stub
		String hql = "select count(*) from CompanyInfo t where t.status='A'";
		return baseDao.count(hql, null);
	}

	/* (non-Javadoc)
	 * @see com.cloud.erp.dao.CompanyInfoDao#persistenceCompanyInfo(com.cloud.erp.entities.table.CompanyInfo)
	 */
	@Override
	public boolean persistenceCompanyInfo(CompanyInfo companyInfo) {
		// TODO Auto-generated method stub
		Integer userId = Constants.getCurrentUser().getUserId();
		if(null == companyInfo.getCompanyId() || "".equals(companyInfo.getCompanyId())){
			companyInfo.setCreated(new Date());
			companyInfo.setCreater(userId);
			companyInfo.setLastmod(new Date());
			companyInfo.setModifier(userId);
			companyInfo.setStatus(Constants.PERSISTENCE_STATUS);
			baseDao.save(companyInfo);
		}else {
			companyInfo.setLastmod(new Date());
			companyInfo.setModifier(userId);
			baseDao.update(companyInfo);
		}
		
		return true;
	}

	/* (non-Javadoc)
	 * @see com.cloud.erp.dao.CompanyInfoDao#delCompanyInfo(java.lang.Integer)
	 */
	@Override
	public boolean delCompanyInfo(Integer companyId) {
		// TODO Auto-generated method stub
		CompanyInfo companyInfo = baseDao.get(CompanyInfo.class, companyId);
		companyInfo.setLastmod(new Date());
		companyInfo.setModifier(Constants.getCurrentUser().getUserId());
		companyInfo.setStatus(Constants.PERSISTENCE_DELETE_STATUS);
		baseDao.deleteToUpdate(companyInfo);
		
		return true;
	}

	

}
