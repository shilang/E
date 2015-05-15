/**
 * @Title:  LogsService.java
 * @Package:  com.cloud.erp.service
 * @Description:  TODO
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年3月19日 下午3:53:18
 * @version:  v1.0
 *
 * History:
 * Date		Author		Version
 * ---------------------------------------------
 * <reasons>
 */
package com.cloud.erp.service;

import java.util.List;
import java.util.Map;

import com.cloud.erp.entities.table.Log;
import com.cloud.erp.utils.PageUtil;

/**
 * @ClassName  LogsService
 * @Description  TODO
 * @author  bollen bollen@live.cn
 * @date  2015年3月19日 下午3:53:18
 *
 */
public interface LogService {
	
	List<Log> findLogAllList(Map<String, Object> map, PageUtil pageUtil);
	
	Long getCount(Map<String, Object> map, PageUtil pageUtil);
	
	boolean persistenceLog(Log l);
	
	boolean delLog(Integer logId);
	
}
