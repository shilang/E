/**
 * @Title:  CustomerDao.java
 * @Package:  com.cloud.erp.dao
 * @Description:  TODO
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年5月12日 上午9:16:19
 * @version:  v1.0
 *
 * History:
 * Date		Author		Version
 * ---------------------------------------------
 * <reasons>
 */
package com.cloud.erp.dao;

import java.util.List;
import java.util.Map;

import com.cloud.erp.entities.table.Customer;
import com.cloud.erp.utils.PageUtil;

/**
 * @ClassName  CustomerDao
 * @Description  TODO
 * @author  bollen bollen@live.cn
 * @date  2015年5月12日 上午9:16:19
 *
 */
public interface CustomerDao {

	List<Customer> findCustomers(Map<String, Object> params, PageUtil pageUtil);
	
	Long getCount(Map<String, Object> params, PageUtil pageUtil);
	
	boolean persistenceCustomer(Customer customer);
	
	boolean delCustomer(Integer id);
}
