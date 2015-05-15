/**
 * @Title:  AuxiliaryResMessageAction.java
 * @Package:  com.cloud.erp.actions
 * @Description:  TODO
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

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.cloud.erp.entities.table.AuxiliaryResMessage;
import com.cloud.erp.entities.viewmodel.GridModel;
import com.cloud.erp.service.AuxiliaryResMessageService;
import com.cloud.erp.utils.Constants;
import com.opensymphony.xwork2.ModelDriven;

/**
 * @ClassName AuxiliaryResMessageAction
 * @Description TODO
 * @author bollen bollen@live.cn
 * @date 2015年5月5日 上午11:07:17
 *
 */
@Namespace("/auxiliaryResMessage")
@Action("auxiliaryResMessageAction")
public class AuxiliaryResMessageAction extends BaseAction implements ModelDriven<AuxiliaryResMessage>{

	private static final long serialVersionUID = 1L;
	private AuxiliaryResMessageService auxiliaryResMessageService;
	private AuxiliaryResMessage auxiliaryResMessage;
	private Integer id;
	
	/**
	 * @param auxiliaryResMessageService
	 *            the auxiliaryResMessageService to set
	 */
	@Autowired
	public void setAuxiliaryResMessageService(
			AuxiliaryResMessageService auxiliaryResMessageService) {
		this.auxiliaryResMessageService = auxiliaryResMessageService;
	}

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
	
	public String findAuxiliaryResMessages() throws Exception {

		GridModel gridModel = new GridModel();
		gridModel.setRows(auxiliaryResMessageService.findAuxiliaryResMessages(id));
		gridModel.setTotal(auxiliaryResMessageService.getCount(id));

		OutputJson(gridModel);

		return null;
	}

	public String persistenceAuxiliaryResMessage() throws Exception {
		OutputJson(
				getMessage(auxiliaryResMessageService
						.persistenceAuxiliaryResMessage(getModel())),
				Constants.TEXT_TYPE_PLAIN);
		return null;
	}

	public String delAuxiliaryResMessage() throws Exception {
		OutputJson(getMessage(auxiliaryResMessageService
				.delAuxiliaryResMessage(id)));
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	@Override
	public AuxiliaryResMessage getModel() {
		// TODO Auto-generated method stub
		if (null == auxiliaryResMessage) {
			auxiliaryResMessage = new AuxiliaryResMessage();
		}
		return auxiliaryResMessage;
	}

}
