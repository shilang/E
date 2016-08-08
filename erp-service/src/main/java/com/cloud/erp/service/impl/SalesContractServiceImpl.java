/**
 * @Title:  SalesContractServiceImpl.java
 * @Package:  com.cloud.erp.service.impl
 * @Description:  
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年6月15日 上午11:27:51
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

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloud.erp.dao.SalesContractDao;
import com.cloud.erp.entities.table.SalesContract;
import com.cloud.erp.entities.table.SalesContractEntry;
import com.cloud.erp.entities.table.SalesContractScheme;
import com.cloud.erp.exceptions.NumberIncrementException;
import com.cloud.erp.service.SalesContractService;
import com.cloud.erp.service.common.AutoNumber;
import com.cloud.erp.utils.PageUtil;

/**
 * @ClassName SalesContractServiceImpl
 * @Description 
 * @author bollen bollen@live.cn
 * @date 2015年6月15日 上午11:27:51
 *
 */
@Service("salesContractServiceImpl")
public class SalesContractServiceImpl implements SalesContractService {

	@Autowired
	private SalesContractDao salesContractDao;

	@Resource
	private AutoNumber<SalesContractEntry> autoNumber;

	@Override
	public List<SalesContract> findAll(Map<String, Object> params,
			PageUtil pageUtil) {
		return salesContractDao.findAll(params, pageUtil);
	}
	
	@Override
	public long getCount(Map<String, Object> params) {
		return salesContractDao.getCount(params);
	}

	@Override
	public SalesContract get(Integer id) {
		return salesContractDao.get(id);
	}

	@Override
	public void update(SalesContract master) {
		salesContractDao.update(master);
	}

	@Override
	public boolean persistence(SalesContract master) throws Exception {
		return salesContractDao.persistence(master);
	}

	@Override
	public <E> boolean persistenceEntries(SalesContract master,
			Map<String, List<E>> entries) {
		return salesContractDao.persistenceEntries(master, entries);
	}
	
	@Override
	public boolean persistence(SalesContract salesContract,
			Map<String, List<SalesContractEntry>> entries,
			Map<String, List<SalesContractScheme>> schemes) throws Exception {
		persistence(salesContract);
		persistenceEntries(salesContract, entries);
		persistenceEntries(salesContract, schemes);
		return true;
	}
	
	@Override
	public boolean deleteToUpdate(Integer pid) {
		return salesContractDao.deleteToUpdate(pid);
	}
	
	@Override
	public boolean deleteToUpdateEntries(Integer pid) {
		return salesContractDao.deleteToUpdateEntries(pid);
	}
	
	@Override
	public boolean deleteToUpdateAll(Integer pid) {
		return deleteToUpdate(pid) && deleteToUpdateEntries(pid);
	}

	@Override
	public String getNumber(List<SalesContractEntry> list) {
		return autoNumber.getNumber(list);
	}

	@Override
	public boolean isThisType(String number, int classId) {
		return autoNumber.isThisType(number, classId);
	}

	@Override
	public void increment(Integer classId) throws NumberIncrementException {
		autoNumber.increment(classId);
	}

	@Override
	public <E> List<E> findEntriesById(Integer pid, Class<E> clazz) {
		return salesContractDao.findEntriesById(pid, clazz);
	}

}
