/**
 * @Title:  LogsDaoImpl.java
 * @Package:  com.cloud.erp.dao.impl
 * @Description:  TODO
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年3月19日 下午3:57:31
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
import com.cloud.erp.dao.LogDao;
import com.cloud.erp.entities.Log;
import com.cloud.erp.utils.Constants;
import com.cloud.erp.utils.PageUtil;

/**
 * @ClassName  LogsDaoImpl
 * @Description  TODO
 * @author  bollen bollen@live.cn
 * @date  2015年3月19日 下午3:57:31
 *
 */
@Repository("logDao")
public class LogDaoImpl implements LogDao {

	private BaseDao<Log> baseDao;
	
	/**
	 * @param baseDao the baseDao to set
	 */
	@Autowired
	public void setBaseDao(BaseDao<Log> baseDao) {
		this.baseDao = baseDao;
	}
	/* (non-Javadoc)
	 * @see com.cloud.erp.dao.LogsDao#findLogsAllList(java.util.Map, com.cloud.erp.utils.PageUtil)
	 */
	@Override
	public List<Log> findLogAllList(Map<String, Object> map, PageUtil pageUtil) {
		// TODO Auto-generated method stub
		String hql = "from Log t where 1=1";
		hql+=Constants.getSearchConditionsHQL("t", map);
		hql+=Constants.getGradeSearchConditionsHQL("t", pageUtil);
		
		return baseDao.find(hql, map, pageUtil.getPage(), pageUtil.getRows());
	}

	/* (non-Javadoc)
	 * @see com.cloud.erp.dao.LogsDao#getCount(java.util.Map, com.cloud.erp.utils.PageUtil)
	 */
	@Override
	public Long getCount(Map<String, Object> map, PageUtil pageUtil) {
		// TODO Auto-generated method stub
		String hql = "select count(*) from Log t where 1=1";
		hql+=Constants.getSearchConditionsHQL("t", map);
		hql+=Constants.getGradeSearchConditionsHQL("t", pageUtil);
		
		return baseDao.count(hql, map);
	}

	/* (non-Javadoc)
	 * @see com.cloud.erp.dao.LogsDao#persistenceLogs(com.cloud.erp.entities.Log)
	 */
	@Override
	public boolean persistenceLog(Log l) {
		// TODO Auto-generated method stub
		if(null == l.getLogId() || "".equals(l.getLogId())){
			l.setLogDate(new Date());
			l.setUserId(Constants.getCurrentUser().getUserId());
			baseDao.save(l);
		}else {
			l.setUserId(Constants.getCurrentUser().getUserId());
			baseDao.update(l);
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see com.cloud.erp.dao.LogsDao#delLogs(java.lang.Integer)
	 */
	@Override
	public boolean delLog(Integer logId) {
		// TODO Auto-generated method stub
		baseDao.delete(baseDao.get(Log.class, logId));
		return true;
	}

}
