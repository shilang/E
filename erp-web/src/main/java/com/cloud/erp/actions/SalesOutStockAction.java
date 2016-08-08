/**
 * @Title:  SalesOutStockAction.java
 * @Package:  com.cloud.erp.actions
 * @Description:  
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年6月23日 下午3:17:34
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
import com.cloud.erp.entities.table.ICStockBill;
import com.cloud.erp.entities.table.ICStockBillEntry;
import com.cloud.erp.service.SalesOutStockService;
import com.cloud.erp.utils.PageUtil;
import com.opensymphony.xwork2.ModelDriven;

/**
 * @ClassName  SalesOutStockAction
 * @Description  销售出库单
 * @author  bollen bollen@live.cn
 * @date  2015年6月23日 下午3:17:34
 *
 */
@Namespace("/salesOutStock")
public class SalesOutStockAction extends BaseAction implements ModelDriven<ICStockBill>{

	private static final long serialVersionUID = 1L;
	@Resource
	private SalesOutStockService salesOutStockService;
	private ICStockBill iCStockBill;
	private String entryType;

	public ICStockBill getiCStockBill() {
		return iCStockBill;
	}
	
	public void setiCStockBill(ICStockBill iCStockBill) {
		this.iCStockBill = iCStockBill;
	}

	public String getEntryType() {
		return entryType;
	}

	public void setEntryType(String entryType) {
		this.entryType = entryType;
	}

	@Action("findAll")
	public String findAll() throws Exception{
		Map<String, Object> params = getQueryParamsForMain();
		PageUtil pageUtil = getPageUtil();
		JSONWriter(salesOutStockService.findAll(params, pageUtil), 
				salesOutStockService.getCount(params));
		return RJSON;
	}
	
	@Action("findEntriesById")
	public String findEntriesById() throws Exception{
		JSONWriter(salesOutStockService.findEntriesById(getId(), ICStockBillEntry.class), null);
		return RJSON;
	}
	
	@Action("findEntriesByType")
	public String findEntriesByType() throws Exception{
		JSONWriter(salesOutStockService.findShareEntries(entryType, getId()), null);
		return RJSON;
	}
	
	@Action("persistence")
	public String persistence() throws Exception{
		Map<String, List<ICStockBillEntry>> entries = getEntriesParams(ICStockBillEntry.class);
		boolean result = salesOutStockService.persistence(iCStockBill, entries);
		JSONWriter(result);
		return RJSON;
	}
	
	@Action("delete")
	public String delete() throws Exception{
		boolean result = salesOutStockService.deleteToUpdateAll(getId());
		JSONWriter(result);
		return RJSON;
	}

	@Override
	public ICStockBill getModel() {
		
		if(null == iCStockBill){
			iCStockBill = new ICStockBill();
		}
		return iCStockBill;
	}
	
	private Integer getId(){
		return iCStockBill.getInterId();
	}

}
