/**
 * @Title:  EmployeeAction.java
 * @Package:  com.cloud.erp.actions
 * @Description:  TODO
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年5月12日 上午9:58:40
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

import org.apache.commons.collections.map.HashedMap;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.cloud.erp.entities.table.Employee;
import com.cloud.erp.entities.viewmodel.GridModel;
import com.cloud.erp.service.EmployeeService;
import com.cloud.erp.utils.Constants;
import com.cloud.erp.utils.PageUtil;
import com.opensymphony.xwork2.ModelDriven;

/**
 * @ClassName EmployeeAction
 * @Description TODO
 * @author bollen bollen@live.cn
 * @date 2015年5月12日 上午9:58:40
 *
 */
@Namespace("/employee")
@Action("employeeAction")
public class EmployeeAction extends BaseAction implements ModelDriven<Employee> {

	private static final long serialVersionUID = 1L;
	private EmployeeService employeeService;
	private Employee employee;

	/**
	 * @param employeeService
	 *            the employeeService to set
	 */
	@Autowired
	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	public String findEmployees() throws Exception{
		Map<String, Object> params = new HashMap<String, Object>();
		if(null != searchValue && !"".equals(searchValue)){
			params.put(searchName, Constants.GET_SQL_LIKE + searchValue + Constants.GET_SQL_LIKE);
		}
		PageUtil pageUtil = new PageUtil(page, rows, searchAnds, searchColumnNames, searchConditions, searchVals);
		GridModel gridModel = new GridModel();
		gridModel.setRows(employeeService.findEmployees(params, pageUtil));
		gridModel.setTotal(employeeService.getCount(params, pageUtil));
		OutputJson(gridModel);
		
		return null;
	}
	
	public String persistenceEmployee() throws Exception{
		OutputJson(getMessage(employeeService.persistenceEmployee(getModel())));
		
		return null;
	}
	
	public String delEmployee() throws Exception{
		OutputJson(getMessage(employeeService.delEmployee(getModel().getEmployeeId())));
		
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	@Override
	public Employee getModel() {
		// TODO Auto-generated method stub
		if (null == employee) {
			employee = new Employee();
		}
		return employee;
	}

}
