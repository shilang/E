/**
 * @Title:  SalesPriceDao.java
 * @Package:  com.cloud.erp.dao
 * @Description:  TODO
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年4月21日 下午2:24:42
 * @version:  v1.0
 *
 * History:
 * Date		Author		Version
 * ---------------------------------------------
 * <reasons>
 */
package com.cloud.erp.dao;

import java.util.List;

import com.cloud.erp.entities.SalesPriceList;

/**
 * @ClassName  SalesPriceDao
 * @Description  TODO
 * @author  bollen bollen@live.cn
 * @date  2015年4月21日 下午2:24:42
 *
 */
public interface SalesPriceDao {
	
	List<SalesPriceList> findSalesPriceList();
	
	boolean persistenceSalesPriceList();
}
