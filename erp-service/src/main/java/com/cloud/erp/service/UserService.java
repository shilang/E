/**
 * @Title:  UserService.java
 * @Package:  com.cloud.erp.service
 * @Description:  
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年2月3日  上午9:24:43
 * @version:  v1.0
 *
 * History:
 * Date		Author		Version
 * ---------------------------------------------
 * <reasons>
 */
package com.cloud.erp.service;

import java.util.List;

import com.cloud.erp.entities.table.Role;
import com.cloud.erp.entities.table.User;
import com.cloud.erp.entities.table.UserInfo;
import com.cloud.erp.entities.viewmodel.UserRoleModel;
import com.cloud.erp.service.common.GeneralService;

/**
 * @ClassName  UserService
 * @Description  
 * @author  bollen bollen@live.cn
 * @date  2015年2月3日  上午9:24:43
 *
 */
public interface UserService extends GeneralService<User>{

	List<User> findUsers();

	List<UserRoleModel> findUserRolesList(Integer userId );

	boolean saveUserRoles(Integer userId, String isCheckedIds );
	
	Role getRole(Integer roleId);
	
	boolean updatePasswd(String username, String oldPasswd, String newPasswd) throws Exception;

	UserInfo getUserById(Integer id);
	
}
