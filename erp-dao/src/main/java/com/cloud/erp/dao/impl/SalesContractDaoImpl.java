/**
 * @Title:  SalesContractDaoImpl.java
 * @Package:  com.cloud.erp.dao.impl
 * @Description:  
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年6月15日 上午10:48:02
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

import com.cloud.erp.dao.SalesContractDao;
import com.cloud.erp.dao.common.GeneralDaoSupport;
import com.cloud.erp.dao.common.MultiEntryDaoSupport;
import com.cloud.erp.dao.common.ReferenceDao;
import com.cloud.erp.dao.common.StatusFields;
import com.cloud.erp.dao.exception.UpdateReferenceException;
import com.cloud.erp.entities.table.SalesContract;
import com.cloud.erp.entities.table.SalesContractEntry;
import com.cloud.erp.entities.table.SalesContractScheme;
import com.cloud.erp.utils.PageUtil;

/**
 * @ClassName  SalesContractDaoImpl
 * @Description  
 * @author  bollen bollen@live.cn
 * @date  2015年6月15日 上午10:48:02
 *
 */
@Repository("salesContractDao")
public class SalesContractDaoImpl implements SalesContractDao {
	
	private static final String ENTRIES = "entries";
	private static final String SCHEME = "schemes";

	@Resource
	private GeneralDaoSupport<SalesContract> generalDao;
	
	@Resource
	private MultiEntryDaoSupport<SalesContract> multiEntryDao;
	
	@Resource
	private ReferenceDao<SalesContract> referenceDao;
	
	@Override
	public List<SalesContract> findAll(Map<String, Object> params, PageUtil pageUtil) {
		return generalDao.findAll(this.getClass(), params, pageUtil);
	}
	
	@Override
	public long getCount(Map<String, Object> params) {
		return generalDao.getCount(this.getClass(), params);
	}
	
	@Override
	public SalesContract get(Integer id) {
		return generalDao.get(SalesContract.class, id);
	}
	
	@Override
	public void update(SalesContract master) {
		generalDao.update(master);
	}
	
	@Override
	public boolean updateReference(Class<SalesContract> clazz, String number,
			boolean mode) throws UpdateReferenceException {
		return referenceDao.updateReference(clazz, number, mode);
	}

	@Override
	public boolean persistence(SalesContract master) throws Exception {
		return generalDao.persistence(master, new StatusFields());
	}

	@Override
	public boolean deleteToUpdate(Integer pid) {
		return generalDao.deleteToUpdate(SalesContract.class, pid, new StatusFields());
	}

	@Override
	public <E> List<E> findEntriesById(Integer pid, Class<E> clazz) {
		if(clazz == SalesContractEntry.class){
			return multiEntryDao.findEntriesById(SalesContract.class, pid, clazz, ENTRIES);
		}else if(clazz == SalesContractScheme.class){
			return multiEntryDao.findEntriesById(SalesContract.class, pid, clazz, SCHEME);
		}
		return null;
	}

	@Override
	public <E> boolean persistenceEntries(SalesContract master,
			Map<String, List<E>> entries) {
		return multiEntryDao.persistenceEntries(master, entries, new StatusFields());
	}

	@Override
	public boolean deleteToUpdateEntries(Integer pid) {
		return multiEntryDao.deleteToUpdateEntries(SalesContract.class, pid, new StatusFields(), ENTRIES, SCHEME);
	}
}
