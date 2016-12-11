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
import com.cloud.erp.entities.table.ICStockBill;
import com.cloud.erp.entities.table.ICStockBillEntry;
import com.cloud.erp.service.SalesOutStockService;
import com.cloud.erp.utils.Commons;
import com.cloud.erp.utils.Constants;
import com.cloud.erp.utils.PageUtil;

@Service("salesOutStockService")
public class SalesOutStockServiceFacade implements SalesOutStockService{
	
	@Resource
	private SalesOutStockService salesOutStockServiceImpl;
	
	@Autowired
	private ProcessManager processManager;


	@Override
	public List<ICStockBill> findAll(Map<String, Object> params,
			PageUtil pageUtil) {
		return salesOutStockServiceImpl.findAll(params, pageUtil);
	}

	@Override
	public long getCount(Map<String, Object> params) {
		return salesOutStockServiceImpl.getCount(params);
	}

	@Override
	public ICStockBill get(Integer id) {
		return salesOutStockServiceImpl.get(id);
	}

	@Override
	public void update(ICStockBill master) {
		salesOutStockServiceImpl.update(master);
	}

	@Override
	public boolean persistence(ICStockBill master) throws Exception {
		return salesOutStockServiceImpl.persistence(master);
	}

	@Override
	public boolean deleteToUpdate(Integer pid) {
		return salesOutStockServiceImpl.deleteToUpdate(pid);
	}

	@Override
	public String getNumber(List<ICStockBillEntry> list) {
		return salesOutStockServiceImpl.getNumber(list);
	}

	@Override
	public boolean isThisType(String number, int classId) {
		return salesOutStockServiceImpl.isThisType(number, classId);
	}

	@Override
	public void increment(Integer classId) throws NumberIncrementException {
		salesOutStockServiceImpl.increment(classId);
	}

	@Override
	public <E> List<E> findEntriesById(Integer pid, Class<E> entry) {
		return salesOutStockServiceImpl.findEntriesById(pid, entry);
	}

	@Override
	public <E> boolean persistenceEntries(ICStockBill master,
			Map<String, List<E>> entries) {
		return salesOutStockServiceImpl.persistenceEntries(master, entries);
	}

	@Override
	public boolean deleteToUpdateEntries(Integer pid)
			throws Exception {
		return salesOutStockServiceImpl.deleteToUpdateEntries(pid);
	}

	@Override
	public List<SalesShareEntry> findShareEntries(String entryType, Integer pid) {
		return salesOutStockServiceImpl.findShareEntries(entryType, pid);
	}

	@Override
	public void updateReferenceFor(String number, boolean mode)
			throws UpdateReferenceException {
		salesOutStockServiceImpl.updateReferenceFor(number, mode);
	}

	@Override
	public void updateReference(List<ICStockBillEntry> entries, boolean mode)
			throws UpdateReferenceException {
		salesOutStockServiceImpl.updateReference(entries, mode);
	}

	@Override
	public boolean persistence(ICStockBill icStockBill,
			Map<String, List<ICStockBillEntry>> entries) throws Exception {
		Integer orignalId = icStockBill.getInterId();
		salesOutStockServiceImpl.persistence(icStockBill, entries);
		Integer businessKey = icStockBill.getInterId();
		if(null == orignalId){
			AuditModel auditModel = new AuditModel();
			auditModel.setTaskBusinessType(Constants.BUSINESS_TYPE_COMMIT);
			auditModel.setBusinessKey(businessKey);
			auditModel.setBusinessClass(ICStockBill.class.getName());
			auditModel.setNumber(icStockBill.getBillNo());
			auditModel.setCreater(Commons.getCurrentUser().getAccount());
			processManager.startProcess(Constants.PROCESS_DEF_KEY_SALES_OUTSTOCK_PROCESS, auditModel);
		}
		return true;
	}

	@Override
	public boolean deleteToUpdateAll(Integer pid)
			throws Exception {
		processManager.deleteProcessInstance(get(pid).getProcInstId());
		salesOutStockServiceImpl.deleteToUpdateAll(pid);
		return true;
	}
	
}
