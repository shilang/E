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
import com.cloud.erp.entities.table.SalesOutStock;
import com.cloud.erp.entities.table.SalesOutStockEntry;
import com.cloud.erp.service.SalesOutStockNoticeService;
import com.cloud.erp.utils.Commons;
import com.cloud.erp.utils.Constants;
import com.cloud.erp.utils.PageUtil;

@Service("salesOutStockNoticeService")
public class SalesOutStockNoticeServiceFacade implements SalesOutStockNoticeService{
	
	@Resource
	private SalesOutStockNoticeService salesOutStockNoticeServiceImpl;
	
	@Autowired
	private ProcessManager processManager;

	@Override
	public List<SalesOutStock> findAll(Map<String, Object> params,
			PageUtil pageUtil) {
		
		return salesOutStockNoticeServiceImpl.findAll(params, pageUtil);
	}

	@Override
	public long getCount(Map<String, Object> params) {
		
		return salesOutStockNoticeServiceImpl.getCount(params);
	}

	@Override
	public SalesOutStock get(Integer id) {
		
		return salesOutStockNoticeServiceImpl.get(id);
	}

	@Override
	public void update(SalesOutStock master) {
		
		salesOutStockNoticeServiceImpl.update(master);
	}

	@Override
	public boolean persistence(SalesOutStock master) throws Exception {
		
		return salesOutStockNoticeServiceImpl.persistence(master);
	}

	@Override
	public boolean deleteToUpdate(Integer pid) {
		
		return salesOutStockNoticeServiceImpl.deleteToUpdate(pid);
	}

	@Override
	public String getNumber(List<SalesOutStockEntry> list) {
		
		return salesOutStockNoticeServiceImpl.getNumber(list);
	}

	@Override
	public boolean isThisType(String number, int classId) {
		
		return salesOutStockNoticeServiceImpl.isThisType(number, classId);
	}

	@Override
	public void increment(Integer classId) throws NumberIncrementException {
		
		salesOutStockNoticeServiceImpl.increment(classId);
	}

	@Override
	public <E> List<E> findEntriesById(Integer pid, Class<E> entry) {
		
		return salesOutStockNoticeServiceImpl.findEntriesById(pid, entry);
	}

	@Override
	public <E> boolean persistenceEntries(SalesOutStock master,
			Map<String, List<E>> entries) {
		
		return salesOutStockNoticeServiceImpl.persistenceEntries(master, entries);
	}

	@Override
	public boolean deleteToUpdateEntries(Integer pid)
			throws Exception {
		
		return salesOutStockNoticeServiceImpl.deleteToUpdateEntries(pid);
	}

	@Override
	public List<SalesShareEntry> findShareEntries(String entryType, Integer pid) {
		
		return salesOutStockNoticeServiceImpl.findShareEntries(entryType, pid);
	}

	@Override
	public void updateReferenceFor(String number, boolean mode)
			throws UpdateReferenceException {
		
		salesOutStockNoticeServiceImpl.updateReferenceFor(number, mode);
	}

	@Override
	public void updateReference(List<SalesOutStockEntry> entries, boolean mode)
			throws UpdateReferenceException {
		
		salesOutStockNoticeServiceImpl.updateReference(entries, mode);
	}

	@Override
	public boolean persistence(SalesOutStock salesOutStock,
			Map<String, List<SalesOutStockEntry>> entries) throws Exception {
		
		Integer orignalId = salesOutStock.getInterId();
		salesOutStockNoticeServiceImpl.persistence(salesOutStock, entries);
		Integer businessKey = salesOutStock.getInterId();
		if(null == orignalId){
			AuditModel auditModel = new AuditModel();
			auditModel.setTaskBusinessType(Constants.BUSINESS_TYPE_COMMIT);
			auditModel.setBusinessKey(businessKey);
			auditModel.setBusinessClass(SalesOutStock.class.getName());
			auditModel.setNumber(salesOutStock.getBillNo());
			auditModel.setCreater(Commons.getCurrentUser().getAccount());
			processManager.startProcess(Constants.PROCESS_DEF_KEY_SALES_OUTSTOCK_NOTICE_PROCESS, auditModel);
		}
		
		return salesOutStockNoticeServiceImpl.persistence(salesOutStock, entries);
	}

	@Override
	public boolean deleteToUpdateAll(Integer pid)throws Exception {
		processManager.deleteProcessInstance(get(pid).getProcInstId());
		salesOutStockNoticeServiceImpl.deleteToUpdateAll(pid);
		return true;
	}


}
