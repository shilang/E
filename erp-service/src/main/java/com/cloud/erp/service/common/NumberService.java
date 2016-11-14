package com.cloud.erp.service.common;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.cloud.erp.dao.common.NumberSetDao;
import com.cloud.erp.dao.exception.NumberIncrementException;
import com.cloud.erp.entities.table.NumberSet;
import com.cloud.erp.utils.Reflect;

/**
 * auto number
 * @author Administrator
 *
 * @param <T> entry type
 */
public abstract class NumberService<T> {
	
	private static final Logger log = LoggerFactory.getLogger(NumberService.class);
	
	private static final String SOURCE_BILL = "sourceBillNo";
	
	private String sourceBillNo = SOURCE_BILL;
	
	private NumberSetDao numberSetDao;
	
	@Autowired
	public void setNumberSetDao(NumberSetDao numberSetDao) {
		this.numberSetDao = numberSetDao;
	}
	
	public String getSourceBillNo() {
		return sourceBillNo;
	}

	public void setSourceBillNo(String sourceBillNo) {
		this.sourceBillNo = sourceBillNo;
	}
	
	@SuppressWarnings("unused")
	private String getTypePrefix(Integer classId) {
		NumberSet numberSet = numberSetDao.getNumberSet(classId);
		return numberSet.getPrefix();
	}

	private int getClassIdFromNumber(String number) {
		String prefix = number.substring(0, number.length() - 6);
		NumberSet numberSet = numberSetDao.getNumberSet(prefix);
		if(numberSet != null){
			return numberSet.getClassId().intValue();
		}
		return -1;
	}

	public String getNumber(List<T> list){
		String number = null;
		Object value = null;
		if(list == null){
			if(log.isDebugEnabled()){
				log.debug("");
			}
		}
		for(T l : list){
			try {
				 value = Reflect.invokeGetMethod(l, getSourceBillNo());
			} catch (Exception e) {
				return null;
			}
			
			if (null !=  value && !"".equals(value)){
				number = (String) value;
				break;
			}
		}
		return number;
	}
	
	public boolean isThisType(String number, int classId){
		if(null != number){
			return getClassIdFromNumber(number) == classId;
		}
		return false;
	}
	
	public void increment(Integer classId) throws NumberIncrementException{
		numberSetDao.increment(classId);
	}
	
}
