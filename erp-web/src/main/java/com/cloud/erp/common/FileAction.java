package com.cloud.erp.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;

import com.cloud.erp.utils.Commons;

@Result(name = "download", type = "stream")
public class FileAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private static final String TMP = "/tmp";

	private String fileName;

	private String filePath;

	public void setFileName(String fileName)
			throws UnsupportedEncodingException {
		this.fileName = new String(fileName.getBytes("ISO8859-1"), "utf-8");
	}

	public String getFileName() {
		return fileName;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFilePath() {
		return filePath;
	}

	public OutputStream getOutputStream() throws FileNotFoundException {

		String filePath = ServletActionContext.getServletContext().getRealPath(
				TMP)
				+ "/" + Commons.getRandomFileName();
		setFilePath(filePath);
		return new FileOutputStream(new File(getFilePath()));
	}

	public InputStream getInputStream() throws IOException {
		//ServletActionContext.getResponse().setHeader("Content-Disposition","attachment;fileName="+java.net.URLEncoder.encode(fileName, "UTF-8"));
		return new FileInputStream(new File(getFilePath()));
	}

}
