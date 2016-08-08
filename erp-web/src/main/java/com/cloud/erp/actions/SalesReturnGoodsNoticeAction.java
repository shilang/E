/**
 * @Title:  SalesReturnGoodsNoticeAction.java
 * @Package:  com.cloud.erp.actions
 * @Description:  
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年7月6日 下午4:48:22
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

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.cloud.erp.common.BaseAction;
import com.cloud.erp.entities.table.SalesOutStock;
import com.cloud.erp.entities.table.SalesOutStockEntry;
import com.cloud.erp.service.SalesReturnGoodsNoticeService;
import com.cloud.erp.utils.PageUtil;
import com.opensymphony.xwork2.ModelDriven;

/**
 * @ClassName  SalesReturnGoodsNoticeAction
 * @Description  
 * @author  bollen bollen@live.cn
 * @date  2015年7月6日 下午4:48:22
 *
 */
@Namespace("/salesReturnGoodsNotice")
public class SalesReturnGoodsNoticeAction extends BaseAction implements ModelDriven<SalesOutStock>{

	private static final long serialVersionUID = 1L;
	private SalesReturnGoodsNoticeService salesReturnGoodsNoticeService;
	private SalesOutStock salesOutStock;
	private String entryType;
	
	@Autowired
	public void setSalesReturnGoodsNoticeService(
			SalesReturnGoodsNoticeService salesReturnGoodsNoticeService) {
		this.salesReturnGoodsNoticeService = salesReturnGoodsNoticeService;
	}
	
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
		JSONWriter(salesReturnGoodsNoticeService.findAll(params, pageUtil),
				salesReturnGoodsNoticeService.getCount(params));
		return RJSON;
	}
	
	@Action("findEntriesById")
	public String findEntriesById() throws Exception{
		JSONWriter(salesReturnGoodsNoticeService.findEntriesById(getId(), SalesOutStockEntry.class), 
				null);
		return RJSON;
	}
	
	@Action("findEntriesByType")
	public String findEntriesByType() throws Exception{
		JSONWriter(salesReturnGoodsNoticeService.findShareEntries(entryType, getId()), 
				null);
		return RJSON;
	}
	
	@Action("persistence")
	public String persistence() throws Exception{
		Map<String, List<SalesOutStockEntry>> entries = getEntriesParams(SalesOutStockEntry.class);
		boolean result = salesReturnGoodsNoticeService.persistence(salesOutStock, entries);
		JSONWriter(result);
		return RJSON;
	}
	
	@Action("delete")
	public String delete() throws Exception{
		boolean result = salesReturnGoodsNoticeService.deleteToUpdateAll(getId());
		JSONWriter(result);
		return RJSON;
	}
	
	private Integer getId(){
		return salesOutStock.getInterId();
	}

}
