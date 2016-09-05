package com.cloud.erp.activiti;

import com.cloud.erp.service.common.CopyService;
import com.cloud.erp.utils.SpringUtil;


public abstract class CopyServiceJavaDelegate extends BaseJavaDelegate {

	public void save() throws Exception{
		getCopyService().save(getBusinessClass(), getBusinessKey());
	}

	private CopyService getCopyService(){
		return (CopyService) SpringUtil.getBean(CopyService.class);
	}
}
