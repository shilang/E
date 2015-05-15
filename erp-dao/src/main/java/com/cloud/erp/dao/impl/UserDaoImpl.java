/**
 * @Title:  UserDaoImpl.java
 * @Package:  com.cloud.erp.dao.impl
 * @Description:  TODO
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
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cloud.erp.dao.BaseDao;
import com.cloud.erp.dao.UserDao;
import com.cloud.erp.entities.shiro.ShiroUser;
import com.cloud.erp.entities.table.Role;
import com.cloud.erp.entities.table.User;
import com.cloud.erp.entities.table.UserRole;
import com.cloud.erp.entities.viewmodel.UserRoleModel;
import com.cloud.erp.utils.Constants;
import com.cloud.erp.utils.PageUtil;

/**
 * @ClassName  UserDaoImpl
 * @Description  TODO
 * @author  bollen bollen@live.cn
 * @date  2015年2月2日  下午5:58:20
 *
 */
@SuppressWarnings("unchecked")
@Repository("userDao")
public class UserDaoImpl implements UserDao {


	@SuppressWarnings("rawtypes")
	private BaseDao baseDao;
	
	/**
	 * @param baseDao the baseDao to set
	 */
	@SuppressWarnings("rawtypes")
	@Autowired
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}
	
	private boolean addUser(List<User> addList){
		if(addList != null && addList.size() != 0){
			ShiroUser shiroUser = Constants.getCurrentUser();
			for(User user : addList){
				user.setCreated(new Date());
				user.setLastmod(new Date());
				user.setCreater(shiroUser.getUserId());
				user.setModifier(shiroUser.getUserId());
				user.setStatus(Constants.PERSISTENCE_STATUS);
				baseDao.save(user);
			}
		}
		return true;
	}
	
	private boolean updUser(List<User> updList){
		if(updList != null && updList.size() != 0){
			ShiroUser shiroUser = Constants.getCurrentUser();
			for(User user : updList){
				user.setLastmod(new Date());
				user.setModifier(shiroUser.getUserId());
				baseDao.update(user);
			}
		}
		return true;
	}
	
	private boolean delUser(List<User> delList){
		ShiroUser shiroUser = Constants.getCurrentUser();
		if(delList != null && delList.size() != 0){
			for(User user : delList){
				user.setLastmod(new Date());
				user.setStatus(Constants.PERSISTENCE_DELETE_STATUS);
				user.setModifier(shiroUser.getUserId());
				baseDao.update(user);
			}
		}
		return true;
	}
	
	/* (non-Javadoc)
	 * @see com.cloud.erp.dao.UserDao#persistenceUsers(java.util.Map)
	 */
	@Override
	public boolean persistenceUsers(Map<String, List<User>> map) {
		// TODO Auto-generated method stub
		this.addUser(map.get("addList"));
		this.updUser(map.get("updList"));
		this.delUser(map.get("delList"));
		return true;
	}

	/* (non-Javadoc)
	 * @see com.cloud.erp.dao.UserDao#findAllUserList(java.util.Map, com.cloud.erp.utils.PageUtil)
	 */
	@Override
	public List<User> findAllUsersList(Map<String, Object> map, PageUtil pageUtil) {
		// TODO Auto-generated method stub
		String hql = "from User u where u.status='A' ";
		hql+=Constants.getSearchConditionsHQL("u", map);
		hql+=Constants.getGradeSearchConditionsHQL("u", pageUtil);
		List<User> list = baseDao.find(hql, map, pageUtil.getPage(), pageUtil.getRows());
		for(User user : list){
			user.setUserRoles(null);
		}
		return list;
	}

	/* (non-Javadoc)
	 * @see com.cloud.erp.dao.UserDao#getCount(java.util.Map, com.cloud.erp.utils.PageUtil)
	 */
	@Override
	public Long getCount(Map<String, Object> map, PageUtil pageUtil) {
		// TODO Auto-generated method stub
		String hql = "select count(*) from User u where u.status='A' ";
		hql += Constants.getSearchConditionsHQL("u", map);
		hql += Constants.getGradeSearchConditionsHQL("u", pageUtil);
		return baseDao.count(hql, map);
	}

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
	/* (non-Javadoc)
	 * @see com.cloud.erp.dao.UserDao#findUsersRolesList(java.lang.Integer)
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public List<UserRoleModel> findUserRolesList(Integer userId) {
		// TODO Auto-generated method stub
		String sql = "SELECT ur.USER_ID, ur.ROLE_ID FROM\n" +
				"USER_ROLE AS ur WHERE ur.STATUS = 'A' AND ur.USER_ID=" + userId;
		List list = baseDao.findBySQL(sql);
		List<UserRoleModel> listm = getUserRoleModelList(userId, list);
		return listm;
	}

	/* (non-Javadoc)
	 * @see com.cloud.erp.dao.UserDao#saveUserRoles(java.lang.Integer, java.lang.String)
	 */
	@Override
	public boolean saveUserRoles(Integer userId, String isCheckedIds) {
		// TODO Auto-generated method stub
		User user = (User) baseDao.get(User.class, userId);
		Set<UserRole> set = user.getUserRoles();
		Map<Integer, UserRole>	map = new HashMap<Integer, UserRole>();
		for(UserRole userRole : set){
			map.put(userRole.getRole().getRoleId(), userRole);
			userRole.setLastmod(new Date());
			userRole.setStatus(Constants.PERSISTENCE_DELETE_STATUS);
			baseDao.deleteToUpdate(userRole);
		}
		if(!"".equals(isCheckedIds) && isCheckedIds.length() != 0){
			String[] ids = isCheckedIds.split(",");
			ShiroUser currUser = Constants.getCurrentUser();
			for(String id : ids){
				Integer tempId = Integer.valueOf(id);
				Role role = (Role) baseDao.get(Role.class, Integer.valueOf(id));
				UserRole userRole = null;
				if(map.containsKey(tempId)){
					userRole = map.get(tempId);
					userRole.setStatus(Constants.PERSISTENCE_STATUS);
					userRole.setCreater(currUser.getUserId());
					userRole.setModifier(currUser.getUserId());
					baseDao.update(userRole);
				}else {
					userRole = new UserRole();
					userRole.setCreated(new Date());
					userRole.setLastmod(new Date());
					userRole.setRole(role);
					userRole.setUser(user);
					userRole.setCreater(currUser.getUserId());
					userRole.setModifier(currUser.getUserId());
					userRole.setStatus(Constants.PERSISTENCE_STATUS);
					baseDao.save(userRole);
				}
			}
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see com.cloud.erp.dao.UserDao#persistenceUsers(com.cloud.erp.entities.User)
	 */
	@Override
	public boolean persistenceUser(User u) {
		// TODO Auto-generated method stub
		Integer userId = Constants.getCurrentUser().getUserId();
		if(null == u.getUserId() || "".equals(u.getUserId())){
			u.setCreated(new Date());
			u.setLastmod(new Date());
			u.setCreater(userId);
			u.setModifier(userId);
			u.setStatus(Constants.PERSISTENCE_STATUS);
			baseDao.save(u);
		}else {
			u.setLastmod(new Date());
			u.setModifier(userId);
			baseDao.update(u);
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see com.cloud.erp.dao.UserDao#delUsers(java.lang.Integer)
	 */
	@Override
	public boolean delUser(Integer userId) {
		// TODO Auto-generated method stub
		User user = (User) baseDao.get(User.class, userId);
		user.setStatus(Constants.PERSISTENCE_DELETE_STATUS);
		user.setLastmod(new Date());
		user.setModifier(Constants.getCurrentUser().getUserId());
		baseDao.deleteToUpdate(user);
		return true;
	}

}
