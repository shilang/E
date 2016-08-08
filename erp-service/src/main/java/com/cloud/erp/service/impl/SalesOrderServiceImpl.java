/**
 * @Title:  SalesOrderServiceImpl.java
 * @Package:  com.cloud.erp.service.impl
 * @Description:  
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年6月23日 下午2:11:47
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

import com.cloud.erp.dao.SalesOrderDao;
import com.cloud.erp.entities.shareentry.SalesShareEntry;
import com.cloud.erp.entities.table.SalesOrder;
import com.cloud.erp.entities.table.SalesOrderEntry;
import com.cloud.erp.exceptions.NumberIncrementException;
import com.cloud.erp.exceptions.UpdateReferenceException;
import com.cloud.erp.service.SalesOrderService;
import com.cloud.erp.service.common.AutoNumber;
import com.cloud.erp.utils.Constants;
import com.cloud.erp.utils.PageUtil;

/**
 * @ClassName  SalesOrderServiceImpl
 * @Description  
 * @author  bollen bollen@live.cn
 * @date  2015年6月23日 下午2:11:47
 *
 */
@Service("salesOrderServiceImpl")
public class SalesOrderServiceImpl implements SalesOrderService {

	@Autowired
	private SalesOrderDao salesOrderDao;
	
	@Resource
	private AutoNumber<SalesOrderEntry> autoNumber;
	
	@Override
	public List<SalesOrder> findAll(Map<String, Object> params,
			PageUtil pageUtil) {
		
		return salesOrderDao.findAll(params, pageUtil);
	}

	@Override
	public long getCount(Map<String, Object> params) {
		
		return salesOrderDao.getCount(params);
	}

	@Override
	public SalesOrder get(Integer id) {
		
		return salesOrderDao.get(id);
	}

	@Override
	public void update(SalesOrder master) {
		
		salesOrderDao.update(master);
	}

	@Override
	public boolean persistence(SalesOrder master) throws Exception {
		
		
		return salesOrderDao.persistence(master);
	}

	@Override
	public <E> List<E> findEntriesById(Integer pid, Class<E> entry) {
		
		return salesOrderDao.findEntriesById(pid, entry);
	}

	@Override
	public <E> boolean persistenceEntries(SalesOrder master,
			Map<String, List<E>> entries) {
		
		return salesOrderDao.persistenceEntries(master, entries);
	}

	@Override
	public boolean persistence(SalesOrder salesOrder,
			Map<String, List<SalesOrderEntry>> entries) throws Exception {
		
		Integer id = salesOrder.getInterId();
		
		if(!(persistence(salesOrder) && persistenceEntries(salesOrder, entries))){
			return false;
		}

		if(null == id){
			//update number
			increment(Constants.NUMBER_SALES_ORDER);

			updateReference(entries.get(Constants.ENTRY_LIST_TYPE_ADD), true);
		}
		
		return true;
	}

	@Override
	public boolean deleteToUpdate(Integer pid) {
		
		return salesOrderDao.deleteToUpdate(pid);
	}
	
	@Override
	public boolean deleteToUpdateEntries(Integer pid) throws Exception {
		
		if(!salesOrderDao.deleteToUpdateEntries(pid)){
			return false;
		}
		//update reference
		updateReference(findEntriesById(pid, SalesOrderEntry.class), false);
		return true;
	}

	@Override
	public boolean deleteToUpdateAll(Integer pid) throws Exception {
		
		return deleteToUpdate(pid) && deleteToUpdateEntries(pid);
	}

	@Override
	public String getNumber(List<SalesOrderEntry> list) {
		
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
		
		List<SalesShareEntry> salesOrderShareEntries = null;
		if(Constants.SHARE_ENTRY_TYPE_SALES_CONTRACT.equals(entryType)){
			salesOrderShareEntries = salesOrderDao.findContractEntriesById(pid);
		}else if(Constants.SHARE_ENTRY_TYPE_SALES_PRICELIST.equals(entryType)){
			salesOrderShareEntries = salesOrderDao.findPriceListEntriesById(pid);
		}
		return salesOrderShareEntries;
	}

	@Override
	public void updateReferenceFor(String number, boolean mode)
			throws UpdateReferenceException {
		
		if(isThisType(number, Constants.NUMBER_SALES_PRICE_LIST))
		{
			salesOrderDao.updatePriceListReference(number, mode);
		}else if(isThisType(number, Constants.NUMBER_SALES_CONTRACT)){
			salesOrderDao.updateContractReference(number, mode);
		}
	}

	@Override
	public void updateReference(List<SalesOrderEntry> entries, boolean mode)
			throws UpdateReferenceException {
		
		String number = getNumber(entries);
		if(null != number){
			updateReferenceFor(number, mode);
		}
	}

}
