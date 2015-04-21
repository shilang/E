/**
 * @Title:  ResourceUtil.java
 * @Package:  com.cloud.erp.utils
 * @Description:  TODO
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年3月31日 上午10:31:06
 * @version:  v1.0
 *
 * History:
 * Date		Author		Version
 * ---------------------------------------------
 * <reasons>
 */
package com.cloud.erp.utils;

import java.util.ResourceBundle;

/**
 * @ClassName  ResourceUtil
 * @Description  TODO
 * @author  bollen bollen@live.cn
 * @date  2015年3月31日 上午10:31:06
 *
 */
public class ResourceUtil {
	
	private static final ResourceBundle bundle = java.util.ResourceBundle.getBundle("config");
	
	/**
	 * function: TODO get sessionInfo name
	 * @Author: bollen bollen@live.cn
	 * @Date: 2015年3月31日 上午10:33:35
	 * @Title: getSessionInfoName
	 * @return
	 */
	public static final String getSessionInfoName(){
		return bundle.getString("sessionInfoName");
	}
	
	/**
	 * function: TODO get name of uploading form field
	 * @Author: bollen bollen@live.cn
	 * @Date: 2015年3月31日 上午10:34:38
	 * @Title: getUploadFieldName
	 * @return
	 */
	public static final String getUploadFieldName(){
		return bundle.getString("uploadFieldName");
	}
	
	/**
	 * 获得上传文件的最大大小限制
	 * 
	 * @return
	 */
	public static final long getUploadFileMaxSize() {
		return Long.valueOf(bundle.getString("uploadFileMaxSize"));
	}

	/**
	 * 获得允许上传文件的扩展名
	 * 
	 * @return
	 */
	public static final String getUploadFileExts() {
		return bundle.getString("uploadFileExts");
	}

	/**
	 * 获得上传文件要放到那个目录
	 * 
	 * @return
	 */
	public static final String getUploadDirectory() {
		return bundle.getString("uploadDirectory");
	}
}
