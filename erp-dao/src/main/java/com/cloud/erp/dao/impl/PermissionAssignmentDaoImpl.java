package com.cloud.erp.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cloud.erp.dao.PermissionAssignmentDao;
import com.cloud.erp.dao.common.BaseDao;
import com.cloud.erp.entities.table.Permission;
import com.cloud.erp.entities.table.Role;
import com.cloud.erp.entities.table.RolePermission;
import com.cloud.erp.utils.Commons;
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
		//return (Role) baseDao.get(Role.class, roleId);
		String hql = "from Role r inner join fetch r.rolePermissions rp inner join fetch rp.permission where r.roleId=" + roleId;
		return (Role) baseDao.get(hql, null);
	}
	
	@Override
	public List<Permission> findAllFunctionsList(Integer pid) {
		String hql = "from Permission t where t.status='A' ";
		return  baseDao.find(hql, null);
	}

	@Override
	public boolean savePermission(Integer roleId, String checkedIds) {
		Integer userId = Commons.getCurrentUser().getUserId();
		Map<String, RolePermission> map = new HashMap<String, RolePermission>();
		
		Role role = this.getRole(roleId);
		if(null != role){
			Set<RolePermission> rolePermissions = role.getRolePermissions();
			for(RolePermission rolePermission : rolePermissions){
				Integer permissionId = rolePermission.getPermission().getPermissionId();
				map.put(permissionId.toString(), rolePermission);
				updRolePermission(userId, rolePermission, Constants.PERSISTENCE_DELETE_STATUS);
			}
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
					rolePermission.setRole(role==null?new Role(roleId):role);
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

	@SuppressWarnings("rawtypes")
	@Override
	public List<Permission> getRolePermission(Integer roleId) {
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

}
