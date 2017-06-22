package com.cloud.erp.dao;

import java.util.List;

import com.cloud.erp.dao.common.GeneralDao;
import com.cloud.erp.dao.common.ReferenceDao;
import com.cloud.erp.dao.common.SingleEntryDao;
import com.cloud.erp.dao.exception.UpdateReferenceException;
import com.cloud.erp.entities.shareentry.SalesShareEntry;
import com.cloud.erp.entities.table.ICSales;

/**
 * 
 * @author Bollen
 *
 */
public interface SalesInvoiceDao extends GeneralDao<ICSales>, SingleEntryDao<ICSales>, ReferenceDao<ICSales>{
	
	List<SalesShareEntry> findSalesOrderEntriesById(Integer id);
	
	List<SalesShareEntry> findSalesOutStockEntriesById(Integer id);
	
	List<SalesShareEntry> findSalesContractEntriesByid(Integer id);
	
	boolean updateSalesOrderReference(String number, boolean mode) throws UpdateReferenceException;
	
	boolean updateSalesOutStockReference(String number, boolean mode) throws UpdateReferenceException;
	
	boolean updateSalesConstractReference(String number, boolean mode) throws UpdateReferenceException;
	
}
