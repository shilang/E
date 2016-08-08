package com.cloud.erp.service.common;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloud.erp.dao.common.BaseDao;

@SuppressWarnings("unchecked")
@Service("baseService")
public class BaseServiceImpl implements BaseService{

	@SuppressWarnings("rawtypes")
	@Autowired
	private BaseDao baseDao;
	
	@Override
	public Serializable save(Object object) {
		return baseDao.save(object);
	}

	@Override
	public void delete(Object object) {
		baseDao.delete(object);
	}

	@Override
	public void update(Object object) {
		baseDao.update(object);
	}

	@Override
	public void saveOrUpdate(Object object) {
		baseDao.saveOrUpdate(object);
	}

	@Override
	public Object get(Class<?> clazz, Serializable id) {
		return baseDao.get(clazz, id);
	}

}
