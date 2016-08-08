/**
 * @Title:  AuxiliaryResAssignAction.java
 * @Package:  com.cloud.erp.actions
 * @Description: 
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年5月15日 上午10:59:01
 * @version:  v1.0
 *
 * History:
 * Date		Author		Version
 * ---------------------------------------------
 * <reasons>
 */
package com.cloud.erp.actions;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;

import com.cloud.erp.common.BaseAction;
import com.cloud.erp.service.AuxiliaryResAssignService;

/**
 * @ClassName AuxiliaryResAssignAction
 * @Description 
 * @author bollen bollen@live.cn
 * @date 2015年5月15日 上午10:59:01
 *
 */
@Namespace("/auxiliaryResAssign")
public class AuxiliaryResAssignAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	@Resource
	private AuxiliaryResAssignService auxiliaryResAssignService;
	private Integer resId;

	public Integer getResId() {
		return resId;
	}

	public void setResId(Integer resId) {
		this.resId = resId;
	}

	@Action(value = "findByTypeId")
	public String findAuxiliaryResMessageByTypeId() throws Exception {
		JSONWriter(auxiliaryResAssignService.findAuxiliaryResMessages(resId), 
				auxiliaryResAssignService.getCount(resId));
		return RJSON;
	}

}
