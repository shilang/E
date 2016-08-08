/**
 * @Title:  SalesOrderDaoImpl.java
 * @Package:  com.cloud.erp.dao.impl
 * @Description:  
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年6月23日 下午1:44:34
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

import com.cloud.erp.dao.SalesContractDao;
import com.cloud.erp.dao.SalesOrderDao;
import com.cloud.erp.dao.SalesPriceListDao;
import com.cloud.erp.dao.common.GeneralDaoSupport;
import com.cloud.erp.dao.common.ReferenceDao;
import com.cloud.erp.dao.common.SingleEntryDaoSupport;
import com.cloud.erp.dao.common.StatusFields;
import com.cloud.erp.entities.shareentry.SalesShareEntry;
import com.cloud.erp.entities.table.SalesContract;
import com.cloud.erp.entities.table.SalesContractEntry;
import com.cloud.erp.entities.table.SalesOrder;
import com.cloud.erp.entities.table.SalesPriceList;
import com.cloud.erp.entities.table.SalesPriceListEntry;
import com.cloud.erp.exceptions.UpdateReferenceException;
import com.cloud.erp.utils.PageUtil;

/**
 * @ClassName  SalesOrderDaoImpl
 * @Description  
 * @author  bollen bollen@live.cn
 * @date  2015年6月23日 下午1:44:34
 *
 */
@Repository("salesOrderDao")
public class SalesOrderDaoImpl implements SalesOrderDao {

	@Resource
	private GeneralDaoSupport<SalesOrder> generalDao;
	
	@Resource
	private SingleEntryDaoSupport<SalesOrder> singleEntryDao;
	
	@Resource
	private ReferenceDao<SalesOrder> referenceDao;
	
	@Autowired
	private SalesPriceListDao salesPriceListDao;
	
	@Autowired
	private SalesContractDao salesContractDao;

	@Override
	public List<SalesOrder> findAll(Map<String, Object> params, PageUtil pageUtil) {
		return generalDao.findAll(this.getClass(), params, pageUtil);
	}

	@Override
	public long getCount(Map<String, Object> params) {
		return generalDao.getCount(this.getClass(), params);
	}

	@Override
	public SalesOrder get(Integer id) {
		return generalDao.get(SalesOrder.class, id);
	}

	@Override
	public void update(SalesOrder master) {
		generalDao.update(master);
	}
	
	@Override
	public boolean persistence(SalesOrder master) throws Exception {
		return generalDao.persistence(master, new StatusFields());
	}

	@Override
	public boolean deleteToUpdate(Integer pid) {
		return generalDao.deleteToUpdate(SalesOrder.class, pid, new StatusFields());
	}

	@Override
	public <E> List<E> findEntriesById(Integer pid, Class<E> entry) {
		return singleEntryDao.findEntriesById(SalesOrder.class, pid, entry);
	}

	@Override
	public <E> boolean persistenceEntries(SalesOrder master,
			Map<String, List<E>> entries) {
		return singleEntryDao.persistenceEntries(master, entries, new StatusFields());
	}

	@Override
	public boolean deleteToUpdateEntries(Integer pid) {
		return singleEntryDao.deleteToUpdateEntries(SalesOrder.class, pid, new StatusFields());
	}

	@Override
	public boolean updateReference(Class<SalesOrder> clazz, String number, boolean mode)
			throws UpdateReferenceException {
		return referenceDao.updateReference(clazz, number, mode);
	}
	
	@Override
	public List<SalesShareEntry> findContractEntriesById(Integer contractId) {
		List<SalesContractEntry> salesContractEntries = salesContractDao.findEntriesById(contractId, SalesContractEntry.class);
		List<SalesShareEntry> salesOrderShareEntries = new ArrayList<SalesShareEntry>();
		for(SalesContractEntry salesContractEntry : salesContractEntries){
			SalesShareEntry salesOrderShareEntry = new SalesShareEntry();
			try {
				BeanUtils.copyProperties(salesOrderShareEntry, salesContractEntry);
			} catch (IllegalAccessException | InvocationTargetException e) {
				e.printStackTrace();
				return null;
			}
			salesOrderShareEntries.add(salesOrderShareEntry);
		}
		return salesOrderShareEntries;
	}

	@Override
	public List<SalesShareEntry> findPriceListEntriesById(Integer priceListId) {
		List<SalesPriceListEntry> salesPriceListEntries = salesPriceListDao.findEntriesById(priceListId, SalesPriceListEntry.class);
		List<SalesShareEntry> salesOrderShareEntries = new ArrayList<SalesShareEntry>();
		for(SalesPriceListEntry salesPriceListEntry : salesPriceListEntries){
			SalesShareEntry salesOrderShareEntry = new SalesShareEntry();
			try {
				BeanUtils.copyProperties(salesOrderShareEntry, salesPriceListEntry);
			} catch (IllegalAccessException | InvocationTargetException e) {
				e.printStackTrace();
				return null;
			}
			salesOrderShareEntries.add(salesOrderShareEntry);
		}
		return salesOrderShareEntries;
	}

	@Override
	public boolean updatePriceListReference(String number, boolean mode) throws UpdateReferenceException {
		return salesPriceListDao.updateReference(SalesPriceList.class, number, mode);
	}

	@Override
	public boolean updateContractReference(String number, boolean mode) throws UpdateReferenceException {
		return salesContractDao.updateReference(SalesContract.class, number, mode);
	}

}
