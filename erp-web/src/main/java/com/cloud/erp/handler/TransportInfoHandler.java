package com.cloud.erp.handler;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cloud.erp.common.BaseHandler;
import com.cloud.erp.entities.table.SalesOutStock;
import com.cloud.erp.entities.table.TransportInfo;
import com.cloud.erp.entities.viewmodel.GridModel;
import com.cloud.erp.entities.viewmodel.Result;
import com.cloud.erp.service.TransportInfoService;

@Controller
@RequestMapping("/transportInfo")
public class TransportInfoHandler extends BaseHandler{

	@Resource
	private TransportInfoService transportInfoService;
	
	@ResponseBody
	@RequestMapping("/get")
    public TransportInfo get(Integer id){
    	return transportInfoService.get(id);
    }
	
	@ResponseBody
	@RequestMapping("/findByPid")
	public GridModel findByPid(Integer pid){
		GridModel gridModel = new GridModel();
		gridModel.setRows(transportInfoService.findByPid(pid));
		return gridModel;
	}
	
	@ResponseBody
	@RequestMapping("/persist")
	public Result persistence(SalesOutStock master, TransportInfo transportInfo, String taskId) throws Exception{
		return getDefaultResult(transportInfoService.persistence(master, transportInfo, taskId));
	}
	
	@ResponseBody
	@RequestMapping("/delete")
	public Result delete(Integer id){
		return getDefaultResult(transportInfoService.deleteToUpdate(id));
	}
}
