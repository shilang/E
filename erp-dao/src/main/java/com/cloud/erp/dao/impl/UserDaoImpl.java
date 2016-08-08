/**
 * @Title:  UserDaoImpl.java
 * @Package:  com.cloud.erp.dao.impl
 * @Description:  
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年2月2日  下午5:58:20
 * @version:  v1.0
 *
 * History:
 * Date		Author		Version
 * ---------------------------------------------
 * <reasons>
 */
package com.cloud.erp.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cloud.erp.dao.UserDao;
import com.cloud.erp.dao.common.BaseDao;
import com.cloud.erp.dao.common.GeneralDaoSupport;
import com.cloud.erp.dao.common.StatusFields;
import com.cloud.erp.entities.table.Role;
import com.cloud.erp.entities.table.User;
import com.cloud.erp.entities.table.UserInfo;
import com.cloud.erp.entities.table.UserRole;
import com.cloud.erp.entities.viewmodel.UserRoleModel;
import com.cloud.erp.utils.Commons;
import com.cloud.erp.utils.Constants;
import com.cloud.erp.utils.PageUtil;

/**
 * @ClassName  UserDaoImpl
 * @Description  
 * @author  bollen bollen@live.cn
 * @date  2015年2月2日  下午5:58:20
 *
 */
@SuppressWarnings("unchecked")
@Repository("userDao")
public class UserDaoImpl implements UserDao {
	
	private final StatusFields statusFields = new StatusFields();
	
	{
		statusFields.setInterId("userId");
	}
	
	@Resource
	private GeneralDaoSupport<User> generalDao;

	@SuppressWarnings("rawtypes")
	@Autowired
	private BaseDao baseDao;
	
	/*@Autowired
	private UserMapper userMapper;*/

	@SuppressWarnings("rawtypes")
	private List<UserRoleModel> getUserRoleModelList(Integer userId, List list){
		List<UserRoleModel> listm = new ArrayList<UserRoleModel>();
		for(Object object : list){
			Object[] obj = (Object[])object;
			UserRoleModel userRoleModel = new UserRoleModel();
			userRoleModel.setUserId(userId);
			userRoleModel.setRoleId(obj[1] == null ? null : Integer.valueOf(obj[1].toString()));
			listm.add(userRoleModel);
		}
		return listm;
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public List<UserRoleModel> findUserRolesList(Integer userId) {
		
		String sql = "SELECT ur.USER_ID, ur.ROLE_ID FROM\n" +
				"USER_ROLE AS ur WHERE ur.STATUS = 'A' AND ur.USER_ID=" + userId;
		List list = baseDao.findBySQL(sql);
		List<UserRoleModel> listm = getUserRoleModelList(userId, list);
		return listm;
	}

	@Override
	public boolean saveUserRoles(Integer userId, String isCheckedIds) {
		
		Integer currUserId = Commons.getCurrentUser().getUserId();
		User user = (User) baseDao.get(User.class, userId);
		Set<UserRole> userRoles =  user.getUserRoles();
		Iterator<UserRole> iterator = userRoles.iterator();
		Map<Integer, UserRole>	map = new HashMap<Integer, UserRole>();
		while(iterator.hasNext()){
			UserRole userRole = iterator.next();
			map.put(userRole.getRole().getRoleId(), userRole);
			this.updateUserRole(userRole, Constants.PERSISTENCE_DELETE_STATUS);
		}
		if(null != isCheckedIds && !"".equals(isCheckedIds)){
			String[] ids = isCheckedIds.split(",");
			for(String id : ids){
				Integer tempId = Integer.valueOf(id);
				UserRole userRole = map.get(tempId);
				if(null != userRole){
					this.updateUserRole(userRole, Constants.PERSISTENCE_STATUS);
				}else {
					userRole = new UserRole();
					userRole.setRole((Role) baseDao.get(Role.class, Integer.valueOf(id)));
					userRole.setUser(user);
					userRole.setCreated(new Date());
					userRole.setLastmod(new Date());
					userRole.setCreater(currUserId);
					userRole.setModifier(currUserId);
					userRole.setStatus(Constants.PERSISTENCE_STATUS);
					baseDao.save(userRole);
				}
			}
		}
		return true;
	}
	
	private boolean updateUserRole(UserRole userRole, String status){
		Integer userId = Commons.getCurrentUser().getUserId();
		userRole.setLastmod(new Date());
		userRole.setModifier(userId);
		userRole.setStatus(status);
		baseDao.update(userRole);
		return true;
	}

	@Override
	public User getUser(String username) {
		String hql = "from User t where t.status = 'A' and t.name = :name";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name", username);
		return (User) baseDao.get(hql, params);
	}

	@Override
	public Role getRole(Integer roleId) {
		
		return (Role) baseDao.get(Role.class, roleId);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public UserInfo getUserById(Integer id) {
		UserInfo userInfo = new UserInfo();
		String sql = "";
		
		sql = "select u.USER_ID u_id, u.NAME u_name, e.EMPLOYEE_ID e_id, e.NAME e_name, d.DEPARTMENT_ID d_id, " + 
		"d.NAME d_name, d.MANAGER d_manager " +
		"from USERS u, EMPLOYEES e, DEPARTMENTS d " + 
		"where u.EMPLOYEE_ID=e.EMPLOYEE_ID and e.DEPARTMENT_ID=d.DEPARTMENT_ID and u.USER_ID=" + id;
		
		List userList = baseDao.findBySQL(sql);
		if(null != userList && userList.size() > 0){
			Object[] user = (Object[])userList.get(0);
			userInfo.setId((Integer) user[0]);
			userInfo.setName(user[1].toString());
			userInfo.setEmployeeId((Integer) user[2]);
			userInfo.setEmployeeName(user[3].toString());
			userInfo.setDepartmentId((Integer) user[4]);
			userInfo.setDepartmentName(user[5].toString());
			
			if(null != user[6]){
				sql = "select e.EMPLOYEE_ID e_id, e.NAME e_name from EMPLOYEES e where e.EMPLOYEE_ID=" + user[6];
			 	List empList = baseDao.findBySQL(sql);
			 	if(null != empList && empList.size() > 0){
			 		Object[] emp = (Object[])empList.get(0);
			 		userInfo.setManagerId((Integer) emp[0]);
			 		userInfo.setManagerName(emp[1].toString());
			 	}
			}
			
		}
		
		return userInfo;
	}

	@Override
	public List<User> findAll(Map<String, Object> params, PageUtil pageUtil) {
		
		return generalDao.findAll(User.class, params, pageUtil);
	}

	@Override
	public long getCount(Map<String, Object> params) {
		
		return generalDao.getCount(User.class, params);
	}

	@Override
	public User get(Integer id) {
		
		return generalDao.get(User.class, id);
	}

	@Override
	public void update(User master) {
		
		generalDao.update(master);
	}

	@Override
	public boolean persistence(User master) throws Exception {
		
		return generalDao.persistence(master, statusFields);
	}

	@Override
	public boolean deleteToUpdate(Integer pid) {
		
		return generalDao.deleteToUpdate(User.class, pid, statusFields);
	}

}
