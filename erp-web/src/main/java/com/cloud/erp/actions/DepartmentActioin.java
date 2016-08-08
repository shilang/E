/**
 * @Title:  DepartmentActioin.java
 * @Package:  com.cloud.erp.actions
 * @Description:  
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

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;

import com.cloud.erp.common.BaseAction;
import com.cloud.erp.entities.table.Department;
import com.cloud.erp.entities.viewmodel.Json;
import com.cloud.erp.entities.viewmodel.TreeModel;
import com.cloud.erp.service.DepartmentService;
import com.cloud.erp.utils.Constants;
import com.opensymphony.xwork2.ModelDriven;

/**
 * @ClassName DepartmentActioin
 * @Description 
 * @author bollen bollen@live.cn
 * @date 2015年3月18日 上午10:18:20
 *
 */
@Namespace("/department")
public class DepartmentActioin extends BaseAction implements
		ModelDriven<Department> {

	private static final long serialVersionUID = 8032589540816717471L;
	@Resource
	private DepartmentService departmentService;
	private Department department;
	private Integer id;

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

	@Action(value = "persist")
	public String persistenceDepartment() throws Exception {
		boolean result = departmentService.persistence(getModel());
		JSONWriter(result);
		return RJSON;
	}

	@Action(value = "delete")
	public String delDepartment() throws Exception {
		Json json = new Json();
		if (departmentService.deleteToUpdate(getModel().getDepartmentId())) {
			json.setStatus(true);
			json.setMessage(Constants.POST_DATA_SUCCESS);
		} else {
			json.setMessage(Constants.POST_DATA_FAIL + Constants.IS_EXT_SUBMENU);
		}
		JSONWriter(json);
		return RJSON;
	}

	@Action(value = "findById")
	public String findDepartmentsByeId() throws Exception {
		JSONWriterGeneral(departmentService.findDepartments(id));
		return RJSON;
	}
	
	@Action(value = "findForTree")
	public String findDepartmentsForTree() throws Exception{
		List<Department> departments = departmentService.findDepartments();
		List<TreeModel> list = new ArrayList<TreeModel>();
		for(Department department : departments){
			TreeModel treeModel = new TreeModel();
			treeModel.setId(department.getDepartmentId().toString());
			treeModel.setPid(department.getPid() == null ? "" : department.getPid().toString());
			treeModel.setName(department.getName());
			treeModel.setState(Constants.TREE_STATUS_OPEN);
			list.add(treeModel);
		}
		JSONWriterGeneral(list);
		return RJSON;
	}

	@Override
	public Department getModel() {
		if (null == department) {
			department = new Department();
		}
		return department;
	}

}
