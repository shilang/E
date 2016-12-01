package com.cloud.erp.actions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;

import com.cloud.erp.common.FileAction;
import com.cloud.erp.utils.Constants;

@Namespace("/file")
public class FileHandleAction extends FileAction {

	private static final long serialVersionUID = 1L;
	
	private static final String UPLOAD_STATUS = "文件上传成功!";
	private static final String DELETE_STATUS = "删除文件成功!";
	private static final String DOWNLOAD_STATUS = "下载文件成功!";

	private File attach;
	private String attachFileName;
	private String attachContentType;
	private String attachSaveAs;

	public File getAttach() {
		return attach;
	}

	public void setAttach(File attach) {
		this.attach = attach;
	}

	public String getAttachFileName() {
		return attachFileName;
	}

	public void setAttachFileName(String attachFileName) {
		this.attachFileName = attachFileName;
	}

	public String getAttachContentType() {
		return attachContentType;
	}

	public void setAttachContentType(String attachContentType) {
		this.attachContentType = attachContentType;
	}
	
	public String getAttachSaveAs() {
		return attachSaveAs;
	}

	public void setAttachSaveAs(String attachSaveAs) {
		this.attachSaveAs = attachSaveAs;
	}
	
	private String getRealPath(){
		return ServletActionContext.getServletContext()
				.getRealPath(Constants.UPLOAD_PATH);
	}

	@Action(value = "upload")
	public String upload() throws Exception {
		InputStream is = new FileInputStream(getAttach()); 
		File toFile = new File(getRealPath(), getAttachSaveAs());
		OutputStream os = new FileOutputStream(toFile);
		byte[] buff = new byte[4096];
		int len = 0;
		while (-1 != (len = is.read(buff,0,buff.length))) {
			os.write(buff, 0, len);
		}
		is.close();
		os.close();
		JSONWriterSuccess(UPLOAD_STATUS);
		return RJSON;
	}
	
	@Action("download")
	public String download() throws Exception{
		
		setFilePath(getRealPath()+"/temp.txt");
		
		setFileName("测试文件");
		
		//JSONWriterSuccess(DOWNLOAD_STATUS);
		return DOWNLOAD;
	}
	
	@Action("delete")
	public String delete() throws Exception{
		File file = new File(getRealPath(), getAttachSaveAs());
		if(file.exists()){
			file.delete();
		}
		JSONWriterSuccess(DELETE_STATUS);
		return RJSON;
	}

}
