/**
 * @Title:  CustomerAction.java
 * @Package:  com.cloud.erp.actions
 * @Description:  TODO
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

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.cloud.erp.entities.table.Customer;
import com.cloud.erp.entities.viewmodel.GridModel;
import com.cloud.erp.service.CustomerService;
import com.cloud.erp.utils.Constants;
import com.cloud.erp.utils.PageUtil;
import com.opensymphony.xwork2.ModelDriven;

/**
 * @ClassName CustomerAction
 * @Description TODO
 * @author bollen bollen@live.cn
 * @date 2015年5月12日 上午9:08:27
 *
 */
@Namespace("/customer")
@Action("customerAction")
public class CustomerAction extends BaseAction implements ModelDriven<Customer> {

	private static final long serialVersionUID = 1L;
	private CustomerService customerService;
	private Customer customer;
	
	/**
	 * @param customerService the customerService to set
	 */
	@Autowired
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public String findCustomers() throws Exception{
		Map<String, Object> params = new HashMap<String, Object>();
		if(null != searchValue && !"".equals(searchValue)){
			params.put(searchName, Constants.GET_SQL_LIKE + searchValue + Constants.GET_SQL_LIKE);
		}
		PageUtil pageUtil = new PageUtil(page, rows, searchAnds, searchColumnNames, searchConditions, searchVals);
		GridModel gridModel = new GridModel();
		gridModel.setRows(customerService.findCustomers(params, pageUtil));
		gridModel.setTotal(customerService.getCount(params, pageUtil));
		OutputJson(gridModel);
		
		return null;
	}
	
	public String persistenceCustomer() throws Exception{
		OutputJson(getMessage(customerService.persistenceCustomer(getModel())));
		
		return null;
	}
	
	public String delCustomer() throws Exception{
		OutputJson(getMessage(customerService.delCustomer(getModel().getCustomerId())));
		
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	@Override
	public Customer getModel() {
		// TODO Auto-generated method stub
		if (null == customer) {
			customer = new Customer();
		}
		return customer;
	}

}
