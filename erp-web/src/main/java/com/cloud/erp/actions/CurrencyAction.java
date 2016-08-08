/**
 * @Title:  CurrencyAction.java
 * @Package:  com.cloud.erp.actions
 * @Description:  
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年5月20日 下午2:31:14
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
import com.cloud.erp.entities.table.Currency;
import com.cloud.erp.service.CurrencyService;
import com.opensymphony.xwork2.ModelDriven;

/**
 * @ClassName CurrencyAction
 * @Description 
 * @author bollen bollen@live.cn
 * @date 2015年5月20日 下午2:31:14
 *
 */
@Namespace("/currency")
public class CurrencyAction extends BaseAction implements ModelDriven<Currency> {

	private static final long serialVersionUID = 1L;
	@Resource
	private CurrencyService currencyService;
	private Currency currency;

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	@Override
	public Currency getModel() {
		if (null == currency) {
			currency = new Currency();
		}

		return currency;
	}

	@Action(value = "find")
	public String findCurrencies() throws Exception {
		JSONWriter(currencyService.findCurrencies(), 
				currencyService.getCount());
		return RJSON;
	}

	@Action(value = "persist")
	public String persistenceCurrency() throws Exception {
		boolean result = currencyService.persistence(getModel());
		JSONWriter(result);
		return RJSON;
	}

	@Action(value = "delete")
	public String delCurrency() throws Exception {
		boolean result = currencyService.deleteToUpdate(getModel().getCurrencyId());
		JSONWriter(result);
		return RJSON;
	}
}
