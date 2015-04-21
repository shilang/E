/**
 * @Title:  BugDao.java
 * @Package:  com.cloud.erp.dao
 * @Description:  TODO
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年3月31日 上午10:20:45
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

import com.cloud.erp.entities.Bug;
import com.cloud.erp.utils.PageUtil;

/**
 * @ClassName BugDao
 * @Description TODO
 * @author bollen bollen@live.cn
 * @date 2015年3月31日 上午10:20:45
 *
 */
public interface BugDao {

	List<Bug> findBugList(Map<String, Object> param, PageUtil pageUtil);

	Long getCount(Map<String, Object> param, PageUtil pageUtil);

	boolean addBug(Bug bug);

	boolean delBug(Integer bugId);
}
