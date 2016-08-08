/**
 * @Title:  CustomerDaoImpl.java
 * @Package:  com.cloud.erp.dao.impl
 * @Description:  
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年5月12日 上午9:18:26
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

import com.cloud.erp.dao.CustomerDao;
import com.cloud.erp.dao.common.GeneralDaoSupport;
import com.cloud.erp.dao.common.StatusFields;
import com.cloud.erp.entities.table.Customer;
import com.cloud.erp.utils.PageUtil;

/**
 * @ClassName CustomerDaoImpl
 * @Description 
 * @author bollen bollen@live.cn
 * @date 2015年5月12日 上午9:18:26
 *
 */
@Repository("customerDao")
public class CustomerDaoImpl implements CustomerDao {
	
	private final StatusFields statusFields = new StatusFields();
	
	{
		statusFields.setInterId("customerId");
	}

	@Resource
	private GeneralDaoSupport<Customer> generalDao;

	@Override
	public List<Customer> findAll(Map<String, Object> params, PageUtil pageUtil) {
		return generalDao.findAll(Customer.class, params, pageUtil);
	}

	@Override
	public long getCount(Map<String, Object> params) {
		return generalDao.getCount(Customer.class, params);
	}

	@Override
	public Customer get(Integer id) {
		return generalDao.get(Customer.class, id);
	}

	@Override
	public void update(Customer master) {
		generalDao.update(master);
	}

	@Override
	public boolean persistence(Customer master) throws Exception {
		return generalDao.persistence(master, statusFields);
	}

	@Override
	public boolean deleteToUpdate(Integer pid) {
		return generalDao.deleteToUpdate(Customer.class, pid, statusFields);
	}

}
