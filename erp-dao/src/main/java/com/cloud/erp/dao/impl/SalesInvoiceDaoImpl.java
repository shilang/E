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
import com.cloud.erp.dao.SalesInvoiceDao;
import com.cloud.erp.dao.SalesOrderDao;
import com.cloud.erp.dao.SalesOutStockDao;
import com.cloud.erp.dao.common.GeneralDaoSupport;
import com.cloud.erp.dao.common.ReferenceDao;
import com.cloud.erp.dao.common.SingleEntryDaoSupport;
import com.cloud.erp.dao.common.StatusFields;
import com.cloud.erp.entities.shareentry.SalesShareEntry;
import com.cloud.erp.entities.table.ICSales;
import com.cloud.erp.entities.table.ICStockBill;
import com.cloud.erp.entities.table.ICStockBillEntry;
import com.cloud.erp.entities.table.SalesContract;
import com.cloud.erp.entities.table.SalesContractEntry;
import com.cloud.erp.entities.table.SalesOrder;
import com.cloud.erp.entities.table.SalesOrderEntry;
import com.cloud.erp.exceptions.UpdateReferenceException;
import com.cloud.erp.utils.PageUtil;

@Repository("salesInvoiceDao")
public class SalesInvoiceDaoImpl implements SalesInvoiceDao {
	
	@Resource
	private GeneralDaoSupport<ICSales> generalDao;
	
	@Resource
	private SingleEntryDaoSupport<ICSales> singleEntryDao;
	
	@Resource
	private ReferenceDao<ICSales> referenceDao;
	
	@Autowired
	private SalesOrderDao salesOrderDao;
	
	@Autowired
	private SalesOutStockDao salesOutStockDao;
	
	@Autowired
	private SalesContractDao salesContractDao;

	@Override
	public List<ICSales> findAll(Map<String, Object> params, PageUtil pageUtil) {
		return generalDao.findAll(this.getClass(), params, pageUtil);
	}

	@Override
	public long getCount(Map<String, Object> params) {
		return generalDao.getCount(this.getClass(), params);
	}

	@Override
	public ICSales get(Integer id) {
		return generalDao.get(ICSales.class, id);
	}

	@Override
	public void update(ICSales master) {
		generalDao.update(master);
	}

	@Override
	public boolean persistence(ICSales master) throws Exception {
		return generalDao.persistence(master, new StatusFields());
	}
	
	@Override
	public <E> boolean persistenceEntries(ICSales master,
			Map<String, List<E>> entries) {
		return singleEntryDao.persistenceEntries(master, entries, new StatusFields());
	}

	@Override
	public boolean deleteToUpdate(Integer pid) {
		return generalDao.deleteToUpdate(ICSales.class, pid, new StatusFields());
	}

	@Override
	public <E> List<E> findEntriesById(Integer pid, Class<E> entry) {
		return singleEntryDao.findEntriesById(ICSales.class, pid, entry);
	}
	
	@Override
	public boolean deleteToUpdateEntries(Integer pid) {
		return singleEntryDao.deleteToUpdateEntries(ICSales.class, pid, new StatusFields());
	}

	@Override
	public boolean updateReference(Class<ICSales> clazz, String number, boolean mode)
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
	public List<SalesShareEntry> findSalesOutStockEntriesById(Integer id) {
		List<ICStockBillEntry> salesOutStockEntries = salesOutStockDao.findEntriesById(id, ICStockBillEntry.class);
		List<SalesShareEntry> salesShareEntries = new ArrayList<SalesShareEntry>();
		for(ICStockBillEntry icStockBillEntry : salesOutStockEntries){
			SalesShareEntry salesShareEntry = new SalesShareEntry();
			try {
				BeanUtils.copyProperties(salesShareEntry, icStockBillEntry);
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
	public boolean updateSalesOrderReference(String number, boolean mode) throws UpdateReferenceException {
		return salesOrderDao.updateReference(SalesOrder.class, number, mode);
	}

	@Override
	public boolean updateSalesOutStockReference(String number, boolean mode) throws UpdateReferenceException {
		return salesOutStockDao.updateReference(ICStockBill.class, number, mode);
	}

	@Override
	public boolean updateSalesConstractReference(String number, boolean mode) throws UpdateReferenceException {
		return salesContractDao.updateReference(SalesContract.class, number, mode);
	}

}