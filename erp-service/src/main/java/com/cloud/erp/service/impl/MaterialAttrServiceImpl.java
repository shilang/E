package com.cloud.erp.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloud.erp.dao.MaterialAttrDao;
import com.cloud.erp.entities.table.ICItemAttr;
import com.cloud.erp.service.MaterialAttrService;
import com.cloud.erp.utils.PageUtil;

@Service("materialAttrService")
public class MaterialAttrServiceImpl implements MaterialAttrService{

	@Autowired
	private MaterialAttrDao materialAttrDao;
	
	@Override
	public List<ICItemAttr> findMaterialAttrsByPId(Integer pid) {
		return materialAttrDao.findMaterialAttrsByPId(pid);
	}

	@Override
	public List<ICItemAttr> findMaterialAttrs() {
		return materialAttrDao.findAll(null, null);
	}

	@Override
	public List<ICItemAttr> findAll(Map<String, Object> params,
			PageUtil pageUtil) {
		return materialAttrDao.findAll(params, pageUtil);
	}

	@Override
	public long getCount(Map<String, Object> params) {
		return materialAttrDao.getCount(params);
	}

	@Override
	public ICItemAttr get(Integer id) {
		return materialAttrDao.get(id);
	}

	@Override
	public void update(ICItemAttr master) {
		materialAttrDao.update(master);
	}

	@Override
	public boolean persistence(ICItemAttr master) throws Exception {
		return materialAttrDao.persistence(master);
	}

	@Override
	public boolean deleteToUpdate(Integer pid) {
		return materialAttrDao.deleteToUpdate(pid);
	}

}
