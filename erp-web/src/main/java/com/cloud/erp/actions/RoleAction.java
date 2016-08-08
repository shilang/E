package com.cloud.erp.actions;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;

import com.cloud.erp.common.BaseAction;
import com.cloud.erp.entities.table.Role;
import com.cloud.erp.service.RoleService;
import com.cloud.erp.utils.PageUtil;
import com.opensymphony.xwork2.ModelDriven;

@Namespace("/role")
public class RoleAction extends BaseAction implements ModelDriven<Role> {

	private static final long serialVersionUID = 1L;
	@Resource
	private RoleService roleService;
	private Role role;

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public Role getModel() {
		if(null == role){
			role = new Role();
		}
		return role;
	}
	
	@Action(value = "find")
	public String findAllRoleList() throws Exception{
		Map<String, Object> params = getQueryParamsForMain();
		PageUtil pageUtil = getPageUtil();
		JSONWriter(roleService.findAll(params, pageUtil), 
				roleService.getCount(params));
		return RJSON;
	}
	
	@Action(value = "findNotPage")
	public String findAllRoleListNotPage() throws Exception{
		JSONWriter(roleService.findAllRoleList(), null);
		return RJSON;
	}
	
	@Action(value = "persist")
	public String persistenceRole() throws Exception{
		boolean result = roleService.persistence(getModel());
		JSONWriter(result);
		return RJSON;
	}
	
	@Action(value = "delete")
	public String delRole() throws Exception{
		boolean result = roleService.deleteToUpdate(getModel().getRoleId());
		JSONWriter(result);
		return RJSON;
	}
}
