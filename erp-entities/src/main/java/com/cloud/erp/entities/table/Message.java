package com.cloud.erp.entities.table;

import java.io.Serializable;
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

import com.cloud.erp.entities.common.MessageType;

@Entity
@Table(name = "MESSAGE")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Message implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer interId;
	private MessageType type;
	private Integer flag;
	private String name;
	private String content;
	private String creater;
	private Date created;

	@Id
	@GeneratedValue
	@Column(name = "INTER_ID", unique = true, nullable = false)
	public Integer getInterId() {
		return interId;
	}

	public void setInterId(Integer interId) {
		this.interId = interId;
	}

	@Column(name = "MESSAGE_TYPE", nullable = false)
	public MessageType getType() {
		return type;
	}

	public void setType(MessageType type) {
		this.type = type;
	}

	@Column(name = "FLAG", nullable = false)
	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	@Column(name = "NAME", length = 50)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "CONTENT", length = 1000)
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@Column(name = "CREATER", length = 20)
	public String getCreater() {
		return creater;
	}
	
	public void setCreater(String creater) {
		this.creater = creater;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED")
	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}
}
