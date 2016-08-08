package com.cloud.erp.entities.viewmodel;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("rawtypes")
public class GridModel {

	private List rows = new ArrayList();
	private Long total = 0L;
	
	public GridModel() {

	}
	
	public GridModel(List rows, Long total) {
		this.rows = rows;
		this.total = total;
	}

	public List getRows() {
		return rows;
	}

	public void setRows(List rows) {
		this.rows = rows;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

}
