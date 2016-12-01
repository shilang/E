package com.cloud.erp.servicefacade;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloud.erp.activiti.ProcessManager;
import com.cloud.erp.activiti.TaskManager;
import com.cloud.erp.activiti.model.AuditModel;
import com.cloud.erp.dao.exception.NumberIncrementException;
import com.cloud.erp.dao.exception.UpdateReferenceException;
import com.cloud.erp.entities.shareentry.SalesShareEntry;
import com.cloud.erp.entities.table.SalesOrder;
import com.cloud.erp.entities.table.SalesOrderEntry;
import com.cloud.erp.service.SalesOrderService;
import com.cloud.erp.utils.Constants;
import com.cloud.erp.utils.PageUtil;

@Service("salesOrderService")
public class SalesOrderServiceFacade implements SalesOrderService{
	
	@Resource
	private SalesOrderService salesOrderServiceImpl;
	
	@Autowired
	private ProcessManager processManager;
	
	@Autowired
	private TaskManager taskManager;

	@Override
	public List<SalesOrder> findAll(Map<String, Object> params,
			PageUtil pageUtil) {
		return salesOrderServiceImpl.findAll(params, pageUtil);
	}

	@Override
	public long getCount(Map<String, Object> params) {
		return salesOrderServiceImpl.getCount(params);
	}

	@Override
	public SalesOrder get(Integer id) {
		return salesOrderServiceImpl.get(id);
	}

	@Override
	public void update(SalesOrder master) {
		salesOrderServiceImpl.update(master);
	}

	@Override
	public boolean persistence(SalesOrder master) throws Exception {
		return salesOrderServiceImpl.persistence(master);
	}
	
	@Override
	public boolean updateOrderReview(String segment, Integer interId, String review,
			String ckreview, String cgreview, String processInstanceId, String taskDefKey) {
		 if(null != segment && !"".equals(segment)){
			 salesOrderServiceImpl.updateOrderReview(segment, interId, review,
						ckreview, cgreview, null, null);
			 String taskId = taskManager.getTaskIdByProcInstIdAndDefKey(processInstanceId, taskDefKey);
			 taskManager.submitTask(taskId);
		 }
		 return true;
	}
	
	@Override
	public boolean updateOrderStatus(Integer interId, String status) {
		return salesOrderServiceImpl.updateOrderStatus(interId, status);
	}


	@Override
	public boolean deleteToUpdate(Integer pid) {
		return salesOrderServiceImpl.deleteToUpdate(pid);
	}

	@Override
	public String getNumber(List<SalesOrderEntry> list) {
		return salesOrderServiceImpl.getNumber(list);
	}

	@Override
	public boolean isThisType(String number, int classId) {
		return salesOrderServiceImpl.isThisType(number, classId);
	}

	@Override
	public void increment(Integer classId) throws NumberIncrementException {
		salesOrderServiceImpl.increment(classId);
	}

	@Override
	public <E> List<E> findEntriesById(Integer pid, Class<E> entry) {
		return salesOrderServiceImpl.findEntriesById(pid, entry);
	}

	@Override
	public <E> boolean persistenceEntries(SalesOrder master,
			Map<String, List<E>> entries) {
		return salesOrderServiceImpl.persistenceEntries(master, entries);
	}

	@Override
	public boolean deleteToUpdateEntries(Integer pid)
			throws Exception {
		return salesOrderServiceImpl.deleteToUpdateEntries(pid);
	}

	@Override
	public List<SalesShareEntry> findShareEntries(String entryType, Integer pid) {
		return salesOrderServiceImpl.findShareEntries(entryType, pid);
	}

	@Override
	public void updateReferenceFor(String number, boolean mode)
			throws UpdateReferenceException {
		salesOrderServiceImpl.updateReferenceFor(number, mode);
	}

	@Override
	public void updateReference(List<SalesOrderEntry> entries, boolean mode)
			throws UpdateReferenceException {
		salesOrderServiceImpl.updateReference(entries, mode);
	}

	@Override
	public boolean persistence(SalesOrder salesOrder,
			Map<String, List<SalesOrderEntry>> entries) throws Exception {
		Integer orignalId = salesOrder.getInterId();
		salesOrderServiceImpl.persistence(salesOrder, entries);
		Integer businessKey = salesOrder.getInterId();
		if(null == orignalId){
			AuditModel auditModel = new AuditModel(Constants.BUSINESS_TYPE_COMMIT, businessKey, SalesOrder.class.getName());
			processManager.startProcess(Constants.PROCESS_DEF_KEY_SALES_ORDER_PROCESS, auditModel);
		}
		return true;
	}

	@Override
	public boolean deleteToUpdateAll(Integer pid)
			throws Exception {
		return salesOrderServiceImpl.deleteToUpdateAll(pid);
	}

	@Override
	public String getCurrTaskDefKey(String userId, String processInstanceId) {
		return taskManager.getTaskDefKeyByCandidateOrAssigned(userId, processInstanceId);
	}
}
