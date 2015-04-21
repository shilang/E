/**
 * @Title:  SystemParameterDao.java
 * @Package:  com.cloud.erp.dao
 * @Description:  TODO
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年3月31日 上午9:32:56
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

import com.cloud.erp.entities.Parameter;
import com.cloud.erp.entities.viewmodel.ParameterModel;

/**
 * @ClassName SystemParameterDao
 * @Description TODO
 * @author bollen bollen@live.cn
 * @date 2015年3月31日 上午9:32:56
 *
 */
public interface SystemParameterDao {

	List<ParameterModel> findParameterList(String type);

	boolean persistenceParameter(Map<String, List<Parameter>> map);

}
