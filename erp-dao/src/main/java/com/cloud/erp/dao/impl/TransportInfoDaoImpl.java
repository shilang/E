package com.cloud.erp.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cloud.erp.dao.TransportInfoDao;
import com.cloud.erp.dao.common.BaseDao;
import com.cloud.erp.dao.common.GeneralDaoSupport;
import com.cloud.erp.dao.common.StatusFields;
import com.cloud.erp.entities.table.TransportInfo;
import com.cloud.erp.utils.PageUtil;

@Repository("transportInfoDao")
public class TransportInfoDaoImpl implements TransportInfoDao {
	
	private final StatusFields statusFields = new StatusFields();
	
	{
		statusFields.setInterId("id");
	}
	
	@Resource
	private GeneralDaoSupport<TransportInfo> generalDao;
	
	@Autowired
	private BaseDao<TransportInfo> baseDao;

	@Override
	public List<TransportInfo> findAll(Map<String, Object> params, PageUtil pageUtil) {
		
		return null;
	}

	@Override
	public long getCount(Map<String, Object> params) {
		
		return 0;
	}

	@Override
	public TransportInfo get(Integer id) {
		
		return generalDao.get(TransportInfo.class, id);
	}

	@Override
	public void update(TransportInfo master) {
		
		generalDao.update(master);
	}

	@Override
	public boolean persistence(TransportInfo master) throws Exception {
		
		return generalDao.persistence(master, statusFields);
	}

	@Override
	public boolean deleteToUpdate(Integer pid) {
		
		return generalDao.deleteToUpdate(TransportInfo.class, pid, statusFields);
	}

	@Override
	public List<TransportInfo> findByPid(Integer pid) {
		
		String hql = "from TransportInfo t where t.status=:status and t.master.interId =:interId";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("status", "A");
		params.put("interId", pid);
		List<TransportInfo> transportInfos = baseDao.find(hql, params);
		return transportInfos;
	}

}
