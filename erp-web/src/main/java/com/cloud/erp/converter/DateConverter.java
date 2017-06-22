package com.cloud.erp.converter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import ognl.DefaultTypeConverter;
/**
 * 
 * @author Bollen
 *
 */
public class DateConverter extends DefaultTypeConverter{

	private static final DateFormat[] ACCEPT_DATE_FORMATS = {
		new SimpleDateFormat("dd/MM/yyyy"),
		new SimpleDateFormat("yyyy-MM-dd"),
		new SimpleDateFormat("yyyy/MM/dd")
	};

	@SuppressWarnings("rawtypes")
	@Override
	public Object convertValue(Map context, Object value, Class toType) {
		
		if(toType == Date.class){
			String dateString = null;
			String[] params = (String[])value;
			dateString = params[0];
			for(DateFormat format : ACCEPT_DATE_FORMATS){
				try {
					return format.parse(dateString);
				} catch (ParseException e) {
					continue;
				}
			}
			return null;
		}else if(toType == String.class){
			Date date = (Date)value;
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
		}
		
		return null;
	}
	
}
