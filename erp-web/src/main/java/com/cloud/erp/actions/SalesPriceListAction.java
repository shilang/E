/**
 * @Title:  SalesPriceListAction.java
 * @Package:  com.cloud.erp.actions
 * @Description:  TODO
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

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.cloud.erp.entities.table.SalesPriceList;
import com.cloud.erp.entities.viewmodel.GridModel;
import com.cloud.erp.service.SalesPriceListService;
import com.cloud.erp.utils.Constants;
import com.cloud.erp.utils.PageUtil;
import com.opensymphony.xwork2.ModelDriven;

/**
 * @ClassName SalesPriceListAction
 * @Description TODO
 * @author bollen bollen@live.cn
 * @date 2015年4月22日 上午10:34:07
 *
 */
@Namespace("/salesPriceList")
@Action("salesPriceListAction")
public class SalesPriceListAction extends BaseAction implements
		ModelDriven<SalesPriceList> {

	private static final long serialVersionUID = 1L;
	private SalesPriceListService salesPriceListService;
	private SalesPriceList salesPriceList;

	/**
	 * @param salesPriceListService
	 *            the salesPriceListService to set
	 */
	@Autowired
	public void setSalesPriceListService(
			SalesPriceListService salesPriceListService) {
		this.salesPriceListService = salesPriceListService;
	}

	public SalesPriceList getSalesPriceList() {
		return salesPriceList;
	}

	public void setSalesPriceList(SalesPriceList salesPriceList) {
		this.salesPriceList = salesPriceList;
	}

	public String findSalesPriceList() throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();
		if (null != searchValue && "".equals(searchValue)) {
			map.put(getSearchName(), Constants.GET_SQL_LIKE + searchValue
					+ Constants.GET_SQL_LIKE);
		}
		PageUtil pageUtil = new PageUtil(page, rows, searchAnds,
				searchColumnNames, searchConditions, searchVals);
		GridModel gridModel = new GridModel();
		gridModel.setRows(salesPriceListService.findSalesPriceList(map,pageUtil));
		gridModel.setTotal(salesPriceListService.getCount(map, pageUtil));
		OutputJson(gridModel);
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	@Override
	public SalesPriceList getModel() {
		// TODO Auto-generated method stub
		if (null == salesPriceList) {
			salesPriceList = new SalesPriceList();
		}
		return salesPriceList;
	}

}
