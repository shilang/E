package com.cloud.erp.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloud.erp.dao.DataPermissionDao;
import com.cloud.erp.entities.table.DataPermission;
import com.cloud.erp.service.DataPermissionService;
import com.cloud.erp.utils.PageUtil;

@Service("dataPermissionService")
public class DataPermissionServiceImpl implements DataPermissionService {

	@Autowired
	private DataPermissionDao dataPermissionDao;

	@Override
	public boolean persistenceDataPermission(Map<String, List<DataPermission>> entries) {
		return dataPermissionDao.persistenceDataPermission(entries);
	}

	@Override
	public List<DataPermission> findAll(Map<String, Object> params,
			PageUtil pageUtil) {
		return dataPermissionDao.findAll(params, pageUtil);
	}

	@Override
	public DataPermission get(Integer id) {
		return dataPermissionDao.get(id);
	}

	@Override
	public void update(DataPermission master) {
		dataPermissionDao.update(master);
	}

	@Override
	public boolean persistence(DataPermission master) throws Exception {
		return dataPermissionDao.persistence(master);
	}

	@Override
	public boolean deleteToUpdate(Integer pid) {
		return dataPermissionDao.deleteToUpdate(pid);
	}

	@Override
	public long getCount(Map<String, Object> params) {
		return dataPermissionDao.getCount(params);
	}

}
