/**
 * @Title:  BugDaoImpl.java
 * @Package:  com.cloud.erp.dao.impl
 * @Description:  TODO
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年3月31日 上午10:21:03
 * @version:  v1.0
 *
 * History:
 * Date		Author		Version
 * ---------------------------------------------
 * <reasons>
 */
package com.cloud.erp.dao.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cloud.erp.dao.BaseDao;
import com.cloud.erp.dao.BugDao;
import com.cloud.erp.entities.Bug;
import com.cloud.erp.utils.Constants;
import com.cloud.erp.utils.PageUtil;

/**
 * @ClassName BugDaoImpl
 * @Description TODO
 * @author bollen bollen@live.cn
 * @date 2015年3月31日 上午10:21:03
 *
 */
@Repository("bugDao")
public class BugDaoImpl implements BugDao {

	private BaseDao<Bug> baseDao;

	/**
	 * @param baseDao
	 *            the baseDao to set
	 */
	@Autowired
	public void setBaseDao(BaseDao<Bug> baseDao) {
		this.baseDao = baseDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cloud.erp.dao.BugDao#findBugList(java.util.Map,
	 * com.cloud.erp.utils.PageUtil)
	 */
	@Override
	public List<Bug> findBugList(Map<String, Object> param, PageUtil pageUtil) {
		String hql = "from Bug t where t.status='A' ";
		hql += Constants.getSearchConditionsHQL("t", param);
		hql += Constants.getGradeSearchConditionsHQL("t", pageUtil);
		hql += " order by t.bugId desc";
		return baseDao.find(hql, param, pageUtil.getPage(), pageUtil.getRows());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cloud.erp.dao.BugDao#getCount(java.util.Map,
	 * com.cloud.erp.utils.PageUtil)
	 */
	@Override
	public Long getCount(Map<String, Object> param, PageUtil pageUtil) {

		String hql = "select count(*) from Bug t where t.status='A' ";
		hql += Constants.getSearchConditionsHQL("t", param);
		hql += Constants.getGradeSearchConditionsHQL("t", pageUtil);
		hql += " order by t.bugId desc";
		return baseDao.count(hql, param);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cloud.erp.dao.BugDao#addBug(com.cloud.erp.entities.Bug)
	 */
	@Override
	public boolean addBug(Bug bug) {
		Integer userId = Constants.getCurrentUser().getUserId();
		if (bug.getBugId() == null || "".equals(bug.getBugId())) {
			bug.setLastmod(new Date());
			bug.setCreated(new Date());
			bug.setModifier(userId);
			bug.setStatus(Constants.PERSISTENCE_STATUS);
			bug.setCreater(userId);
			baseDao.save(bug);
		} else {
			bug.setLastmod(new Date());
			bug.setModifier(userId);
			bug.setCreater(userId);
			baseDao.update(bug);
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cloud.erp.dao.BugDao#delBug(java.lang.Integer)
	 */
	@Override
	public boolean delBug(Integer bugId) {
		Bug b = baseDao.get(Bug.class, bugId);
		b.setLastmod(new Date());
		b.setModifier(Constants.getCurrentUser().getUserId());
		b.setStatus(Constants.PERSISTENCE_DELETE_STATUS);
		baseDao.deleteToUpdate(b);
		return true;
	}

}
