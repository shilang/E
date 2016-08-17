/**
 * @Title:  SalesOutStockServiceImpl.java
 * @Package:  com.cloud.erp.service.impl
 * @Description:  
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年6月23日 下午3:12:07
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

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloud.erp.dao.SalesOutStockDao;
import com.cloud.erp.dao.exception.NumberIncrementException;
import com.cloud.erp.dao.exception.UpdateReferenceException;
import com.cloud.erp.entities.shareentry.SalesShareEntry;
import com.cloud.erp.entities.table.ICStockBill;
import com.cloud.erp.entities.table.ICStockBillEntry;
import com.cloud.erp.service.SalesOutStockService;
import com.cloud.erp.service.common.AutoNumber;
import com.cloud.erp.utils.Constants;
import com.cloud.erp.utils.PageUtil;

/**
 * @ClassName  SalesOutStockServiceImpl
 * @Description  
 * @author  bollen bollen@live.cn
 * @date  2015年6月23日 下午3:12:07
 *
 */
@Service("salesOutStockServiceImpl")
public class SalesOutStockServiceImpl implements SalesOutStockService {
	
	@Autowired
	private SalesOutStockDao salesOutStockDao;

	@Resource
	private AutoNumber<ICStockBillEntry> autoNumber;

	@Override
	public List<ICStockBill> findAll(Map<String, Object> params,
			PageUtil pageUtil) {
		return salesOutStockDao.findAll(params, pageUtil);
	}

	@Override
	public long getCount(Map<String, Object> params) {
		return salesOutStockDao.getCount(params);
	}

	@Override
	public ICStockBill get(Integer id) {
		return salesOutStockDao.get(id);
	}

	@Override
	public void update(ICStockBill master) {
		salesOutStockDao.update(master);
	}
	
	@Override
	public boolean persistence(ICStockBill master) throws Exception {
		return salesOutStockDao.persistence(master);
	}

	@Override
	public <E> boolean persistenceEntries(ICStockBill master,
			Map<String, List<E>> entries) {
		return salesOutStockDao.persistenceEntries(master, entries);
	}

	@Override
	public boolean persistence(ICStockBill icStockBill,
			Map<String, List<ICStockBillEntry>> entries) throws Exception {
		Integer id = icStockBill.getInterId();
		
		if(!(persistence(icStockBill) && persistenceEntries(icStockBill, entries))){
			return false;
		}
		
		if(null == id){
			
			increment(Constants.NUMBER_SALES_OUT_STOCK);
			
			updateReference(entries.get(Constants.ENTRY_LIST_TYPE_ADD), true);
		}
		
		return true;
	}

	@Override
	public boolean deleteToUpdateEntries(Integer pid) throws Exception {
		if(!salesOutStockDao.deleteToUpdateEntries(pid)){
			return false;
		}
		updateReference(findEntriesById(pid, ICStockBillEntry.class), false);
		return true;
	}

	@Override
	public boolean deleteToUpdate(Integer pid) {
		return salesOutStockDao.deleteToUpdate(pid);
	}
	
	@Override
	public boolean deleteToUpdateAll(Integer pid) throws Exception {
		return deleteToUpdate(pid) && deleteToUpdateEntries(pid);
	}

	@Override
	public String getNumber(List<ICStockBillEntry> list) {
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
	public <E> List<E> findEntriesById(Integer pid, Class<E> entry) {
		return salesOutStockDao.findEntriesById(pid, entry);
	}

	@Override
	public List<SalesShareEntry> findShareEntries(String entryType, Integer pid) {
		List<SalesShareEntry> salesShareEntries = null;
		if(Constants.SHARE_ENTRY_TYPE_SALES_OUTNOTICE.equals(entryType)){
			salesShareEntries = salesOutStockDao.findSalesOutStockNoticeEntriesById(pid);
		}else if (Constants.SHARE_ENTRY_TYPE_SALES_ORDER.equals(entryType)) {
			salesShareEntries = salesOutStockDao.findSalesOrderEntriesById(pid);
		}
		return salesShareEntries;
	}

	@Override
	public void updateReferenceFor(String number, boolean mode)
			throws UpdateReferenceException {
		if(isThisType(number, Constants.NUMBER_SALES_OUT_STOCK_NOTICE)){
			salesOutStockDao.updateSalesOutStockNoticeReference(number, mode);
		}else if(isThisType(number, Constants.NUMBER_SALES_ORDER)){
			salesOutStockDao.updateSalesOrderReference(number, mode);
		}
	}

	@Override
	public void updateReference(List<ICStockBillEntry> entries, boolean mode)
			throws UpdateReferenceException {
		String number = getNumber(entries);
		if(null != number){
			updateReferenceFor(number, mode);
		}
	}

}
