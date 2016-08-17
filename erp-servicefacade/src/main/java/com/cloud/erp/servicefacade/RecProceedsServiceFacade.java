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
import com.cloud.erp.entities.table.RecProceeds;
import com.cloud.erp.entities.table.RecProceedsEntry;
import com.cloud.erp.service.RecProceedsService;
import com.cloud.erp.utils.Constants;
import com.cloud.erp.utils.PageUtil;

@Service("recProceedsService")
public class RecProceedsServiceFacade implements RecProceedsService{

	@Resource
	private RecProceedsService recProceedsServiceImpl;

	@Autowired
	private ProcessManager processManager;
	
	@Override
	public List<RecProceeds> findAll(Map<String, Object> params,
			PageUtil pageUtil) {
		return recProceedsServiceImpl.findAll(params, pageUtil);
	}

	@Override
	public long getCount(Map<String, Object> params) {
		return recProceedsServiceImpl.getCount(params);
	}

	@Override
	public RecProceeds get(Integer id) {
		return recProceedsServiceImpl.get(id);
	}

	@Override
	public void update(RecProceeds master) {
		recProceedsServiceImpl.update(master);
	}

	@Override
	public boolean persistence(RecProceeds master) throws Exception {
		return false;
	}

	@Override
	public boolean deleteToUpdate(Integer pid) {
		return false;
	}

	@Override
	public String getNumber(List<RecProceedsEntry> list) {
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
		return recProceedsServiceImpl.findEntriesById(pid, entry);
	}

	@Override
	public <E> boolean persistenceEntries(RecProceeds master,
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
		return recProceedsServiceImpl.findShareEntries(entryType, pid);
	}

	@Override
	public void updateReferenceFor(String number, boolean mode)
			throws UpdateReferenceException {
	}

	@Override
	public void updateReference(List<RecProceedsEntry> entries, boolean mode)
			throws UpdateReferenceException {
	}

	@Override
	public boolean persistence(RecProceeds recProceeds,
			Map<String, List<RecProceedsEntry>> entries) throws Exception {
		Integer orignalId = recProceeds.getInterId();
		recProceedsServiceImpl.persistence(recProceeds, entries);
		Integer businessKey = recProceeds.getInterId();
		if(null == orignalId){
			AuditModel auditModel = new AuditModel(Constants.BUSINESS_TYPE_COMMIT, businessKey, RecProceeds.class.getName());
			processManager.startProcess(Constants.PROCESS_DEF_KEY_PROCEEDS_PROCESS, auditModel);
		}
		return true;
	}

	@Override
	public boolean deleteToUpdateAll(Integer pid)
			throws Exception {
		return recProceedsServiceImpl.deleteToUpdateAll(pid);
	}

}
