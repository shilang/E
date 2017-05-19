package com.cloud.erp.handler;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cloud.erp.common.BaseHandler;
import com.cloud.erp.entities.viewmodel.GridModel;
import com.cloud.erp.entities.viewmodel.RequestParams;
import com.cloud.erp.entities.viewmodel.Result;
import com.cloud.erp.service.MessageService;
import com.cloud.erp.utils.PageUtil;

@Controller
@RequestMapping("/msg")
public class MessageHandler extends BaseHandler{

	@Resource
	private MessageService messageService;

	@ResponseBody
	@RequestMapping("list")
	public GridModel findAll(RequestParams rp){
		Map<String, Object> params = getQueryParams(rp);
		PageUtil pageUtil = getPageUtil(rp);
		GridModel gridModel = new GridModel();
		gridModel.setRows(messageService.findAll(params, pageUtil));
		gridModel.setTotal(messageService.getCount(params));
		return gridModel;
	}
	
	@ResponseBody
	@RequestMapping("updateReadStatus")
	public Result updateReadStatus(String ids, int read){
		String[] idss = ids.split(",");
		for (String id : idss) {
			messageService.updateReadStatus(Integer.valueOf(id), read);
		}
		return getDefaultResult(true);
	}

}
