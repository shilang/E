/**
 * @Title:  LogsAction.java
 * @Package:  com.cloud.erp.actions
 * @Description:  TODO
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

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.cloud.erp.entities.table.Log;
import com.cloud.erp.entities.viewmodel.GridModel;
import com.cloud.erp.service.LogService;
import com.cloud.erp.utils.Constants;
import com.cloud.erp.utils.PageUtil;
import com.opensymphony.xwork2.ModelDriven;

/**
 * @ClassName  LogAction
 * @Description  TODO
 * @author  bollen bollen@live.cn
 * @date  2015年3月19日 下午3:28:17
 *
 */
@Namespace("/log")
@Action("logAction")
public class LogAction extends BaseAction implements ModelDriven<Log>{

	private static final long serialVersionUID = 1L;
	private Log log;
	private LogService logsService;
	
	/**
	 * @param logsService the logsService to set
	 */
	@Autowired
	public void setLogsService(LogService logsService) {
		this.logsService = logsService;
	}
	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	@Override
	public Log getModel() {
		// TODO Auto-generated method stub
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
	 * function: TODO query all logs
	 * @Author: bollen bollen@live.cn
	 * @Date: 2015年3月20日 上午11:07:27
	 * @Title: findLogsAllList
	 * @return
	 * @throws Exception
	 */
	public String findLogAllList() throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		if(null != searchValue && !"".equals(searchValue)){
			map.put(searchName, Constants.GET_SQL_LIKE + searchValue + Constants.GET_SQL_LIKE);
		}
		PageUtil pageUtil = new PageUtil(page, rows, searchAnds, searchColumnNames, searchConditions, searchVals);
		GridModel gridModel = new GridModel();
		gridModel.setRows(logsService.findLogAllList(map, pageUtil));
		gridModel.setTotal(logsService.getCount(map, pageUtil));
		OutputJson(gridModel);
		return  null;
	}

	/**
	 * function: TODO persistence log
	 * @Author: bollen bollen@live.cn
	 * @Date: 2015年3月20日 上午11:17:09
	 * @Title: persistenceLogs
	 * @return
	 * @throws Exception
	 */
	public String persistenceLog() throws Exception{
		OutputJson(getMessage(logsService.persistenceLog(getModel())),Constants.TEXT_TYPE_PLAIN);
		return null;
	}
	
	public String delLog() throws Exception{
		OutputJson(getMessage(logsService.delLog(getModel().getLogId())));
		return null;
	}
}
