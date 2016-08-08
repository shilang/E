/**
 * @Title:  NumberSetAction.java
 * @Package:  com.cloud.erp.actions
 * @Description:  
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年5月27日 下午2:33:58
 * @version:  v1.0
 *
 * History:
 * Date		Author		Version
 * ---------------------------------------------
 * <reasons>
 */
package com.cloud.erp.actions;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;

import com.cloud.erp.common.BaseAction;
import com.cloud.erp.entities.viewmodel.Json;
import com.cloud.erp.service.common.NumberSetService;
import com.cloud.erp.utils.Constants;

/**
 * @ClassName NumberSetAction
 * @Description 
 * @author bollen bollen@live.cn
 * @date 2015年5月27日 下午2:33:58
 *
 */

@Namespace("/numberSet")
public class NumberSetAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	@Resource
	private NumberSetService numberSetService;
	private int classId;

	public int getClassId() {
		return classId;
	}

	public void setClassId(int classId) {
		this.classId = classId;
	}

	private Json getNumber(int classId){
		Json json = new Json();
		json.setStatus(true);
		json.setTitle(Constants.NUMBER_TITLE);
		json.setMessage(numberSetService.getNumberSet(classId));
		
		return json;
	}
	
	@Action(value = "getSalesPriceListNumber")
	public String getSalesPriceListNumber() throws Exception{
		JSONWriter(getNumber(Constants.NUMBER_SALES_PRICE_LIST));
		return RJSON;
	}
	
	@Action(value = "getSalesContractNumber")
	public String getSalesContractNumber() throws Exception{
		JSONWriter(getNumber(Constants.NUMBER_SALES_CONTRACT));
		return RJSON;
	}
	
	@Action(value = "getSalesOrderNumber")
	public String getSalesOrderNumber() throws Exception{
		JSONWriter(getNumber(Constants.NUMBER_SALES_ORDER));
		return RJSON;
	}
	
	@Action(value = "getSalesOutStockNoticeNumber")
	public String getSalesOutStockNoticeNumber() throws Exception{
		JSONWriter(getNumber(Constants.NUMBER_SALES_OUT_STOCK_NOTICE));
		return RJSON;
	}
	
	@Action(value = "getSalesReturnGoodsNoticeNumber")
	public String getSalesReturnGoodsNoticeNumber() throws Exception{
		JSONWriter(getNumber(Constants.NUMBER_SALES_RETURN_GOODS_NOTICE));
		return RJSON;
	}
	
	@Action(value = "getSalesOutStockNumber")
	public String getSalesOutStockNumber() throws Exception{
		JSONWriter(getNumber(Constants.NUMBER_SALES_OUT_STOCK));
		return RJSON;
	}
	
	@Action(value = "getSalesInvoiceNumber")
	public String getSalesInvoiceNumber(){
		JSONWriter(getNumber(Constants.NUMBER_SALES_INVOICE));
		return RJSON;
	}
	
	@Action(value = "getRecProceedsNumber")
	public String getRecProceedsNumber(){
		JSONWriter(getNumber(Constants.NUMBER_REC_PROCEEDS));
		return RJSON;
	}
}
