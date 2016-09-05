package com.cloud.erp.service.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloud.erp.dao.common.BaseDao;
import com.cloud.erp.utils.BusinessUtil;
import com.cloud.erp.utils.ObjectUtil;
import com.cloud.erp.utils.Reflect;

@Service("copyService")
public class CopyServiceSupport implements CopyService {
	
	private static final String ID = "interId";
	
	@SuppressWarnings("rawtypes")
	@Autowired
	private BaseDao baseDao;
	
	@SuppressWarnings("unchecked")
	@Override
	public void save(Class<?> clazz, Integer id) throws Exception {
		Object obj = baseDao.get(clazz, id);
		BusinessUtil.loadCollectionAttr(obj);
		Object newObj = ObjectUtil.deepClone(obj);
		Reflect.invokeSetMethodAllowNull(newObj, ID, Integer.class, null);
		baseDao.save(newObj);
	}

}
