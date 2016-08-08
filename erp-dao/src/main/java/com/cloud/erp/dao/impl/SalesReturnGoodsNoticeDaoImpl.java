/**
\ * @Title:  SalesReturnGoodsNoticeDaoImpl.java
 * @Package:  com.cloud.erp.dao.impl
 * @Description:  
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年7月6日 下午4:00:01
 * @version:  v1.0
 *
 * History:
 * Date		Author		Version
 * ---------------------------------------------
 * <reasons>
 */
package com.cloud.erp.dao.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cloud.erp.dao.SalesOutStockNoticeDao;
import com.cloud.erp.dao.SalesReturnGoodsNoticeDao;
import com.cloud.erp.dao.common.GeneralDaoSupport;
import com.cloud.erp.dao.common.ReferenceDao;
import com.cloud.erp.dao.common.SingleEntryDaoSupport;
import com.cloud.erp.dao.common.StatusFields;
import com.cloud.erp.entities.shareentry.SalesShareEntry;
import com.cloud.erp.entities.table.SalesOutStock;
import com.cloud.erp.entities.table.SalesOutStockEntry;
import com.cloud.erp.exceptions.UpdateReferenceException;
import com.cloud.erp.utils.Constants;
import com.cloud.erp.utils.PageUtil;

/**
 * @ClassName  SalesReturnGoodsNoticeDaoImpl
 * @Description  
 * @author  bollen bollen@live.cn
 * @date  2015年7月6日 下午4:00:01
 *
 */
@Repository("salesReturnGoodsNoticeDao")
public class SalesReturnGoodsNoticeDaoImpl implements SalesReturnGoodsNoticeDao {
	
	private static final String BILL_TYPE = "and billType=" + Constants.SALES_OUT_STOCK_TYPE_IN;

	@Resource
	private GeneralDaoSupport<SalesOutStock> generalDao;
	
	@Resource
	private SingleEntryDaoSupport<SalesOutStock> singleEntryDao;
	
	@Resource
	private ReferenceDao<SalesOutStock> referenceDao;
	
	@Autowired
	private SalesOutStockNoticeDao salesOutStockNoticeDao;

	@Override
	public List<SalesOutStock> findAll(Map<String, Object> params, PageUtil pageUtil) {
		return generalDao.findAll(this.getClass(), params, pageUtil, BILL_TYPE);
	}

	@Override
	public long getCount(Map<String, Object> params) {
		return generalDao.getCount(this.getClass(), params, BILL_TYPE);
	}

	@Override
	public SalesOutStock get(Integer id) {
		return generalDao.get(SalesOutStock.class, id);
	}

	@Override
	public void update(SalesOutStock master) {
		generalDao.update(master);
	}

	@Override
	public boolean persistence(SalesOutStock master) throws Exception {
		return generalDao.persistence(master, new StatusFields());
	}

	@Override
	public boolean deleteToUpdate(Integer pid) {
		return generalDao.deleteToUpdate(SalesOutStock.class, pid, new StatusFields());
	}

	@Override
	public <E> List<E> findEntriesById(Integer pid, Class<E> entry) {
		return singleEntryDao.findEntriesById(SalesOutStock.class, pid, entry);
	}

	@Override
	public <E> boolean persistenceEntries(SalesOutStock master,
			Map<String, List<E>> entries) {
		return singleEntryDao.persistenceEntries(master, entries, new StatusFields());
	}

	@Override
	public boolean deleteToUpdateEntries(Integer pid) {
		return singleEntryDao.deleteToUpdateEntries(SalesOutStock.class, pid, new StatusFields());
	}

	@Override
	public boolean updateReference(Class<SalesOutStock> clazz, String number, boolean mode)
			throws UpdateReferenceException {
		return referenceDao.updateReference(clazz, number, mode);
	}
	
	@Override
	public List<SalesShareEntry> findSalesOutStockNoticeEntriesById(
			Integer parentId) {
		List<SalesOutStockEntry> salesOutStockEntries = salesOutStockNoticeDao.findEntriesById(parentId, SalesOutStockEntry.class);
		List<SalesShareEntry> salesShareEntries = new ArrayList<SalesShareEntry>();
		for(SalesOutStockEntry salesOutStockEntry : salesOutStockEntries){
			SalesShareEntry salesShareEntry = new SalesShareEntry();
			try {
				BeanUtils.copyProperties(salesShareEntry, salesOutStockEntry);
			} catch (IllegalAccessException | InvocationTargetException e) {
				e.printStackTrace();
				return null;
			}
			salesShareEntries.add(salesShareEntry);
		}
		return salesShareEntries;
	}
	
	@Override
	public boolean updateSalesOutStockNoticeReference(String number,
			boolean mode) throws UpdateReferenceException {
		return salesOutStockNoticeDao.updateReference(SalesOutStock.class, number, mode);
	}

}
