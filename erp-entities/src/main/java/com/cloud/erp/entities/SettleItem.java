package com.cloud.erp.entities;


public class SettleItem {

	private double amount = 0.00;
	private double freightAmount = 0.00;
	private double totalAmount = 0.00;
	private double settleAmount = 0.00;
	private double bankCost = 0.00;

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
