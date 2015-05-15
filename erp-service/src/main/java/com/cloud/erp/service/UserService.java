/**
 * @Title:  UserService.java
 * @Package:  com.cloud.erp.service
 * @Description:  TODO
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
import java.util.Map;

import com.cloud.erp.entities.table.User;
import com.cloud.erp.entities.viewmodel.UserRoleModel;
import com.cloud.erp.utils.PageUtil;

/**
 * @ClassName  UserService
 * @Description  TODO
 * @author  bollen bollen@live.cn
 * @date  2015年2月3日  上午9:24:43
 *
 */
public interface UserService {

	boolean persistenceUsers(Map<String, List<User>> map );

	List<User> findAllUsersList(Map<String, Object> map, PageUtil pageUtil);

	Long getCount(Map<String, Object> map , PageUtil pageUtil);

	List<UserRoleModel> findUserRolesList(Integer userId );

	boolean saveUserRoles(Integer userId, String isCheckedIds );

	boolean persistenceUser(User u );

	boolean delUser(Integer userId );
}
