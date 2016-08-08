/**
 * @Title:  MeasureUnitAction.java
 * @Package:  com.cloud.erp.actions
 * @Description:  
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年5月20日 下午2:46:22
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
import com.cloud.erp.entities.table.MeasureUnit;
import com.cloud.erp.service.MeasureUnitService;
import com.opensymphony.xwork2.ModelDriven;

/**
 * @ClassName MeasureUnitAction
 * @Description 
 * @author bollen bollen@live.cn
 * @date 2015年5月20日 下午2:46:22
 *
 */
@Namespace("/measureUnit")
public class MeasureUnitAction extends BaseAction implements
		ModelDriven<MeasureUnit> {

	private static final long serialVersionUID = 1L;
	@Resource
	private MeasureUnitService measureUnitService;
	private MeasureUnit measureUnit;

	public MeasureUnit getMeasureUnit() {
		return measureUnit;
	}

	public void setMeasureUnit(MeasureUnit measureUnit) {
		this.measureUnit = measureUnit;
	}

	@Override
	public MeasureUnit getModel() {
		if(null == measureUnit){
			measureUnit = new MeasureUnit();
		}
		
		return measureUnit;
	}
	
	@Action(value = "find")
	public String findMeasureUnits() throws Exception{
		JSONWriter(measureUnitService.findMeasureUnits(), 
				measureUnitService.getCount());
		return RJSON;
	}
	
	@Action(value = "persist")
	public String persistenceMeasureUnit() throws Exception{
		boolean result = measureUnitService.persistence(getModel());
		JSONWriter(result);
		return RJSON;
	}
	
	@Action(value = "delete")
	public String delMeasureUnit() throws Exception{
		boolean result = measureUnitService.deleteToUpdate(getModel().getMeasureUnitId());
		JSONWriter(result);
		return RJSON;
	}

}
