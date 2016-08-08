package com.cloud.erp.servicefacade;

import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloud.erp.activiti.ProcessManager;
import com.cloud.erp.activiti.model.AuditModel;
import com.cloud.erp.entities.shareentry.SalesShareEntry;
import com.cloud.erp.entities.table.SalesPriceList;
import com.cloud.erp.entities.table.SalesPriceListEntry;
import com.cloud.erp.exceptions.NumberIncrementException;
import com.cloud.erp.exceptions.UpdateReferenceException;
import com.cloud.erp.service.SalesPriceListService;
import com.cloud.erp.utils.Constants;
import com.cloud.erp.utils.PageUtil;

@Service("salesPriceListService")
public class SalesPriceListServiceFacade implements SalesPriceListService{

	@Resource
	private SalesPriceListService salesPriceListServiceImpl;
	
	@Autowired
	private ProcessManager processManager;
	
	@Override
	public List<SalesPriceList> findAll(Map<String, Object> params,
			PageUtil pageUtil) {
		return salesPriceListServiceImpl.findAll(params, pageUtil);
	}

	@Override
	public long getCount(Map<String, Object> params) {
		return salesPriceListServiceImpl.getCount(params);
	}

	@Override
	public SalesPriceList get(Integer id) {
		return salesPriceListServiceImpl.get(id);
	}

	@Override
	public void update(SalesPriceList master) {
		salesPriceListServiceImpl.update(master);
	}

	@Override
	public boolean persistence(SalesPriceList master) throws Exception {
		return salesPriceListServiceImpl.persistence(master);
	}

	@Override
	public boolean deleteToUpdate(Integer pid) {
		return salesPriceListServiceImpl.deleteToUpdate(pid);
	}

	@Override
	public String getNumber(List<SalesPriceListEntry> list) {
		return null;
	}

	@Override
	public boolean isThisType(String number, int classId) {
		return true;
	}

	@Override
	public void increment(Integer classId) throws NumberIncrementException {
	}

	@Override
	public <E> List<E> findEntriesById(Integer pid, Class<E> entry) {
		return salesPriceListServiceImpl.findEntriesById(pid, entry);
	}

	@Override
	public <E> boolean persistenceEntries(SalesPriceList master,
			Map<String, List<E>> entries) {
		return salesPriceListServiceImpl.persistenceEntries(master, entries);
	}

	@Override
	public boolean deleteToUpdateEntries(Integer pid)
			throws Exception {
		return salesPriceListServiceImpl.deleteToUpdateEntries(pid);
	}

	@Override
	public List<SalesShareEntry> findShareEntries(String entryType, Integer pid) {
		return salesPriceListServiceImpl.findShareEntries(entryType, pid);
	}

	@Override
	public void updateReferenceFor(String number, boolean mode)
			throws UpdateReferenceException {
	}

	@Override
	public void updateReference(List<SalesPriceListEntry> entries, boolean mode)
			throws UpdateReferenceException {
	}

	@Override
	public boolean persistence(SalesPriceList salesPriceList,
			Map<String, List<SalesPriceListEntry>> entries) throws Exception {
		Integer orignalId = salesPriceList.getInterId();
		salesPriceListServiceImpl.persistence(salesPriceList, entries);
		Integer businessKey = salesPriceList.getInterId();
		if(null == orignalId){
			AuditModel auditModel = new AuditModel(Constants.BUSINESS_TYPE_COMMIT, businessKey, SalesPriceList.class.getName());
			processManager.startProcess(Constants.PROCESS_DEF_KEY_SALES_PRICELIST_PROCESS, auditModel);
		}
		return true;
	}

	@Override
	public boolean deleteToUpdateAll(Integer pid) throws Exception {
		return salesPriceListServiceImpl.deleteToUpdateAll(pid);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public String exportExcel(List list, OutputStream os) {
		return salesPriceListServiceImpl.exportExcel(list, os);
	}

}
