package com.itheima.domain;

public class Visit {

	private Long visit_id;
	private String visit_time;
	private String visit_addr;
	private String visit_detail;
	private String visit_nexttime;
	
	//多对一
	//特殊外键字段   表示拜访的客户是谁
	private Customer customer;
	
	//特殊外键字段   表示谁去拜访的客户
	private User user;
	
	//特殊外键字段 表示用户去拜访的客户下面的员工是谁
	private Linkman linkman;

	public Long getVisit_id() {
		return visit_id;
	}

	public void setVisit_id(Long visit_id) {
		this.visit_id = visit_id;
	}

	public String getVisit_time() {
		return visit_time;
	}

	public void setVisit_time(String visit_time) {
		this.visit_time = visit_time;
	}

	public String getVisit_addr() {
		return visit_addr;
	}

	public void setVisit_addr(String visit_addr) {
		this.visit_addr = visit_addr;
	}

	public String getVisit_detail() {
		return visit_detail;
	}

	public void setVisit_detail(String visit_detail) {
		this.visit_detail = visit_detail;
	}

	public String getVisit_nexttime() {
		return visit_nexttime;
	}

	public void setVisit_nexttime(String visit_nexttime) {
		this.visit_nexttime = visit_nexttime;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Linkman getLinkman() {
		return linkman;
	}

	public void setLinkman(Linkman linkman) {
		this.linkman = linkman;
	}
	
	public String toString() {
		return "Visit [visit_id=" + visit_id + ", visit_time=" + visit_time +
				", visit_addr=" + visit_addr + ", visit_detail=" + visit_detail + ", visit_nexttime=" + visit_nexttime
				+ ", customer=" + customer + ", user=" + user + ", linkman=" + linkman + "]";
	}

}
