package com.cloud.erp.utils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.struts2.ServletActionContext;

import com.cloud.erp.entities.shiro.ShiroUser;

public class Commons {

	/**
	 * function: 获取UUID生成的主键
	 * @Author: bollen bollen@live.cn
	 * @Date: 2015年3月31日 上午10:37:38
	 * @Title: getPrimaryKeyByUUID
	 * @return
	 */
	public static String getPrimaryKeyByUUID(){
		return UUID.randomUUID().toString();
	}
	
	public static String getRandomFileName(){
		Date dt = new Date(System.currentTimeMillis());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		return sdf.format(dt);
	}
	
	public static String convertResult(int result) {
		String resultString = "";
		if (result == Constants.RESULT_CHECK_PENDING) {
			resultString = Constants.RESULT_CHECK_PENDING_DESC;
		} else if (result == Constants.RESULT_CHECK_OK) {
			resultString = Constants.RESULT_CHECK_OK_DESC;
		} else if (result == Constants.RESULT_CHECK_CHANGE) {
			resultString = Constants.RESULT_CHECK_CHANGE_DESC;
		}
		return resultString;
	}
	
	/**
	 * function: 获取当前登录用户实体类
	 * @Author: bollen bollen@live.cn
	 * @Date: 2015年3月31日 上午10:37:58
	 * @Title: getCurrendUser
	 * @return
	 */
	public static ShiroUser getCurrentUser(){
//		Subject subject=SecurityUtils.getSubject();
//		return (ShiroUser)subject.getSession().getAttribute(SHIRO_USER);
		return (ShiroUser) SecurityUtils.getSubject().getPrincipal();
	} 
	
	private static String[] convertParams(Map<String, Object> params, String type){
		String[] tokens = null;
		if(null != params){
			String searchString = (String)params.get(type);
			if(null != searchString && !"".equals(searchString)){
				tokens = searchString.split(Constants.SEARCH_PARAM_SEP);
			}
		}
		return tokens;
	}
	
	/**
	 * function: 获得简单查询条件
	 * @Author: bollen bollen@live.cn
	 * @Date: 2015年3月31日 上午10:38:33
	 * @Title: getSearchConditionsHQL
	 * @param asName
	 * @param param
	 * @return
	 */
	public static String getSearchConditionHQL(String asName ,Map<String, Object> params){
		String searchCondition = "";
		String[] tokens = convertParams(params, Constants.SEARCH_PARAM_TYPE_SIMPLE);
		if(null != tokens){
			String searchName = tokens[0];
			searchCondition = " and " + asName + Constants.IS_DOT + searchName + " like :" + searchName;
		}
		return searchCondition;
	}	
	
	/**
	 * function: 高级查询hql条件拼接
	 * @Author: bollen bollen@live.cn
	 * @Date: 2015年3月31日 上午10:38:17
	 * @Title: getGradeSearchConditionsHQL
	 * @param asName
	 * @param pageUtil
	 * @return
	 */
	public static String getGradeSearchConditionsHQL(String asName,Map<String, Object> params)
	{
/*		String searchAnds = pageUtil.getSearchAnds();
		String searchColumnNames=pageUtil.getSearchColumnNames();
		String searchConditions=pageUtil.getSearchConditions();
		String searchVals=pageUtil.getSearchVals();
		if(null!=searchColumnNames && searchColumnNames.trim().length()>0){
			StringBuffer sb=new StringBuffer();
			String[] searchColumnNameArray=searchColumnNames.split("\\,");
			String[] searchAndsArray=searchAnds.split("\\,");
			String[] searchConditionsArray=searchConditions.split("\\,");
			String[] searchValsArray=searchVals.split("\\,");
			for (int i = 0; i < searchColumnNameArray.length; i++) {
				if (searchColumnNameArray[i].trim().length() > 0 && searchConditionsArray[i].trim().length()>0) {
					if (i == 0) {
						sb.append(asName+"."+searchColumnNameArray[i].trim() + " " + searchConditionsArray[i].trim() + " " + searchValsArray[i].trim());
					} else {
					}
					String temp=searchValsArray[i].trim().replaceAll("\\'", "");
					if (Constants.HQL_LIKE.equals(searchConditionsArray[i].trim()))
					{
						sb.append(" " + searchAndsArray[i].trim() + " " + asName+Constants.IS_DOT+searchColumnNameArray[i].trim() + " " + searchConditionsArray[i].trim() + " " +"'%"+ temp+"%'");

					}else {
						sb.append(" " + searchAndsArray[i].trim() + " " + asName+Constants.IS_DOT+searchColumnNameArray[i].trim() + " " + searchConditionsArray[i].trim() + " " +"'"+ temp+"'");
					}
				}
			}
			if(sb.length()>0){
				return sb.toString();
			}
		}*/
		return Constants.NULL_STRING;
	}
	
	public static String getSortHQL(String asName, Map<String, Object> params){
		String sortString = "";
		String[] tokens = convertParams(params, Constants.SEARCH_PARAM_TYPE_SORT);
		if(null != tokens){
			String sort = tokens[0];
			String order = tokens[1];
			sortString = " order by " + asName + Constants.IS_DOT + sort + " " + order;
		}
		return sortString;
	}
	
	public static String getCriteriaHQL(String hql, String asName, Map<String, Object> params){
		String criteria = hql;
		criteria += getSearchConditionHQL(asName, params);
		criteria +=	getGradeSearchConditionsHQL(asName, params);
		criteria += getSortHQL(asName, params);
		return criteria;
	}
	
	/**
	 * format   searchName:searchValue:searchType
	 * @param params
	 * @return
	 */
	public static Map<String, Object> getSearchParamsHQL(Map<String, Object> params){
		Map<String, Object> newParams = new HashMap<String, Object>();
		String[] tokens = convertParams(params, Constants.SEARCH_PARAM_TYPE_SIMPLE);
		if(null != tokens){
			String searchName = tokens[0];
			String searchValue = tokens[1];
			String searchType = tokens[2];
			if("int".equals(searchType)){
				newParams.put(searchName, Integer.valueOf(searchValue));
			}else {
				newParams.put(searchName, searchValue);
			}
		}
		return newParams;
	}
	
	public static String[] getFiledName(Object o )
	{
		try
		{
			Field[] fields = o.getClass().getDeclaredFields();
			String[] fieldNames = new String[fields.length];
			for (int i = 0; i < fields.length; i++)
			{
				fieldNames[i] = fields[i].getName();
			}
			return fieldNames;
		} catch (SecurityException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public static Object getFieldValueByName(String fieldName, Object o)    
	{       
	   try    
	   {       
	       String firstLetter = fieldName.substring(0, 1).toUpperCase();       
	       String getter = "get" + firstLetter + fieldName.substring(1);       
	       Method method = o.getClass().getMethod(getter, new Class[] {});       
	       Object value = method.invoke(o, new Object[] {});       
	       return value;       
	   } catch (Exception e)    
	   {       
	       System.out.println("属性不存在");       
	       return "";       
	   }       
	}  
	
	  public static HashMap<String, Method> ConverBean(Class<?> drbean) {  
	        Class<?> stopClass = null;  
	        // 存放class信息  
	        BeanInfo drbeaninfo = null;  
	        // 存放属性信息  
	        PropertyDescriptor[] props;  
	        HashMap<String, Method> map = new HashMap<String, Method>();  
	        try {  
	            // 获取class中得属性方法信息  
	            drbeaninfo = Introspector.getBeanInfo(drbean, stopClass);  
	            // 把class中属性放入PropertyDescriptor数组中  
	            props = drbeaninfo.getPropertyDescriptors();  
	            for (int i = 0; i < props.length; i++) {  
	                // 获取属性所对应的set方法  
	                Method setMethod = props[i].getWriteMethod();  
	                // 判断属性是否有set方法 如果有放入map<属性名，set方法>中  
	                if (setMethod != null) {  
	                    String field = props[i].getName().toLowerCase();  
	                    map.put(field, setMethod);  
	                }  
	            }  
	        } catch (IntrospectionException e) {  
	            e.printStackTrace();  
	        }  
	        return map;  
	    }
	  
	/**
	 * function: 获取客户端ip地址
	 * @Author: bollen bollen@live.cn
	 * @Date: 2015年3月31日 上午10:39:14
	 * @Title: getIpAddr
	 * @return
	 */
	public static String getIpAddr() {
		   HttpServletRequest request=ServletActionContext.getRequest();
	       String ip = request.getHeader("x-forwarded-for");
	       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	           ip = request.getHeader("Proxy-Client-IP");
	       }
	       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	           ip = request.getHeader("WL-Proxy-Client-IP");
	       }
	       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	           ip = request.getRemoteAddr();
	       }
	       return ip;
	   } 
	
	/** 
	 * function: 获取客户端mac地址
	 * @Author: bollen bollen@live.cn
	 * @Date: 2015年3月31日 上午10:39:29
	 * @Title: getMacAddr
	 * @return
	 */
	public static String  getMacAddr()
	{
		String smac = "";
		try
		{
			UdpGetClientMacAddr umac = new UdpGetClientMacAddr(getIpAddr());
			smac = umac.GetRemoteMacAddr();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return smac;
	}
	private static final int BUFFER_SIZE = 16 * 1024;
	 public static void copy(File src, String fullSavePath) {
	        InputStream in = null; 
	        OutputStream out = null; 
	        File newFile=new File(fullSavePath);
	        try { 
	            in = new BufferedInputStream(new FileInputStream(src), BUFFER_SIZE); 
	            out = new BufferedOutputStream(new FileOutputStream(newFile), 
	                    BUFFER_SIZE); 
	            byte[] buffer = new byte[BUFFER_SIZE]; 
	            int len = 0; 
	            while ((len = in.read(buffer)) > 0) { 
	                out.write(buffer, 0, len); 
	            } 
	            out.flush();
	        } catch (Exception e) { 
	            e.printStackTrace(); 
	        } finally { 
	            if (null != in) { 
	                try { 
	                    in.close(); 
	                } catch (IOException e) { 
	                    e.printStackTrace(); 
	                } 
	            } 
	            if (null != out) { 
	                try { 
	                    out.close(); 
	                } catch (IOException e) { 
	                    e.printStackTrace(); 
	                } 
	            } 
	        } 
	    }
	 public static String BASE_PATH =System.getProperty("erp");
	 @SuppressWarnings("resource")
	public static String dbBackUp()
		{
			//生成临时备份文件
			SimpleDateFormat sd=new SimpleDateFormat("yyyyMMddHHmmss");
			String fineName="dbBackUp-"+sd.format(new Date());
			String sqlName=fineName+Constants.FILE_SUFFIX_SQL;
			String pathSql=BASE_PATH+"attachment"+File.separator+"dbBackUp";
			try {
				File filePathSql = new File(pathSql);
				if(!filePathSql.exists()){
					filePathSql.mkdir();
				}
				StringBuffer sbs = new StringBuffer();
				sbs.append("mysqldump ");
				sbs.append("-h 192.168.110.10 ");
				sbs.append("--user=root");
				sbs.append(" --password=fortune123");
				sbs.append(" --lock-all-tables=true ");
				sbs.append("--result-file="+pathSql+File.separator);
				sbs.append(sqlName+" ");
				sbs.append(" --default-character-set=utf8 ");
				sbs.append("ERP");
		        Runtime runtime = Runtime.getRuntime();
		        Process child = runtime.exec(sbs.toString());
		        //读取备份数据并生成临时文件
		        InputStream in = child.getInputStream();
		        OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(pathSql), "utf8");
		        BufferedReader reader = new BufferedReader(new InputStreamReader(in, "utf8"));
		        String line=reader.readLine();
		        while (line != null) {
		                writer.write(line+"\n");
		                line=reader.readLine();
		         }
		         writer.flush();
			} catch (Exception e) {
				
			}
			return sqlName;
		}
}
