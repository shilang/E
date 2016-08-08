package com.cloud.erp.entities.viewmodel;

public class RequestParams {

	public String searchName;
	public String searchValue;
	public String searchType;
	public String inserted;
	public String updated;
	public String deleted;
	public String sort;
	public String order;
	public Integer page;
	public Integer rows;
	public String searchAnds;
	public String searchColumnNames;
	public String searchConditions;
	public String searchVals;

	public String getSearchName() {
		return searchName;
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}

	public String getSearchValue() {
		return searchValue;
	}

	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getInserted() {
		return inserted;
	}

	public void setInserted(String inserted) {
		this.inserted = inserted;
	}

	public String getUpdated() {
		return updated;
	}

	public void setUpdated(String updated) {
		this.updated = updated;
	}

	public String getDeleted() {
		return deleted;
	}

	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getSearchAnds() {
		return searchAnds;
	}

	public void setSearchAnds(String searchAnds) {
		this.searchAnds = searchAnds;
	}

	public String getSearchColumnNames() {
		return searchColumnNames;
	}

	public void setSearchColumnNames(String searchColumnNames) {
		this.searchColumnNames = searchColumnNames;
	}

	public String getSearchConditions() {
		return searchConditions;
	}

	public void setSearchConditions(String searchConditions) {
		this.searchConditions = searchConditions;
	}

	public String getSearchVals() {
		return searchVals;
	}

	public void setSearchVals(String searchVals) {
		this.searchVals = searchVals;
	}
}
