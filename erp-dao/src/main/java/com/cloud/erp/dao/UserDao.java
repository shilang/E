/**
 * @Title:  UserDao.java
 * @Package:  com.cloud.erp.dao
 * @Description:  
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年2月2日  下午5:45:48
 * @version:  v1.0
 *
 * History:
 * Date		Author		Version
 * ---------------------------------------------
 * <reasons>
 */
package com.cloud.erp.dao;

import java.util.List;

import com.cloud.erp.dao.common.GeneralDao;
import com.cloud.erp.entities.table.Role;
import com.cloud.erp.entities.table.User;
import com.cloud.erp.entities.viewmodel.UserRoleModel;
import com.cloud.erp.mapper.UserMapper;

/**
 * @ClassName UserDao
 * @Description 
 * @author bollen bollen@live.cn
 * @date 2015年2月2日 下午5:45:48
 *
 */
public interface UserDao extends GeneralDao<User>, UserMapper{

	List<UserRoleModel> findUserRolesList(Integer userId);

	boolean saveUserRoles(Integer userId, String isCheckedIds);
	
	User getUser(String username);
	
	Role getRole(Integer roleId);



}
