/**
 * @Title:  SalesContractAction.java
 * @Package:  com.cloud.erp.actions
 * @Description:  
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年6月15日 下午6:05:14
 * @version:  v1.0
 *
 * History:
 * Date		Author		Version
 * ---------------------------------------------
 * <reasons>
 */
package com.cloud.erp.actions;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;

import com.cloud.erp.common.BaseAction;
import com.cloud.erp.entities.table.SalesContract;
import com.cloud.erp.entities.table.SalesContractEntry;
import com.cloud.erp.entities.table.SalesContractScheme;
import com.cloud.erp.service.SalesContractService;
import com.cloud.erp.utils.PageUtil;
import com.opensymphony.xwork2.ModelDriven;

/**
 * @ClassName SalesContractAction
 * @Description 
 * @author bollen bollen@live.cn
 * @date 2015年6月15日 下午6:05:14
 *
 */
@Namespace("/salesContract")
public class SalesContractAction extends BaseAction implements
		ModelDriven<SalesContract> {

	private static final long serialVersionUID = 1L;
	
	@Resource
	private SalesContractService salesContractService;
	private SalesContract salesContract;
	private String schemeInserted;
	private String schemeUpdated;
	private String schemeDeleted;

	public SalesContract getSalesContract() {
		return salesContract;
	}

	public void setSalesContract(SalesContract salesContract) {
		this.salesContract = salesContract;
	}
	
	public String getSchemeInserted() {
		return schemeInserted;
	}

	public void setSchemeInserted(String schemeInserted) {
		this.schemeInserted = schemeInserted;
	}

	public String getSchemeUpdated() {
		return schemeUpdated;
	}

	public void setSchemeUpdated(String schemeUpdated) {
		this.schemeUpdated = schemeUpdated;
	}

	public String getSchemeDeleted() {
		return schemeDeleted;
	}

	public void setSchemeDeleted(String schemeDeleted) {
		this.schemeDeleted = schemeDeleted;
	}

	@Action("findAll")
	public String findAll() throws Exception{
		Map<String, Object> params = getQueryParamsForMain();
		PageUtil pageUtil = getPageUtil();
		JSONWriter(salesContractService.findAll(params, pageUtil),
				salesContractService.getCount(params));
		return RJSON;
	}
	
	@Action("findEntriesById")
	public String findEntriesById() throws Exception{
		JSONWriter(salesContractService.findEntriesById(getId(), SalesContractEntry.class), null);
		return RJSON;
	}
	
	@Action("findSchemesById")
	public String findSchemesById() throws Exception{
		JSONWriter(salesContractService.findEntriesById(getId(), SalesContractScheme.class),null);
		return RJSON;
	}

	@Action("persistence")
	public String persistence() throws Exception{
		Map<String, List<SalesContractEntry>> entries = getEntriesParams(SalesContractEntry.class);
		Map<String, List<SalesContractScheme>> schemes = getEntriesParams(SalesContractScheme.class, schemeInserted, schemeUpdated, schemeDeleted);
		boolean result = salesContractService.persistence(salesContract, entries, schemes);
		JSONWriter(result);
		return RJSON;
	}
	
	@Action("delete")
	public String delete() throws Exception{
		boolean result = salesContractService.deleteToUpdateAll(getId());
		JSONWriter(result);
		return RJSON;
	}
	
	@Override
	public SalesContract getModel() {
		if(null == salesContract){
			salesContract = new SalesContract();
		}
		return salesContract;
	}
	
	private Integer getId(){
		return getModel().getInterId();
	}

}
