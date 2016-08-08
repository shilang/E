package com.cloud.erp.dao.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cloud.erp.dao.RecProceedsDao;
import com.cloud.erp.dao.SalesContractDao;
import com.cloud.erp.dao.SalesInvoiceDao;
import com.cloud.erp.dao.SalesOrderDao;
import com.cloud.erp.dao.common.GeneralDaoSupport;
import com.cloud.erp.dao.common.ReferenceDao;
import com.cloud.erp.dao.common.SingleEntryDaoSupport;
import com.cloud.erp.dao.common.StatusFields;
import com.cloud.erp.entities.shareentry.SalesShareEntry;
import com.cloud.erp.entities.table.ICSales;
import com.cloud.erp.entities.table.ICSalesEntry;
import com.cloud.erp.entities.table.RecProceeds;
import com.cloud.erp.entities.table.SalesContract;
import com.cloud.erp.entities.table.SalesContractEntry;
import com.cloud.erp.entities.table.SalesOrder;
import com.cloud.erp.entities.table.SalesOrderEntry;
import com.cloud.erp.exceptions.UpdateReferenceException;
import com.cloud.erp.utils.PageUtil;

@Repository("recProceedsDao")
public class RecProceedsDaoImpl implements RecProceedsDao {
	
	private final StatusFields statusFields = new StatusFields();

	@Resource
	private GeneralDaoSupport<RecProceeds> generalDao;
	
	@Resource
	private SingleEntryDaoSupport<RecProceeds> singleEntryDao;
	
	@Resource
	private ReferenceDao<RecProceeds> referenceDao;
	
	@Autowired
	private SalesOrderDao salesOrderDao;
	
	@Autowired
	private SalesInvoiceDao salesInvoiceDao;
	
	@Autowired
	private SalesContractDao salesContractDao;
	
	@Override
	public List<RecProceeds> findAll(Map<String, Object> params, PageUtil pageUtil) {
		return generalDao.findAll(this.getClass(), params, pageUtil);
	}

	@Override
	public long getCount(Map<String, Object> params) {
		return generalDao.getCount(this.getClass(), params);
	}

	@Override
	public RecProceeds get(Integer id) {
		return generalDao.get(RecProceeds.class, id);
	}

	@Override
	public void update(RecProceeds master) {
		generalDao.update(master);
	}
	
	@Override
	public boolean persistence(RecProceeds master) throws Exception {
		return generalDao.persistence(master, statusFields);
	}
	
	@Override
	public <E> boolean persistenceEntries(RecProceeds master,
			Map<String, List<E>> entries) {
		return singleEntryDao.persistenceEntries(master, entries, statusFields);
	}
	
	@Override
	public boolean deleteToUpdate(Integer pid) {
		return generalDao.deleteToUpdate(RecProceeds.class, pid, statusFields);
	}
	
	@Override
	public boolean deleteToUpdateEntries(Integer pid) {
		return singleEntryDao.deleteToUpdateEntries(RecProceeds.class, pid, statusFields);
	}

	@Override
	public <E> List<E> findEntriesById(Integer pid, Class<E> entry) {
		return singleEntryDao.findEntriesById(RecProceeds.class, pid, entry);
	}

	@Override
	public boolean updateReference(Class<RecProceeds> clazz, String number, boolean mode)
			throws UpdateReferenceException {
		return referenceDao.updateReference(clazz, number, mode);
	}

	@Override
	public List<SalesShareEntry> findSalesOrderEntriesById(Integer id) {
		List<SalesOrderEntry> salesOrderEntries = salesOrderDao.findEntriesById(id, SalesOrderEntry.class);
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

	@Override
	public List<SalesShareEntry> findSalesInvoiceEntriesById(Integer id) {
		List<ICSalesEntry> salesInvoiceEntries = salesInvoiceDao.findEntriesById(id, ICSalesEntry.class);
		List<SalesShareEntry> salesShareEntries = new ArrayList<SalesShareEntry>();
		for(ICSalesEntry entry : salesInvoiceEntries){
			SalesShareEntry salesShareEntry = new SalesShareEntry();
			try {
				BeanUtils.copyProperties(salesShareEntry, entry);
			} catch (IllegalAccessException | InvocationTargetException e) {
				e.printStackTrace();
				return null;
			}
			salesShareEntries.add(salesShareEntry);
		}
		
		return salesShareEntries;
	}

	@Override
	public List<SalesShareEntry> findSalesContractEntriesByid(Integer id) {
		List<SalesContractEntry> salesContractEntries = salesContractDao.findEntriesById(id, SalesContractEntry.class);
		List<SalesShareEntry> salesShareEntries = new ArrayList<SalesShareEntry>();
		for(SalesContractEntry salesContractEntry : salesContractEntries){
			SalesShareEntry salesShareEntry = new SalesShareEntry();
			try {
				BeanUtils.copyProperties(salesShareEntry, salesContractEntry);
			} catch (IllegalAccessException | InvocationTargetException e) {
				e.printStackTrace();
				return null;
			}
			salesShareEntries.add(salesShareEntry);
		}
		return salesShareEntries;
	}

	@Override
	public boolean updateSalesOrderReference(String number, boolean mode)
			throws UpdateReferenceException {
		return salesOrderDao.updateReference(SalesOrder.class, number, mode);
	}

	@Override
	public boolean updateSalesInvoiceReference(String number, boolean mode)
			throws UpdateReferenceException {
		return salesInvoiceDao.updateReference(ICSales.class, number, mode);
	}

	@Override
	public boolean updateSalesConstractReference(String number, boolean mode)
			throws UpdateReferenceException {
		return salesContractDao.updateReference(SalesContract.class, number, mode);
	}

}
