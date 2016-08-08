package com.cloud.erp.actions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;

import com.alibaba.fastjson.JSON;
import com.cloud.erp.common.BaseAction;
import com.cloud.erp.entities.table.DataPermission;
import com.cloud.erp.service.DataPermissionService;
import com.cloud.erp.utils.PageUtil;

@Namespace("/dataPermission")
public class DataPermissionAction extends BaseAction{

	private static final long serialVersionUID = 1L;
	@Resource
	private DataPermissionService dataPermissionService;
	
	@Action(value = "find")
	public String findDataPermissions() throws Exception{
		Map<String, Object> params = getQueryParamsForMain();
		PageUtil pageUtil = getPageUtil();
		JSONWriter(dataPermissionService.findAll(params, pageUtil), 
				dataPermissionService.getCount(params));
		return RJSON;
	}
	
	@Action(value = "persist")
	public String persistenceDataPermission() throws Exception{
		Map<String, List<DataPermission>> entries = new HashMap<String, List<DataPermission>>();
		entries.put("addList", JSON.parseArray(inserted, DataPermission.class));
		entries.put("updList", JSON.parseArray(updated, DataPermission.class));
		entries.put("delList", JSON.parseArray(deleted, DataPermission.class));
		boolean result = dataPermissionService.persistenceDataPermission(entries);
		JSONWriter(result);
		return RJSON;
	}	
}
