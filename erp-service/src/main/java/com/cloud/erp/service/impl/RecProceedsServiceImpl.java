package com.cloud.erp.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloud.erp.dao.RecProceedsDao;
import com.cloud.erp.dao.exception.NumberIncrementException;
import com.cloud.erp.dao.exception.UpdateReferenceException;
import com.cloud.erp.entities.shareentry.SalesShareEntry;
import com.cloud.erp.entities.table.RecProceeds;
import com.cloud.erp.entities.table.RecProceedsEntry;
import com.cloud.erp.service.RecProceedsService;
import com.cloud.erp.service.common.AutoNumber;
import com.cloud.erp.utils.Constants;
import com.cloud.erp.utils.PageUtil;

@Service("recProceedsServiceImpl")
public class RecProceedsServiceImpl implements RecProceedsService {
	
	@Autowired
	private RecProceedsDao recProceedsDao;
	
	@Resource
	private AutoNumber<RecProceedsEntry> autoNumber;
	
	@Override
	public List<RecProceeds> findAll(Map<String, Object> params,
			PageUtil pageUtil) {
		return recProceedsDao.findAll(params, pageUtil);
	}

	@Override
	public long getCount(Map<String, Object> params) {
		return recProceedsDao.getCount( params);
	}

	@Override
	public RecProceeds get(Integer id) {
		return recProceedsDao.get(id);
	}

	@Override
	public void update(RecProceeds master) {
		recProceedsDao.update(master);
	}
	
	@Override
	public boolean persistence(RecProceeds master) throws Exception {
		return recProceedsDao.persistence(master);
	}

	@Override
	public <E> boolean persistenceEntries(RecProceeds master,
			Map<String, List<E>> entries) {
		return recProceedsDao.persistenceEntries(master, entries);
	}
	
	@Override
	public boolean persistence(RecProceeds recProceeds,
			Map<String, List<RecProceedsEntry>> entries) throws Exception {
		Integer id = recProceeds.getInterId();
		
		if(!(persistence(recProceeds) && persistenceEntries(recProceeds, entries))){
			return false;
		}
		
		if(null == id){
			
			increment(Constants.NUMBER_REC_PROCEEDS);
			
			updateReference(entries.get(Constants.ENTRY_LIST_TYPE_ADD), true);
		}
		
		return true;
	}

	@Override
	public boolean deleteToUpdateEntries(Integer pid) throws Exception {
		if(!recProceedsDao.deleteToUpdateEntries(pid)){
			return false;
		}
		updateReference(findEntriesById(pid, RecProceedsEntry.class), false);
		return true;
	}

	@Override
	public boolean deleteToUpdateAll(Integer pid) throws Exception {
		return deleteToUpdate(pid) && deleteToUpdateEntries(pid);
	}

	@Override
	public boolean deleteToUpdate(Integer pid) {
		return recProceedsDao.deleteToUpdate(pid);
	}
	
	@Override
	public <E> List<E> findEntriesById(Integer pid, Class<E> entry) {
		return recProceedsDao.findEntriesById(pid, entry);
	}

	@Override
	public String getNumber(List<RecProceedsEntry> list) {
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


	@Override
	public List<SalesShareEntry> findShareEntries(String entryType, Integer pid) {
		List<SalesShareEntry> shareEntries = null;
		if(Constants.SHARE_ENTRY_TYPE_SALES_ORDER.equals(entryType)){
			shareEntries = recProceedsDao.findSalesOrderEntriesById(pid);
		}else if(Constants.SHARE_ENTRY_TYPE_SALES_INVOICE.equals(entryType)){
			shareEntries = recProceedsDao.findSalesInvoiceEntriesById(pid);
		}else if(Constants.SHARE_ENTRY_TYPE_SALES_CONTRACT.equals(entryType)){
			shareEntries = recProceedsDao.findSalesContractEntriesByid(pid);
		}
		return shareEntries;
	}

	@Override
	public void updateReferenceFor(String number, boolean mode)
			throws UpdateReferenceException {
		if(isThisType(number, Constants.NUMBER_SALES_ORDER)){
			recProceedsDao.updateSalesOrderReference(number, mode);
		}else if(isThisType(number, Constants.NUMBER_SALES_INVOICE)){
			recProceedsDao.updateSalesInvoiceReference(number, mode);
		}else if(isThisType(number, Constants.NUMBER_SALES_CONTRACT)){
			recProceedsDao.updateSalesConstractReference(number, mode);
		}
	}

	@Override
	public void updateReference(List<RecProceedsEntry> entries, boolean mode)
			throws UpdateReferenceException {
		String number = getNumber(entries);
		if(null != number){
			updateReferenceFor(number, mode);
		}
	}
	
}
