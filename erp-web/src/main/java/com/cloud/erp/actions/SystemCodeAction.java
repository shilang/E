/**
 * @Title:  SystemCodeAction.java
 * @Package:  com.cloud.erp.actions
 * @Description:  
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年3月30日 上午9:56:09
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

import com.cloud.erp.common.BaseAction;
import com.cloud.erp.entities.table.SystemCode;
import com.cloud.erp.entities.viewmodel.Json;
import com.cloud.erp.service.SystemCodeService;
import com.cloud.erp.utils.Constants;
import com.opensymphony.xwork2.ModelDriven;

/**
 * @ClassName  SystemCodeAction
 * @Description  
 * @author  bollen bollen@live.cn
 * @date  2015年3月30日 上午9:56:09
 *
 */
@Namespace("/systemCode")
@Action("systemCodeAction")
public class SystemCodeAction extends BaseAction implements ModelDriven<SystemCode>{

	private static final long serialVersionUID = 1L;
	private SystemCodeService systemCodeService;
	private SystemCode systemCode;
	private String permissionName;
	private Integer id;
	private Integer codePid;

	@Autowired
	public void setSystemCodeService(SystemCodeService systemCodeService) {
		this.systemCodeService = systemCodeService;
	}
	
	public SystemCode getSystemCode() {
		return systemCode;
	}

	public void setSystemCode(SystemCode systemCode) {
		this.systemCode = systemCode;
	}

	public String getPermissionName() {
		return permissionName;
	}

	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCodePid() {
		return codePid;
	}

	public void setCodePid(Integer codePid) {
		this.codePid = codePid;
	}

	/**
	 * function: query all by node
	 * @Author: bollen bollen@live.cn
	 * @Date: 2015年3月30日 下午2:43:29
	 * @Title: findSystemCodeList
	 * @return
	 * @throws Exception
	 */
	public String findSystemCodeList() throws Exception{
		OutputJson(systemCodeService.findSystemCodeList(id));
		return null;
	}
	
	public String findAllSystemCodeList() throws Exception{
		
		OutputJson(systemCodeService.findSystemCodeList());
		return null;
	}
	
	public String persistenceSystemCodeDig() throws Exception{
		OutputJson(getMessage(systemCodeService.persistenceSystemCodeDig(getModel(), permissionName, codePid)), Constants.TEXT_TYPE_PLAIN);
		return null;
	}
	
	public String delSystemCode() throws Exception{
		Json json = new Json();
		json.setTitle("提示");
		if(systemCodeService.delSystemCode(getModel().getCodeId())){
			json.setStatus(true);
			json.setMessage("数据更新成功！");
		}else {
			json.setStatus(false);
			json.setMessage("数据更新失败，或含有子项不能删除！");
		}
		OutputJson(json);
		return null;
	}
	
	public String findSystemCodeByType() throws Exception{
		OutputJson(systemCodeService.findSystemCodeByType(getModel().getCodeMyid()));
		return null;
	}

	@Override
	public SystemCode getModel() {
		
		if( null == systemCode){
			systemCode = new SystemCode();
		}
		return systemCode;
	}

}
