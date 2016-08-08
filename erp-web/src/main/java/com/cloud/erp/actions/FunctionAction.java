/**
 * @Title:  FunctionAction.java
 * @Package:  com.cloud.erp.actions
 * @Description:  
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年2月13日  下午2:23:52
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

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;

import com.cloud.erp.common.BaseAction;
import com.cloud.erp.entities.table.Permission;
import com.cloud.erp.entities.viewmodel.Json;
import com.cloud.erp.entities.viewmodel.TreeGridModel;
import com.cloud.erp.entities.viewmodel.TreeModel;
import com.cloud.erp.service.FunctionService;
import com.cloud.erp.utils.Constants;
import com.opensymphony.xwork2.ModelDriven;

/**
 * @ClassName  FunctionAction
 * @Description  
 * @author  bollen bollen@live.cn
 * @date  2015年2月13日  下午2:23:52
 *
 */
@Namespace("/function")
public class FunctionAction extends BaseAction implements ModelDriven<Permission>{

	private static final long serialVersionUID = 1L;
	@Resource
	private FunctionService functionService;
	private Permission permission;
	private Integer id;
	
	public Permission getPermission() {
		return permission;
	}

	public void setPermission(Permission permission) {
		this.permission = permission;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Action(value = "persist")
	public String persistenceFunction() throws Exception{
		boolean result = functionService.persistence(getModel());
		JSONWriter(result);
		return RJSON;
	}
	
	@Action(value = "delete")
	public String delFunction() throws Exception{
		Json json = new Json();
		if(functionService.deleteToUpdate(id)){
			json.setStatus(true);
			json.setMessage(Constants.POST_DATA_SUCCESS);
		}else {
			json.setMessage(Constants.POST_DATA_FAIL + Constants.IS_EXT_SUBMENU);
		}
		JSONWriter(json);
		return RJSON;
	}
	
	@Action(value = "findById")
	public String findFunctionsById() throws Exception{
		List<Permission> permissions = functionService.findFunctionsById(id);
		List<TreeGridModel> tempList = new ArrayList<TreeGridModel>();
		for (Permission function : permissions) {
			TreeGridModel treeGridModel = new TreeGridModel();
			try {
				BeanUtils.copyProperties(treeGridModel, function);
				if (id == null || "".equals(id)) {
					treeGridModel.setPid(null);
				}
				tempList.add(treeGridModel);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		JSONWriterGeneral(tempList);
		return RJSON;
	}
	
	@Action(value = "find")
	public String findFunctions() throws Exception{
		List<Permission> permissions = functionService.findFunctions();
		List<TreeModel> tempList = new ArrayList<TreeModel>();
		for (Permission function : permissions) {
			TreeModel treeModel = new TreeModel();
			treeModel.setId(function.getPermissionId().toString());
			treeModel.setPid(function.getPid() == null ? "" : function.getPid()
					.toString());
			treeModel.setName(function.getName());
			treeModel.setIconCls(function.getIconCls());
			treeModel.setState(Constants.TREE_STATUS_OPEN);
			tempList.add(treeModel);
		}
		JSONWriterGeneral(tempList);
		return RJSON;
	}

	@Override
	public Permission getModel() {
		if(null == permission){
			permission = new Permission();
		}
		return permission;
	}

}
