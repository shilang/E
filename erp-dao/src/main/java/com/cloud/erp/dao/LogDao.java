/**
 * @Title:  LogsDao.java
 * @Package:  com.cloud.erp.dao
 * @Description:  TODO
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年3月19日 下午3:57:00
 * @version:  v1.0
 *
 * History:
 * Date		Author		Version
 * ---------------------------------------------
 * <reasons>
 */
package com.cloud.erp.dao;

import java.util.List;
import java.util.Map;

import com.cloud.erp.entities.Log;
import com.cloud.erp.utils.PageUtil;

/**
 * @ClassName LogsDao
 * @Description TODO
 * @author bollen bollen@live.cn
 * @date 2015年3月19日 下午3:57:00
 *
 */
public interface LogDao {

	List<Log> findLogAllList(Map<String, Object> map, PageUtil pageUtil);

	Long getCount(Map<String, Object> map, PageUtil pageUtil);

	boolean persistenceLog(Log l);

	boolean delLog(Integer logId);

}
