package com.cloud.erp.dao;

import java.util.List;

import com.cloud.erp.dao.common.GeneralDao;
import com.cloud.erp.entities.table.ICItemAttr;

/**
 * 
 * @author Bollen
 *
 */
public interface MaterialAttrDao extends GeneralDao<ICItemAttr>{

	List<ICItemAttr> findMaterialAttrsByPId(Integer pid);

}
