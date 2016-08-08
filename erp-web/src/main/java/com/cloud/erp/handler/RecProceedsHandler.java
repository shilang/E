package com.cloud.erp.handler;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cloud.erp.common.BaseHandler;
import com.cloud.erp.entities.table.RecProceeds;
import com.cloud.erp.entities.table.RecProceedsEntry;
import com.cloud.erp.entities.viewmodel.GridModel;
import com.cloud.erp.entities.viewmodel.RequestParams;
import com.cloud.erp.entities.viewmodel.Result;
import com.cloud.erp.service.RecProceedsService;
import com.cloud.erp.utils.PageUtil;

@Controller
@RequestMapping("/proceeds")
public class RecProceedsHandler extends BaseHandler{

	@Resource
	private RecProceedsService recProceedsService;
	
	@ResponseBody
	@RequestMapping("/list")
	public GridModel findRecProceeds(RequestParams rp){
		Map<String, Object> params = getQueryParams(rp);
		PageUtil pageUtil = getPageUtil(rp);
		GridModel gridModel = new GridModel();
		gridModel.setRows(recProceedsService.findAll(params, pageUtil));
		gridModel.setTotal(recProceedsService.getCount(params));
		return gridModel;
	}
	
	@ResponseBody
	@RequestMapping("/persist")
	public Result persistenceRecProceeds(RecProceeds recProceeds,String inserted, String updated, String deleted ) throws Exception{
		Map<String, List<RecProceedsEntry>> entries =  getEntriesParams(RecProceedsEntry.class, inserted, updated, deleted);
		return getDefaultResult(recProceedsService.persistence(recProceeds, entries));
	}
	
	@ResponseBody
	@RequestMapping("/entries")
	public GridModel findEntriesByPid(@RequestParam(value = "interId", required = false)Integer id){
		GridModel gridModel = new GridModel();
		gridModel.setRows(recProceedsService.findEntriesById(id, RecProceedsEntry.class));
		return gridModel;
	}
	
	@ResponseBody
	@RequestMapping("/shareEntries")
	public GridModel findEntiesByType(String entryType, @RequestParam(value = "interId", required = true)Integer pid){
		GridModel gridModel = new GridModel();
		gridModel.setRows(recProceedsService.findShareEntries(entryType, pid));
		return gridModel;
	}
	
	@ResponseBody
	@RequestMapping("/delete")
	public Result delRecProceeds(Integer id) throws Exception{
		return getDefaultResult(recProceedsService.deleteToUpdateAll(id));
	}

}
