package com.cloud.erp.servicefacade;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloud.erp.activiti.ProcessManager;
import com.cloud.erp.activiti.model.AuditModel;
import com.cloud.erp.entities.shareentry.SalesShareEntry;
import com.cloud.erp.entities.table.SalesOutStock;
import com.cloud.erp.entities.table.SalesOutStockEntry;
import com.cloud.erp.exceptions.NumberIncrementException;
import com.cloud.erp.exceptions.UpdateReferenceException;
import com.cloud.erp.service.SalesReturnGoodsNoticeService;
import com.cloud.erp.utils.Constants;
import com.cloud.erp.utils.PageUtil;

@Service("salesReturnGoodsNoticeService")
public class SalesReturnGoodsNoticeServiceFacade implements
		SalesReturnGoodsNoticeService {
	
	@Resource
	private SalesReturnGoodsNoticeService salesReturnGoodsNoticeServiceImpl;
	
	@Autowired
	private ProcessManager processManager;

	@Override
	public List<SalesOutStock> findAll(Map<String, Object> params,
			PageUtil pageUtil) {
		return salesReturnGoodsNoticeServiceImpl.findAll(params, pageUtil);
	}

	@Override
	public long getCount(Map<String, Object> params) {
		return salesReturnGoodsNoticeServiceImpl.getCount(params);
	}

	@Override
	public SalesOutStock get(Integer id) {
		return salesReturnGoodsNoticeServiceImpl.get(id);
	}

	@Override
	public void update(SalesOutStock master) {
		salesReturnGoodsNoticeServiceImpl.update(master);
	}

	@Override
	public boolean persistence(SalesOutStock master) throws Exception {
		return false;
	}

	@Override
	public boolean deleteToUpdate(Integer pid) {
		return false;
	}

	@Override
	public String getNumber(List<SalesOutStockEntry> list) {
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
		return salesReturnGoodsNoticeServiceImpl.findEntriesById(pid, entry);
	}

	@Override
	public <E> boolean persistenceEntries(SalesOutStock master,
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
		return salesReturnGoodsNoticeServiceImpl.findShareEntries(entryType, pid);
	}

	@Override
	public void updateReferenceFor(String number, boolean mode)
			throws UpdateReferenceException {
	}

	@Override
	public void updateReference(List<SalesOutStockEntry> entries, boolean mode)
			throws UpdateReferenceException {

	}

	@Override
	public boolean persistence(SalesOutStock salesOutStock,
			Map<String, List<SalesOutStockEntry>> entries) throws Exception {
		Integer orignalId = salesOutStock.getInterId();
		salesReturnGoodsNoticeServiceImpl.persistence(salesOutStock, entries);
		Integer businessKey = salesOutStock.getInterId();
		if(null == orignalId){
			AuditModel auditModel = new AuditModel(Constants.BUSINESS_TYPE_COMMIT, businessKey, SalesOutStock.class.getName());
			processManager.startProcess(Constants.PROCESS_DEF_KEY_SALES_RETURNGOODS_NOTICE_PROCESS, auditModel);
		}
		return true;
	}

	@Override
	public boolean deleteToUpdateAll(Integer pid)
			throws Exception {
		return salesReturnGoodsNoticeServiceImpl.deleteToUpdateAll(pid);
	}

}
