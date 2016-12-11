package com.cloud.erp.servicefacade;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloud.erp.activiti.ProcessManager;
import com.cloud.erp.activiti.model.AuditModel;
import com.cloud.erp.dao.exception.NumberIncrementException;
import com.cloud.erp.entities.table.SalesContract;
import com.cloud.erp.entities.table.SalesContractEntry;
import com.cloud.erp.entities.table.SalesContractScheme;
import com.cloud.erp.service.SalesContractService;
import com.cloud.erp.utils.Commons;
import com.cloud.erp.utils.Constants;
import com.cloud.erp.utils.PageUtil;

@Service("salesContractService")
public class SalesContractServiceFacade implements SalesContractService {
	
	@Resource
	private SalesContractService salesContractServiceImpl;
	
	@Autowired
	private ProcessManager processManager;

	@Override
	public List<SalesContract> findAll(Map<String, Object> params,
			PageUtil pageUtil) {
		return salesContractServiceImpl.findAll(params, pageUtil);
	}

	@Override
	public long getCount(Map<String, Object> params) {
		return salesContractServiceImpl.getCount(params);
	}

	@Override
	public SalesContract get(Integer id) {
		return salesContractServiceImpl.get(id);
	}

	@Override
	public void update(SalesContract master) {
		salesContractServiceImpl.update(master);
	}

	@Override
	public boolean persistence(SalesContract master) throws Exception {
		return salesContractServiceImpl.persistence(master);
	}

	@Override
	public boolean deleteToUpdate(Integer pid) {
		return salesContractServiceImpl.deleteToUpdate(pid);
	}

	@Override
	public String getNumber(List<SalesContractEntry> list) {
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
	public <E> List<E> findEntriesById(Integer pid, Class<E> clazz) {
		return salesContractServiceImpl.findEntriesById(pid, clazz);
	}

	@Override
	public <E> boolean persistenceEntries(SalesContract master,
			Map<String, List<E>> entries) {
		return false;
	}

	@Override
	public boolean deleteToUpdateEntries(Integer pid) {
		return false;
	}

	@Override
	public boolean persistence(SalesContract salesContract,
			Map<String, List<SalesContractEntry>> entries,
			Map<String, List<SalesContractScheme>> schemes) throws Exception {
		
		Integer orignalId = salesContract.getInterId();
		salesContractServiceImpl.persistence(salesContract, entries,schemes);
		Integer businessKey = salesContract.getInterId();
		if(null == orignalId){
			AuditModel auditModel = new AuditModel();
			auditModel.setTaskBusinessType(Constants.BUSINESS_TYPE_COMMIT);
			auditModel.setBusinessKey(businessKey);
			auditModel.setBusinessClass(SalesContract.class.getName());
			auditModel.setNumber(salesContract.getBillNo());
			auditModel.setCreater(Commons.getCurrentUser().getAccount());
			processManager.startProcess(Constants.PROCESS_DEF_KEY_SALES_CONTRACT_PROCESS, auditModel);
		}
		return true;
	}

	@Override
	public boolean deleteToUpdateAll(Integer pid) {
		return salesContractServiceImpl.deleteToUpdateAll(pid);
	}

}
