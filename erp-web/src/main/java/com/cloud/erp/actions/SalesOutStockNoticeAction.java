/**
 * @Title:  SalesOutStockNoticeAction.java
 * @Package:  com.cloud.erp.actions
 * @Description:  
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年7月6日 上午9:41:17
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
import com.cloud.erp.entities.table.SalesOutStock;
import com.cloud.erp.entities.table.SalesOutStockEntry;
import com.cloud.erp.service.SalesOutStockNoticeService;
import com.cloud.erp.utils.PageUtil;
import com.opensymphony.xwork2.ModelDriven;

/**
 * @ClassName  SalesOutStockNoticeAction
 * @Description  
 * @author  bollen bollen@live.cn
 * @date  2015年7月6日 上午9:41:17
 *
 */
@Namespace("/salesOutStockNotice")
public class SalesOutStockNoticeAction extends BaseAction implements ModelDriven<SalesOutStock>{

	private static final long serialVersionUID = 1L;
	
	@Resource
	private SalesOutStockNoticeService salesOutStockNoticeService;
	private SalesOutStock salesOutStock;
	private String entryType;

	public SalesOutStock getSalesOutStock() {
		return salesOutStock;
	}

	public void setSalesOutStock(SalesOutStock salesOutStock) {
		this.salesOutStock = salesOutStock;
	}
	
	public String getEntryType() {
		return entryType;
	}

	public void setEntryType(String entryType) {
		this.entryType = entryType;
	}

	@Override
	public SalesOutStock getModel() {
		
		if(null == salesOutStock){
			salesOutStock = new SalesOutStock();
		}
		return salesOutStock;
	}
	
	@Action("findAll")
	public String findAll() throws Exception{
		Map<String, Object> params = getQueryParamsForMain();
		PageUtil pageUtil = getPageUtil();
		JSONWriter(salesOutStockNoticeService.findAll(params, pageUtil), 
				salesOutStockNoticeService.getCount(params));
		return RJSON;
	}
	
	@Action("findEntriesById")
	public String findEntriesById() throws Exception{
		JSONWriter(salesOutStockNoticeService.findEntriesById(getId(), SalesOutStockEntry.class), null);
		return RJSON;
	}
	
	@Action("persistence")
	public String persistence() throws Exception{
		Map<String, List<SalesOutStockEntry>> entries = getEntriesParams(SalesOutStockEntry.class);
		boolean result = salesOutStockNoticeService.persistence(salesOutStock, entries);
		JSONWriter(result);
		return RJSON;
	}
	
	@Action("delete")
	public String delete() throws Exception{
		boolean result = salesOutStockNoticeService.deleteToUpdateAll(getId());
		JSONWriter(result);
		return RJSON;
	}
	
	@Action("findEntriesByType")
	public String findEntriesByType() throws Exception{
		JSONWriter(salesOutStockNoticeService.findShareEntries(entryType, getId()), null);
		return RJSON;
	}
	
	private Integer getId(){
		return salesOutStock.getInterId();
	}
}
