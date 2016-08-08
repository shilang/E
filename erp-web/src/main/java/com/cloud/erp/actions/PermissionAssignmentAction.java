package com.cloud.erp.actions;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;

import com.cloud.erp.common.BaseAction;
import com.cloud.erp.entities.table.Permission;
import com.cloud.erp.entities.viewmodel.Json;
import com.cloud.erp.entities.viewmodel.TreeGrid;
import com.cloud.erp.service.PermissionAssignmentService;

/**
 * @ClassName  PermissionAssignmentAction
 * @Description  
 * @author  bollen bollen@live.cn
 * @date  2015年3月11日 下午3:23:19
 *
 */
@Namespace("/permission")
public class PermissionAssignmentAction extends BaseAction{

	private static final long serialVersionUID = 2569750004296530915L;
	
	@Resource
	private PermissionAssignmentService permissionAssignmentService;
	
	private Integer id;
	private String checkedIds;
	private Integer roleId;

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

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	/**
	 * function: 按节点查询所有程序
	 * @Author: bollen bollen@live.cn
	 * @Date: 2015年3月12日 上午9:11:23
	 * @Title: findAllFunctionList
	 * @Param:
	 * @Return: String
	 * @throws Exception
	 */
	@Action(value = "find")
	public String findAllFunctionList() throws Exception{
		
		List<Permission> permissions = permissionAssignmentService.findAllFunctionsList(id);
		List<TreeGrid> tempList = new ArrayList<TreeGrid>();
		for(Permission function : permissions){
			TreeGrid treeGridModel = new TreeGrid();
			treeGridModel.setId(function.getPermissionId() + "");
			if(null != function.getPid() ){
				treeGridModel.setState("open");
			}
			treeGridModel.setPid(function.getPid() == null ? null: function.getPid().toString());
			treeGridModel.setIconCls(function.getIconCls());
			treeGridModel.setName(function.getName());
			treeGridModel.setPath(function.getUrl());
			treeGridModel.setMyid(function.getMyid());
			treeGridModel.setPName(function.getPname());
			treeGridModel.setSort(function.getSort() + "");
			treeGridModel.setIsused(function.getIsused());
			treeGridModel.setType(function.getType());
			treeGridModel.setDescripiton(function.getDescription());
			tempList.add(treeGridModel);
		}
		JSONWriterGeneral(tempList);
		return RJSON;
	}
		
	/**
	 * function: gets role permission via roleId.
	 * @Author: bollen bollen@live.cn
	 * @Date: 2015年3月12日 上午9:21:02
	 * @Title: getRolePermission
	 * @return
	 * @throws Exception
	 */
	@Action(value = "getRolePermission")
	public String getRolePermission() throws Exception{
		JSONWriterGeneral(permissionAssignmentService.getRolePermission(roleId));
		return RJSON;
	}
	
	/**
	 * function: save role permission
	 * @Author: bollen bollen@live.cn
	 * @Date: 2015年3月12日 上午9:25:29
	 * @Title: savePermission
	 * @return
	 * @throws Exception
	 */
	@Action(value = "savePermission")
	public String savePermission() throws Exception{
		Json json = new Json();
		if(permissionAssignmentService.savePermission(roleId, checkedIds)){
			json.setStatus(true);
			json.setMessage("分配成功！查看已分配权限请重新登录！");
		}else {
			json.setMessage("分配失败！");
		}
		JSONWriter(json);
		return RJSON;
	}
}
