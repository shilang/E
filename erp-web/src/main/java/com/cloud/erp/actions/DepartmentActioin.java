/**
 * @Title:  DepartmentActioin.java
 * @Package:  com.cloud.erp.actions
 * @Description:  TODO
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年3月18日 上午10:18:20
 * @version:  v1.0
 *
 * History:
 * Date		Author		Version
 * ---------------------------------------------
 * <reasons>
 */
package com.cloud.erp.actions;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.cloud.erp.entities.table.Department;
import com.cloud.erp.entities.viewmodel.Json;
import com.cloud.erp.service.DepartmentService;
import com.cloud.erp.utils.Constants;
import com.opensymphony.xwork2.ModelDriven;

/**
 * @ClassName DepartmentActioin
 * @Description TODO
 * @author bollen bollen@live.cn
 * @date 2015年3月18日 上午10:18:20
 *
 */
@Namespace("/department")
@Action(value = "departmentAction")
public class DepartmentActioin extends BaseAction implements
		ModelDriven<Department> {

	private static final long serialVersionUID = 8032589540816717471L;
	private DepartmentService departmentService;
	private Department department;
	private Integer id;

	/**
	 * @param departmentService
	 *            the departmentService to set
	 */
	@Autowired
	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String persistenceDepartment() throws Exception {
		OutputJson(
				getMessage(departmentService.persistenceDepartment(getModel())),
				Constants.TEXT_TYPE_PLAIN);
		return null;
	}

	public String delDepartment() throws Exception {
		Json json = new Json();
		if (departmentService.delDepartment(getModel().getDepartmentId())) {
			json.setStatus(true);
			json.setMessage(Constants.POST_DATA_SUCCESS);
		} else {
			json.setMessage(Constants.POST_DATA_FAIL + Constants.IS_EXT_SUBMENU);
		}
		OutputJson(json);

		return null;
	}

	public String findDepartmentsByeId() throws Exception {
		OutputJson(departmentService.findDepartments(id));
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	@Override
	public Department getModel() {
		// TODO Auto-generated method stub
		if (null == department) {
			department = new Department();
		}
		return department;
	}

}
