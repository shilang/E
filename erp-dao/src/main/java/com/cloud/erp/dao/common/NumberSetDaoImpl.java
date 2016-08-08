/**
 * @Title:  NumberSetDaoImpl.java
 * @Package:  com.cloud.erp.dao.impl
 * @Description:  
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年5月19日 下午2:38:35
 * @version:  v1.0
 *
 * History:
 * Date		Author		Version
 * ---------------------------------------------
 * <reasons>
 */
package com.cloud.erp.dao.common;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cloud.erp.entities.table.NumberSet;
import com.cloud.erp.exceptions.NumberIncrementException;

/**
 * @ClassName  NumberSetDaoImpl
 * @Description  
 * @author  bollen bollen@live.cn
 * @date  2015年5月19日 下午2:38:35
 *
 */
@Repository("numberSetDao")
public class NumberSetDaoImpl implements NumberSetDao {

	@Autowired
	private BaseDao<NumberSet> baseDao;
	
	@Override
	public NumberSet getNumberSet(Integer classId) {
		String hql = "from NumberSet t where t.classId=:classId";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("classId", classId);
		return baseDao.get(hql, params);
	}
	
	@Override
	public NumberSet getNumberSet(String prefix) {
		String hql = "from NumberSet t where t.prefix=:prefix";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("prefix", prefix);
		return baseDao.get(hql, params);
	}
	
	@Override
	public void increment(Integer classId) throws NumberIncrementException {
		String hql = "update NumberSet t set t.number = t.number + 1 where t.classId = " + classId;
		if(baseDao.executeUpdate(hql) <= 0){
			throw new NumberIncrementException("Number increment exception");
		}
	}
}
