package com.cloud.erp.handler;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cloud.erp.common.BaseHandler;
import com.cloud.erp.entities.table.ICSales;
import com.cloud.erp.entities.table.ICSalesEntry;
import com.cloud.erp.entities.viewmodel.GridModel;
import com.cloud.erp.entities.viewmodel.RequestParams;
import com.cloud.erp.entities.viewmodel.Result;
import com.cloud.erp.service.SalesInvoiceService;
import com.cloud.erp.utils.PageUtil;

@Controller
@RequestMapping("/salesInvoice")
public class SalesInvoiceHandler extends BaseHandler{
	
	@Resource
	private SalesInvoiceService salesInvoiceService;
	
	@ResponseBody
	@RequestMapping("/list")
	public GridModel findSalesInvoices(RequestParams rp){
		Map<String, Object> params = getQueryParams(rp);
		PageUtil pageUtil = getPageUtil(rp);
		GridModel gridModel = new GridModel();
		gridModel.setRows(salesInvoiceService.findAll(params, pageUtil));
		gridModel.setTotal(salesInvoiceService.getCount(params));
		return gridModel;
	}
	
	@ResponseBody
	@RequestMapping("/persist")
	public Result persistenceSalesInvoice(ICSales iCSales , String inserted, String updated, String deleted) throws Exception{
		Map<String, List<ICSalesEntry>> entries = getEntriesParams(ICSalesEntry.class, inserted, updated, deleted);
		return getDefaultResult(salesInvoiceService.persistence(iCSales, entries));
	}
	
	@ResponseBody
	@RequestMapping("/entries")
	public GridModel findEntriesById(@RequestParam(value="interId",required=false) Integer id){
		GridModel gridModel = new GridModel();
		gridModel.setRows(salesInvoiceService.findEntriesById(id, ICSalesEntry.class));
		return gridModel;
	}
	
	@ResponseBody
	@RequestMapping("/delete")
	public Result delSalesInvoice(@RequestParam(value="id", required = true) Integer id) throws Exception{
		return getDefaultResult(salesInvoiceService.deleteToUpdateAll(id));
	}
	
	@ResponseBody
	@RequestMapping("/shareEntries")
	public GridModel findEntriesByType(String entryType, @RequestParam(value = "interId") Integer pid){
		GridModel gridModel = new GridModel();
		gridModel.setRows(salesInvoiceService.findShareEntries(entryType, pid));
		return gridModel;
	}
	
}
