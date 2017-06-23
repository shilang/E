package com.cloud.erp.handler;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cloud.erp.common.BaseHandler;
import com.cloud.erp.entities.viewmodel.GridModel;
import com.cloud.erp.entities.viewmodel.RequestParams;
import com.cloud.erp.utils.PageUtil;

@Controller
@RequestMapping("/quality")
public class ComplainHandler extends BaseHandler{

	
	/*public GridModel findAll(RequestParams rp){
		Map<String, Object> params = getQueryParams(rp);
		PageUtil pageUtil = getPageUtil(rp);
		GridModel gridModel = new GridModel();
		gridModel.setRows(rows);
		gridModel.setTotal(total);
		return gridModel;
	}
	
	public String getSegment(){
		
	}*/
}
