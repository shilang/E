/**
 * @Title:  SalesOrderAction.java
 * @Package:  com.cloud.erp.actions
 * @Description:  
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年6月23日 下午2:14:22
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
import com.cloud.erp.entities.table.SalesOrder;
import com.cloud.erp.entities.table.SalesOrderEntry;
import com.cloud.erp.service.SalesOrderService;
import com.cloud.erp.utils.PageUtil;
import com.opensymphony.xwork2.ModelDriven;

/**
 * @ClassName  SalesOrderAction
 * @Description  
 * @author  bollen bollen@live.cn
 * @date  2015年6月23日 下午2:14:22
 *
 */
@Namespace("/salesOrder")
public class SalesOrderAction extends BaseAction implements ModelDriven<SalesOrder>{

	private static final long serialVersionUID = 1L;
	
	@Resource
	private SalesOrderService salesOrderService;
	
	private SalesOrder salesOrder;
	private String entryType;
	
	public String getEntryType() {
		return entryType;
	}

	public void setEntryType(String entryType) {
		this.entryType = entryType;
	}

	@Override
	public SalesOrder getModel() {
		
		if(null == salesOrder){
			salesOrder =  new SalesOrder();
		}
		return salesOrder;
	}
	
	@Action("findAll")
	public String findAll() throws Exception{
		Map<String, Object> params = getQueryParamsForMain();
		PageUtil pageUtil = getPageUtil();
		JSONWriter(salesOrderService.findAll(params, pageUtil), 
				salesOrderService.getCount(params));
		return RJSON;
	}
	
	@Action("findEntriesById")
	public String findEntriesById() throws Exception{
		JSONWriter(salesOrderService.findEntriesById(getId(), SalesOrderEntry.class), null);
		return RJSON;
	}
	
	@Action("findEntriesByType")
	public String findEntriesByType() throws Exception{
		JSONWriter(salesOrderService.findShareEntries(entryType, getId()), null);
		return RJSON;
	}
	
	@Action("persistence")
	public String persistence() throws Exception{
		Map<String, List<SalesOrderEntry>> entries = getEntriesParams(SalesOrderEntry.class);
		boolean result = salesOrderService.persistence(salesOrder, entries);
		JSONWriter(result);
		return RJSON;
	}
	
	@Action("delete")
	public String delete() throws Exception{
		boolean result = salesOrderService.deleteToUpdateAll(getId());
		JSONWriter(result);
		return RJSON;
	}
	
	private Integer getId(){
		return salesOrder.getInterId();
	}
}
