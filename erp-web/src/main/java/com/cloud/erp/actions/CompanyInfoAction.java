/**
 * @Title:  companyInfoAction.java
 * @Package:  com.cloud.erp.actions
 * @Description:  
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年4月28日 上午10:17:14
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
import com.cloud.erp.entities.table.CompanyInfo;
import com.cloud.erp.service.CompanyInfoService;
import com.opensymphony.xwork2.ModelDriven;

/**
 * @ClassName companyInfoAction
 * @Description 
 * @author bollen bollen@live.cn
 * @date 2015年4月28日 上午10:17:14
 *
 */
@Namespace("/companyInfo")
public class CompanyInfoAction extends BaseAction implements
		ModelDriven<CompanyInfo> {

	private static final long serialVersionUID = 1L;
	@Resource
	private CompanyInfoService companyInfoService;
	private CompanyInfo companyInfo;

	public CompanyInfo getCompanyInfo() {
		return companyInfo;
	}

	public void setCompanyInfo(CompanyInfo companyInfo) {
		this.companyInfo = companyInfo;
	}

	@Action(value = "find")
	public String findCompanyInfos() throws Exception {
		JSONWriter(companyInfoService.findCompanyInfos(), 
				companyInfoService.getCount());
		return RJSON;
	}
	
	@Action(value = "persist")
	public String persistenceCompanyInfo() throws Exception{
		boolean result = companyInfoService.persistence(companyInfo);
		JSONWriter(result);
		return RJSON;
	}

	@Action(value = "delete")
	public String delCompanyInfo() throws Exception {
		boolean result = companyInfoService.deleteToUpdate(getModel().getCompanyId());
		JSONWriter(result);
		return RJSON;
	}

	@Override
	public CompanyInfo getModel() {
		if(null == companyInfo){
			companyInfo = new CompanyInfo();
		}
		return companyInfo;
	}

}
