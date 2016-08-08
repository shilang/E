/**
 * @Title:  SalesPriceListAction.java
 * @Package:  com.cloud.erp.actions
 * @Description:  
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年4月22日 上午10:34:07
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
import com.cloud.erp.entities.table.SalesPriceList;
import com.cloud.erp.entities.table.SalesPriceListEntry;
import com.cloud.erp.service.SalesPriceListService;
import com.cloud.erp.utils.PageUtil;
import com.opensymphony.xwork2.ModelDriven;

/**
 * @ClassName SalesPriceListAction
 * @Description 
 * @author bollen bollen@live.cn
 * @date 2015年4月22日 上午10:34:07
 *
 */
@Namespace("/salesPriceList")
public class SalesPriceListAction extends BaseAction implements
		ModelDriven<SalesPriceList> {

	private static final long serialVersionUID = 1L;
	
	@Resource
	private SalesPriceListService salesPriceListService;
	private SalesPriceList salesPriceList;

	public SalesPriceList getSalesPriceList() {
		return salesPriceList;
	}

	public void setSalesPriceList(SalesPriceList salesPriceList) {
		this.salesPriceList = salesPriceList;
	}

	@Action("findAll")
	public String findAll() throws Exception {
		Map<String, Object> params = getQueryParamsForMain();
		PageUtil pageUtil = getPageUtil();
		JSONWriter(salesPriceListService.findAll(params,pageUtil), 
				salesPriceListService.getCount(params));
		return RJSON;
	}
	
	@Action("findEntriesById")
	public String findEntriesById() throws Exception{
		JSONWriter(salesPriceListService.findEntriesById(getId(), SalesPriceListEntry.class), null);
		return RJSON;
	}
	
	@Action("persistence")
	public String persistence() throws Exception{
		Map<String, List<SalesPriceListEntry>> entries = getEntriesParams(SalesPriceListEntry.class);
		boolean result = salesPriceListService.persistence(salesPriceList, entries);
		JSONWriter(result);
		return RJSON;
	}
	
	@Action("delete")
	public String delete() throws Exception{
		boolean result = salesPriceListService.deleteToUpdateAll(getId());
		JSONWriter(result);
		return RJSON;
	}
	
	@Action("exportExcel")
	public String exportExcel() throws Exception{
		String fileName = salesPriceListService.exportExcel(salesPriceListService.findAll(null, null), 
				getOutputStream());
		setFileName(fileName);
		return DOWNLOAD;
	}
	
	@Override
	public SalesPriceList getModel() {
		if (null == salesPriceList) {
			salesPriceList = new SalesPriceList();
		}
		return salesPriceList;
	}
	
	private Integer getId(){
		return this.salesPriceList.getInterId();
	}
}
