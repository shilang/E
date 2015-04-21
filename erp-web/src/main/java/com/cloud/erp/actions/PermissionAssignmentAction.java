package com.cloud.erp.actions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.cloud.erp.entities.Role;
import com.cloud.erp.entities.viewmodel.GridModel;
import com.cloud.erp.entities.viewmodel.Json;
import com.cloud.erp.service.PermissionAssignmentService;
import com.cloud.erp.utils.Constants;
import com.opensymphony.xwork2.ModelDriven;

/**
 * @ClassName  PermissionAssignmentAction
 * @Description  TODO
 * @author  bollen bollen@live.cn
 * @date  2015年3月11日 下午3:23:19
 *
 */
@Namespace("/permission")
@Action(value="permissionAssignmentAction")
public class PermissionAssignmentAction extends BaseAction implements ModelDriven<Role>{

	private static final long serialVersionUID = 2569750004296530915L;
	private PermissionAssignmentService permissionAssignmentService;
	private Integer id;
	private String checkedIds;
	private Role role;
	
	@Autowired
	public void setPermissionAssignmentService(
			PermissionAssignmentService permissionAssignmentService) {
		this.permissionAssignmentService = permissionAssignmentService;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCheckedIds() {
		return checkedIds;
	}
	
	public void setCheckedIds(String checkedIds) {
		this.checkedIds = checkedIds;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	/**
	 * function: TODO 按节点查询所有程序
	 * @Author: bollen bollen@live.cn
	 * @Date: 2015年3月12日 上午9:11:23
	 * @Title: findAllFunctionList
	 * @Param:
	 * @Return: String
	 * @throws Exception
	 */
	public String findAllFunctionList() throws Exception{
		OutputJson(permissionAssignmentService.findAllFunctionsList(id));
		return null;
	}
	
	/**
	 * function: TODO 查询所有角色
	 * @Author: bollen bollen@live.cn
	 * @Date: 2015年3月12日 上午9:13:53
	 * @Title: findAllRoleList
	 * @Param:
	 * @Return: String
	 * @throws Exception
	 */
	public String findAllRoleList() throws Exception{
		Map<String, Object> map = searchRole();
		GridModel gridModel = new GridModel();
		gridModel.setRows(permissionAssignmentService.findAllRoleList(map, page, rows, true));
		gridModel.setTotal(permissionAssignmentService.getCount(map));
		OutputJson(gridModel);
		return null;
	}
	
	private Map<String, Object> searchRole(){
		Map<String, Object> map = new HashMap<String, Object>();
		if(null != searchValue && !"".equals(searchValue)){
			map.put(searchName, Constants.GET_SQL_LIKE + searchValue + Constants.GET_SQL_LIKE);
		}
		return map;
	}
	
	/**
	 * function: TODO: query all roles, not paging.
	 * @Author: bollen bollen@live.cn
	 * @Date: 2015年3月12日 上午9:18:04
	 * @Title: findAllRoleListNotPage
	 * @return
	 * @throws Exception
	 */
	public String findAllRoleListNotPage() throws Exception{
		Map<String, Object> map = searchRole();
		GridModel gridModel = new GridModel();
		gridModel.setRows(permissionAssignmentService.findAllRoleList(map, null, null, false));
		OutputJson(gridModel);
		return null;
	}
	
	/**
	 * function: TODO: gets role permission via roleId.
	 * @Author: bollen bollen@live.cn
	 * @Date: 2015年3月12日 上午9:21:02
	 * @Title: getRolePermission
	 * @return
	 * @throws Exception
	 */
	public String getRolePermission() throws Exception{
		OutputJson(permissionAssignmentService.getRolePermission(getModel().getRoleId()));
		return null;
	}
	
	/**
	 * function: TODO save role permission
	 * @Author: bollen bollen@live.cn
	 * @Date: 2015年3月12日 上午9:25:29
	 * @Title: savePermission
	 * @return
	 * @throws Exception
	 */
	public String savePermission() throws Exception{
		Json json = new Json();
		if(permissionAssignmentService.savePermission(getModel().getRoleId(), checkedIds)){
			json.setStatus(true);
			json.setMessage("分配成功！查看已分配权限请重新登录！");
		}else {
			json.setMessage("分配失败！");
		}
		OutputJson(json);
		return null;
	}
	
	/**
	 * function: TODO persistence role
	 * @Author: bollen bollen@live.cn
	 * @Date: 2015年3月12日 上午9:30:23
	 * @Title: persistenceRole
	 * @return
	 * @throws Exception
	 */
	public String persistenceRole() throws Exception{
		Map<String, List<Role>> map = new HashMap<String, List<Role>>();
		map.put("addList", JSON.parseArray(inserted, Role.class));
		map.put("updList", JSON.parseArray(updated, Role.class));
		map.put("delList", JSON.parseArray(deleted, Role.class));
		Json json = new Json();
		if(permissionAssignmentService.persistenceRole(map)){
			json.setStatus(true);
			json.setMessage("数据更新成功!");
		}else {
			json.setMessage("提交失败了！");
		}
		OutputJson(json);
		return null;
	}
	
	/**
	 * function: TODO popup window of persistence role
	 * @Author: bollen bollen@live.cn
	 * @Date: 2015年3月12日 上午9:32:48
	 * @Title: persistenceRoleDlg
	 * @return
	 * @throws Exception
	 */
	public String persistenceRoleDlg() throws Exception{
		OutputJson(getMessage(permissionAssignmentService.persistenceRole(getModel())), Constants.TEXT_TYPE_PLAIN);
		return null;
	}
	
	public String delRole() throws Exception{
		OutputJson(getMessage(permissionAssignmentService.persistenceRole(getModel().getRoleId())));
		return null;
	}
	
	@Override
	public Role getModel() {
		// TODO Auto-generated method stub
		if(null == role){
			role = new Role();
		}
		return role;
	}

}
