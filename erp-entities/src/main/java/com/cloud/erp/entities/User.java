/**
 * @Title:  Users.java
 * @Package:  com.cloud.erp.entities
 * @Description:  TODO
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年2月2日  上午11:57:03
 * @version:  v1.0
 *
 * History:
 * Date		Author		Version
 * ---------------------------------------------
 * <reasons>
 */
package com.cloud.erp.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * @ClassName  Users
 * @Description  TODO
 * @author  bollen bollen@live.cn
 * @date  2015年2月2日  上午11:57:03
 *
 */

@Entity
@Table(name = "USERS")
@DynamicInsert(true)
@DynamicUpdate(true)
public class User implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer userId;
	private String myid;
	private String account;
	private String name;
	private Integer organizeId;
	private String organizeName;
	private Integer dutyId;
	private Integer titleId;
	private String password;
	private String email;
	private String lang;
	private String theme;
	private Date firstVisit;
	private Date previousVisit;
	private Date lastVisit;
	private Integer loginCount;
	private Integer isEmployee;
	private String status;
	private String ip;
	private String description;
	private Integer questionId;
	private String answer;
	private Integer isOnline;
	private Date created;
	private Date lastmod;
	private Integer creater;
	private Integer modifier;
	private String tel;
	private Set<UserRole> userRoles = new HashSet<UserRole>(0);
	
	/**
	 * default constructor
	 */
	public User() {
	}
	
	/**
	 * full constructor
	 */
	public User(String myid, String account, String name,
			Integer organizeId, String organizeName, Integer dutyId,
			Integer titleId, String password, String email, String lang,
			String theme, Date firstVisit, Date previousVisit, Date lastVisit,
			Integer loginCount, Integer isEmployee, String status, String ip,
			String description, Integer questionId, String answer,
			Integer isOnline, Date created, Date lastmod, Integer creater,
			Integer modifier, String tel, Set<UserRole> userRoles) {
		this.myid = myid;
		this.account = account;
		this.name = name;
		this.organizeId = organizeId;
		this.organizeName = organizeName;
		this.dutyId = dutyId;
		this.titleId = titleId;
		this.password = password;
		this.email = email;
		this.lang = lang;
		this.theme = theme;
		this.firstVisit = firstVisit;
		this.previousVisit = previousVisit;
		this.lastVisit = lastVisit;
		this.loginCount = loginCount;
		this.isEmployee = isEmployee;
		this.status = status;
		this.ip = ip;
		this.description = description;
		this.questionId = questionId;
		this.answer = answer;
		this.isOnline = isOnline;
		this.created = created;
		this.lastmod = lastmod;
		this.creater = creater;
		this.modifier = modifier;
		this.tel = tel;
		this.userRoles = userRoles;
	}
	
	@Id
	@GeneratedValue
	@Column(name="USER_ID", unique = true, nullable = false)
	public Integer getUserId() {
		return userId;
	}
	
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	@Column(name = "MYID", length = 50)
	public String getMyid() {
		return myid;
	}
	
	public void setMyid(String myid) {
		this.myid = myid;
	}
	
	@Column(name = "ACCOUNT", length = 50)
	public String getAccount() {
		return account;
	}
	
	public void setAccount(String account) {
		this.account = account;
	}
	
	@Column(name = "NAME", length = 50)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "ORGANIZE_ID")
	public Integer getOrganizeId() {
		return organizeId;
	}
	
	public void setOrganizeId(Integer organizeId) {
		this.organizeId = organizeId;
	}
	
	@Column(name = "ORGANIZE_NAME", length = 50)
	public String getOrganizeName() {
		return organizeName;
	}
	
	public void setOrganizeName(String organizeName) {
		this.organizeName = organizeName;
	}
	
	@Column(name = "DUTY_ID")
	public Integer getDutyId() {
		return dutyId;
	}
	
	public void setDutyId(Integer dutyId) {
		this.dutyId = dutyId;
	}
	
	@Column(name = "TITLE_ID")
	public Integer getTitleId() {
		return titleId;
	}
	
	public void setTitleId(Integer titleId) {
		this.titleId = titleId;
	}
	
	@Column(name = "PASSWORD", length = 128)
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Column(name = "EMAIL", length = 200)
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Column(name = "LANG", length = 50)
	public String getLang() {
		return lang;
	}
	
	public void setLang(String lang) {
		this.lang = lang;
	}
	
	@Column(name = "THEME", length = 50)
	public String getTheme() {
		return theme;
	}
	
	public void setTheme(String theme) {
		this.theme = theme;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FIRST_VISIT", length = 10)
	public Date getFirstVisit() {
		return firstVisit;
	}
	
	public void setFirstVisit(Date firstVisit) {
		this.firstVisit = firstVisit;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "PREVIOUS_VISIT", length = 10)
	public Date getPreviousVisit() {
		return previousVisit;
	}
	
	public void setPreviousVisit(Date previousVisit) {
		this.previousVisit = previousVisit;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LAST_VISIT", length = 10)
	public Date getLastVisit() {
		return lastVisit;
	}
	
	public void setLastVisit(Date lastVisit) {
		this.lastVisit = lastVisit;
	}
	
	@Column(name = "LOGIN_COUNT")
	public Integer getLoginCount() {
		return loginCount;
	}
	
	public void setLoginCount(Integer loginCount) {
		this.loginCount = loginCount;
	}
	
	@Column(name = "ISEMPLOYEE")
	public Integer getIsEmployee() {
		return isEmployee;
	}
	
	public void setIsEmployee(Integer isEmployee) {
		this.isEmployee = isEmployee;
	}
	
	@Column(name = "STATUS", length = 1)
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Column(name = "IP", length = 20)
	public String getIp() {
		return ip;
	}
	
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	@Column(name = "DESCRIPTION", length = 2000)
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Column(name = "QUESTION_ID")
	public Integer getQuestionId() {
		return questionId;
	}
	
	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}
	
	@Column(name = "ANSWER", length = 100)
	public String getAnswer() {
		return answer;
	}
	
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	@Column(name = "ISONLINE")
	public Integer getIsOnline() {
		return isOnline;
	}
	
	public void setIsOnline(Integer isOnline) {
		this.isOnline = isOnline;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED", length = 10)
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
	
	@Column(name = "TEL", length = 30)
	public String getTel() {
		return tel;
	}
	
	public void setTel(String tel) {
		this.tel = tel;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
	public Set<UserRole> getUserRoles() {
		return userRoles;
	}
	
	public void setUserRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}
		
}
