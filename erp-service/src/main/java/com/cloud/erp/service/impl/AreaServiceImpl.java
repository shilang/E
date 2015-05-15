/**
 * @Title:  AreaServiceImpl.java
 * @Package:  com.cloud.erp.service.impl
 * @Description:  TODO
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年5月12日 上午10:26:41
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

import com.cloud.erp.dao.AreaDao;
import com.cloud.erp.entities.table.Area;
import com.cloud.erp.service.AreaService;
import com.cloud.erp.utils.PageUtil;

/**
 * @ClassName  AreaServiceImpl
 * @Description  TODO
 * @author  bollen bollen@live.cn
 * @date  2015年5月12日 上午10:26:41
 *
 */
@Service("areaService")
public class AreaServiceImpl implements AreaService {

	private AreaDao areaDao;
	
	/**
	 * @param areaDao the areaDao to set
	 */
	@Autowired
	public void setAreaDao(AreaDao areaDao) {
		this.areaDao = areaDao;
	}
	/* (non-Javadoc)
	 * @see com.cloud.erp.service.AreaService#findAreas(java.util.Map, com.cloud.erp.utils.PageUtil)
	 */
	@Override
	public List<Area> findAreas(Map<String, Object> params, PageUtil pageUtil) {
		// TODO Auto-generated method stub
		return areaDao.findAreas(params, pageUtil);
	}

	/* (non-Javadoc)
	 * @see com.cloud.erp.service.AreaService#getCount(java.util.Map, com.cloud.erp.utils.PageUtil)
	 */
	@Override
	public Long getCount(Map<String, Object> params, PageUtil pageUtil) {
		// TODO Auto-generated method stub
		return areaDao.getCount(params, pageUtil);
	}

	/* (non-Javadoc)
	 * @see com.cloud.erp.service.AreaService#persistenceArea(com.cloud.erp.entities.table.Area)
	 */
	@Override
	public boolean persistenceArea(Area area) {
		// TODO Auto-generated method stub
		return areaDao.persistenceArea(area);
	}

	/* (non-Javadoc)
	 * @see com.cloud.erp.service.AreaService#delArea(java.lang.Integer)
	 */
	@Override
	public boolean delArea(Integer id) {
		// TODO Auto-generated method stub
		return areaDao.delArea(id);
	}

}
