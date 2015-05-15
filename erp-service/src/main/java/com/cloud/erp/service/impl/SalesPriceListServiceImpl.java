/**
 * @Title:  SalesPriceListServiceImpl.java
 * @Package:  com.cloud.erp.service.impl
 * @Description:  TODO
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年4月22日 上午10:31:42
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

import com.cloud.erp.dao.SalesPriceListDao;
import com.cloud.erp.entities.table.SalesPriceList;
import com.cloud.erp.service.SalesPriceListService;
import com.cloud.erp.utils.PageUtil;

/**
 * @ClassName  SalesPriceListServiceImpl
 * @Description  TODO
 * @author  bollen bollen@live.cn
 * @date  2015年4月22日 上午10:31:42
 *
 */
@Service("salesPriceListService")
public class SalesPriceListServiceImpl implements SalesPriceListService {

	private SalesPriceListDao salesPriceListDao;
	
	/**
	 * @param salesPriceListDao the salesPriceListDao to set
	 */
	@Autowired
	public void setSalesPriceListDao(SalesPriceListDao salesPriceListDao) {
		this.salesPriceListDao = salesPriceListDao;
	}
	@Override
	public List<SalesPriceList> findSalesPriceList(Map<String, Object> params,
			PageUtil pageUtil) {
		// TODO Auto-generated method stub
		return salesPriceListDao.findSalesPriceList(params, pageUtil);
	}
	/* (non-Javadoc)
	 * @see com.cloud.erp.service.SalesPriceListService#getCount(java.util.Map, com.cloud.erp.utils.PageUtil)
	 */
	@Override
	public Long getCount(Map<String, Object> params, PageUtil pageUtil) {
		// TODO Auto-generated method stub
		return salesPriceListDao.getCount(params, pageUtil);
	}

}
