/**
 * @Title:  ICItemBase.java
 * @Package:  com.cloud.erp.entities.table
 * @Description:  
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年4月22日 下午5:20:08
 * @version:  v1.0
 *
 * History:
 * Date		Author		Version
 * ---------------------------------------------
 * <reasons>
 */
package com.cloud.erp.entities.table;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * @ClassName ICItemBase
 * @Description 
 * @author bollen bollen@live.cn
 * @date 2015年4月22日 下午5:20:08
 *
 */
@Entity
@Table(name = "ICITEM_BASE")
@DynamicInsert(true)
@DynamicUpdate(true)
public class ICItemBase implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer itemId;
	private Integer source;
	private Integer lowLimit;
	private Integer highLimit;
	private Integer useState;
	private Integer defaultLoc;

	@Id
	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}

	public Integer getLowLimit() {
		return lowLimit;
	}

	public void setLowLimit(Integer lowLimit) {
		this.lowLimit = lowLimit;
	}

	public Integer getHighLimit() {
		return highLimit;
	}

	public void setHighLimit(Integer highLimit) {
		this.highLimit = highLimit;
	}

	public Integer getUseState() {
		return useState;
	}

	public void setUseState(Integer useState) {
		this.useState = useState;
	}

	public Integer getDefaultLoc() {
		return defaultLoc;
	}

	public void setDefaultLoc(Integer defaultLoc) {
		this.defaultLoc = defaultLoc;
	}

}
