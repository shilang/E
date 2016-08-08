package com.cloud.erp.service;

import java.util.List;
import java.util.Map;

import com.cloud.erp.entities.shareentry.SalesShareEntry;
import com.cloud.erp.entities.table.RecProceeds;
import com.cloud.erp.entities.table.RecProceedsEntry;
import com.cloud.erp.service.common.AutoNumber;
import com.cloud.erp.service.common.GeneralService;
import com.cloud.erp.service.common.ShareEntryService;
import com.cloud.erp.service.common.SingleEntryService;

public interface RecProceedsService extends
	GeneralService<RecProceeds>,
	AutoNumber<RecProceedsEntry>,
	SingleEntryService<RecProceeds>,
	ShareEntryService<SalesShareEntry, RecProceedsEntry>{
	
	boolean persistence(RecProceeds recProceeds, Map<String, List<RecProceedsEntry>> entries) throws Exception;
	
	boolean deleteToUpdateAll(Integer pid) throws Exception;

}
