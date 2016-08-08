/**
 * @Title:  AuxiliaryResMessageAction.java
 * @Package:  com.cloud.erp.actions
 * @Description:  
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年5月5日 上午11:07:17
 * @version:  v1.0
 *
 * History:
 * Date		Author		Version
 * ---------------------------------------------
 * <reasons>
 */
package com.cloud.erp.actions;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;

import com.cloud.erp.common.BaseAction;
import com.cloud.erp.entities.table.AuxiliaryResMessage;
import com.cloud.erp.service.AuxiliaryResMessageService;
import com.opensymphony.xwork2.ModelDriven;

/**
 * @ClassName AuxiliaryResMessageAction
 * @Description 
 * @author bollen bollen@live.cn
 * @date 2015年5月5日 上午11:07:17
 *
 */
@Namespace("/auxiliaryResMessage")
public class AuxiliaryResMessageAction extends BaseAction implements ModelDriven<AuxiliaryResMessage>{

	private static final long serialVersionUID = 1L;
	
	@Resource
	private AuxiliaryResMessageService auxiliaryResMessageService;
	
	private AuxiliaryResMessage auxiliaryResMessage;
	private Integer id;

	public AuxiliaryResMessage getAuxiliaryResMessage() {
		return auxiliaryResMessage;
	}

	public void setAuxiliaryResMessage(AuxiliaryResMessage auxiliaryResMessage) {
		this.auxiliaryResMessage = auxiliaryResMessage;
	}

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Action(value = "find")
	public String findAuxiliaryResMessages() throws Exception {
		JSONWriter(auxiliaryResMessageService.findAuxiliaryResMessages(id), 
				auxiliaryResMessageService.getCount(id));
		return RJSON;
	}

	@Action(value = "persist")
	public String persistenceAuxiliaryResMessage() throws Exception {
		boolean result = auxiliaryResMessageService.persistence(getModel());
		JSONWriter(result);
		return RJSON;
	}

	@Action(value = "delete")
	public String delAuxiliaryResMessage() throws Exception {
		boolean result = auxiliaryResMessageService.deleteToUpdate(id);
		JSONWriter(result);
		return RJSON;
	}

	@Override
	public AuxiliaryResMessage getModel() {
		if (null == auxiliaryResMessage) {
			auxiliaryResMessage = new AuxiliaryResMessage();
		}
		return auxiliaryResMessage;
	}

}
