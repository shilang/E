/**
 * @Title:  BugService.java
 * @Package:  com.cloud.erp.service
 * @Description:  TODO
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年3月31日 上午10:18:14
 * @version:  v1.0
 *
 * History:
 * Date		Author		Version
 * ---------------------------------------------
 * <reasons>
 */
package com.cloud.erp.service;

import com.cloud.erp.entities.table.Bug;
import com.cloud.erp.utils.PageUtil;

import java.util.List;
import java.util.Map;

/**
 * @ClassName  BugService
 * @Description  TODO
 * @author  bollen bollen@live.cn
 * @date  2015年3月31日 上午10:18:14
 *
 */
public interface BugService {

	List<Bug> findBugList(Map<String, Object> param, PageUtil pageUtil);
	
	Long getCount(Map<String, Object> param, PageUtil pageUtil);
	
	boolean addBug(Bug bug);
	
	boolean delBug(Integer bugId);
}
