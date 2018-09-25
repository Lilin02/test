package com.itheima.domain;

import java.util.HashSet;
import java.util.Set;

import com.alibaba.fastjson.annotation.JSONField;

public class Linkman {

	private long lkm_id;
	private String lkm_name;
	private String lkm_gender;
	private String lkm_phone;
	private String lkm_mobile;
	
	private Customer customer;
	@JSONField(serialize=false)
	private Set<Visit> visits = new HashSet<>();

	public long getLkm_id() {
		return lkm_id;
	}

	public void setLkm_id(long lkm_id) {
		this.lkm_id = lkm_id;
	}

	public String getLkm_name() {
		return lkm_name;
	}

	public void setLkm_name(String lkm_name) {
		this.lkm_name = lkm_name;
	}

	public String getLkm_gender() {
		return lkm_gender;
	}

	public void setLkm_gender(String lkm_gender) {
		this.lkm_gender = lkm_gender;
	}

	public String getLkm_phone() {
		return lkm_phone;
	}

	public void setLkm_phone(String lkm_phone) {
		this.lkm_phone = lkm_phone;
	}

	public String getLkm_mobile() {
		return lkm_mobile;
	}

	public void setLkm_mobile(String lkm_mobile) {
		this.lkm_mobile = lkm_mobile;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	
	public String toString() {
		return "Linkman [lkm_id=" + lkm_id + ", lkm_name=" + lkm_name + ", lkm_gender=" + lkm_gender + ", lkm_phone="
				+ lkm_phone + ", lkm_mobile=" + lkm_mobile + ", customer=" + customer + "]";
	}

	public Set<Visit> getVisits() {
		return visits;
	}

	public void setVisits(Set<Visit> visits) {
		this.visits = visits;
	}
	
}
