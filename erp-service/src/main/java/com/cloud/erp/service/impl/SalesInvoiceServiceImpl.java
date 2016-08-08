package com.cloud.erp.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloud.erp.dao.SalesInvoiceDao;
import com.cloud.erp.entities.shareentry.SalesShareEntry;
import com.cloud.erp.entities.table.ICSales;
import com.cloud.erp.entities.table.ICSalesEntry;
import com.cloud.erp.exceptions.NumberIncrementException;
import com.cloud.erp.exceptions.UpdateReferenceException;
import com.cloud.erp.service.SalesInvoiceService;
import com.cloud.erp.service.common.AutoNumber;
import com.cloud.erp.utils.Constants;
import com.cloud.erp.utils.PageUtil;

@Service("salesInoviceServiceImpl")
public class SalesInvoiceServiceImpl implements SalesInvoiceService {
	
	@Autowired
	private SalesInvoiceDao salesInvoiceDao;
	
	@Resource
	private AutoNumber<ICSalesEntry> autoNumber;

	@Override
	public List<ICSales> findAll(Map<String, Object> params, PageUtil pageUtil) {
		return salesInvoiceDao.findAll(params, pageUtil);
	}

	@Override
	public long getCount(Map<String, Object> params) {
		return salesInvoiceDao.getCount(params);
	}

	@Override
	public ICSales get(Integer id) {
		return salesInvoiceDao.get(id);
	}

	@Override
	public void update(ICSales master) {
		salesInvoiceDao.update(master);
	}

	@Override
	public boolean persistence(ICSales master,
			Map<String, List<ICSalesEntry>> entries) throws Exception {
		Integer id = master.getInterId();
		
		if(!(persistence(master) && persistenceEntries(master, entries))){
			return false;
		}
		
		if(null == id){
			//update number
			increment(Constants.NUMBER_SALES_INVOICE);
			
			//update reference
			updateReference(entries.get(Constants.ENTRY_LIST_TYPE_ADD), true);
		}
		
		return true;
	}
	
	@Override
	public boolean persistence(ICSales master) throws Exception {
		return salesInvoiceDao.persistence(master);
	}

	@Override
	public <E> boolean persistenceEntries(ICSales master,
			Map<String, List<E>> entries) {
		return salesInvoiceDao.persistenceEntries(master, entries);
	}

	@Override
	public boolean deleteToUpdateEntries(Integer pid) throws Exception {
		if(!salesInvoiceDao.deleteToUpdateEntries(pid)){
			return false;
		}
		updateReference(findEntriesById(pid, ICSalesEntry.class), false);
		return true;
	}

	@Override
	public boolean deleteToUpdateAll(Integer pid) throws Exception {
		return deleteToUpdate(pid) && deleteToUpdateEntries(pid);
	}

	@Override
	public boolean deleteToUpdate(Integer pid) {
		return salesInvoiceDao.deleteToUpdate(pid);
	}
	
	@Override
	public <E> List<E> findEntriesById(Integer pid, Class<E> entry) {
		return salesInvoiceDao.findEntriesById(pid, entry);
	}

	@Override
	public List<SalesShareEntry> findShareEntries(String entryType, Integer pid) {
		List<SalesShareEntry> shareEntries = null;
		if(Constants.SHARE_ENTRY_TYPE_SALES_ORDER.equals(entryType)){
			shareEntries = salesInvoiceDao.findSalesOrderEntriesById(pid);
		}else if(Constants.SHARE_ENTRY_TYPE_SALES_OUTSTOCK.equals(entryType)){
			shareEntries = salesInvoiceDao.findSalesOutStockEntriesById(pid);
		}else if(Constants.SHARE_ENTRY_TYPE_SALES_CONTRACT.equals(entryType)){
			shareEntries = salesInvoiceDao.findSalesContractEntriesByid(pid);
		}
		return shareEntries;
	}

	@Override
	public void updateReferenceFor(String number, boolean mode)
			throws UpdateReferenceException {
		if(isThisType(number, Constants.NUMBER_SALES_ORDER)){
			salesInvoiceDao.updateSalesOrderReference(number, mode);
		}else if(isThisType(number, Constants.NUMBER_SALES_OUT_STOCK)){
			salesInvoiceDao.updateSalesOutStockReference(number, mode);
		}else if(isThisType(number, Constants.NUMBER_SALES_CONTRACT)){
			salesInvoiceDao.updateSalesConstractReference(number, mode);
		}
	}

	@Override
	public void updateReference(List<ICSalesEntry> entries, boolean mode)
			throws UpdateReferenceException {
		String number = getNumber(entries);
		if(null != number){
			updateReferenceFor(number, mode);
		}
	}

	@Override
	public String getNumber(List<ICSalesEntry> list) {
		return autoNumber.getNumber(list);
	}

	@Override
	public boolean isThisType(String number, int classId) {
		return autoNumber.isThisType(number, classId);
	}

	@Override
	public void increment(Integer classId) throws NumberIncrementException {
		autoNumber.increment(classId);
	}

}