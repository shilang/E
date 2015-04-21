/**
 * @Title:  LoginService.java
 * @Package:  com.cloud.erp.service
 * @Description:  TODO
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年2月4日  下午5:26:10
 * @version:  v1.0
 *
 * History:
 * Date		Author		Version
 * ---------------------------------------------
 * <reasons>
 */
package com.cloud.erp.service;

import java.util.List;

import com.cloud.erp.entities.viewmodel.MenuModel;

/**
 * @ClassName  LoginService
 * @Description  TODO
 * @author  bollen bollen@live.cn
 * @date  2015年2月4日  下午5:26:10
 *
 */
public interface LoginService {
	
	List<MenuModel> findMenuList();
	
}
