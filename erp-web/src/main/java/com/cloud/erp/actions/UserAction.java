/**
 * @Title:  UserAction.java
 * @Package:  com.cloud.erp.actions
 * @Description:  TODO
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.cloud.erp.entities.User;
import com.cloud.erp.entities.viewmodel.GridModel;
import com.cloud.erp.entities.viewmodel.Json;
import com.cloud.erp.service.UserService;
import com.cloud.erp.utils.Constants;
import com.cloud.erp.utils.PageUtil;
import com.opensymphony.xwork2.ModelDriven;
import com.sun.tools.doclets.internal.toolkit.util.DocFinder.Output;

/**
 * @ClassName  UserAction
 * @Description  TODO
 * @author  bollen bollen@live.cn
 * @date  2015年3月20日 上午11:24:29
 *
 */
@Namespace("/user")
@Action("userAction")
public class UserAction extends BaseAction implements ModelDriven<User>{
	
	private static final long serialVersionUID = 1L;
	private UserService userService;
	private String isCheckedIds;
	private User user;
	
	/**
	 * @param userService the userService to set
	 */
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
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
	
	/** 
	 * function: TODO query all users
	 * @Author: bollen bollen@live.cn
	 * @Date: 2015年3月23日 上午10:02:31
	 * @Title: findAllUserList
	 * @return
	 * @throws Exception
	 */
	public String findAllUserList() throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		if(null != searchValue && !"".equals(searchValue)){
			map.put(searchName, Constants.GET_SQL_LIKE + searchValue + Constants.GET_SQL_LIKE);
		}
		PageUtil pageUtil = new PageUtil(page, rows, searchAnds, searchColumnNames, searchConditions, searchVals);
		GridModel gridModel = new GridModel();
		gridModel.setRows(userService.findAllUsersList(map, pageUtil));
		gridModel.setTotal(userService.getCount(map, pageUtil));
		OutputJson(gridModel);
		return null;
	}
	
	/**
	 * function: TODO persistence user
	 * @Author: bollen bollen@live.cn
	 * @Date: 2015年3月23日 上午10:09:21
	 * @Title: persistenceUsers
	 * @return
	 * @throws Exception
	 */
	public String persistenceUsers() throws Exception{
		Map<String, List<User>> map = new HashMap<String, List<User>>();
		map.put("addList", JSON.parseArray(inserted, User.class));
		map.put("updList", JSON.parseArray(updated, User.class));
		map.put("delList", JSON.parseArray(deleted, User.class));
		Json json = new Json();
		if(userService.persistenceUsers(map)){
			json.setStatus(true);
			json.setMessage("数据更新成功！");
		}else {
			json.setMessage("提交失败了!");
		}
		OutputJson(json);
		return null;
	}
	
	public String persistenceUserDig() throws Exception{
		OutputJson(getMessage(userService.persistenceUser(getModel())), Constants.TEXT_TYPE_PLAIN);
		return null;
	}
	
	/**
	 * function: TODO delete user
	 * @Author: bollen bollen@live.cn
	 * @Date: 2015年3月23日 上午10:12:15
	 * @Title: delUser
	 * @return
	 * @throws Exception
	 */
	public String delUser() throws Exception{
		OutputJson(getMessage(userService.delUser(getModel().getUserId())));
		return null;
	}
	
	/**
	 * function: TODO query roles
	 * @Author: bollen bollen@live.cn
	 * @Date: 2015年3月23日 上午10:14:00
	 * @Title: findUserRoleList
	 * @return
	 * @throws Exception
	 */
	public String findUserRoleList() throws Exception{
		OutputJson(userService.findUserRolesList(getModel().getUserId()));
		return null;
	}
	
	public String saveUserRoles() throws Exception{
		Json json = new Json();
		if(userService.saveUserRoles(getModel().getUserId(), isCheckedIds)){
			json.setStatus(true);
			json.setMessage("数据更新成功！");
		}else {
			json.setMessage("提交失败了！");
		}
		OutputJson(json);
		return null;
	}
	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	@Override
	public User getModel() {
		// TODO Auto-generated method stub
		if(null == user){
			user = new User();
		}
		return user;
	}
}
