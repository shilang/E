/**
 * @Title:  SalesOutStockDaoImpl.java
 * @Package:  com.cloud.erp.dao.impl
 * @Description:  
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年6月23日 下午2:53:49
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

import com.cloud.erp.dao.SalesOrderDao;
import com.cloud.erp.dao.SalesOutStockDao;
import com.cloud.erp.dao.SalesOutStockNoticeDao;
import com.cloud.erp.dao.common.BaseDao;
import com.cloud.erp.dao.common.GeneralDaoSupport;
import com.cloud.erp.dao.common.ReferenceDao;
import com.cloud.erp.dao.common.SingleEntryDaoSupport;
import com.cloud.erp.dao.common.StatusFields;
import com.cloud.erp.entities.shareentry.SalesShareEntry;
import com.cloud.erp.entities.table.ICStockBill;
import com.cloud.erp.entities.table.SalesOrder;
import com.cloud.erp.entities.table.SalesOrderEntry;
import com.cloud.erp.entities.table.SalesOutStock;
import com.cloud.erp.entities.table.SalesOutStockEntry;
import com.cloud.erp.exceptions.UpdateReferenceException;
import com.cloud.erp.utils.PageUtil;

/**
 * @ClassName  SalesOutStockDaoImpl
 * @Description  
 * @author  bollen bollen@live.cn
 * @date  2015年6月23日 下午2:53:49
 *
 */
@Repository("salesOutStockDao")
public class SalesOutStockDaoImpl implements SalesOutStockDao {

	@Resource
	private GeneralDaoSupport<ICStockBill> generalDao;
	
	@Resource
	private SingleEntryDaoSupport<ICStockBill> singleEntryDao;
	
	@Resource
	private ReferenceDao<ICStockBill> referenceDao;
	
	@SuppressWarnings("rawtypes")
	@Autowired
	private BaseDao baseDao;
	
	@Autowired
	private SalesOutStockNoticeDao salesOutStockNoticeDao;
	@Autowired
	private SalesOrderDao salesOrderDao;
	
	@Override
	public List<ICStockBill> findAll(Map<String, Object> params, PageUtil pageUtil) {
		return generalDao.findAll(this.getClass(), params, pageUtil);
	}

	@Override
	public long getCount(Map<String, Object> params) {
		return generalDao.getCount(this.getClass(), params);
	}

	@Override
	public ICStockBill get(Integer id) {
		return generalDao.get(ICStockBill.class, id);
	}

	@Override
	public void update(ICStockBill master) {
		generalDao.update(master);
	}

	@Override
	public boolean persistence(ICStockBill master) throws Exception {
		return generalDao.persistence(master, new StatusFields());
	}

	@Override
	public boolean deleteToUpdate(Integer pid) {
		return generalDao.deleteToUpdate(ICStockBill.class, pid, new StatusFields());
	}

	@Override
	public <E> List<E> findEntriesById(Integer pid, Class<E> entry) {
		return singleEntryDao.findEntriesById(ICStockBill.class, pid, entry);
	}

	@Override
	public <E> boolean persistenceEntries(ICStockBill master,
			Map<String, List<E>> entries) {
		return singleEntryDao.persistenceEntries(master, entries, new StatusFields());
	}

	@Override
	public boolean deleteToUpdateEntries(Integer pid) {
		return singleEntryDao.deleteToUpdateEntries(ICStockBill.class, pid, new StatusFields());
	}

	@Override
	public boolean updateReference(Class<ICStockBill> clazz, String number, boolean mode)
			throws UpdateReferenceException {
		return referenceDao.updateReference(clazz, number, mode);
	}
	
	@Override
	public boolean updateSalesOutStockNoticeReference(String number, boolean mode) throws UpdateReferenceException {
		return salesOutStockNoticeDao.updateReference(SalesOutStock.class, number, mode);
	}

	@Override
	public boolean updateSalesOrderReference(String number, boolean mode) throws UpdateReferenceException {
		return salesOrderDao.updateReference(SalesOrder.class, number, mode);
	}
	
	@Override
	public List<SalesShareEntry> findSalesOutStockNoticeEntriesById(Integer noticeId) {
		List<SalesOutStockEntry> salesOutStockEntries = salesOutStockNoticeDao.findEntriesById(noticeId, SalesOutStockEntry.class);
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
	public List<SalesShareEntry> findSalesOrderEntriesById(Integer orderId) {
		List<SalesOrderEntry> salesOrderEntries = salesOrderDao.findEntriesById(orderId, SalesOrderEntry.class);
		List<SalesShareEntry> salesShareEntries = new ArrayList<SalesShareEntry>();
		for(SalesOrderEntry salesOrderEntry : salesOrderEntries){
			SalesShareEntry salesShareEntry = new SalesShareEntry();
			try {
				BeanUtils.copyProperties(salesShareEntry, salesOrderEntry);
			} catch (IllegalAccessException | InvocationTargetException e) {
				e.printStackTrace();
				return null;
			}
			salesShareEntries.add(salesShareEntry);
		}
		return salesShareEntries;
	}

}
