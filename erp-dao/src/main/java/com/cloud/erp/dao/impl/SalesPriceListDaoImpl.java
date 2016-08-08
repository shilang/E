/**
\ * @Title:  SalesPriceListDaoImpl.java
 * @Package:  com.cloud.erp.dao.impl
 * @Description:  
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

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.cloud.erp.dao.SalesPriceListDao;
import com.cloud.erp.dao.common.GeneralDaoSupport;
import com.cloud.erp.dao.common.ReferenceDao;
import com.cloud.erp.dao.common.SingleEntryDaoSupport;
import com.cloud.erp.dao.common.StatusFields;
import com.cloud.erp.entities.table.SalesPriceList;
import com.cloud.erp.exceptions.UpdateReferenceException;
import com.cloud.erp.utils.PageUtil;

/**
 * @ClassName SalesPriceListDaoImpl
 * @Description 
 * @author bollen bollen@live.cn
 * @date 2015年4月22日 上午9:16:31
 *
 */
@Repository("salesPriceListDao")
public class SalesPriceListDaoImpl implements SalesPriceListDao {

	@Resource
	private GeneralDaoSupport<SalesPriceList> generalDao;
	
	@Resource
	private SingleEntryDaoSupport<SalesPriceList> singleEntryDao;
	
	@Resource
	private ReferenceDao<SalesPriceList> referenceDao;

	@Override
	public List<SalesPriceList> findAll(Map<String, Object> params, PageUtil pageUtil) {
		return generalDao.findAll(this.getClass(), params, pageUtil);
	}

	@Override
	public long getCount(Map<String, Object> params) {
		return generalDao.getCount(this.getClass(), params);
	}

	@Override
	public SalesPriceList get(Integer id) {
		return generalDao.get(SalesPriceList.class, id);
	}

	@Override
	public void update(SalesPriceList master) {
		generalDao.update(master);
	}

	@Override
	public boolean persistence(SalesPriceList master) throws Exception {
		return generalDao.persistence(master, new StatusFields());
	}

	@Override
	public boolean deleteToUpdate(Integer pid) {
		return generalDao.deleteToUpdate(SalesPriceList.class, pid, new StatusFields());
	}

	@Override
	public <E> List<E> findEntriesById(Integer pid, Class<E> entry) {
		return singleEntryDao.findEntriesById(SalesPriceList.class, pid, entry);
	}

	@Override
	public <E> boolean persistenceEntries(SalesPriceList master,
			Map<String, List<E>> entries) {
		return singleEntryDao.persistenceEntries(master, entries, new StatusFields());
	}

	@Override
	public boolean deleteToUpdateEntries(Integer pid) {
		return singleEntryDao.deleteToUpdateEntries(SalesPriceList.class, pid, new StatusFields());
	}

	@Override
	public boolean updateReference(Class<SalesPriceList> clazz, String number, boolean mode)
			throws UpdateReferenceException {
		return referenceDao.updateReference(clazz, number, mode);
	}
}
