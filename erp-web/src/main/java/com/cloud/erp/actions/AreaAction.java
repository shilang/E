/**
 * @Title:  AreaAction.java
 * @Package:  com.cloud.erp.actions
 * @Description:  TODO
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年5月12日 上午10:21:50
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

import com.cloud.erp.entities.table.Area;
import com.cloud.erp.entities.viewmodel.GridModel;
import com.cloud.erp.service.AreaService;
import com.cloud.erp.utils.Constants;
import com.cloud.erp.utils.PageUtil;
import com.opensymphony.xwork2.ModelDriven;

/**
 * @ClassName AreaAction
 * @Description TODO
 * @author bollen bollen@live.cn
 * @date 2015年5月12日 上午10:21:50
 *
 */
@Namespace("/area")
@Action("areaAction")
public class AreaAction extends BaseAction implements ModelDriven<Area> {

	private static final long serialVersionUID = 1L;
	private AreaService areaService;
	private Area area;

	/**
	 * @param areaService
	 *            the areaService to set
	 */
	@Autowired
	public void setAreaService(AreaService areaService) {
		this.areaService = areaService;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}
	
	public String findAreas() throws Exception{
		Map<String, Object> params = new HashMap<String, Object>();
		if(null != searchValue && !"".equals(searchValue)){
			params.put(searchName, Constants.GET_SQL_LIKE  + searchValue + Constants.GET_SQL_LIKE);
		}
		PageUtil pageUtil = new PageUtil(page, rows, searchAnds, searchColumnNames, searchConditions, searchVals);
		GridModel gridModel = new GridModel();
		gridModel.setRows(areaService.findAreas(params, pageUtil));
		gridModel.setTotal(areaService.getCount(params, pageUtil));
		OutputJson(gridModel);
		
		return null;
	}
	
	public String persistenceArea() throws Exception{
		OutputJson(getMessage(areaService.persistenceArea(getModel())));
		return null;
	}
	
	public String delArea() throws Exception{
		OutputJson(getMessage(areaService.delArea(getModel().getAreaId())));
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	@Override
	public Area getModel() {
		// TODO Auto-generated method stub
		if(null == area){
			area = new Area();
		}
		return area;
	}

}
