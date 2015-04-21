/**
 * @Title:  FunctionAction.java
 * @Package:  com.cloud.erp.actions
 * @Description:  TODO
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年2月13日  下午2:23:52
 * @version:  v1.0
 *
 * History:
 * Date		Author		Version
 * ---------------------------------------------
 * <reasons>
 */
package com.cloud.erp.actions;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.cloud.erp.entities.Permission;
import com.cloud.erp.entities.viewmodel.Json;
import com.cloud.erp.logging.LogUtil;
import com.cloud.erp.service.FunctionService;
import com.cloud.erp.utils.Constants;
import com.opensymphony.xwork2.ModelDriven;

/**
 * @ClassName  FunctionAction
 * @Description  TODO
 * @author  bollen bollen@live.cn
 * @date  2015年2月13日  下午2:23:52
 *
 */
@Namespace("/function")
@Action(value = "functionAction")
public class FunctionAction extends BaseAction implements ModelDriven<Permission>{

	private static final long serialVersionUID = 1L;
	private static final Logger log = LogUtil.getLogger(FunctionAction.class);
	private FunctionService functionService;
	private Permission permission;
	private Integer id;
	
	/**
	 * @param functionService the functionService to set
	 */
	@Autowired
	public void setFunctionService(FunctionService functionService) {
		this.functionService = functionService;
	}
	
	public Permission getPermission() {
		return permission;
	}

	public void setPermission(Permission permission) {
		this.permission = permission;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	/**
	 * function: TODO persistance function entity
	 *
	 * @Title:  persistenceFunction
	 * @param: 
	 * @return:  String
	 * @throws:
	 */
	public String persistenceFunction() throws Exception{
		Json json = new Json();
		if(functionService.persistenceFunction(JSON.parseArray(updated, Permission.class))){
			json.setStatus(true);
			json.setMessage(Constants.POST_DATA_SUCCESS);
		}else {
			json.setMessage(Constants.POST_DATA_FAIL);
		}
		OutputJson(json);
		return null;		
	}
	
	/**
	 * function: TODO show editor
	 *
	 * @Title:  persistenceFunctionDig
	 * @param: 
	 * @return:  String
	 * @throws:
	 */
	public String persistenceFunctionDig() throws Exception{
		OutputJson(getMessage(functionService.persistenceFunction(getModel())),Constants.TEXT_TYPE_PLAIN);
		return null;
	}
	
	/**
	 * function: TODO delete function
	 *
	 * @Title:  delFunction
	 * @param: 
	 * @return:  String
	 * @throws:
	 */
	public String delFunction() throws Exception{
		Json json = new Json();
		if(functionService.delFunction(id)){
			json.setStatus(true);
			json.setMessage(Constants.POST_DATA_SUCCESS);
		}else {
			json.setMessage(Constants.POST_DATA_FAIL + Constants.IS_EXT_SUBMENU);
		}
		OutputJson(json);
		return null;
	}
	
	/**
	 * function: TODO query all function by node
	 *
	 * @Title:  findAllFunctionList
	 * @param: 
	 * @return:  String
	 * @throws:
	 */
	public String findAllFunctionList() throws Exception{
		OutputJson(functionService.findAllFunctionList(id));
		return null;
	}
	
	public String findAllFunctionLists() throws Exception{
		OutputJson(functionService.findAllFunctionList());
		return null;
	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	@Override
	public Permission getModel() {
		// TODO Auto-generated method stub
		if(null == permission){
			permission = new Permission();
		}
		return permission;
	}

}
