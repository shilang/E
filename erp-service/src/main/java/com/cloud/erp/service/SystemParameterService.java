/**
 * @Title:  SystemParameterService.java
 * @Package:  com.cloud.erp.service
 * @Description:  
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年3月30日 下午5:25:51
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

import com.cloud.erp.entities.table.Parameter;
import com.cloud.erp.entities.viewmodel.ParameterModel;

/**
 * @ClassName  SystemParameterService
 * @Description  
 * @author  bollen bollen@live.cn
 * @date  2015年3月30日 下午5:25:51
 *
 */
public interface SystemParameterService {
	
	List<ParameterModel> findParameterList(String type);
	
	boolean persistenceParameter(Map<String, List<Parameter>> map);
}
