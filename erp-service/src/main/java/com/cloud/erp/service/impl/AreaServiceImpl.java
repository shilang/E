/**
 * @Title:  AreaServiceImpl.java
 * @Package:  com.cloud.erp.service.impl
 * @Description:  
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
 * @ClassName AreaServiceImpl
 * @Description 
 * @author bollen bollen@live.cn
 * @date 2015年5月12日 上午10:26:41
 *
 */
@Service("areaService")
public class AreaServiceImpl implements AreaService {

	@Autowired
	private AreaDao areaDao;

	@Override
	public List<Area> findAll(Map<String, Object> params, PageUtil pageUtil) {
		return areaDao.findAll(params, pageUtil);
	}

	@Override
	public long getCount(Map<String, Object> params) {
		return areaDao.getCount(params);
	}

	@Override
	public Area get(Integer id) {
		return areaDao.get(id);
	}

	@Override
	public void update(Area master) {
		areaDao.update(master);
	}

	@Override
	public boolean persistence(Area master) throws Exception {
		return areaDao.persistence(master);
	}

	@Override
	public boolean deleteToUpdate(Integer pid) {
		return areaDao.deleteToUpdate(pid);
	}

}
