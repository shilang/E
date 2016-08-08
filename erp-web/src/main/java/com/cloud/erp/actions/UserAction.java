/**
 * @Title:  UserAction.java
 * @Package:  com.cloud.erp.actions
 * @Description:  
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年3月20日 上午11:24:29
 * @version:  v1.0
 *
 * History:
 * Date		Author		Version
 * ---------------------------------------------
 * <reasons>
 */
package com.cloud.erp.actions;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;

import com.cloud.erp.common.BaseAction;
import com.cloud.erp.entities.table.User;
import com.cloud.erp.entities.viewmodel.Json;
import com.cloud.erp.service.UserService;
import com.cloud.erp.utils.PageUtil;
import com.opensymphony.xwork2.ModelDriven;

/**
 * @ClassName  UserAction
 * @Description  
 * @author  bollen bollen@live.cn
 * @date  2015年3月20日 上午11:24:29
 *
 */
@Namespace("/user")
public class UserAction extends BaseAction implements ModelDriven<User>{
	
	private static final long serialVersionUID = 1L;
	
	@Resource
	private UserService userService;
	
	private String isCheckedIds;
	private User user;
	private String oldPassword;
	
	public String getIsCheckedIds() {
		return isCheckedIds;
	}

	public void setIsCheckedIds(String isCheckedIds) {
		this.isCheckedIds = isCheckedIds;
	}

	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	/** 
	 * function: query all users
	 * @Author: bollen bollen@live.cn
	 * @Date: 2015年3月23日 上午10:02:31
	 * @Title: findAllUserList
	 * @return
	 * @throws Exception
	 */
	@Action(value = "findUsers")
	public String findUsers() throws Exception{
		Map<String, Object> params = getQueryParamsForMain();
		PageUtil pageUtil = getPageUtil();
		JSONWriter(userService.findAll(params, pageUtil), 
				userService.getCount(params));
		return RJSON;
	}
	
	/**
	 * function: persistence user
	 * @Author: bollen bollen@live.cn
	 * @Date: 2015年3月23日 上午10:09:21
	 * @Title: persistenceUsers
	 * @return
	 * @throws Exception
	 */
	@Action("persistence")
	public String persistenceUser() throws Exception{
		boolean result = userService.persistence(getUser());
		JSONWriter(result);
		return RJSON;
	}
	
	/**
	 * function: delete user
	 * @Author: bollen bollen@live.cn
	 * @Date: 2015年3月23日 上午10:12:15
	 * @Title: delUser
	 * @return
	 * @throws Exception
	 */
	@Action("delUser")
	public String delUser() throws Exception{
		boolean result = userService.deleteToUpdate(getUser().getUserId());
		JSONWriter(result);
		return RJSON;
	}
	
	/**
	 * function: query roles
	 * @Author: bollen bollen@live.cn
	 * @Date: 2015年3月23日 上午10:14:00
	 * @Title: findUserRoleList
	 * @return
	 * @throws Exception
	 */
	@Action("findUserRoleList")
	public String findUserRoleList() throws Exception{
		JSONWriterGeneral(userService.findUserRolesList(getModel().getUserId()));
		return RJSON;
	}
	
	@Action("saveUserRoles")
	public String saveUserRoles() throws Exception{
		boolean result = userService.saveUserRoles(getModel().getUserId(), isCheckedIds);
		JSONWriter(result);
		return RJSON;
	}

	@Action("updatePasswd")
	public String updatePasswd() throws Exception{
		Json json = new Json();
		if(userService.updatePasswd(user.getName(), oldPassword, user.getPassword())){
			json.setStatus(true);
			json.setMessage("密码修改成功！");
		}else {
			json.setStatus(false);
			json.setMessage("密码修改失败！");
		}
		JSONWriter(json);
		return RJSON;
	}
	
	@Action(value = "getUserById")
	public String getUserById() throws Exception{
		JSONWriterGeneral(userService.getUserById(user.getUserId()));
		return RJSON;
	}
	
	@Override
	public User getModel() {
		
		if(null == user){
			user = new User();
		}
		return user;
	}
}
