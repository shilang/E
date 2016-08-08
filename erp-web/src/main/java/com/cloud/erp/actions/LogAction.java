/**
 * @Title:  LogsAction.java
 * @Package:  com.cloud.erp.actions
 * @Description:  
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年3月19日 下午3:28:17
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
import com.cloud.erp.entities.table.Log;
import com.cloud.erp.service.LogService;
import com.cloud.erp.utils.PageUtil;
import com.opensymphony.xwork2.ModelDriven;

/**
 * @ClassName  LogAction
 * @Description  
 * @author  bollen bollen@live.cn
 * @date  2015年3月19日 下午3:28:17
 *
 */
@Namespace("/log")
public class LogAction extends BaseAction implements ModelDriven<Log>{

	private static final long serialVersionUID = 1L;
	private Log log;
	
	@Resource
	private LogService logsService;
	
	@Override
	public Log getModel() {
		if(null == log){
			log = new Log();
		}
		return log;
	}
	
	public Log getLog() {
		return log;
	}
	
	public void setLog(Log log) {
		this.log = log;
	}
	
	/**
	 * function: query all logs
	 * @Author: bollen bollen@live.cn
	 * @Date: 2015年3月20日 上午11:07:27
	 * @Title: findLogsAllList
	 * @return
	 * @throws Exception
	 */
	@Action(value = "find")
	public String findLogAllList() throws Exception{
		Map<String, Object> params = getQueryParamsForMain();
		PageUtil pageUtil = getPageUtil();
		JSONWriter(logsService.findAll(params, pageUtil), 
				logsService.getCount(params));
		return RJSON;
	}

	@Action(value = "delete")
	public String delLog() throws Exception{
		boolean result = logsService.deleteToUpdate(getModel().getLogId());
		JSONWriter(result);
		return RJSON;
	}
}
