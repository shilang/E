/**
 * @Title:  CustomerService.java
 * @Package:  com.cloud.erp.service
 * @Description:  TODO
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年5月12日 上午9:12:24
 * @version:  v1.0
 *
 * History:
 * Date		Author		Version
 * ---------------------------------------------
 * <reasons>
 */
package com.cloud.erp.service;

import java.util.List;
import java.util.Map;

import com.cloud.erp.entities.table.Customer;
import com.cloud.erp.utils.PageUtil;

/**
 * @ClassName  CustomerService
 * @Description  TODO
 * @author  bollen bollen@live.cn
 * @date  2015年5月12日 上午9:12:24
 *
 */
public interface CustomerService {

	List<Customer> findCustomers(Map<String, Object> params, PageUtil pageUtil);
	
	Long getCount(Map<String, Object> params, PageUtil pageUtil);
	
	boolean persistenceCustomer(Customer customer);
	
	boolean delCustomer(Integer id);
}
