/**
 * @Title:  AuxiliaryResTypeDaoImpl.java
 * @Package:  com.cloud.erp.dao.impl
 * @Description:  TODO
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年4月30日 下午2:26:12
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

import com.cloud.erp.dao.AuxiliaryResTypeDao;
import com.cloud.erp.dao.BaseDao;
import com.cloud.erp.entities.table.AuxiliaryResType;
import com.cloud.erp.utils.Constants;

/**
 * @ClassName  AuxiliaryResTypeDaoImpl
 * @Description  TODO
 * @author  bollen bollen@live.cn
 * @date  2015年4月30日 下午2:26:12
 *
 */
@Repository("auxiliaryResTypeDao")
public class AuxiliaryResTypeDaoImpl implements AuxiliaryResTypeDao {

	private BaseDao<AuxiliaryResType> baseDao;
	
	/**
	 * @param baseDao the baseDao to set
	 */
	@Autowired
	public void setBaseDao(BaseDao<AuxiliaryResType> baseDao) {
		this.baseDao = baseDao;
	}
	/* (non-Javadoc)
	 * @see com.cloud.erp.dao.AuxiliaryResTypeDao#findAuxiliaryResTypes(java.util.Map)
	 */
	@Override
	public List<AuxiliaryResType> findAuxiliaryResTypes() {
		// TODO Auto-generated method stub
		String hql = "from AuxiliaryResType t where t.status='A'";
		return baseDao.find(hql);
	}

	/* (non-Javadoc)
	 * @see com.cloud.erp.dao.AuxiliaryResTypeDao#persistenceAuxiliaryResType(com.cloud.erp.entities.table.AuxiliaryResType)
	 */
	@Override
	public boolean persistenceAuxiliaryResType(AuxiliaryResType auxiliaryResType) {
		// TODO Auto-generated method stub
		Integer userId = Constants.getCurrentUser().getUserId();
		if(null == auxiliaryResType.getResId() || "".equals(auxiliaryResType.getResId())){
			auxiliaryResType.setCreated(new Date());
			auxiliaryResType.setCreater(userId);
			auxiliaryResType.setLastmod(new Date());
			auxiliaryResType.setModifier(userId);
			auxiliaryResType.setStatus(Constants.PERSISTENCE_STATUS);
			baseDao.save(auxiliaryResType);
		}else {
			auxiliaryResType.setLastmod(new Date());
			auxiliaryResType.setModifier(userId);
			baseDao.update(auxiliaryResType);
		}
		
		return true;
	}

	/* (non-Javadoc)
	 * @see com.cloud.erp.dao.AuxiliaryResTypeDao#delAuxiliaryResType(java.lang.Integer)
	 */
	@Override
	public boolean delAuxiliaryResType(Integer id) {
		// TODO Auto-generated method stub
		String hql = " from AuxiliaryResType t where t.status='A' and t.pid=" + id;
		List<AuxiliaryResType> list = baseDao.find(hql);
		if(list.size() != 0){
			return false;
		}else {
			AuxiliaryResType auxiliaryResType = baseDao.get(AuxiliaryResType.class, id);
			auxiliaryResType.setLastmod(new Date());
			auxiliaryResType.setModifier(Constants.getCurrentUser().getUserId());
			auxiliaryResType.setStatus(Constants.PERSISTENCE_DELETE_STATUS);
			baseDao.deleteToUpdate(auxiliaryResType);
			return true;
		}
	}

}
