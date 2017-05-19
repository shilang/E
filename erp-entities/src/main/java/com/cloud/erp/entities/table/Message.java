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

import com.cloud.erp.entities.MessageType;
import com.cloud.erp.entities.serializer.DateTimeSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name = "MESSAGE")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Message implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer interId;
	private MessageType type;
	private String flag;
	private String name;
	private String content;
	private String sender;
	private String owner;
	private int read;
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

	@Column(name = "MESSAGE_TYPE", nullable = false)
	public MessageType getType() {
		return type;
	}

	public void setType(MessageType type) {
		this.type = type;
	}

	@Column(name = "FLAG", nullable = false)
	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
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
	
	@Column(name = "SENDER", length = 20)
	public String getSender() {
		return sender;
	}
	
	public void setSender(String sender) {
		this.sender = sender;
	}

	
	@Column(name = "OWNER", length = 20)
	public String getOwner() {
		return owner;
	}
	
	public void setOwner(String owner) {
		this.owner = owner;
	}
	
	@Column(name = "STATUS", length = 1)
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Column(name = "READ_", columnDefinition = "INT default 0")
	public int getRead() {
		return read;
	}
	
	public void setRead(int read) {
		this.read = read;
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
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LASTMOD", length = 10)
	public Date getLastmod() {
		return lastmod;
	}
	
	public void setLastmod(Date lastmod) {
		this.lastmod = lastmod;
	}
	
	@Column(name = "MODIFIER")
	public Integer getModifier() {
		return modifier;
	}
	
	public void setModifier(Integer modifier) {
		this.modifier = modifier;
	}
	
}
