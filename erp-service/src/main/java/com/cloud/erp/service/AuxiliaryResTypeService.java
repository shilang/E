/**
 * @Title:  AuxiliaryResTypeService.java
 * @Package:  com.cloud.erp.service
 * @Description:  TODO
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年4月30日 下午2:33:22
 * @version:  v1.0
 *
 * History:
 * Date		Author		Version
 * ---------------------------------------------
 * <reasons>
 */
package com.cloud.erp.service;

import java.util.List;

import com.cloud.erp.entities.table.AuxiliaryResType;

/**
 * @ClassName  AuxiliaryResTypeService
 * @Description  TODO
 * @author  bollen bollen@live.cn
 * @date  2015年4月30日 下午2:33:22
 *
 */
public interface AuxiliaryResTypeService {

	List<AuxiliaryResType> findAuxiliaryResTypes();
	
	boolean persistenceAuxiliaryResType(AuxiliaryResType auxiliaryResType);
	
	boolean delAuxiliaryResType(Integer	id);
	
}
