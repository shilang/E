/**
 * @Title:  MaterialAction.java
 * @Package:  com.cloud.erp.actions
 * @Description:  
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年5月22日 下午2:59:45
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
import com.cloud.erp.entities.table.ICItemCore;
import com.cloud.erp.service.MaterialService;
import com.cloud.erp.utils.PageUtil;
import com.opensymphony.xwork2.ModelDriven;

/**
 * @ClassName MaterialAction
 * @Description 
 * @author bollen bollen@live.cn
 * @date 2015年5月22日 下午2:59:45
 *
 */
@Namespace("/material")
public class MaterialAction extends BaseAction implements
		ModelDriven<ICItemCore> {

	private static final long serialVersionUID = 1L;
	@Resource
	private MaterialService materialService;
	private ICItemCore icItemCore;

	public ICItemCore getIcItemCore() {
		return icItemCore;
	}

	public void setIcItemCore(ICItemCore icItemCore) {
		this.icItemCore = icItemCore;
	}

	@Override
	public ICItemCore getModel() {
		
		if(null == icItemCore){
			icItemCore = new ICItemCore();
		}
		return icItemCore;
	}
	
	@Action(value = "find")
	public String findMaterials() throws Exception{
		Map<String, Object> params = getQueryParamsForMain();
		PageUtil pageUtil = getPageUtil();
		JSONWriter(materialService.findAll(params, pageUtil), 
				materialService.getCount(params));
		return RJSON; 
	}

	@Action(value = "persist")
	public String persistenceMaterial() throws Exception{
		boolean result = materialService.persistence(getModel());
		JSONWriter(result);
		return RJSON;
	}
	
	@Action(value = "delete")
	public String delMaterial() throws Exception{
		boolean result = materialService.deleteToUpdate(getModel().getItemId());
		JSONWriter(result);
		return RJSON;
	}
}
