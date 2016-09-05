package com.cloud.erp.service.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloud.erp.dao.common.BaseDao;

@Service("copyService")
public class CopyServiceSupport implements CopyService {
	
	@SuppressWarnings("rawtypes")
	@Autowired
	private BaseDao baseDao;
	
	@SuppressWarnings("unchecked")
	@Override
	public void save(Class<?> clazz, Integer id) {
		Object object = baseDao.get(clazz, id);
		baseDao.save(object);
	}

}
