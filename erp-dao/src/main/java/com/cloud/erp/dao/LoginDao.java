/**
 * @Title:  LoginDao.java
 * @Package:  com.cloud.erp.dao
 * @Description:  TODO
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年4月16日 下午4:40:32
 * @version:  v1.0
 *
 * History:
 * Date		Author		Version
 * ---------------------------------------------
 * <reasons>
 */
package com.cloud.erp.dao;

import java.util.List;

import com.cloud.erp.entities.viewmodel.MenuModel;

/**
 * @ClassName LoginDao
 * @Description TODO
 * @author bollen bollen@live.cn
 * @date 2015年4月16日 下午4:40:32
 *
 */
public interface LoginDao {

	List<MenuModel> findMenuList();
}
