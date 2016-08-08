/**
 * @Title:  BugServiceImpl.java
 * @Package:  com.cloud.erp.service.impl
 * @Description:  
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年3月31日 上午10:25:21
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

import com.cloud.erp.dao.BugDao;
import com.cloud.erp.entities.table.Bug;
import com.cloud.erp.service.BugService;
import com.cloud.erp.utils.PageUtil;

/**
 * @ClassName BugServiceImpl
 * @Description 
 * @author bollen bollen@live.cn
 * @date 2015年3月31日 上午10:25:21
 *
 */
@Service("bugService")
public class BugServiceImpl implements BugService {

	@Autowired
	private BugDao bugDao;

	@Override
	public List<Bug> findAll(Map<String, Object> params, PageUtil pageUtil) {
		return bugDao.findAll(params, pageUtil);
	}

	@Override
	public long getCount(Map<String, Object> params) {
		return bugDao.getCount(params);
	}

	@Override
	public Bug get(Integer id) {
		return bugDao.get(id);
	}

	@Override
	public void update(Bug master) {
		bugDao.update(master);
	}

	@Override
	public boolean persistence(Bug master) throws Exception {
		return bugDao.persistence(master);
	}

	@Override
	public boolean deleteToUpdate(Integer pid) {
		return bugDao.deleteToUpdate(pid);
	}

}
