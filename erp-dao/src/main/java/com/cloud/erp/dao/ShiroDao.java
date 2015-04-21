/**
 * @Title:  ShiroDao.java
 * @Package:  com.cloud.erp.dao
 * @Description:  TODO
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年2月3日  下午3:03:32
 * @version:  v1.0
 *
 * History:
 * Date		Author		Version
 * ---------------------------------------------
 * <reasons>
 */
package com.cloud.erp.dao;

import java.util.List;

import com.cloud.erp.entities.User;

/**
 * @ClassName ShiroDao
 * @Description TODO
 * @author bollen bollen@live.cn
 * @date 2015年2月3日 下午3:03:32
 *
 */
public interface ShiroDao {

	public User getUser(String username);

	public List<Object> getPermissions(boolean isAdmin, String username);

}
