package com.cloud.erp.actions;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;

import com.cloud.erp.common.BaseAction;
import com.cloud.erp.entities.table.ICItemAttr;
import com.cloud.erp.entities.viewmodel.Json;
import com.cloud.erp.entities.viewmodel.TreeGeneralModel;
import com.cloud.erp.service.MaterialAttrService;
import com.cloud.erp.utils.Constants;
import com.opensymphony.xwork2.ModelDriven;

@Namespace("/materialAttr")
public class MaterialAttrAction extends BaseAction implements ModelDriven<ICItemAttr>{

	private static final long serialVersionUID = 1L;
	@Resource
	private MaterialAttrService materialAttrService;
	private ICItemAttr icItemAttr;
	private Integer id;
	
	public ICItemAttr getIcItemAttr() {
		return icItemAttr;
	}

	public void setIcItemAttr(ICItemAttr icItemAttr) {
		this.icItemAttr = icItemAttr;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Action(value = "findById")
	public String findMaterialAttrsByPId() throws Exception{
		JSONWriterGeneral(materialAttrService.findMaterialAttrsByPId(id));
		return RJSON;
	}
	
	@Action(value = "findByIdForTree")
	public String findMaterialAttrsByIdForTree() throws Exception{
		List<ICItemAttr> icItemAttrs = materialAttrService.findMaterialAttrs();
		List<TreeGeneralModel> tree = new ArrayList<TreeGeneralModel>();
		for(ICItemAttr icItemAttr : icItemAttrs){
			if(null != id && id == icItemAttr.getAttrId()){
				//TreeGeneralModel root = new TreeGeneralModel();
				//root.setId(icItemAttr.getAttrId());
				//root.setText(icItemAttr.getName());
				//List<TreeGeneralModel> children1 = new ArrayList<TreeGeneralModel>();
				for(ICItemAttr icItemAttr2 : icItemAttrs){
					if(icItemAttr.getAttrId() == icItemAttr2.getPid()){
						TreeGeneralModel node1 = new TreeGeneralModel();
						node1.setId(icItemAttr2.getAttrId());
						node1.setText(icItemAttr2.getName());
						List<TreeGeneralModel> children2 = new ArrayList<TreeGeneralModel>();
						for(ICItemAttr icItemAttr3 : icItemAttrs){
							if(icItemAttr2.getAttrId() == icItemAttr3.getPid()){
								TreeGeneralModel node2 = new TreeGeneralModel();
								node2.setId(icItemAttr3.getAttrId());
								node2.setText(icItemAttr3.getName());
								List<TreeGeneralModel> children3 = new ArrayList<TreeGeneralModel>();
								for(ICItemAttr icItemAttr4: icItemAttrs){
									if(icItemAttr3.getAttrId() == icItemAttr4.getPid()){
										TreeGeneralModel node3 = new TreeGeneralModel();
										node3.setId(icItemAttr4.getAttrId());
										node3.setText(icItemAttr4.getName());
										children3.add(node3);
									}
								}
								node2.setChildren(children3);
								children2.add(node2);
							}
						}
						node1.setChildren(children2);;
						//children1.add(node1);
						tree.add(node1);
					}
				}
				//root.setChildren(children1);
				//tree.add(root); 
			}
		}
		JSONWriterGeneral(tree);
		return RJSON;
	}
	
	@Action(value = "persist")
	public String persistenceMaterialAttr() throws Exception{
		boolean result = materialAttrService.persistence(getModel());
		JSONWriter(result);
		return RJSON;
	}
	
	@Action(value = "delete")
	public String delMaterialAttr() throws Exception{
		Json json = new Json();
		if(materialAttrService.deleteToUpdate(getModel().getAttrId())){
			json.setStatus(true);
			json.setMessage(Constants.POST_DATA_SUCCESS);
		}else {
			json.setMessage(Constants.POST_DATA_FAIL + Constants.IS_EXT_SUBMENU);
		}
		JSONWriter(json);
		return RJSON;
	}
	
	@Override
	public ICItemAttr getModel() {
		if(null == icItemAttr){
			icItemAttr = new ICItemAttr();
		}
		return icItemAttr;
	}
	
}
