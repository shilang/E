package com.cloud.erp.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloud.erp.dao.ComplainDao;
import com.cloud.erp.entities.table.QualityComplain;
import com.cloud.erp.service.ComplainService;
import com.cloud.erp.utils.PageUtil;

@Service("complainServiceImpl")
public class ComplainServiceImpl implements ComplainService {

	@Autowired
	private ComplainDao complainDao;
	
	@Override
	public List<QualityComplain> findAll(Map<String, Object> params,
			PageUtil pageUtil) {
		return complainDao.findAll(params, pageUtil);
	}

	@Override
	public long getCount(Map<String, Object> params) {
		return complainDao.getCount(params);
	}

	@Override
	public QualityComplain get(Integer id) {
		return complainDao.get(id);
	}

	@Override
	public void update(QualityComplain master) {
		complainDao.update(master);
	}

	@Override
	public boolean persistence(QualityComplain master) throws Exception {
		return complainDao.persistence(master);
	}

	@Override
	public boolean deleteToUpdate(Integer pid) {
		return complainDao.deleteToUpdate(pid);
	}

	@Override
	public String getCurrTaskDefKey(String userId, String processInstanceId) {
		return null;
	}

	@Override
	public boolean updateSegment(String segment, QualityComplain qualityComplain) throws Exception {
		Date currTime = new Date();
		return this.persistence(qualityComplain);
	}

}
