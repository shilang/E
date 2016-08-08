package com.cloud.erp.servicefacade;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloud.erp.activiti.TaskManager;
import com.cloud.erp.activiti.model.AuditModel;
import com.cloud.erp.entities.table.SalesOutStock;
import com.cloud.erp.entities.table.TransportInfo;
import com.cloud.erp.service.TransportInfoService;
import com.cloud.erp.utils.PageUtil;

@Service("transportInfoService")
public class TransportInfoServiceFacade implements TransportInfoService {

	@Resource
	private TransportInfoService transportInfoServiceImpl;
	
	@Autowired
	private TaskManager taskManager;
	
	@Override
	public List<TransportInfo> findAll(Map<String, Object> params,
			PageUtil pageUtil) {
		
		return transportInfoServiceImpl.findAll(params, pageUtil);
	}

	@Override
	public long getCount(Map<String, Object> params) {
		
		return transportInfoServiceImpl.getCount(params);
	}

	@Override
	public TransportInfo get(Integer id) {
		
		return transportInfoServiceImpl.get(id);
	}

	@Override
	public void update(TransportInfo master) {
		
		transportInfoServiceImpl.update(master);
	}

	@Override
	public boolean persistence(TransportInfo master) throws Exception {
		
		return transportInfoServiceImpl.persistence(master);
	}

	@Override
	public boolean deleteToUpdate(Integer pid) {
		
		return transportInfoServiceImpl.deleteToUpdate(pid);
	}

	@Override
	public List<TransportInfo> findByPid(Integer pid) {
		
		return transportInfoServiceImpl.findByPid(pid);
	}

	@Override
	public boolean persistence(SalesOutStock master, TransportInfo transportInfo, String taskId)
			throws Exception {
		
		transportInfoServiceImpl.persistence(master, transportInfo, taskId);
		taskManager.submitTaskWithForm(taskId, new AuditModel());
		return true;
	}

}
