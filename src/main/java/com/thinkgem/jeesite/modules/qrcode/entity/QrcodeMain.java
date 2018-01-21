/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.qrcode.entity;

import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 物品数据生成Entity
 * @author Murphy
 * @version 2018-01-21
 */
public class QrcodeMain extends DataEntity<QrcodeMain> {
	
	private static final long serialVersionUID = 1L;
	private User user;		// 归属用户
	private Office office;		// 归属部门
	private Office team;		// 归属班组
	private QrcodeSubject subjectmain;		// 主分类ID
	private QrcodeSubject subjectbranch;		// 子分类ID
	private String name;		// 名称
	private String location;	// 所在地
	private String subjectCode;	// 物品编码
	private Date inDate;		// 加入日期
	
	public QrcodeMain() {
		super();
	}

	public QrcodeMain(String id){
		super(id);
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}
	
	@Length(min=0, max=64, message="归属班组长度必须介于 0 和 64 之间")
	public Office getTeam() {
		return team;
	}

	public void setTeam(Office team) {
		this.team = team;
	}
	
	@Length(min=0, max=64, message="主分类ID长度必须介于 0 和 64 之间")
	public QrcodeSubject getSubjectmain() {
		return subjectmain;
	}

	public void setSubjectmain(QrcodeSubject subjectmain) {
		this.subjectmain = subjectmain;
	}
	
	@Length(min=0, max=64, message="子分类ID长度必须介于 0 和 64 之间")
	public QrcodeSubject getSubjectbranch() {
		return subjectbranch;
	}

	public void setSubjectbranch(QrcodeSubject subjectbranch) {
		this.subjectbranch = subjectbranch;
	}
	
	@Length(min=0, max=100, message="名称长度必须介于 0 和 100 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getInDate() {
		return inDate;
	}

	public void setInDate(Date inDate) {
		this.inDate = inDate;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getSubjectCode() {
		return subjectCode;
	}

	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}
	
}