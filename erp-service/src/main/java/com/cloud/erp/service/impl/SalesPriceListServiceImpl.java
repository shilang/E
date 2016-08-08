/**
 * @Title:  SalesPriceListServiceImpl.java
 * @Package:  com.cloud.erp.service.impl
 * @Description:  
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年4月22日 上午10:31:42
 * @version:  v1.0
 *
 * History:
 * Date		Author		Version
 * ---------------------------------------------
 * <reasons>
 */
package com.cloud.erp.service.impl;

import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloud.erp.dao.SalesPriceListDao;
import com.cloud.erp.entities.shareentry.SalesShareEntry;
import com.cloud.erp.entities.table.SalesPriceList;
import com.cloud.erp.entities.table.SalesPriceListEntry;
import com.cloud.erp.exceptions.NumberIncrementException;
import com.cloud.erp.exceptions.UpdateReferenceException;
import com.cloud.erp.service.SalesPriceListService;
import com.cloud.erp.service.common.AutoNumber;
import com.cloud.erp.service.report.ReportPOI;
import com.cloud.erp.utils.Constants;
import com.cloud.erp.utils.PageUtil;

/**
 * @ClassName SalesPriceListServiceImpl
 * @Description 
 * @author bollen bollen@live.cn
 * @date 2015年4月22日 上午10:31:42
 *
 */
@Service("salesPriceListServiceImpl")
public class SalesPriceListServiceImpl implements SalesPriceListService {
	
	private static final String SALES_PRICE_LIST_REPORT = "salesPriceListReport.xml";

	@Autowired
	private SalesPriceListDao salesPriceListDao;
	
	@Resource
	private AutoNumber<SalesPriceListEntry> autoNumber;

	@Override
	public List<SalesPriceList> findAll(Map<String, Object> params,
			PageUtil pageUtil) {
		
		return salesPriceListDao.findAll(params, pageUtil);
	}

	@Override
	public long getCount(Map<String, Object> params) {
		
		return salesPriceListDao.getCount( params);
	}

	@Override
	public SalesPriceList get(Integer id) {
		
		return salesPriceListDao.get(id);
	}

	@Override
	public void update(SalesPriceList master) {
		
		salesPriceListDao.update(master);
	}

	@Override
	public boolean persistence(SalesPriceList master) throws Exception {
		
		return salesPriceListDao.persistence(master);
	}

	@Override
	public <E> List<E> findEntriesById(Integer pid, Class<E> entry) {
		
		return salesPriceListDao.findEntriesById(pid, entry);
	}

	@Override
	public <E> boolean persistenceEntries(SalesPriceList master,
			Map<String, List<E>> entries) {
		
		return salesPriceListDao.persistenceEntries(master, entries);
	}

	@Override
	public boolean deleteToUpdateEntries(Integer pid) throws Exception {
		
		return salesPriceListDao.deleteToUpdateEntries(pid);
	}

	@Override
	public boolean persistence(SalesPriceList salesPriceList,
			Map<String, List<SalesPriceListEntry>> entries) throws Exception {
		
		Integer id = salesPriceList.getInterId();
		
		if(!(persistence(salesPriceList) && persistenceEntries(salesPriceList, entries))){
			return false;
		}
		
		if(null == id){
			increment(Constants.NUMBER_SALES_PRICE_LIST);
		}
		
		return true;
	}

	@Override
	public boolean deleteToUpdateAll(Integer pid) throws Exception {
		
		return deleteToUpdate(pid) && deleteToUpdateEntries(pid);
	}

	@Override
	public boolean deleteToUpdate(Integer pid) {
		
		return salesPriceListDao.deleteToUpdate(pid);
	}

	@Override
	public String getNumber(List<SalesPriceListEntry> list) {
		
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
		
		return null;
	}

	@Override
	public void updateReferenceFor(String number, boolean mode)
			throws UpdateReferenceException {
		
	}

	@Override
	public void updateReference(List<SalesPriceListEntry> entries, boolean mode)
			throws UpdateReferenceException {
	}

	@SuppressWarnings("rawtypes")
	@Override
	public String exportExcel(List list, OutputStream os) {
		ReportPOI poi = new ReportPOI(SALES_PRICE_LIST_REPORT);
		return poi.export(list, os);
	}

}
