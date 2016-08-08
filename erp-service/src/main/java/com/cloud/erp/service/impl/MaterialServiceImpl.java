/**
 * @Title:  MaterialServiceImpl.java
 * @Package:  com.cloud.erp.service.impl
 * @Description:  
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年5月22日 下午2:24:28
 * @version:  v1.0
 *
 * History:
 * Date		Author		Version
 * ---------------------------------------------
 * <reasons>
 */
package com.cloud.erp.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloud.erp.dao.MaterialDao;
import com.cloud.erp.entities.table.ICItemCore;
import com.cloud.erp.service.MaterialService;
import com.cloud.erp.utils.PageUtil;

/**
 * @ClassName MaterialServiceImpl
 * @Description 
 * @author bollen bollen@live.cn
 * @date 2015年5月22日 下午2:24:28
 *
 */
@Service("materialService")
public class MaterialServiceImpl implements MaterialService {

	@Autowired
	private MaterialDao materialDao;

	@Autowired
	public void setMaterialDao(MaterialDao materialDao) {
		this.materialDao = materialDao;
	}

	@Override
	public List<ICItemCore> findAll(Map<String, Object> params,
			PageUtil pageUtil) {
		return materialDao.findAll(params, pageUtil);
	}

	@Override
	public ICItemCore get(Integer id) {
		return materialDao.get(id);
	}

	@Override
	public void update(ICItemCore master) {
		materialDao.update(master);
	}

	@Override
	public boolean persistence(ICItemCore master) throws Exception {
		return materialDao.persistence(master);
	}

	@Override
	public boolean deleteToUpdate(Integer pid) {
		return materialDao.deleteToUpdate(pid);
	}

	@Override
	public long getCount(Map<String, Object> params) {
		return materialDao.getCount(params);
	}

}
