/**
 * @Title:  CustomerServiceImpl.java
 * @Package:  com.cloud.erp.service.impl
 * @Description:  TODO
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
 * @ClassName  CustomerServiceImpl
 * @Description  TODO
 * @author  bollen bollen@live.cn
 * @date  2015年5月12日 上午9:15:08
 *
 */
@Service("customerService")
public class CustomerServiceImpl implements CustomerService {

	private CustomerDao customerDao;
	
	/**
	 * @param customerDao the customerDao to set
	 */
	@Autowired
	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}
	/* (non-Javadoc)
	 * @see com.cloud.erp.service.CustomerService#findCustomers(java.util.Map, com.cloud.erp.utils.PageUtil)
	 */
	@Override
	public List<Customer> findCustomers(Map<String, Object> params,
			PageUtil pageUtil) {
		// TODO Auto-generated method stub
		return customerDao.findCustomers(params, pageUtil);
	}

	/* (non-Javadoc)
	 * @see com.cloud.erp.service.CustomerService#getCount(java.util.Map, com.cloud.erp.utils.PageUtil)
	 */
	@Override
	public Long getCount(Map<String, Object> params, PageUtil pageUtil) {
		// TODO Auto-generated method stub
		return customerDao.getCount(params, pageUtil);
	}

	/* (non-Javadoc)
	 * @see com.cloud.erp.service.CustomerService#persistenceCustomer(com.cloud.erp.entities.table.Customer)
	 */
	@Override
	public boolean persistenceCustomer(Customer customer) {
		// TODO Auto-generated method stub
		return customerDao.persistenceCustomer(customer);
	}

	/* (non-Javadoc)
	 * @see com.cloud.erp.service.CustomerService#delCustomer(java.lang.Integer)
	 */
	@Override
	public boolean delCustomer(Integer id) {
		// TODO Auto-generated method stub
		return customerDao.delCustomer(id);
	}

}
