package com.cloud.erp.servicefacade;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloud.erp.activiti.ProcessManager;
import com.cloud.erp.activiti.model.AuditModel;
import com.cloud.erp.dao.exception.NumberIncrementException;
import com.cloud.erp.dao.exception.UpdateReferenceException;
import com.cloud.erp.entities.shareentry.SalesShareEntry;
import com.cloud.erp.entities.table.ICSales;
import com.cloud.erp.entities.table.ICSalesEntry;
import com.cloud.erp.service.SalesInvoiceService;
import com.cloud.erp.utils.Constants;
import com.cloud.erp.utils.PageUtil;

@Service("salesInvoiceService")
public class SalesInvoiceServiceFacade implements SalesInvoiceService {

	@Resource
	private SalesInvoiceService salesInoviceServiceImpl;
	
	@Autowired
	private ProcessManager processManager;
	
	@Override
	public List<ICSales> findAll(Map<String, Object> params, PageUtil pageUtil) {
		return salesInoviceServiceImpl.findAll(params, pageUtil);
	}

	@Override
	public long getCount(Map<String, Object> params) {
		return salesInoviceServiceImpl.getCount(params);
	}

	@Override
	public ICSales get(Integer id) {
		return salesInoviceServiceImpl.get(id);
	}

	@Override
	public void update(ICSales master) {
		salesInoviceServiceImpl.update(master);
	}

	@Override
	public boolean persistence(ICSales master) throws Exception {
		return false;
	}

	@Override
	public boolean deleteToUpdate(Integer pid) {
		return false;
	}

	@Override
	public String getNumber(List<ICSalesEntry> list) {
		return null;
	}

	@Override
	public boolean isThisType(String number, int classId) {
		return false;
	}

	@Override
	public void increment(Integer classId) throws NumberIncrementException {

	}

	@Override
	public <E> List<E> findEntriesById(Integer pid, Class<E> entry) {
		return salesInoviceServiceImpl.findEntriesById(pid, entry);
	}

	@Override
	public <E> boolean persistenceEntries(ICSales master,
			Map<String, List<E>> entries) {
		return false;
	}

	@Override
	public boolean deleteToUpdateEntries(Integer pid)
			throws UpdateReferenceException {
		return false;
	}

	@Override
	public List<SalesShareEntry> findShareEntries(String entryType, Integer pid) {
		return salesInoviceServiceImpl.findShareEntries(entryType, pid);
	}

	@Override
	public void updateReferenceFor(String number, boolean mode)
			throws UpdateReferenceException {
	}

	@Override
	public void updateReference(List<ICSalesEntry> entries, boolean mode)
			throws UpdateReferenceException {
	}

	@Override
	public boolean persistence(ICSales master,
			Map<String, List<ICSalesEntry>> entries) throws Exception {
		Integer orignalId = master.getInterId();
		salesInoviceServiceImpl.persistence(master, entries);
		Integer businessKey = master.getInterId();
		if(null == orignalId){
			AuditModel auditModel = new AuditModel(Constants.BUSINESS_TYPE_COMMIT, businessKey, ICSales.class.getName());
			processManager.startProcess(Constants.PROCESS_DEF_KEY_SALES_INVOICE_PROCESS, auditModel);
		}
		return true;
	}

	@Override
	public boolean deleteToUpdateAll(Integer pid)
			throws Exception {
		return salesInoviceServiceImpl.deleteToUpdateAll(pid);
	}

}
