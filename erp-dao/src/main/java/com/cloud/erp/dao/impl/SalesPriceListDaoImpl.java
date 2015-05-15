/**
 * @Title:  SalesPriceListDaoImpl.java
 * @Package:  com.cloud.erp.dao.impl
 * @Description:  TODO
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年4月22日 上午9:16:31
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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cloud.erp.dao.BaseDao;
import com.cloud.erp.dao.SalesPriceListDao;
import com.cloud.erp.entities.table.SalesPriceList;
import com.cloud.erp.utils.Constants;
import com.cloud.erp.utils.PageUtil;

/**
 * @ClassName  SalesPriceListDaoImpl
 * @Description  TODO
 * @author  bollen bollen@live.cn
 * @date  2015年4月22日 上午9:16:31
 *
 */
@Repository("salesPriceListDao")
public class SalesPriceListDaoImpl implements SalesPriceListDao {

	private BaseDao<SalesPriceList> baseDao;
	
	/**
	 * @param baseDao the baseDao to set
	 */
	@Autowired
	public void setBaseDao(BaseDao<SalesPriceList> baseDao) {
		this.baseDao = baseDao;
	}
	/* (non-Javadoc)
	 * @see com.cloud.erp.dao.SalesPriceListDao#findSalesPriceList(com.sun.xml.internal.xsom.impl.scd.Iterators.Map, com.cloud.erp.utils.PageUtil)
	 */
	@Override
	public List<SalesPriceList> findSalesPriceList(Map<String, Object> params,
			PageUtil pageUtil) {
		// TODO Auto-generated method stub
		String hql = "from SalesPriceList t where t.status='A' ";
		hql += Constants.getSearchConditionsHQL("t", params);
		hql += Constants.getGradeSearchConditionsHQL("t", pageUtil);
		
		return baseDao.find(hql, params, pageUtil.getPage(), pageUtil.getRows());
	}

	/* (non-Javadoc)
	 * @see com.cloud.erp.dao.SalesPriceListDao#persistenceSalesPriceList(com.cloud.erp.entities.SalesPriceList)
	 */
	@Override
	public boolean persistenceSalesPriceList(SalesPriceList salesPriceList) {
		// TODO Auto-generated method stub	
		return false;
	}

	/* (non-Javadoc)
	 * @see com.cloud.erp.dao.SalesPriceListDao#delSalesPriceList(java.lang.Integer)
	 */
	@Override
	public boolean delSalesPriceList(Integer salesPriceListId) {
		// TODO Auto-generated method stub
		return false;
	}
	/* (non-Javadoc)
	 * @see com.cloud.erp.dao.SalesPriceListDao#getCount(java.util.Map, com.cloud.erp.utils.PageUtil)
	 */
	@Override
	public Long getCount(Map<String, Object> params, PageUtil pageUtil) {
		// TODO Auto-generated method stub
		String hql = "select count(*) from SalesPriceList t where t.status='A' ";
		hql += Constants.getSearchConditionsHQL("t", params);
		hql += Constants.getGradeSearchConditionsHQL("t", pageUtil);
		return baseDao.count(hql, params);
	}

}
