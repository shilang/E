/**
 * @Title:  MeasureUnitDaoImpl.java
 * @Package:  com.cloud.erp.dao.impl
 * @Description:  
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年5月20日 下午2:15:26
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

import com.cloud.erp.dao.MeasureUnitDao;
import com.cloud.erp.dao.common.BaseDao;
import com.cloud.erp.dao.common.GeneralDaoSupport;
import com.cloud.erp.dao.common.StatusFields;
import com.cloud.erp.entities.table.MeasureUnit;
import com.cloud.erp.utils.PageUtil;

/**
 * @ClassName MeasureUnitDaoImpl
 * @Description 
 * @author bollen bollen@live.cn
 * @date 2015年5月20日 下午2:15:26
 *
 */
@Repository("measureUnitDao")
public class MeasureUnitDaoImpl implements MeasureUnitDao {
	
	private final StatusFields statusFields = new StatusFields();
	
	{
		statusFields.setInterId("measureUnitId");
	}

	@Resource
	private GeneralDaoSupport<MeasureUnit> generalDao;

	@Autowired
	private BaseDao<MeasureUnit> baseDao;

	@Override
	public List<MeasureUnit> findAll(Map<String, Object> params, PageUtil pageUtil) {
		return generalDao.findAll(MeasureUnit.class, params, pageUtil);
	}

	@Override
	public long getCount(Map<String, Object> params) {
		return generalDao.getCount(MeasureUnit.class, params);
	}

	@Override
	public MeasureUnit get(Integer id) {
		return generalDao.get(MeasureUnit.class, id);
	}

	@Override
	public void update(MeasureUnit master) {
		generalDao.update(master);
	}

	@Override
	public boolean persistence(MeasureUnit master) throws Exception {
		return generalDao.persistence(master, statusFields);
	}

	@Override
	public boolean deleteToUpdate(Integer pid) {
		return generalDao.deleteToUpdate(MeasureUnit.class, pid, statusFields);
	}

}
