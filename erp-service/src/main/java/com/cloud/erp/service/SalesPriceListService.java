/**
 * @Title:  SalesPriceListService.java
 * @Package:  com.cloud.erp.service
 * @Description:  TODO
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年4月22日 上午10:29:25
 * @version:  v1.0
 *
 * History:
 * Date		Author		Version
 * ---------------------------------------------
 * <reasons>
 */
package com.cloud.erp.service;

import java.util.List;
import java.util.Map;

import com.cloud.erp.entities.table.SalesPriceList;
import com.cloud.erp.utils.PageUtil;


/**
 * @ClassName  SalesPriceListService
 * @Description  TODO
 * @author  bollen bollen@live.cn
 * @date  2015年4月22日 上午10:29:25
 *
 */
public interface SalesPriceListService {

	List<SalesPriceList> findSalesPriceList(Map<String, Object> params, PageUtil pageUtil);

	Long getCount(Map<String, Object> params, PageUtil pageUtil);
	
}
