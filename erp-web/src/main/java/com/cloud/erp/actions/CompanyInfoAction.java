/**
 * @Title:  companyInfoAction.java
 * @Package:  com.cloud.erp.actions
 * @Description:  TODO
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

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.cloud.erp.entities.table.CompanyInfo;
import com.cloud.erp.entities.viewmodel.GridModel;
import com.cloud.erp.service.CompanyInfoService;
import com.opensymphony.xwork2.ModelDriven;

/**
 * @ClassName companyInfoAction
 * @Description TODO
 * @author bollen bollen@live.cn
 * @date 2015年4月28日 上午10:17:14
 *
 */
@Namespace("/companyInfo")
@Action("companyInfoAction")
public class CompanyInfoAction extends BaseAction implements
		ModelDriven<CompanyInfo> {

	private static final long serialVersionUID = 1L;
	private CompanyInfoService companyInfoService;
	private CompanyInfo companyInfo;
	/**
	 * @param companyInfoService
	 *            the companyInfoService to set
	 */
	@Autowired
	public void setCompanyInfoService(CompanyInfoService companyInfoService) {
		this.companyInfoService = companyInfoService;
	}
	
	public CompanyInfo getCompanyInfo() {
		return companyInfo;
	}

	public void setCompanyInfo(CompanyInfo companyInfo) {
		this.companyInfo = companyInfo;
	}

	public String findCompanyInfos() throws Exception {
		GridModel gridModel = new GridModel();
		gridModel.setRows(companyInfoService.findCompanyInfos());
		gridModel.setTotal(companyInfoService.getCount());
		OutputJson(gridModel);
		return null;
	}
	
	public String persistenceCompanyInfo() throws Exception{
		OutputJson(getMessage(companyInfoService.persistenceCompanyInfo(companyInfo)));
		return null;
	}

	public String delCompanyInfo() throws Exception {
		OutputJson(getMessage(companyInfoService.delCompanyInfo(getModel().getCompanyId())));
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	@Override
	public CompanyInfo getModel() {
		// TODO Auto-generated method stub
		if(null == companyInfo){
			companyInfo = new CompanyInfo();
		}
		return companyInfo;
	}

}
