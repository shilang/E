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
import com.cloud.erp.utils.Constants;

/**
 * 
 * @author Bollen
 *
 */
@Result(name = "download", type = "stream")
public class FileBaseAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	private static final String TMP = "/tmp";

	private boolean isTmpFile = false;

	private String fileName;

	private String fileSaveAs;
	
	private File tmpFile;
	
	public void setFileName(String fileName)
			throws UnsupportedEncodingException {
		this.fileName = new String(fileName.getBytes(), "ISO-8859-1");
	}

	public String getFileName() {
		return fileName;
	}

	public String getFileSaveAs() {
		return fileSaveAs;
	}
	
	public void setFileSaveAs(String fileSaveAs) {
		this.fileSaveAs = fileSaveAs;
	}
	
	public File getTmpFile() {
		return tmpFile;
	}
	
	public void setTmpFile(File tmpFile) {
		this.tmpFile = tmpFile;
	}

	public void checkPath(String path){
		File file = new File(path);
		if(!file.exists()){
			file.mkdir();
		}
	}

	public String getRealPath(String relativePath){
		String realPath = ServletActionContext.getServletContext()
				.getRealPath(relativePath);
		checkPath(realPath);
		return realPath;
	}
	
	private File getUploadFile(){
		return new File(getRealPath(Constants.UPLOAD_PATH) + "/" + getFileSaveAs());
	}
	
	private File generateTmpFile(){
		return new File(getRealPath(TMP) + "/" + Commons.getRandomFileName());
	}
	
	public OutputStream getUploadOutputStream() throws FileNotFoundException{
		isTmpFile = false;
		return new FileOutputStream(getUploadFile());
	}
	
	public OutputStream getTmpOutputStream() throws FileNotFoundException{
		isTmpFile = true;
		File file = generateTmpFile();
		setTmpFile(file);
		return new FileOutputStream(file);
	}

	public InputStream getInputStream() throws IOException {
		File file = null;
		if(isTmpFile){
			file = getTmpFile();
		}else {
			file = getUploadFile();
		}
		return new FileInputStream(file);
	}
	
	public void writeFile(File file, boolean isTmpfile) throws IOException{
		InputStream is = new FileInputStream(file); 
		OutputStream os = null;
		if(isTmpfile){
			os = getTmpOutputStream();
		}else {
			os = getUploadOutputStream();
		}
		byte[] buff = new byte[4096];
		int len = 0;
		while (-1 != (len = is.read(buff,0,buff.length))) {
			os.write(buff, 0, len);
		}
		is.close();
		os.close();
	}
}
