/**
 * @Title:  AuxiliaryResTypeDaoImpl.java
 * @Package:  com.cloud.erp.dao.impl
 * @Description:  
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年4月30日 下午2:26:12
 * @version:  v1.0
 *
 * History:
 * Date		Author		Version
 * ---------------------------------------------
 * <reasons>
 */
package com.cloud.erp.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cloud.erp.dao.AuxiliaryResTypeDao;
import com.cloud.erp.dao.common.BaseDao;
import com.cloud.erp.dao.common.GeneralDaoSupport;
import com.cloud.erp.dao.common.StatusFields;
import com.cloud.erp.entities.table.AuxiliaryResType;
import com.cloud.erp.utils.PageUtil;

/**
 * @ClassName  AuxiliaryResTypeDaoImpl
 * @Description  
 * @author  bollen bollen@live.cn
 * @date  2015年4月30日 下午2:26:12
 *
 */
@Repository("auxiliaryResTypeDao")
public class AuxiliaryResTypeDaoImpl implements AuxiliaryResTypeDao {
	
	private final StatusFields statusFields = new StatusFields();
	
	{
		statusFields.setInterId("resId");
	}
	
	@Resource
	private GeneralDaoSupport<AuxiliaryResType> generalDao;

	@Autowired
	private BaseDao<AuxiliaryResType> baseDao;
	
	@Override
	public List<AuxiliaryResType> findAll(Map<String, Object> params, PageUtil pageUtil) {
		return generalDao.findAll(AuxiliaryResType.class, params, pageUtil);
	}
	
	@Override
	public long getCount(Map<String, Object> params) {
		return generalDao.getCount(AuxiliaryResType.class, params);
	}
	
	@Override
	public AuxiliaryResType get(Integer id) {
		return generalDao.get(AuxiliaryResType.class, id);
	}
	@Override
	public void update(AuxiliaryResType master) {
		generalDao.update(master);
	}
	
	@Override
	public boolean persistence(AuxiliaryResType master) throws Exception {
		return generalDao.persistence(master, statusFields);
	}
	
	@Override
	public boolean deleteToUpdate(Integer pid) {
		String hql = " from AuxiliaryResType t where t.status='A' and t.pid=" + pid;
		List<AuxiliaryResType> list = baseDao.find(hql);
		if(list.size() != 0){
			return false;
		}else {
			return generalDao.deleteToUpdate(AuxiliaryResType.class, pid, statusFields);
		}
	}
}
