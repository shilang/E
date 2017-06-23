package com.cloud.erp.actions;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;

import com.cloud.erp.common.BaseAction;
import com.cloud.erp.entities.table.QualityComplain;
import com.cloud.erp.service.ComplainService;
import com.cloud.erp.utils.PageUtil;
import com.opensymphony.xwork2.ModelDriven;

@Namespace("/quality")
public class ComplainAction extends BaseAction implements
		ModelDriven<QualityComplain> {

	private static final long serialVersionUID = 1L;

	@Resource
	private ComplainService copmlainService;

	private String segment;
	private QualityComplain qualityComplain;
	private String userId;
	private String processInstanceId;
	
	public String getSegment() {
		return segment;
	}
	
	public void setSegment(String segment) {
		this.segment = segment;
	}

	public QualityComplain getQualityComplain() {
		return qualityComplain;
	}

	public void setQualityComplain(QualityComplain qualityComplain) {
		this.qualityComplain = qualityComplain;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	@Action("findAll")
	public String findAll() throws Exception {
		Map<String, Object> params = getQueryParamsForMain();
		PageUtil pageUtil = getPageUtil();
		JSONWriter(copmlainService.findAll(params, pageUtil),
				copmlainService.getCount(params));
		return RJSON;
	}

	@Action("getSegment")
	public String getCurrSegment() throws Exception {
		String taskDefKey = copmlainService.getCurrTaskDefKey(userId,
				processInstanceId);
		JSONWriterSuccess(taskDefKey);
		return RJSON;
	}
	
	@Action("update")
	public String updateSegment() throws Exception{
		copmlainService.updateSegment(getSegment(), qualityComplain);
		return RJSON;
	}

	@Override
	public QualityComplain getModel() {
		if (null == qualityComplain) {
			qualityComplain = new QualityComplain();
		}
		return qualityComplain;
	}
}
