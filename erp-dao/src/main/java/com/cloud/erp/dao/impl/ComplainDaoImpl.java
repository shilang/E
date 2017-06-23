package com.cloud.erp.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.cloud.erp.dao.ComplainDao;
import com.cloud.erp.dao.common.GeneralDaoSupport;
import com.cloud.erp.dao.common.StatusFields;
import com.cloud.erp.entities.table.QualityComplain;
import com.cloud.erp.utils.PageUtil;

@Repository("complainDao")
public class ComplainDaoImpl implements ComplainDao {

	@Resource
	private GeneralDaoSupport<QualityComplain> generalDao;
	
	
	@Override
	public List<QualityComplain> findAll(Map<String, Object> params,
			PageUtil pageUtil) {
		return generalDao.findAll(this.getClass(), params, pageUtil);
	}

	@Override
	public long getCount(Map<String, Object> params) {
		return generalDao.getCount(this.getClass(), params);
	}

	@Override
	public QualityComplain get(Integer id) {
		return generalDao.get(QualityComplain.class, id);
	}

	@Override
	public void update(QualityComplain master) {
		generalDao.update(master);
	}

	@Override
	public boolean persistence(QualityComplain master) throws Exception {
		return generalDao.persistence(master, new StatusFields());
	}

	@Override
	public boolean deleteToUpdate(Integer pid) {
		return generalDao.deleteToUpdate(QualityComplain.class, pid, new StatusFields());
	}

}
