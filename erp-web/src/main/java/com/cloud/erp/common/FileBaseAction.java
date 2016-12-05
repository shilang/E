package com.cloud.erp.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;

import com.cloud.erp.utils.Constants;

@Result(name = "download", type = "stream")
public class FileBaseAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private String fileName;

	private String fSaveAs;

	public void setFileName(String fileName)
			throws UnsupportedEncodingException {
		this.fileName = new String(fileName.getBytes(), "ISO8859-1");
	}

	public String getFileName() {
		return fileName;
	}

	public String getfSaveAs() {
		return fSaveAs;
	}

	public void setfSaveAs(String fSaveAs) {
		this.fSaveAs = fSaveAs;
	}

	public String getUploadRealPath(){
		return ServletActionContext.getServletContext()
				.getRealPath(Constants.UPLOAD_PATH);
	}
	
	private File getFile(){
		return new File(getUploadRealPath() + "/" + getfSaveAs());
	}
	
	/*public OutputStream getOutputStream() throws FileNotFoundException {

		String filePath = ServletActionContext.getServletContext().getRealPath(
				TMP)
				+ "/" + Commons.getRandomFileName();
		setFilePath(filePath);
		return new FileOutputStream(new File(getFilePath()));
	}*/

	public InputStream getInputStream() throws IOException {
		return new FileInputStream(getFile());
	}

}
