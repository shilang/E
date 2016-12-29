package com.cloud.erp.actions;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;

import com.cloud.erp.common.FileBaseAction;
import com.cloud.erp.service.report.ReportPOI;
import com.cloud.erp.utils.PageUtil;
import com.cloud.erp.utils.Reflect;
import com.cloud.erp.utils.SpringUtil;

@Namespace("/file")
public class FileHandleAction extends FileBaseAction {

	private static final long serialVersionUID = 1L;
	
	private static final String UPLOAD_STATUS = "文件上传成功!";
	private static final String DELETE_STATUS = "删除文件成功!";
	//private static final String DOWNLOAD_STATUS = "下载文件成功!";
	
	private static final String QUERY_METHOD = "findAll";

	private File filebox;
	private String fileboxFileName;
	private String fileboxContentType;
	
	private String bean;
	private String queryMethod = QUERY_METHOD;
	private String reportSchema;
	

	public File getFilebox() {
		return filebox;
	}

	public void setFilebox(File filebox) {
		this.filebox = filebox;
	}

	public String getFileboxFileName() {
		return fileboxFileName;
	}

	public void setFileboxFileName(String fileboxFileName) {
		this.fileboxFileName = fileboxFileName;
	}

	public String getFileboxContentType() {
		return fileboxContentType;
	}

	public void setFileboxContentType(String fileboxContentType) {
		this.fileboxContentType = fileboxContentType;
	}
	
	public String getBean() {
		return bean;
	}
	
	public void setBean(String bean) {
		this.bean = bean;
	}
	
	public String getQueryMethod() {
		return queryMethod;
	}
	
	public void setQueryMethod(String queryMethod) {
		this.queryMethod = queryMethod;
	}
	
	public String getReportSchema() {
		return reportSchema;
	}
	
	public void setReportSchema(String reportSchema) {
		this.reportSchema = reportSchema;
	}

	@Action(value = "upload")
	public String upload() throws Exception {
		writeFile(getFilebox(), false);
		JSONWriterSuccess(UPLOAD_STATUS);
		return RJSON;
	}
	
	@Action("download")
	public String download() throws Exception{
		return DOWNLOAD;
	}
	
	@Action("delete")
	public String delete() throws Exception{
		
		JSONWriterSuccess(DELETE_STATUS);
		return RJSON;
	}
	
	@SuppressWarnings("rawtypes")
	@Action("exportExcel")
	public String exportExcel() throws Exception{
		Map<String, Object> params = getQueryParamsForMain();
		PageUtil pageUtil = getPageUtil();
		
		Object bean = SpringUtil.getBean(getBean());
		List list = Reflect.invokeQueryMethod(bean, getQueryMethod(), params, pageUtil);
		
		ReportPOI poi = new ReportPOI(getReportSchema());
		String fileName = poi.exportExcel(list, getTmpOutputStream());
		setFileName(fileName);
		
		return DOWNLOAD;
	}

}
