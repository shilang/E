/**
 * @Title:  CurrencyDaoImpl.java
 * @Package:  com.cloud.erp.dao.impl
 * @Description:  
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年5月20日 下午1:36:25
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

import com.cloud.erp.dao.CurrencyDao;
import com.cloud.erp.dao.common.BaseDao;
import com.cloud.erp.dao.common.GeneralDaoSupport;
import com.cloud.erp.dao.common.StatusFields;
import com.cloud.erp.entities.table.Currency;
import com.cloud.erp.utils.PageUtil;

/**
 * @ClassName  CurrencyDaoImpl
 * @Description  
 * @author  bollen bollen@live.cn
 * @date  2015年5月20日 下午1:36:25
 *
 */
@Repository("currencyDao")
public class CurrencyDaoImpl implements CurrencyDao {
	
	private final StatusFields statusFields = new StatusFields();
	
	{
		statusFields.setInterId("currencyId");
	}
	
	@Resource
	private GeneralDaoSupport<Currency> generalDao;

	@Autowired
	private BaseDao<Currency> baseDao;

	@Override
	public List<Currency> findAll(Map<String, Object> params,
			PageUtil pageUtil) {
		return generalDao.findAll(Currency.class, params, pageUtil);
	}
	@Override
	public long getCount(Map<String, Object> params) {
		return generalDao.getCount(Currency.class, params);
	}
	@Override
	public Currency get(Integer id) {
		return generalDao.get(Currency.class, id);
	}
	@Override
	public void update(Currency master) {
		generalDao.update(master);
	}
	@Override
	public boolean persistence(Currency master) throws Exception {
		return generalDao.persistence(master, statusFields);
	}
	@Override
	public boolean deleteToUpdate(Integer pid) {
		return generalDao.deleteToUpdate(Currency.class, pid, statusFields);
	}

}
