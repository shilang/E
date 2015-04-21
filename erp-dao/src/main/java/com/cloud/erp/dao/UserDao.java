/**
 * @Title:  UserDao.java
 * @Package:  com.cloud.erp.dao
 * @Description:  TODO
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
import java.util.Map;

import com.cloud.erp.entities.User;
import com.cloud.erp.entities.viewmodel.UserRoleModel;
import com.cloud.erp.utils.PageUtil;

/**
 * @ClassName UserDao
 * @Description TODO
 * @author bollen bollen@live.cn
 * @date 2015年2月2日 下午5:45:48
 *
 */
public interface UserDao {

	boolean persistenceUsers(Map<String, List<User>> map);

	List<User> findAllUsersList(Map<String, Object> map, PageUtil pageUtil);

	Long getCount(Map<String, Object> map, PageUtil pageUtil);

	List<UserRoleModel> findUserRolesList(Integer userId);

	boolean saveUserRoles(Integer userId, String isCheckedIds);

	boolean persistenceUser(User u);

	boolean delUser(Integer userId);

}
