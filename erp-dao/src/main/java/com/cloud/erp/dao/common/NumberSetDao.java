/**
 * @Title:  NumberSetDao.java
 * @Package:  com.cloud.erp.dao
 * @Description:  
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年5月19日 下午2:32:20
 * @version:  v1.0
 *
 * History:
 * Date		Author		Version
 * ---------------------------------------------
 * <reasons>
 */
package com.cloud.erp.dao.common;

import com.cloud.erp.entities.table.NumberSet;
import com.cloud.erp.exceptions.NumberIncrementException;

/**
 * @ClassName  NumberSetDao
 * @Description  
 * @author  bollen bollen@live.cn
 * @date  2015年5月19日 下午2:32:20
 *
 */
public interface NumberSetDao {

	NumberSet getNumberSet(Integer classId);
	
	NumberSet getNumberSet(String prefix);
	
	void increment(Integer classId) throws NumberIncrementException;
	
}
