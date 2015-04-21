/**
 * @Title:  LoginServiceImpl.java
 * @Package:  com.cloud.erp.service.impl
 * @Description:  TODO
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年2月4日  下午5:31:14
 * @version:  v1.0
 *
 * History:
 * Date		Author		Version
 * ---------------------------------------------
 * <reasons>
 */
package com.cloud.erp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloud.erp.dao.LoginDao;
import com.cloud.erp.entities.viewmodel.MenuModel;
import com.cloud.erp.service.LoginService;

/**
 * @ClassName  LoginServiceImpl
 * @Description  TODO
 * @author  bollen bollen@live.cn
 * @date  2015年2月4日  下午5:31:14
 *
 */

@Service("loginService")
public class LoginServiceImpl implements LoginService {

	private LoginDao loginDao;
	
	/**
	 * @param loginDao the loginDao to set
	 */
	@Autowired
	public void setLoginDao(LoginDao loginDao) {
		this.loginDao = loginDao;
	}
	/* (non-Javadoc)
	 * @see com.cloud.erp.service.LoginService#findMenuList()
	 */
	@Override
	public List<MenuModel> findMenuList() {
		// TODO Auto-generated method stub
		return loginDao.findMenuList();
	}

}
