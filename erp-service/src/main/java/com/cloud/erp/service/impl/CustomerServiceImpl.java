/**
 * @Title:  CustomerServiceImpl.java
 * @Package:  com.cloud.erp.service.impl
 * @Description:  
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年5月12日 上午9:15:08
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

import com.cloud.erp.dao.CustomerDao;
import com.cloud.erp.entities.table.Customer;
import com.cloud.erp.service.CustomerService;
import com.cloud.erp.utils.PageUtil;

/**
 * @ClassName CustomerServiceImpl
 * @Description 
 * @author bollen bollen@live.cn
 * @date 2015年5月12日 上午9:15:08
 *
 */
@Service("customerService")
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDao customerDao;

	@Override
	public List<Customer> findAll(Map<String, Object> params, PageUtil pageUtil) {
		return customerDao.findAll(params, pageUtil);
	}

	@Override
	public long getCount(Map<String, Object> params) {
		return customerDao.getCount(params);
	}

	@Override
	public Customer get(Integer id) {
		return customerDao.get(id);
	}

	@Override
	public void update(Customer master) {
		customerDao.update(master);
	}

	@Override
	public boolean persistence(Customer master) throws Exception {
		return customerDao.persistence(master);
	}

	@Override
	public boolean deleteToUpdate(Integer pid) {
		return customerDao.deleteToUpdate(pid);
	}

}
