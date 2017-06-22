package com.cloud.erp.entities.table;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.cloud.erp.entities.CustomerSuggestion;

@Entity
@Table(name = "QUALITY_COMPLAIN")
@DynamicInsert(true)
@DynamicUpdate(true)
public class QualityComplain {

	private Integer interId;
	private String procInstId;
	private String billNo;
	private Date date;
	private String orderStatus;
	private String content;
	private CustomerSuggestion suggestion;
	private String attachment;
	private String reason;
	//private String solveDept;
	private boolean SCBchk;
	private String SCB;
	private Date SCBDate;
	private boolean PZBchk;
	private String PZB;
	private Date PZBDate;
	private boolean CGBchk;
	private String CGB;
	private Date CGBDate;
	private boolean GCBchk;
	private String GCB;
	private Date GCBDate;
	private boolean GMBchk;
	private String GMB;
	private Date GMBDate;
	private boolean WJBchk;
	private String WJB;
	private Date WJBDate;
	private String solveWay;
	private String solveWayMark;
	private String result;
	private String status;
	private Date created;
	private Date lastmod;
	private Integer creater;
	private Integer modifier;

	@Id
	@GeneratedValue
	@Column(name = "INTER_ID", unique = true, nullable = false)
	public Integer getInterId() {
		return interId;
	}

	public void setInterId(Integer interId) {
		this.interId = interId;
	}
	
	@Column(name = "PROC_INST_ID", length = 64)
	public String getProcInstId() {
		return procInstId;
	}
	
	public void setProcInstId(String procInstId) {
		this.procInstId = procInstId;
	}

	@Column(name = "BILL_NO", length = 20)
	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	@Column(name = "CONTENT", length = 1000)
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "SUGGESTION")
	public CustomerSuggestion getSuggestion() {
		return suggestion;
	}

	public void setSuggestion(CustomerSuggestion suggestion) {
		this.suggestion = suggestion;
	}

	@Column(name = "ATTACHMENT", length = 200)
	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	@Column(name = "REASON", length = 1000)
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	/*@Column(name = "SOLVE_DEPT")
	public String getSolveDept() {
		return solveDept;
	}

	public void setSolveDept(String solveDept) {
		this.solveDept = solveDept;
	}*/

	@Column(name = "IS_SCB_CHK")
	public boolean isSCBchk() {
		return SCBchk;
	}

	public void setSCBchk(boolean sCBchk) {
		SCBchk = sCBchk;
	}

	@Column(name = "SCB", length = 200)
	public String getSCB() {
		return SCB;
	}

	public void setSCB(String sCB) {
		SCB = sCB;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "SCB_DATE")
	public Date getSCBDate() {
		return SCBDate;
	}

	public void setSCBDate(Date sCBDate) {
		SCBDate = sCBDate;
	}

	@Column(name = "IS_PZB_CHK")
	public boolean isPZBchk() {
		return PZBchk;
	}

	public void setPZBchk(boolean pZBchk) {
		PZBchk = pZBchk;
	}

	@Column(name = "PZB", length = 200)
	public String getPZB() {
		return PZB;
	}

	public void setPZB(String pZB) {
		PZB = pZB;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "PZB_DATE")
	public Date getPZBDate() {
		return PZBDate;
	}

	public void setPZBDate(Date pZBDate) {
		PZBDate = pZBDate;
	}

	@Column(name = "IS_CGB_CHK")
	public boolean isCGBchk() {
		return CGBchk;
	}

	public void setCGBchk(boolean cGBchk) {
		CGBchk = cGBchk;
	}

	@Column(name = "CGB", length = 200)
	public String getCGB() {
		return CGB;
	}

	public void setCGB(String cGB) {
		CGB = cGB;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CGB_DATE")
	public Date getCGBDate() {
		return CGBDate;
	}

	public void setCGBDate(Date cGBDate) {
		CGBDate = cGBDate;
	}

	@Column(name = "IS_GCB_CHK")
	public boolean isGCBchk() {
		return GCBchk;
	}

	public void setGCBchk(boolean gCBchk) {
		GCBchk = gCBchk;
	}

	@Column(name = "GCG", length = 200)
	public String getGCB() {
		return GCB;
	}

	public void setGCB(String gCB) {
		GCB = gCB;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "GCB_DATE")
	public Date getGCBDate() {
		return GCBDate;
	}

	public void setGCBDate(Date gCBDate) {
		GCBDate = gCBDate;
	}

	@Column(name= "IS_GMB_CHK")
	public boolean isGMBchk() {
		return GMBchk;
	}

	public void setGMBchk(boolean gMBchk) {
		GMBchk = gMBchk;
	}

	@Column(name = "GMB", length = 200)
	public String getGMB() {
		return GMB;
	}

	public void setGMB(String gMB) {
		GMB = gMB;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "GMB_DATE")
	public Date getGMBDate() {
		return GMBDate;
	}

	public void setGMBDate(Date gMBDate) {
		GMBDate = gMBDate;
	}

	@Column(name = "IS_WJB_CHK")
	public boolean isWJBchk() {
		return WJBchk;
	}

	public void setWJBchk(boolean wJBchk) {
		WJBchk = wJBchk;
	}

	@Column(name = "WJB", length = 200)
	public String getWJB() {
		return WJB;
	}

	public void setWJB(String wJB) {
		WJB = wJB;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "WJB_DATE")
	public Date getWJBDate() {
		return WJBDate;
	}

	public void setWJBDate(Date wJBDate) {
		WJBDate = wJBDate;
	}

	@Column(name = "SOLVE_WAY", length = 20)
	public String getSolveWay() {
		return solveWay;
	}

	public void setSolveWay(String solveWay) {
		this.solveWay = solveWay;
	}

	@Column(name = "SOLVE_WAY_MARK")
	public String getSolveWayMark() {
		return solveWayMark;
	}

	public void setSolveWayMark(String solveWayMark) {
		this.solveWayMark = solveWayMark;
	}

	@Column(name = "RESULT", length = 20)
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	@Column(name = "STATUS", length = 1)
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED")
	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LASTMOD")
	public Date getLastmod() {
		return lastmod;
	}

	public void setLastmod(Date lastmod) {
		this.lastmod = lastmod;
	}

	@Column(name = "CREATER")
	public Integer getCreater() {
		return creater;
	}

	public void setCreater(Integer creater) {
		this.creater = creater;
	}

	@Column(name = "MODIFIER")
	public Integer getModifier() {
		return modifier;
	}

	public void setModifier(Integer modifier) {
		this.modifier = modifier;
	}

}
