/**
 * @Title:  OrganizationActioin.java
 * @Package:  com.cloud.erp.actions
 * @Description:  TODO
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年3月18日 上午10:18:20
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

import com.cloud.erp.entities.Organization;
import com.cloud.erp.entities.viewmodel.Json;
import com.cloud.erp.service.OrganizationService;
import com.cloud.erp.utils.Constants;
import com.opensymphony.xwork2.ModelDriven;

/**
 * @ClassName  OrganizationActioin
 * @Description  TODO
 * @author  bollen bollen@live.cn
 * @date  2015年3月18日 上午10:18:20
 *
 */
@Namespace("/orgz")
@Action(value="organizationAction")
public class OrganizationActioin extends BaseAction implements ModelDriven<Organization> {

	private static final long serialVersionUID = 8032589540816717471L;
	private OrganizationService organizationService;
	private Integer id;
	private Organization organization;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Organization getOrganization() {
		return organization;
	}
	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
	/**
	 * @param organizationService the organizationService to set
	 */
	@Autowired
	public void setOrganizationService(OrganizationService organizationService) {
		this.organizationService = organizationService;
	}
	
	/**
	 * function: TODO query all organizations
	 * @Author: bollen bollen@live.cn
	 * @Date: 2015年3月18日 上午11:22:38
	 * @Title: findOrganizationList
	 * @return
	 * @throws Exception
	 */
	public String findOrganizationList() throws Exception{
		
		OutputJson(organizationService.findOrganizationList());
		return null;
	}
	
	/**
	 * function: TODO query all organizations by node
	 * @Author: bollen bollen@live.cn
	 * @Date: 2015年3月18日 上午11:24:24
	 * @Title: findOrganizationListTreeGrid
	 * @return
	 * @throws Exception
	 */
	public String findOrganizationListTreeGrid() throws Exception{
		
		OutputJson(organizationService.findOrganizationList(id));
		return null;
	}
	
	/**
	 * function: TODO persistence organization
	 * @Author: bollen bollen@live.cn
	 * @Date: 2015年3月18日 上午11:26:31
	 * @Title: persistenceOrganization
	 * @return
	 * @throws Exception
	 */
	public String persistenceOrganization() throws Exception{
		
		OutputJson(getMessage(organizationService.persistenceOrganization(getModel())),Constants.TEXT_TYPE_PLAIN);
		return null;
	}
	
	public String delOrganization() throws Exception{
		Json json = new Json();
		if(organizationService.delOrganization(id)){
			json.setStatus(true);
			json.setMessage(Constants.POST_DATA_SUCCESS);
		}else {
			json.setMessage(Constants.POST_DATA_FAIL+Constants.IS_EXT_SUBMENU);
		}
		OutputJson(json);
		return null;
	}
	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	@Override
	public Organization getModel() {
		// TODO Auto-generated method stub
		if(null == organization){
			organization = new Organization();
		}
		return organization;
	}
	
}
