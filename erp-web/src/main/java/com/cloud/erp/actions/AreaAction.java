/**
 * @Title:  AreaAction.java
 * @Package:  com.cloud.erp.actions
 * @Description:  
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

import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;

import com.cloud.erp.common.BaseAction;
import com.cloud.erp.entities.table.Area;
import com.cloud.erp.service.AreaService;
import com.cloud.erp.utils.PageUtil;
import com.opensymphony.xwork2.ModelDriven;

/**
 * @ClassName AreaAction
 * @Description 
 * @author bollen bollen@live.cn
 * @date 2015年5月12日 上午10:21:50
 *
 */
@Namespace("/area")
public class AreaAction extends BaseAction implements ModelDriven<Area> {

	private static final long serialVersionUID = 1L;
	
	@Resource
	private AreaService areaService;
	
	private Area area;

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}
	
	@Action(value = "findAreas")
	public String findAreas() throws Exception{
		Map<String, Object> params = getQueryParamsForMain();
		PageUtil pageUtil = getPageUtil();
		JSONWriter(areaService.findAll(params, pageUtil),
				areaService.getCount(params));
		return RJSON;
	}
	
	@Action(value = "persist")
	public String persistenceArea() throws Exception{
		boolean result = areaService.persistence(getModel());
		JSONWriter(result);
		return RJSON;
	}
	
	@Action(value = "delArea")
	public String delArea() throws Exception{
		boolean result = areaService.deleteToUpdate(getModel().getAreaId());
		JSONWriter(result);
		return RJSON;
	}

	@Override
	public Area getModel() {
		if(null == area){
			area = new Area();
		}
		return area;
	}

}
