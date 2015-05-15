package com.cloud.erp.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cloud.erp.dao.BaseDao;
import com.cloud.erp.dao.PermissionAssignmentDao;
import com.cloud.erp.entities.shiro.ShiroUser;
import com.cloud.erp.entities.table.Permission;
import com.cloud.erp.entities.table.Role;
import com.cloud.erp.entities.table.RolePermission;
import com.cloud.erp.entities.viewmodel.TreeGrid;
import com.cloud.erp.utils.Constants;

@SuppressWarnings("unchecked")
@Repository("permissionAssignmentDao")
public class PermissionAssignmentDaoImpl implements PermissionAssignmentDao {

	@SuppressWarnings("rawtypes")
	private BaseDao baseDao;
	
	@SuppressWarnings("rawtypes")
	@Autowired
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}
	
	public Permission getFunction(Integer id){
		return (Permission) baseDao.get(Permission.class, id);
	}
	
	public Role getRole(Integer roleId){
		return (Role) baseDao.get(Role.class, roleId);
	}
	
	@Override
	public List<TreeGrid> findAllFunctionsList(Integer pid) {
		// TODO Auto-generated method stub
		String hql = "from Permission t where t.status='A' ";
		List<Permission> list = baseDao.find(hql, null);
		List<TreeGrid> tempList = new ArrayList<TreeGrid>();
		for(Permission function : list){
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
			treeGridModel.setIsused(function.getIssued());
			treeGridModel.setType(function.getType());
			treeGridModel.setDescripiton(function.getDescription());
			tempList.add(treeGridModel);
		}
		return tempList;
	}

	@Override
	public Long getCount(Map<String, Object> param) {
		// TODO Auto-generated method stub
		String hql = "select count(*) from Role t where t.status = 'A' ";
		hql += Constants.getSearchConditionsHQL("t", param);
		return baseDao.count(hql, param);
	}

	@Override
	public boolean savePermission(Integer roleId, String checkedIds) {
		// TODO Auto-generated method stub
		Integer userId = Constants.getCurrentUser().getUserId();
		Role role = this.getRole(roleId);
		Map<String, RolePermission> map = new HashMap<String, RolePermission>();
		Set<RolePermission> rolePermissions = role.getRolePermissions();
		for(RolePermission rolePermission : rolePermissions){
			Integer permissionId = rolePermission.getPermission().getPermissionId();
			map.put(permissionId.toString(), rolePermission);
			updRolePermission(userId, rolePermission, Constants.PERSISTENCE_DELETE_STATUS);
		}
		if(null != checkedIds && !"".equals(checkedIds)){
			String[] ids = checkedIds.split(",");
			for(String id : ids){
				RolePermission rolePermission = map.get(id);
				if(rolePermission != null){
					updRolePermission(userId, rolePermission, Constants.PERSISTENCE_STATUS);
				}else {
					Permission function= this.getFunction(Integer.valueOf(id));
					Date date = new Date();
					rolePermission = new RolePermission();
					rolePermission.setCreated(date);
					rolePermission.setLastmod(date);
					rolePermission.setStatus(Constants.PERSISTENCE_STATUS);
					rolePermission.setCreater(userId);
					rolePermission.setModifier(userId);
					rolePermission.setPermission(function);
					rolePermission.setRole(role);
					baseDao.save(rolePermission);
					
				}
			}
		}
		
		return true;
	}
	
	private void updRolePermission(Integer userId, RolePermission rolePermission, String status){
		rolePermission.setLastmod(new Date());
		rolePermission.setCreater(userId);
		rolePermission.setModifier(userId);
		rolePermission.setStatus(status);
		baseDao.update(rolePermission);
	}

	@Override
	public List<Permission> getRolePermission(Integer roleId) {
		// TODO Auto-generated method stub
		String sql = "SELECT t.PERMISSION_ID FROM ROLE_PERMISSION t WHERE t.STATUS = 'A' AND t.ROLE_ID=" + roleId;
		List list = baseDao.findBySQL(sql);
		List<Permission> list2 = new ArrayList<Permission>();
		if(0 != list.size()){
			for(Object object : list){
				Permission p = new Permission();
				p.setPermissionId(Integer.valueOf(object.toString()));
				list2.add(p);
			}
		}
		return list2;
	}

	@Override
	public boolean persistenceRole(Map<String, List<Role>> map) {
		// TODO Auto-generated method stub
		this.addRole(map.get("addList"));
		this.updRole(map.get("updList"));
		this.delRole(map.get("delList"));
		return true;
	}
	
	private boolean addRole(List<Role> addList){
		if(addList != null && addList.size() != 0){
			ShiroUser users = Constants.getCurrentUser();
			for(Role role : addList){
				role.setCreated(new Date());
				role.setLastmod(new Date());
				role.setStatus(Constants.PERSISTENCE_STATUS);
				role.setCreater(users.getUserId());
				role.setModifier(users.getUserId());
				baseDao.save(role);
			}
		}
		return true;
	}
	
	private boolean delRole(List<Role> delList){
		if(delList != null && delList.size() != 0){
			ShiroUser users = Constants.getCurrentUser();
			for(Role role : delList){
				role.setLastmod(new Date());
				role.setModifier(users.getUserId());
				role.setStatus(Constants.PERSISTENCE_DELETE_STATUS);
				baseDao.deleteToUpdate(role);
			}
		}
		return true;
	}
	
	private boolean updRole(List<Role> updList){
		if(updList != null && updList.size() != 0){
			ShiroUser users = Constants.getCurrentUser();
			for(Role role : updList){
				role.setLastmod(new Date());
				role.setModifier(users.getUserId());
				baseDao.update(role);
			}
		}
		return true;
	}

	@Override
	public List<Role> findAllRoleList(Map<String, Object> param, Integer page,
			Integer rows, boolean isPage) {
		// TODO Auto-generated method stub
		String hql = "from Role t where t.status='A' ";
		hql += Constants.getSearchConditionsHQL("t", param);
		List<Role> tempList = null;
		if(isPage){
			tempList = baseDao.find(hql, param, page, rows);
		}else {
			tempList = baseDao.find(hql, param);
		}
		for(Role role : tempList){
			role.setRolePermissions(null);
			role.setUserRoles(null);
		}
		return tempList;
	}

	@Override
	public boolean persistenceRole(Role r) {
		// TODO Auto-generated method stub
		Integer userId = Constants.getCurrentUser().getUserId();
		if(null == r.getRoleId() || "".equals(r.getRoleId())){
			r.setCreated(new Date());
			r.setLastmod(new Date());
			r.setCreater(userId);
			r.setModifier(userId);
			r.setStatus(Constants.PERSISTENCE_STATUS);
			baseDao.save(r);
		}else {
			r.setLastmod(new Date());
			r.setModifier(userId);
			baseDao.update(r);
		}
		return true;
	}

	@Override
	public boolean persistenceRole(Integer roleId) {
		// TODO Auto-generated method stub
		Integer userId = Constants.getCurrentUser().getUserId();
		Role role = (Role) baseDao.get(Role.class, roleId);
		role.setLastmod(new Date());
		role.setModifier(userId);
		role.setStatus(Constants.PERSISTENCE_DELETE_STATUS);
		baseDao.deleteToUpdate(role);
		return true;
	}

}
