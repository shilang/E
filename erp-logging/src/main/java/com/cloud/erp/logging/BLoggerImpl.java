package com.cloud.erp.logging;

import java.util.Date;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.cloud.erp.entities.table.Log;
import com.cloud.erp.service.LogService;
import com.cloud.erp.utils.Commons;

@Component
public class BLoggerImpl implements BLogger{
	
	private static final Logger logger = LoggerFactory.getLogger(BLoggerImpl.class);
	
	@Resource
	private LogService logService;

	public void write(String module, String content){
		
		Integer userId = Commons.getCurrentUser().getUserId();
		String name = Commons.getCurrentUser().getAccount();
		Date date = new Date();
		String ipAddr = null;//Commons.getIpAddr();
		String machine = null;
		
		Log log = new Log();
		log.setUserId(userId);
		log.setName(name);
		log.setModule(module);
		log.setContent(content);
		log.setDate(date);
		log.setIpAddr(ipAddr);
		log.setMachine(machine);
		
		try {
			logService.persistence(log);
		} catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.debug("persisted log failure!");
			}
		}
	}

}
