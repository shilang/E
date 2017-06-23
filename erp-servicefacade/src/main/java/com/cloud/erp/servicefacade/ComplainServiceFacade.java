package com.cloud.erp.servicefacade;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloud.erp.activiti.TaskManager;
import com.cloud.erp.entities.table.QualityComplain;
import com.cloud.erp.service.ComplainService;
import com.cloud.erp.utils.PageUtil;

@Service("complainService")
public class ComplainServiceFacade implements ComplainService{
	
	@Resource
	private ComplainService complainService;
	
	@Autowired
	private TaskManager taskManager;

	@Override
	public List<QualityComplain> findAll(Map<String, Object> params,
			PageUtil pageUtil) {
		return complainService.findAll(params, pageUtil);
	}

	@Override
	public long getCount(Map<String, Object> params) {
		return complainService.getCount(params);
	}

	@Override
	public QualityComplain get(Integer id) {
		return null;
	}

	@Override
	public void update(QualityComplain master) {
	}

	@Override
	public boolean persistence(QualityComplain master) throws Exception {
		return false;
	}

	@Override
	public boolean deleteToUpdate(Integer pid) {
		return false;
	}

	@Override
	public String getCurrTaskDefKey(String userId, String processInstanceId) {
		return taskManager.getTaskDefKeyByCandidateOrAssigned(userId, processInstanceId);
	}

	@Override
	public boolean updateSegment(String segment, QualityComplain qualityComplain)
			throws Exception {
		return complainService.updateSegment(segment, qualityComplain);
	}

}
