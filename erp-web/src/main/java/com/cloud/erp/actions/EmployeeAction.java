/**
 * @Title:  EmployeeAction.java
 * @Package:  com.cloud.erp.actions
 * @Description:  
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

import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;

import com.cloud.erp.common.BaseAction;
import com.cloud.erp.entities.table.Employee;
import com.cloud.erp.service.EmployeeService;
import com.cloud.erp.utils.PageUtil;
import com.opensymphony.xwork2.ModelDriven;

/**
 * @ClassName EmployeeAction
 * @Description 
 * @author bollen bollen@live.cn
 * @date 2015年5月12日 上午9:58:40
 *
 */
@Namespace("/employee")
public class EmployeeAction extends BaseAction implements ModelDriven<Employee> {

	private static final long serialVersionUID = 1L;
	@Resource
	private EmployeeService employeeService;
	private Employee employee;
	
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	@Action(value = "find")
	public String findEmployees() throws Exception{
		Map<String, Object> params = getQueryParamsForMain();
		PageUtil pageUtil = getPageUtil();
		JSONWriter(employeeService.findAll(params, pageUtil), 
				employeeService.getCount(params));
		return RJSON;
	}
	
	@Action(value = "persist")
	public String persistenceEmployee() throws Exception{
		boolean result = employeeService.persistence(getModel());
		JSONWriter(result);
		return RJSON;
	}
	
	@Action(value = "delete")
	public String delEmployee() throws Exception{
		boolean result = employeeService.deleteToUpdate(getModel().getEmployeeId());
		JSONWriter(result);
		return RJSON;
	}

	@Override
	public Employee getModel() {
		if (null == employee) {
			employee = new Employee();
		}
		return employee;
	}

}
