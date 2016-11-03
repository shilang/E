/**
 * @Title:  AuxiliaryResMessageDaoImpl.java
 * @Package:  com.cloud.erp.dao.impl
 * @Description:  
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年5月5日 上午11:17:18
 * @version:  v1.0
 *
 * History:
 * Date		Author		Version
 * ---------------------------------------------
 * <reasons>
 */
package com.cloud.erp.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cloud.erp.dao.AuxiliaryResMessageDao;
import com.cloud.erp.dao.common.BaseDao;
import com.cloud.erp.dao.common.GeneralDaoSupport;
import com.cloud.erp.dao.common.StatusFields;
import com.cloud.erp.entities.table.AuxiliaryResMessage;
import com.cloud.erp.utils.PageUtil;

/**
 * @ClassName AuxiliaryResMessageDaoImpl
 * @Description 
 * @author bollen bollen@live.cn
 * @date 2015年5月5日 上午11:17:18
 *
 */
@Repository("auxiliaryResMessageDao")
public class AuxiliaryResMessageDaoImpl implements AuxiliaryResMessageDao {
	
	private final StatusFields statusFields = new StatusFields();
	
	{
		statusFields.setInterId("messageId");
	}

	@Resource
	private GeneralDaoSupport<AuxiliaryResMessage> generalDao;

	@Autowired
	private BaseDao<AuxiliaryResMessage> baseDao;

	@Override
	public List<AuxiliaryResMessage> findAuxiliaryResMessages(Integer resId) {
		if(resId == null){
			return new ArrayList<AuxiliaryResMessage>();
		}
		
		String hql = " from AuxiliaryResMessage t where t.status='A' and t.resId="
				+ resId;
		return baseDao.find(hql);
	}

	@Override
	public Long getCount(Integer resId) {
		if(resId == null){
			return 0L;
		}
		
		String hql = " select count(*) from AuxiliaryResMessage t where t.status='A' and t.resId="
				+ resId;
		return baseDao.count(hql, null);
	}

	@Override
	public List<AuxiliaryResMessage> findAll(Map<String, Object> params, PageUtil pageUtil) {
		return generalDao.findAll(AuxiliaryResMessage.class, params, pageUtil);
	}

	@Override
	public long getCount(Map<String, Object> params) {
		return generalDao.getCount(AuxiliaryResMessage.class, params);
	}

	@Override
	public AuxiliaryResMessage get(Integer id) {
		return generalDao.get(AuxiliaryResMessage.class, id);
	}

	@Override
	public void update(AuxiliaryResMessage master) {
		generalDao.update(master);
	}

	@Override
	public boolean persistence(AuxiliaryResMessage master) throws Exception {
		return generalDao.persistence(master, statusFields);
	}

	@Override
	public boolean deleteToUpdate(Integer pid) {
		return generalDao.deleteToUpdate(AuxiliaryResMessage.class, pid, statusFields);
	}

}
