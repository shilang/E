/**
 * @Title:  CurrencyService.java
 * @Package:  com.cloud.erp.service
 * @Description:  
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年5月20日 下午2:25:53
 * @version:  v1.0
 *
 * History:
 * Date		Author		Version
 * ---------------------------------------------
 * <reasons>
 */
package com.cloud.erp.service;

import java.util.List;

import com.cloud.erp.entities.table.Currency;
import com.cloud.erp.service.common.GeneralService;

/**
 * @ClassName  CurrencyService
 * @Description  
 * @author  bollen bollen@live.cn
 * @date  2015年5月20日 下午2:25:53
 *
 */
public interface CurrencyService extends GeneralService<Currency>{

	List<Currency> findCurrencies();
	
	long getCount();
	
}
