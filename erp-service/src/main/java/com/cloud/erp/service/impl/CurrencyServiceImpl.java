/**
 * @Title:  CurrencyServiceImpl.java
 * @Package:  com.cloud.erp.service.impl
 * @Description:  
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年5月20日 下午2:28:54
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

import com.cloud.erp.dao.CurrencyDao;
import com.cloud.erp.entities.table.Currency;
import com.cloud.erp.service.CurrencyService;
import com.cloud.erp.utils.PageUtil;

/**
 * @ClassName  CurrencyServiceImpl
 * @Description  
 * @author  bollen bollen@live.cn
 * @date  2015年5月20日 下午2:28:54
 *
 */
@Service("currencyService")
public class CurrencyServiceImpl implements CurrencyService {

	@Autowired
	private CurrencyDao currencyDao;
	
	@Override
	public List<Currency> findCurrencies() {
		return currencyDao.findAll(null, null);
	}

	@Override
	public long getCount() {
		return currencyDao.getCount(null);
	}
	
	@Override
	public List<Currency> findAll(Map<String, Object> params, PageUtil pageUtil) {
		return currencyDao.findAll(params, pageUtil);
	}
	@Override
	public long getCount(Map<String, Object> params) {
		return currencyDao.getCount(params);
	}
	@Override
	public Currency get(Integer id) {
		return currencyDao.get(id);
	}
	@Override
	public void update(Currency master) {
		currencyDao.update(master);
	}
	@Override
	public boolean persistence(Currency master) throws Exception {
		return currencyDao.persistence(master);
	}
	@Override
	public boolean deleteToUpdate(Integer pid) {
		return currencyDao.deleteToUpdate(pid);
	}

}
