/**
 * @Title:  NumberSetServiceImpl.java
 * @Package:  com.cloud.erp.service.impl
 * @Description:  
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年5月19日 下午2:46:13
 * @version:  v1.0
 *
 * History:
 * Date		Author		Version
 * ---------------------------------------------
 * <reasons>
 */
package com.cloud.erp.service.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloud.erp.dao.common.NumberSetDao;
import com.cloud.erp.entities.table.NumberSet;

/**
 * @ClassName  NumberSetServiceImpl
 * @Description  
 * @author  bollen bollen@live.cn
 * @date  2015年5月19日 下午2:46:13
 *
 */
@Service("numberSetService")
public class NumberSetServiceImpl implements NumberSetService {
	
	private static final Logger log = LoggerFactory.getLogger(NumberSetServiceImpl.class);

	private NumberSetDao numberSetDao;
	
	/**
	 * @param numberSetDao the numberSetDao to set
	 */
	@Autowired
	public void setNumberSetDao(NumberSetDao numberSetDao) {
		this.numberSetDao = numberSetDao;
	}
	
	private String parseNumber(Integer number){
		String str = "000000" + number;
		return str.substring(str.length() - 6);
	}
	
	@Override
	public String getNumberSet(Integer classId) {
		if(log.isDebugEnabled()){
			log.debug("Current NumberSet object: {}", this);
		}
		NumberSet numberSet = numberSetDao.getNumberSet(classId);
		String number = "";
		if(null != numberSet){
			
			number = numberSet.getPrefix() + parseNumber(numberSet.getNumber());
		}
		
		return number;
	}

}
