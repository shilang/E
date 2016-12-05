package com.cloud.erp.actions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;

import com.cloud.erp.common.FileBaseAction;

@Namespace("/file")
public class FileHandleAction extends FileBaseAction {

	private static final long serialVersionUID = 1L;
	
	private static final String UPLOAD_STATUS = "文件上传成功!";
	private static final String DELETE_STATUS = "删除文件成功!";
	private static final String DOWNLOAD_STATUS = "下载文件成功!";

	private File filebox;
	private String fileboxFileName;
	private String fileboxContentType;
	private String fileSaveAs;
	private String fileNm;

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

	public String getFileSaveAs() {
		return fileSaveAs;
	}

	public void setFileSaveAs(String fileSaveAs) {
		this.fileSaveAs = fileSaveAs;
	}
	
	public String getFileNm() {
		return fileNm;
	}

	public void setFileNm(String fileNm) {
		this.fileNm = fileNm;
	}

	@Action(value = "upload")
	public String upload() throws Exception {
		InputStream is = new FileInputStream(getFilebox()); 
		File toFile = new File(getUploadRealPath(), getFileSaveAs());
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
		setfSaveAs(getFileSaveAs());
		setFileName(getFileNm());
		JSONWriterSuccess(DOWNLOAD_STATUS);
		return DOWNLOAD;
	}
	
	@Action("delete")
	public String delete() throws Exception{
		File file = new File(getUploadRealPath(), getFileSaveAs());
		if(file.exists()){
			file.delete();
		}
		JSONWriterSuccess(DELETE_STATUS);
		return RJSON;
	}

}
