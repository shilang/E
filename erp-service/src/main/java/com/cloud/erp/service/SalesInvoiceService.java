package com.cloud.erp.service;

import java.util.List;
import java.util.Map;

import com.cloud.erp.entities.shareentry.SalesShareEntry;
import com.cloud.erp.entities.table.ICSales;
import com.cloud.erp.entities.table.ICSalesEntry;
import com.cloud.erp.service.common.AutoNumber;
import com.cloud.erp.service.common.GeneralService;
import com.cloud.erp.service.common.ShareEntryService;
import com.cloud.erp.service.common.SingleEntryService;

public interface SalesInvoiceService extends 
    GeneralService<ICSales>, 
    AutoNumber<ICSalesEntry>, 
    SingleEntryService<ICSales>, 
    ShareEntryService<SalesShareEntry, ICSalesEntry>{
	
	boolean persistence(ICSales master, Map<String, List<ICSalesEntry>> entries) throws Exception;
	
	boolean  deleteToUpdateAll(Integer pid) throws Exception;
	
}
