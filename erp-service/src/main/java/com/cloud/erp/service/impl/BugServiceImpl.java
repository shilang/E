/**
 * @Title:  BugServiceImpl.java
 * @Package:  com.cloud.erp.service.impl
 * @Description:  TODO
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
 * @ClassName  BugServiceImpl
 * @Description  TODO
 * @author  bollen bollen@live.cn
 * @date  2015年3月31日 上午10:25:21
 *
 */
@Service("bugService")
public class BugServiceImpl implements BugService {

	private BugDao bugDao;
	
	/**
	 * @param bugDao the bugDao to set
	 */
	@Autowired
	public void setBugDao(BugDao bugDao) {
		this.bugDao = bugDao;
	}
	/* (non-Javadoc)
	 * @see com.cloud.erp.service.BugService#findBugList(java.util.Map, com.cloud.erp.utils.PageUtil)
	 */
	@Override
	public List<Bug> findBugList(Map<String, Object> param, PageUtil pageUtil) {
		// TODO Auto-generated method stub
		return bugDao.findBugList(param, pageUtil);
	}

	/* (non-Javadoc)
	 * @see com.cloud.erp.service.BugService#getCount(java.util.Map, com.cloud.erp.utils.PageUtil)
	 */
	@Override
	public Long getCount(Map<String, Object> param, PageUtil pageUtil) {
		// TODO Auto-generated method stub
		return bugDao.getCount(param, pageUtil);
	}

	/* (non-Javadoc)
	 * @see com.cloud.erp.service.BugService#addBug(com.cloud.erp.entities.Bug)
	 */
	@Override
	public boolean addBug(Bug bug) {
		// TODO Auto-generated method stub
		return bugDao.addBug(bug);
	}

	/* (non-Javadoc)
	 * @see com.cloud.erp.service.BugService#delBug(java.lang.Integer)
	 */
	@Override
	public boolean delBug(Integer bugId) {
		// TODO Auto-generated method stub
		return bugDao.delBug(bugId);
	}

}
