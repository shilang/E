/**
 * @Title:  AuxiliaryResAction.java
 * @Package:  com.cloud.erp.actions
 * @Description:  TODO
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年4月29日 下午6:16:23
 * @version:  v1.0
 *
 * History:
 * Date		Author		Version
 * ---------------------------------------------
 * <reasons>
 */
package com.cloud.erp.actions;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.cloud.erp.entities.table.AuxiliaryResType;
import com.cloud.erp.entities.viewmodel.Json;
import com.cloud.erp.entities.viewmodel.TreeGeneralModel;
import com.cloud.erp.service.AuxiliaryResTypeService;
import com.cloud.erp.utils.Constants;
import com.opensymphony.xwork2.ModelDriven;

/**
 * @ClassName AuxiliaryResAction
 * @Description TODO
 * @author bollen bollen@live.cn
 * @date 2015年4月29日 下午6:16:23
 *
 */
@Namespace("/auxiliaryResType")
@Action("auxiliaryResTypeAction")
public class AuxiliaryResTypeAction extends BaseAction implements
		ModelDriven<AuxiliaryResType> {

	private static final long serialVersionUID = 1L;
	private AuxiliaryResTypeService auxiliaryResTypeService;
	private AuxiliaryResType auxiliaryResType;
	private Integer id;

	@Autowired
	public void setAuxiliaryResTypeService(
			AuxiliaryResTypeService auxiliaryResTypeService) {
		this.auxiliaryResTypeService = auxiliaryResTypeService;
	}

	public AuxiliaryResType getAuxiliaryResType() {
		return auxiliaryResType;
	}

	public void setAuxiliaryResType(AuxiliaryResType auxiliaryResType) {
		this.auxiliaryResType = auxiliaryResType;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String findAuxiliaryResTypes() throws Exception {
		List<AuxiliaryResType> auxiliaryResTypes = auxiliaryResTypeService
				.findAuxiliaryResTypes();
		List<TreeGeneralModel> treeGeneralModels = new ArrayList<TreeGeneralModel>();
		for (AuxiliaryResType auxiliaryResType : auxiliaryResTypes) {
			Integer pid = auxiliaryResType.getPid();
			if (null == pid) {
				TreeGeneralModel treeGeneralModel = new TreeGeneralModel();
				treeGeneralModel.setId(auxiliaryResType.getResId());
				treeGeneralModel.setText(auxiliaryResType.getName());
				treeGeneralModel.setState(auxiliaryResType.getStatus());
				treeGeneralModel.setIconCls("icon-sys");
				treeGeneralModel.setAttributes(auxiliaryResType);
				for (AuxiliaryResType auxiliaryResType2 : auxiliaryResTypes) {
					if (auxiliaryResType.getResId().equals(
							auxiliaryResType2.getPid())) {
						TreeGeneralModel treeGeneralModel2 = new TreeGeneralModel();
						treeGeneralModel2.setId(auxiliaryResType2.getResId());
						treeGeneralModel2.setText(auxiliaryResType2.getName());
						treeGeneralModel2.setState(auxiliaryResType2
								.getStatus());
						treeGeneralModel2.setIconCls("icon-item");
						treeGeneralModel2.setAttributes(auxiliaryResType2);
						treeGeneralModel.getChildren().add(treeGeneralModel2);
					}
				}
				treeGeneralModels.add(treeGeneralModel);
			}
		}
		OutputJson(treeGeneralModels);
		return null;
	}

	public String persistenceAuxiliaryResType() throws Exception {
		OutputJson(getMessage(auxiliaryResTypeService.persistenceAuxiliaryResType(getModel())),
				Constants.TEXT_TYPE_PLAIN);
		return null;
	}

	public String delAuxiliaryResType() throws Exception {
		Json json = new Json();
		if (auxiliaryResTypeService.delAuxiliaryResType(id)) {
			json.setStatus(true);
			json.setMessage(Constants.POST_DATA_SUCCESS);
		} else {
			json.setStatus(false);
			json.setMessage(Constants.POST_DATA_FAIL + Constants.IS_EXT_SUBITEM);
		}
		OutputJson(json);
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	@Override
	public AuxiliaryResType getModel() {
		// TODO Auto-generated method stub
		if (null == auxiliaryResType) {
			auxiliaryResType = new AuxiliaryResType();
		}
		return auxiliaryResType;
	}
}
