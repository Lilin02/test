package com.itheima.domain;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.hql.internal.ast.util.NodeTraverser.VisitationStrategy;

import com.alibaba.fastjson.annotation.JSONField;

public class Customer {
	
	private Long cust_id;			//客户编号(主键)
	private String cust_name;		//客户名称(公司名称)
	
	private Long cust_user_id;		//负责人id
	private Long cust_create_id;	//创建人id
	
	/* 
	 
	private String cust_source;		//客户信息来源
	private String cust_industry;	//客户所属行业
	private String cust_level;		//客户级别
	*/
	
	private String cust_linkman;	//联系人
	private String cust_phone;		//固定电话
	private String cust_mobile;		//移动电话
	private String cust_addr;		//联系地址
	
	// 描述的是   一客户的来源，多是客户
	private Dict source;
	
	// 一客户的行业，多是客户
	private Dict industry;
	
	// 一客户的级别，多是客户
	private Dict level;
	
	//上传文件保存的路径
	private String filepath;
	@JSONField(serialize=false)
	private Set<Visit> visits = new HashSet<Visit>();
	
	public Set<Visit> getVisits() {
		return visits;
	}
	public void setVisits(Set<Visit> visits) {
		this.visits = visits;
	}
	@JSONField(serialize=false)
	private Set<Linkman> linkmans = new HashSet<Linkman>();
	
	public Set<Linkman> getLinkmans() {
		return linkmans;
	}
	public void setLinkmans(Set<Linkman> linkmans) {
		this.linkmans = linkmans;
	}
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	public Dict getSource() {
		return source;
	}
	public void setSource(Dict source) {
		this.source = source;
	}
	public Dict getIndustry() {
		return industry;
	}
	public void setIndustry(Dict industry) {
		this.industry = industry;
	}
	public Dict getLevel() {
		return level;
	}
	public void setLevel(Dict level) {
		this.level = level;
	}
	public Long getCust_id() {
		return cust_id;
	}
	public void setCust_id(Long cust_id) {
		this.cust_id = cust_id;
	}
	public String getCust_name() {
		return cust_name;
	}
	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}
	public Long getCust_user_id() {
		return cust_user_id;
	}
	public void setCust_user_id(Long cust_user_id) {
		this.cust_user_id = cust_user_id;
	}
	public Long getCust_create_id() {
		return cust_create_id;
	}
	public void setCust_create_id(Long cust_create_id) {
		this.cust_create_id = cust_create_id;
	}

	public String getCust_linkman() {
		return cust_linkman;
	}
	public void setCust_linkman(String cust_linkman) {
		this.cust_linkman = cust_linkman;
	}
	public String getCust_phone() {
		return cust_phone;
	}
	public void setCust_phone(String cust_phone) {
		this.cust_phone = cust_phone;
	}
	public String getCust_mobile() {
		return cust_mobile;
	}
	public void setCust_mobile(String cust_mobile) {
		this.cust_mobile = cust_mobile;
	}
	public String getCust_addr() {
		return cust_addr;
	}
	public void setCust_addr(String cust_addr) {
		this.cust_addr = cust_addr;
	}	
}
