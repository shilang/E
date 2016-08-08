package com.cloud.erp.actions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.cloud.erp.common.BaseAction;
import com.cloud.erp.service.RecProceedsService;
import com.cloud.erp.service.SalesContractService;
import com.cloud.erp.service.SalesInvoiceService;
import com.cloud.erp.service.SalesOrderService;
import com.cloud.erp.service.SalesOutStockNoticeService;
import com.cloud.erp.service.SalesOutStockService;
import com.cloud.erp.service.SalesPriceListService;
import com.cloud.erp.service.SalesReturnGoodsNoticeService;
import com.cloud.erp.utils.PageUtil;

@Namespace("/salesMgmt")
public class SalesMgmtAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	@Autowired
	private SalesPriceListService salesPriceListService;
	@Autowired
	private SalesContractService salesContractService;
	@Autowired
	private SalesOrderService salesOrderService;
	@Autowired
	private SalesOutStockNoticeService salesOutStockNoticeService;
	@Autowired
	private SalesReturnGoodsNoticeService salesReturnGoodsNoticeService;
	@Autowired
	private SalesOutStockService salesOutStockService;
	@Autowired
	private SalesInvoiceService salesInvoiceService;
	@Autowired
	private RecProceedsService recProceedsService;
	
	@Action("chkPending")
	public String chkPending() throws Exception{
		Map<String, Object> params = getQueryParamsForMain();
		PageUtil pageUtil = getPageUtil();
		Map<String, List<?>> list = new HashMap<String, List<?>>();
		list.put("AQ", salesPriceListService.findAll(params, pageUtil));
		list.put("XSHT", salesContractService.findAll(params, pageUtil));
		list.put("SEORD", salesOrderService.findAll(params, pageUtil));
		list.put("SEOUT", salesOutStockNoticeService.findAll(params, pageUtil));
		list.put("SEIN", salesReturnGoodsNoticeService.findAll(params, pageUtil));
		list.put("XOUT", salesOutStockService.findAll(params, pageUtil));
		list.put("ZSEFP", salesInvoiceService.findAll(params, pageUtil));
		list.put("XSKD", recProceedsService.findAll(params, pageUtil));
		JSONWriterGeneral(list);
		return RJSON;
	}
}
