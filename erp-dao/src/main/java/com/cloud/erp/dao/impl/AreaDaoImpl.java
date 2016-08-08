/**
 * @Title:  AreaDaoImpl.java
 * @Package:  com.cloud.erp.dao.impl
 * @Description:  
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年5月12日 上午10:29:23
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

import org.springframework.stereotype.Repository;

import com.cloud.erp.dao.AreaDao;
import com.cloud.erp.dao.common.GeneralDaoSupport;
import com.cloud.erp.dao.common.StatusFields;
import com.cloud.erp.entities.table.Area;
import com.cloud.erp.utils.PageUtil;

/**
 * @ClassName  AreaDaoImpl
 * @Description  
 * @author  bollen bollen@live.cn
 * @date  2015年5月12日 上午10:29:23
 *
 */
@Repository("areaDao")
public class AreaDaoImpl implements AreaDao {
	
	private final StatusFields statusFields = new StatusFields();
	
	{
		statusFields.setInterId("areaId");
	}
	
	@Resource
	private GeneralDaoSupport<Area> generalDao;

	@Override
	public List<Area> findAll(Map<String, Object> params,
			PageUtil pageUtil) {
		return generalDao.findAll(Area.class, params, pageUtil);
	}
	@Override
	public long getCount(Map<String, Object> params) {
		return generalDao.getCount(Area.class, params);
	}
	@Override
	public Area get(Integer id) {
		return generalDao.get(Area.class, id);
	}
	@Override
	public void update(Area master) {
		generalDao.update(master);
	}
	@Override
	public boolean persistence(Area master) throws Exception {
		return generalDao.persistence(master, statusFields);
	}
	@Override
	public boolean deleteToUpdate(Integer pid) {
		return generalDao.deleteToUpdate(Area.class, pid, statusFields);
	}

}
