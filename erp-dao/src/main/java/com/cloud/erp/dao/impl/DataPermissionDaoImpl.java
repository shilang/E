package com.cloud.erp.dao.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cloud.erp.dao.DataPermissionDao;
import com.cloud.erp.dao.common.BaseDao;
import com.cloud.erp.dao.common.GeneralDaoSupport;
import com.cloud.erp.dao.common.StatusFields;
import com.cloud.erp.entities.table.DataPermission;
import com.cloud.erp.utils.Commons;
import com.cloud.erp.utils.Constants;
import com.cloud.erp.utils.PageUtil;

@Repository("dataPermissionDao")
public class DataPermissionDaoImpl implements DataPermissionDao {
	
	private final StatusFields statusFields = new StatusFields();
	
	{
		statusFields.setInterId("id");
	}
	
	@Resource
	private GeneralDaoSupport<DataPermission> generalDao;

	@Autowired
	private BaseDao<DataPermission> baseDao;

	@Override
	public boolean persistenceDataPermission(Map<String,List<DataPermission>> entries) {
		this.addDataPermission(entries.get("addList"));
		this.updateDataPermission(entries.get("updList"));
		this.delDataPermission(entries.get("delList"));
		return true;
	}
	
	private boolean addDataPermission(List<DataPermission> addList){
		if(null != addList && !addList.isEmpty()){
			Integer userId = Commons.getCurrentUser().getUserId();
			for(DataPermission dataPermission : addList){
				dataPermission.setCreated(new Date());
				dataPermission.setCreater(userId);
				dataPermission.setLastmod(new Date());
				dataPermission.setModifier(userId);
				dataPermission.setStatus(Constants.PERSISTENCE_STATUS);
				baseDao.save(dataPermission);
			}
		}
		return true;
	}
	
	private boolean updateDataPermission(List<DataPermission> updList){
		if(null != updList && !updList.isEmpty()){
			Integer userId = Commons.getCurrentUser().getUserId();
			for(DataPermission dataPermission : updList){
				dataPermission.setLastmod(new Date());
				dataPermission.setModifier(userId);
				baseDao.update(dataPermission);
			}
		}
		return true;
	}
	
	private boolean delDataPermission(List<DataPermission> delList){
		if(null != delList && !delList.isEmpty()){
			Integer userId = Commons.getCurrentUser().getUserId();
			for(DataPermission dataPermission : delList){
				dataPermission.setLastmod(new Date());
				dataPermission.setModifier(userId);
				dataPermission.setStatus(Constants.PERSISTENCE_DELETE_STATUS);
				baseDao.deleteToUpdate(dataPermission);
			}
		}
		return true;
	}

	@Override
	public List<DataPermission> findAll(Map<String, Object> params, PageUtil pageUtil) {
		return generalDao.findAll(DataPermission.class, params, pageUtil);
	}

	@Override
	public long getCount(Map<String, Object> params) {
		return generalDao.getCount(DataPermission.class, params);
	}

	@Override
	public DataPermission get(Integer id) {
		return generalDao.get(DataPermission.class, id);
	}

	@Override
	public void update(DataPermission master) {
		generalDao.update(master);
	}

	@Override
	public boolean persistence(DataPermission master) throws Exception {
		return generalDao.persistence(master, statusFields);
	}

	@Override
	public boolean deleteToUpdate(Integer pid) {
		return generalDao.deleteToUpdate(DataPermission.class, pid, statusFields);
	}

}
