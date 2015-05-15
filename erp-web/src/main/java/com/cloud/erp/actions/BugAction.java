/**
 * @Title:  BugAction.java
 * @Package:  com.cloud.erp.actions
 * @Description:  TODO
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年3月31日 上午10:02:04
 * @version:  v1.0
 *
 * History:
 * Date		Author		Version
 * ---------------------------------------------
 * <reasons>
 */
package com.cloud.erp.actions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;

import com.cloud.erp.entities.table.Bug;
import com.cloud.erp.entities.viewmodel.GridModel;
import com.cloud.erp.service.BugService;
import com.cloud.erp.utils.Constants;
import com.cloud.erp.utils.PageUtil;
import com.cloud.erp.utils.ResourceUtil;
import com.opensymphony.xwork2.ModelDriven;

/**
 * @ClassName  BugAction
 * @Description  TODO
 * @author  bollen bollen@live.cn
 * @date  2015年3月31日 上午10:02:04
 *
 */
@Namespace("/bug")
@Action("bugAction")
public class BugAction extends BaseAction implements ModelDriven<Bug>{

	private static final long serialVersionUID = 1L;
	private BugService bugService;
	private Bug bug;
	private File filedata;
	private String filedataFileName;
	private String filedataContentType;

	/**
	 * @param bugService the bugService to set
	 */
	@Autowired
	public void setBugService(BugService bugService) {
		this.bugService = bugService;
	}
	
	public Bug getBug() {
		return bug;
	}

	public void setBug(Bug bug) {
		this.bug = bug;
	}

	public File getFiledata() {
		return filedata;
	}

	public void setFiledata(File filedata) {
		this.filedata = filedata;
	}

	public String getFiledataFileName() {
		return filedataFileName;
	}

	public void setFiledataFileName(String filedataFileName) {
		this.filedataFileName = filedataFileName;
	}

	public String getFiledataContentType() {
		return filedataContentType;
	}

	public void setFiledataContentType(String filedataContentType) {
		this.filedataContentType = filedataContentType;
	}
	

	public String findBugList() throws Exception
	{
		Map<String, Object> map=new HashMap<String, Object>();
		if (null!=searchValue&&!"".equals(searchValue))
		{
			map.put(getSearchName(), Constants.GET_SQL_LIKE+searchValue+Constants.GET_SQL_LIKE);
		}
		PageUtil pageUtil=new PageUtil(page, rows, searchAnds, searchColumnNames, searchConditions, searchVals);
		GridModel gridModel=new GridModel();
		gridModel.setRows(bugService.findBugList(map, pageUtil));
		gridModel.setTotal(bugService.getCount(map,pageUtil));
		OutputJson(gridModel);
		return null;
	}
	

	public String addBug() throws Exception
	{
		OutputJson(getMessage(bugService.addBug(getModel())),Constants.TEXT_TYPE_PLAIN);
		return null;
	}
	

	public String delBug() throws Exception
	{
		OutputJson(getMessage(bugService.delBug(getModel().getBugId())));
		return null;
	}
	
	
	public String upload() throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		HttpServletRequest request=ServletActionContext.getRequest();
		//HttpServletResponse response=ServletActionContext.getResponse();
		
		
		HttpSession session = request.getSession();
		String contextPath=request.getContextPath();
		String savePath = session.getServletContext().getRealPath(File.separator) + ResourceUtil.getUploadDirectory() + "/";// 文件保存目录路径
		String saveUrl =contextPath+ "/" + ResourceUtil.getUploadDirectory() + "/";// 要返回给xhEditor的文件保存目录URL
		SimpleDateFormat yearDf = new SimpleDateFormat("yyyy");
		SimpleDateFormat monthDf = new SimpleDateFormat("MM");
		SimpleDateFormat dateDf = new SimpleDateFormat("dd");
		Date date = new Date();
		String ymd = yearDf.format(date) + "/" + monthDf.format(date) + "/" + dateDf.format(date) + "/";
		savePath += ymd;
		saveUrl += ymd;
		
		System.out.println("request.getContextPath()==>"+request.getContextPath());
		File uploadDir = new File(savePath);// 创建要上传文件到指定的目录
		if (!uploadDir.exists()) {
			uploadDir.mkdirs();
		}

		String contentDisposition = request.getHeader("Content-Disposition");// 如果是HTML5上传文件，那么这里有相应头的
		if (contentDisposition != null) {// HTML5拖拽上传文件
			Long fileSize = Long.valueOf(request.getHeader("Content-Length"));// 上传的文件大小
			String fileName = contentDisposition.substring(contentDisposition.lastIndexOf("filename=\""));// 文件名称
			fileName = fileName.substring(fileName.indexOf("\"") + 1);
			fileName = fileName.substring(0, fileName.indexOf("\""));
			fileName=URLDecoder.decode(fileName, "utf-8");
			ServletInputStream inputStream = null;
			try {
				inputStream = request.getInputStream();
			} catch (IOException e) {
				map.put("err", "上传文件出错！");
			}

			if (inputStream == null) {
				map.put("err", "您没有上传任何文件！");
			}

			if (fileSize > ResourceUtil.getUploadFileMaxSize()) {
				map.put("err", "上传文件超出限制大小！");
				map.put("msg", fileName);
			}else {
				// 检查文件扩展名
				String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
				String newFileName = UUID.randomUUID().toString().replaceAll("-", "") + "." + fileExt;// 新的文件名称
				File uploadedFile = new File(savePath, newFileName);

				try {
					FileCopyUtils.copy(inputStream, new FileOutputStream(uploadedFile));
				} catch (FileNotFoundException e) {
					map.put("err", "上传文件出错！");
				} catch (IOException e) {
					map.put("err", "上传文件出错！");
				}
				map.put("err", "");
				System.out.println("----"+saveUrl);
				Map<String, Object> nm = new HashMap<String, Object>();
				nm.put("url", saveUrl+newFileName);
				nm.put("localfile", fileName);
				nm.put("id", 0);
				map.put("msg", nm);
			}
		} else {// 不是HTML5拖拽上传,普通上传
			if (ServletFileUpload.isMultipartContent(request)) {// 判断表单是否存在enctype="multipart/form-data"
				Long fileSize = Long.valueOf(request.getHeader("Content-Length"));
				if (fileSize>ResourceUtil.getUploadFileMaxSize())
				{
					map.put("err", "上传文件超出限制大小！");
					map.put("msg", filedataFileName);
				}else {
					String fileExt = filedataFileName.substring(filedataFileName.lastIndexOf(".") + 1).toLowerCase();
					String newFileName = UUID.randomUUID().toString().replaceAll("-", "") + "." + fileExt;
					Constants.copy(filedata, savePath+newFileName);
					Map<String, Object> nm = new HashMap<String, Object>();
					map.put("err", "");
					nm.put("url", saveUrl+newFileName);
					nm.put("localfile", filedataFileName);
					nm.put("id", 0);
					map.put("msg", nm);
				}
			} else {
				// 不是multipart/form-data表单
				map.put("err", "上传文件出错！");
			}
		}
		System.out.println(request.getHeader("Content-Length"));
		System.out.println("filedataContentType=>>"+filedataContentType);
		System.out.println("filedata=>>"+filedata);
		System.out.println("filedataFileName==>>"+filedataFileName);
		System.out.println("savePath==>>"+savePath);
		System.out.println("saveUrl==>>"+saveUrl);
		OutputJson(map,Constants.TEXT_TYPE_PLAIN);
		return null;

	}


	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	@Override
	public Bug getModel() {
		if(null == bug){
			bug = new Bug();
		}
		return bug;
	}

}
