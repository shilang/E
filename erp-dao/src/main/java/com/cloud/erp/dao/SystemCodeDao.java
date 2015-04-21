/**
 * @Title:  SystemCodeDao.java
 * @Package:  com.cloud.erp.dao
 * @Description:  TODO
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年3月30日 上午11:00:45
 * @version:  v1.0
 *
 * History:
 * Date		Author		Version
 * ---------------------------------------------
 * <reasons>
 */
package com.cloud.erp.dao;

import java.util.List;

import com.cloud.erp.entities.SystemCode;
import com.cloud.erp.entities.viewmodel.TreeModel;

/**
 * @ClassName SystemCodeDao
 * @Description TODO
 * @author bollen bollen@live.cn
 * @date 2015年3月30日 上午11:00:45
 *
 */
public interface SystemCodeDao {

	List<SystemCode> findSystemCodeList(Integer id);

	List<TreeModel> findSystemCodeList();

	boolean persistenceSystemCodeDig(SystemCode systemCode,
			String permissionName, Integer codePid);

	boolean delSystemCode(Integer codeId);

	List<SystemCode> findSystemCodeByType(String codeMyid);

}
