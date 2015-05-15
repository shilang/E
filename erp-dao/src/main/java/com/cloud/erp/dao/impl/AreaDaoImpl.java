/**
 * @Title:  AreaDaoImpl.java
 * @Package:  com.cloud.erp.dao.impl
 * @Description:  TODO
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

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cloud.erp.dao.AreaDao;
import com.cloud.erp.dao.BaseDao;
import com.cloud.erp.entities.table.Area;
import com.cloud.erp.utils.Constants;
import com.cloud.erp.utils.PageUtil;

/**
 * @ClassName  AreaDaoImpl
 * @Description  TODO
 * @author  bollen bollen@live.cn
 * @date  2015年5月12日 上午10:29:23
 *
 */
@Repository("areaDao")
public class AreaDaoImpl implements AreaDao {

	private BaseDao<Area> baseDao;
	
	/**
	 * @param baseDao the baseDao to set
	 */
	@Autowired
	public void setBaseDao(BaseDao<Area> baseDao) {
		this.baseDao = baseDao;
	}
	/* (non-Javadoc)
	 * @see com.cloud.erp.dao.AreaDao#findAreas(java.util.Map, com.cloud.erp.utils.PageUtil)
	 */
	@Override
	public List<Area> findAreas(Map<String, Object> params, PageUtil pageUtil) {
		// TODO Auto-generated method stub
		String hql = "from Area t where t.status='A' ";
		hql += Constants.getSearchConditionsHQL("t", params);
		hql += Constants.getGradeSearchConditionsHQL("t", pageUtil);
		
		return baseDao.find(hql, params, pageUtil.getPage(), pageUtil.getRows());
	}

	/* (non-Javadoc)
	 * @see com.cloud.erp.dao.AreaDao#getCount(java.util.Map, com.cloud.erp.utils.PageUtil)
	 */
	@Override
	public Long getCount(Map<String, Object> params, PageUtil pageUtil) {
		// TODO Auto-generated method stub
		String hql = "select count(*) from Area t where t.status='A' ";
		hql += Constants.getSearchConditionsHQL("t", params);
		hql += Constants.getGradeSearchConditionsHQL("t", pageUtil);
		
		return baseDao.count(hql, params);
	}

	/* (non-Javadoc)
	 * @see com.cloud.erp.dao.AreaDao#persistenceArea(com.cloud.erp.entities.table.Area)
	 */
	@Override
	public boolean persistenceArea(Area area) {
		// TODO Auto-generated method stub
		Integer userId = Constants.getCurrentUser().getUserId();
		if(null == area.getAreaId() || "".equals(area.getAreaId())){
			area.setCreated(new Date());
			area.setCreater(userId);
			area.setLastmod(new Date());
			area.setModifier(userId);
			area.setStatus(Constants.PERSISTENCE_STATUS);
			baseDao.save(area);
		}else {
			area.setLastmod(new Date());
			area.setModifier(userId);
			baseDao.update(area);
		}
		
		return true;
	}

	/* (non-Javadoc)
	 * @see com.cloud.erp.dao.AreaDao#delArea(java.lang.Integer)
	 */
	@Override
	public boolean delArea(Integer id) {
		// TODO Auto-generated method stub
		Area area = baseDao.get(Area.class, id);
		area.setLastmod(new Date());
		area.setModifier(Constants.getCurrentUser().getUserId());
		area.setStatus(Constants.PERSISTENCE_DELETE_STATUS);
		baseDao.deleteToUpdate(area);
		
		return true;
	}

}
