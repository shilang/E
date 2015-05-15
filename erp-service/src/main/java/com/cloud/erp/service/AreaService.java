/**
 * @Title:  AreaService.java
 * @Package:  com.cloud.erp.service
 * @Description:  TODO
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年5月12日 上午10:23:59
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

import com.cloud.erp.entities.table.Area;
import com.cloud.erp.utils.PageUtil;

/**
 * @ClassName  AreaService
 * @Description  TODO
 * @author  bollen bollen@live.cn
 * @date  2015年5月12日 上午10:23:59
 *
 */
public interface AreaService {

	List<Area> findAreas(Map<String, Object> params, PageUtil pageUtil);
	
	Long getCount(Map<String, Object> params, PageUtil pageUtil);
	
	boolean persistenceArea(Area area);
	
	boolean delArea(Integer id);
}
