package com.cloud.erp.dao;

import java.util.List;

import com.cloud.erp.dao.common.GeneralDao;
import com.cloud.erp.dao.common.ReferenceDao;
import com.cloud.erp.dao.common.SingleEntryDao;
import com.cloud.erp.dao.exception.UpdateReferenceException;
import com.cloud.erp.entities.SettleItem;
import com.cloud.erp.entities.shareentry.SalesShareEntry;
import com.cloud.erp.entities.table.RecProceeds;


public interface RecProceedsDao
	extends GeneralDao<RecProceeds>,
	SingleEntryDao<RecProceeds>,
	ReferenceDao<RecProceeds>{
	
	List<SalesShareEntry> findSalesOrderEntriesById(Integer id);
	
	List<SalesShareEntry> findSalesInvoiceEntriesById(Integer id);
	
	List<SalesShareEntry> findSalesContractEntriesByid(Integer id);
	
	boolean updateSalesOrderReference(String number, boolean mode) throws UpdateReferenceException;
	
	boolean updateSalesInvoiceReference(String number, boolean mode) throws UpdateReferenceException;
	
	boolean updateSalesConstractReference(String number, boolean mode) throws UpdateReferenceException;
	
	boolean updateSalesOrderRelatedAmount(RecProceeds recProceeds);
	
	SettleItem mergeSettleAmount(String sourceBillNo);
	
}
