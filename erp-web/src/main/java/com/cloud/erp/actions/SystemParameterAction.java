/**
 * @Title:  SystemParameterAction.java
 * @Package:  com.cloud.erp.actions
 * @Description:  
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年3月30日 下午5:00:19
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
import com.cloud.erp.common.BaseAction;
import com.cloud.erp.entities.table.Parameter;
import com.cloud.erp.service.SystemParameterService;

/**
 * @ClassName  SystemParameterAction
 * @Description  
 * @author  bollen bollen@live.cn
 * @date  2015年3月30日 下午5:00:19
 *
 */
@Namespace("/systemParameter")
@Action("systemParameterAction")
public class SystemParameterAction extends BaseAction{

	private static final long serialVersionUID = 1L;
	private SystemParameterService systemParameterService;
	private String type;
	
	/**
	 * @param systemParameterService the systemParameterService to set
	 */
	@Autowired
	public void setSystemParameterService(
			SystemParameterService systemParameterService) {
		this.systemParameterService = systemParameterService;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String persistenceCompanyInfo() throws Exception {
		Map<String, List<Parameter>> map=new HashMap<String, List<Parameter>>();
		map.put("addList", JSON.parseArray(inserted, Parameter.class));
		map.put("updList", JSON.parseArray(updated, Parameter.class));
		map.put("delList", JSON.parseArray(deleted, Parameter.class));
		OutputJson(getMessage(systemParameterService.persistenceParameter(map)));
		return null;
	}
	public String findParameterList() throws Exception
	{
		OutputJson(systemParameterService.findParameterList(type));
		return null;
	}
}
