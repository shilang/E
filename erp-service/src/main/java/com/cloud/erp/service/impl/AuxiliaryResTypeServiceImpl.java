/**
 * @Title:  AuxiliaryResTypeServiceImpl.java
 * @Package:  com.cloud.erp.service.impl
 * @Description:  
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年4月30日 下午2:33:53
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

import com.cloud.erp.dao.AuxiliaryResTypeDao;
import com.cloud.erp.entities.table.AuxiliaryResType;
import com.cloud.erp.service.AuxiliaryResTypeService;
import com.cloud.erp.utils.PageUtil;

/**
 * @ClassName AuxiliaryResTypeServiceImpl
 * @Description 
 * @author bollen bollen@live.cn
 * @date 2015年4月30日 下午2:33:53
 *
 */
@Service("auxiliaryResTypeService")
public class AuxiliaryResTypeServiceImpl implements AuxiliaryResTypeService {

	@Autowired
	private AuxiliaryResTypeDao auxiliaryResTypeDao;

	@Override
	public List<AuxiliaryResType> findAuxiliaryResTypes() {
		return auxiliaryResTypeDao.findAll(null, null);
	}

	@Override
	public List<AuxiliaryResType> findAll(Map<String, Object> params,
			PageUtil pageUtil) {
		return auxiliaryResTypeDao.findAll(params, pageUtil);
	}

	@Override
	public long getCount(Map<String, Object> params) {
		return auxiliaryResTypeDao.getCount(params);
	}

	@Override
	public AuxiliaryResType get(Integer id) {
		return auxiliaryResTypeDao.get(id);
	}

	@Override
	public void update(AuxiliaryResType master) {
		auxiliaryResTypeDao.update(master);
	}

	@Override
	public boolean persistence(AuxiliaryResType master) throws Exception {
		return auxiliaryResTypeDao.persistence(master);
	}

	@Override
	public boolean deleteToUpdate(Integer pid) {
		return auxiliaryResTypeDao.deleteToUpdate(pid);
	}

}
