package com.cloud.erp.entities;

public class SettleItem {

	private Double amount = 0;
	private Double freightAmount;
	private Double totalAmount;
	private Double settleAmount;
	private Double bankCost;

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getFreightAmount() {
		return freightAmount;
	}

	public void setFreightAmount(Double freightAmount) {
		this.freightAmount = freightAmount;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Double getSettleAmount() {
		return settleAmount;
	}

	public void setSettleAmount(Double settleAmount) {
		this.settleAmount = settleAmount;
	}

	public Double getBankCost() {
		return bankCost;
	}

	public void setBankCost(Double bankCost) {
		this.bankCost = bankCost;
	}

}
