package com.cloud.erp.entities.table;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.cloud.erp.entities.serializer.DateSerializer;
import com.cloud.erp.entities.serializer.DateTimeSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name = "TRANSPORT_INFO")
@DynamicInsert(true)
@DynamicUpdate(true)
public class TransportInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private SalesOutStock master;
	private String freight;
	private Date predictSail;
	private Date predictReach;
	private Double weight;
	private Double bulk;
	private Integer quantity;
	private String way;
	private Date cutoff;
	private Double customsCost;
	private Double commodityInspCost;
	private Double deliverGoodsCost;
	private Double storageCost;
	private Double fileCost;
	private Double buildCost;
	private Double transportCost;
	private Double thcOrcCost;
	private Double securityCost;
	private Double operationCost;
	private Double otherCost;
	private Double total;
	private String remark;
	private String status;
	private Integer creater;
	private Date created;
	private Integer modifier;
	private Date lastmod;

	@Id
	@GeneratedValue
	@Column(name = "ID", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "INTER_ID")
	public SalesOutStock getMaster() {
		return master;
	}

	public void setMaster(SalesOutStock master) {
		this.master = master;
	}

	@Column(name = "FREIGHT", length = 64)
	public String getFreight() {
		return freight;
	}

	public void setFreight(String freight) {
		this.freight = freight;
	}

	@JsonSerialize(using = DateSerializer.class)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "PREDICT_SAIL")
	public Date getPredictSail() {
		return predictSail;
	}

	public void setPredictSail(Date predictSail) {
		this.predictSail = predictSail;
	}

	@JsonSerialize(using = DateSerializer.class)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "PREDICT_REACH")
	public Date getPredictReach() {
		return predictReach;
	}

	public void setPredictReach(Date predictReach) {
		this.predictReach = predictReach;
	}

	@Column(name = "WEIGHT")
	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	@Column(name = "BULK")
	public Double getBulk() {
		return bulk;
	}

	public void setBulk(Double bulk) {
		this.bulk = bulk;
	}

	@Column(name = "QUANTITY")
	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getWay() {
		return way;
	}

	public void setWay(String way) {
		this.way = way;
	}

	@JsonSerialize(using = DateSerializer.class)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CUTOFF")
	public Date getCutoff() {
		return cutoff;
	}

	public void setCutoff(Date cutoff) {
		this.cutoff = cutoff;
	}

	@Column(name = "CUSTOMS_COST")
	public Double getCustomsCost() {
		return customsCost;
	}

	public void setCustomsCost(Double customsCost) {
		this.customsCost = customsCost;
	}

	@Column(name = "COMMODITY_INSP_COST")
	public Double getCommodityInspCost() {
		return commodityInspCost;
	}

	public void setCommodityInspCost(Double commodityInspCost) {
		this.commodityInspCost = commodityInspCost;
	}

	@Column(name = "DELIVER_GOODS_COST")
	public Double getDeliverGoodsCost() {
		return deliverGoodsCost;
	}

	public void setDeliverGoodsCost(Double deliverGoodsCost) {
		this.deliverGoodsCost = deliverGoodsCost;
	}

	@Column(name = "STORAGE_COST")
	public Double getStorageCost() {
		return storageCost;
	}

	public void setStorageCost(Double storageCost) {
		this.storageCost = storageCost;
	}

	@Column(name = "FILE_COST")
	public Double getFileCost() {
		return fileCost;
	}

	public void setFileCost(Double fileCost) {
		this.fileCost = fileCost;
	}

	@Column(name = "BUILD_COST")
	public Double getBuildCost() {
		return buildCost;
	}

	public void setBuildCost(Double buildCost) {
		this.buildCost = buildCost;
	}

	@Column(name = "TRANSPORT_COST")
	public Double getTransportCost() {
		return transportCost;
	}

	public void setTransportCost(Double transportCost) {
		this.transportCost = transportCost;
	}

	@Column(name = "THC_ORC_COST")
	public Double getThcOrcCost() {
		return thcOrcCost;
	}

	public void setThcOrcCost(Double thcOrcCost) {
		this.thcOrcCost = thcOrcCost;
	}

	@Column(name = "SECURITY_COST")
	public Double getSecurityCost() {
		return securityCost;
	}

	public void setSecurityCost(Double securityCost) {
		this.securityCost = securityCost;
	}

	@Column(name = "OPERATION_COST")
	public Double getOperationCost() {
		return operationCost;
	}

	public void setOperationCost(Double operationCost) {
		this.operationCost = operationCost;
	}

	@Column(name = "OTHER_COST")
	public Double getOtherCost() {
		return otherCost;
	}

	public void setOtherCost(Double otherCost) {
		this.otherCost = otherCost;
	}

	@Column(name = "TOTAL")
	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	@Column(name = "REMARK", length = 200)
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "STATUS", length = 1)
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "CREATER")
	public Integer getCreater() {
		return creater;
	}

	public void setCreater(Integer creater) {
		this.creater = creater;
	}

	@JsonSerialize(using = DateTimeSerializer.class)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED")
	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	@Column(name = "MODIFIER")
	public Integer getModifier() {
		return modifier;
	}

	public void setModifier(Integer modifier) {
		this.modifier = modifier;
	}

	@JsonSerialize(using = DateTimeSerializer.class)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LASTMOD")
	public Date getLastmod() {
		return lastmod;
	}

	public void setLastmod(Date lastmod) {
		this.lastmod = lastmod;
	}

}
