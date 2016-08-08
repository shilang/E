/**
 * @Title:  CustomerAction.java
 * @Package:  com.cloud.erp.actions
 * @Description:  
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年5月12日 上午9:08:27
 * @version:  v1.0
 *
 * History:
 * Date		Author		Version
 * ---------------------------------------------
 * <reasons>
 */
package com.cloud.erp.actions;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;

import com.cloud.erp.common.BaseAction;
import com.cloud.erp.entities.table.Customer;
import com.cloud.erp.service.CustomerService;
import com.cloud.erp.utils.PageUtil;
import com.opensymphony.xwork2.ModelDriven;

/**
 * @ClassName CustomerAction
 * @Description 
 * @author bollen bollen@live.cn
 * @date 2015年5月12日 上午9:08:27
 *
 */
@Namespace("/customer")
public class CustomerAction extends BaseAction implements ModelDriven<Customer> {

	private static final long serialVersionUID = 1L;
	@Resource
	private CustomerService customerService;
	private Customer customer;

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	@Action(value = "find")
	public String findCustomers() throws Exception{
		Map<String, Object> params = getQueryParamsForMain();
		PageUtil pageUtil = getPageUtil();
		JSONWriter(customerService.findAll(params, pageUtil), 
				customerService.getCount(params));
		return RJSON;
	}
	
	@Action(value = "persist")
	public String persistenceCustomer() throws Exception{
		boolean result = customerService.persistence(getModel());
		JSONWriter(result);
		return RJSON;
	}
	
	@Action(value = "delete")
	public String delCustomer() throws Exception{
		boolean result = customerService.deleteToUpdate(getModel().getCustomerId());
		JSONWriter(result);
		return RJSON;
	}

	@Override
	public Customer getModel() {
		if (null == customer) {
			customer = new Customer();
		}
		return customer;
	}

}
