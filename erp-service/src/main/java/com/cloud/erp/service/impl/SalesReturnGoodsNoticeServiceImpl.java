/**
 * @Title:  SalesReturnGoodsNoticeServiceImpl.java
 * @Package:  com.cloud.erp.service.impl
 * @Description:  
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年7月6日 下午4:45:34
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

import com.cloud.erp.dao.SalesReturnGoodsNoticeDao;
import com.cloud.erp.dao.exception.NumberIncrementException;
import com.cloud.erp.dao.exception.UpdateReferenceException;
import com.cloud.erp.entities.shareentry.SalesShareEntry;
import com.cloud.erp.entities.table.SalesOutStock;
import com.cloud.erp.entities.table.SalesOutStockEntry;
import com.cloud.erp.service.SalesReturnGoodsNoticeService;
import com.cloud.erp.service.common.AutoNumber;
import com.cloud.erp.utils.Constants;
import com.cloud.erp.utils.PageUtil;

/**
 * @ClassName  SalesReturnGoodsNoticeServiceImpl
 * @Description  
 * @author  bollen bollen@live.cn
 * @date  2015年7月6日 下午4:45:34
 *
 */
@Service("salesReturnGoodsNoticeServiceImpl")
public class SalesReturnGoodsNoticeServiceImpl implements SalesReturnGoodsNoticeService {
	
	@Autowired
	private SalesReturnGoodsNoticeDao salesReturnGoodsNoticeDao;
	
	@Resource
	private AutoNumber<SalesOutStockEntry> autoNumber;

	@Override
	public List<SalesOutStock> findAll(Map<String, Object> params,
			PageUtil pageUtil) {

		return salesReturnGoodsNoticeDao.findAll(params, pageUtil);
	}
	
	@Override
	public long getCount(Map<String, Object> params) {
	
		return salesReturnGoodsNoticeDao.getCount(params);
	}

	@Override
	public SalesOutStock get(Integer id) {
	
		return salesReturnGoodsNoticeDao.get(id);
	}

	@Override
	public void update(SalesOutStock master) {
	
		salesReturnGoodsNoticeDao.update(master);
	}
	
	@Override
	public boolean persistence(SalesOutStock master) throws Exception {
		
		master.setBillType(Constants.SALES_OUT_STOCK_TYPE_IN);
		return salesReturnGoodsNoticeDao.persistence(master);
	}

	@Override
	public <E> List<E> findEntriesById(Integer pid, Class<E> entry) {
		
		return salesReturnGoodsNoticeDao.findEntriesById(pid, entry);
	}

	@Override
	public <E> boolean persistenceEntries(SalesOutStock master,
			Map<String, List<E>> entries) {
		
		return salesReturnGoodsNoticeDao.persistenceEntries(master, entries);
	}

	@Override
	public boolean deleteToUpdateEntries(Integer pid) throws Exception {
		
		if(!salesReturnGoodsNoticeDao.deleteToUpdateEntries(pid)){
			return false;
		}
		updateReference(findEntriesById(pid, SalesOutStockEntry.class), false);
		return true;
	}

	@Override
	public boolean persistence(SalesOutStock salesOutStock,
			Map<String, List<SalesOutStockEntry>> entries) throws Exception {
		
		Integer id = salesOutStock.getInterId();
		
		if(!(persistence(salesOutStock) && persistenceEntries(salesOutStock, entries))){
			return false;
		}
		
		if(null == id){
			
			increment(Constants.NUMBER_SALES_RETURN_GOODS_NOTICE);
			
			updateReference(entries.get(Constants.ENTRY_LIST_TYPE_ADD), true);
		}
		
		return true;
	}

	@Override
	public boolean deleteToUpdateAll(Integer pid) throws Exception {
		
		return deleteToUpdate(pid) && deleteToUpdateEntries(pid);
	}


	@Override
	public boolean deleteToUpdate(Integer pid) {
		
		return salesReturnGoodsNoticeDao.deleteToUpdate(pid);
	}

	@Override
	public String getNumber(List<SalesOutStockEntry> list) {
		
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
		
		List<SalesShareEntry> salesShareEntries = null;
		if(Constants.SHARE_ENTRY_TYPE_SALES_OUTNOTICE.equals(entryType)){
			salesShareEntries = salesReturnGoodsNoticeDao.findSalesOutStockNoticeEntriesById(pid);
		}
		return salesShareEntries;
	}

	@Override
	public void updateReferenceFor(String number, boolean mode)
			throws UpdateReferenceException {
		
		if(isThisType(number, Constants.NUMBER_SALES_OUT_STOCK_NOTICE)){
			salesReturnGoodsNoticeDao.updateSalesOutStockNoticeReference(number, mode);
		}
	}

	@Override
	public void updateReference(List<SalesOutStockEntry> entries, boolean mode)
			throws UpdateReferenceException {
		
		String number = getNumber(entries);
		if(null != number){
			updateReferenceFor(number,mode);
		}
	}

}
