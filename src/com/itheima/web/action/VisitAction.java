package com.itheima.web.action;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.itheima.domain.Customer;
import com.itheima.domain.Linkman;
import com.itheima.domain.PageBean;
import com.itheima.domain.User;
import com.itheima.domain.Visit;
import com.itheima.service.VisitService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.ValueStack;

public class VisitAction extends ActionSupport implements ModelDriven<Visit>{

	private static final long serialVersionUID = 5874133917553447949L;
	
	private VisitService visitService;
	public void setVisitService(VisitService visitService) {
		this.visitService = visitService;
	}

	private Visit visit = new Visit();
	@Override
	public Visit getModel() {
		// TODO 自动生成的方法存根
		return visit;
	}
	
	/**
	 * 初始化到添加页面（add.jsp）
	 * @return
	 */
	public String initAddUI() {
		return "initAddUI";
	}
	
	public String save(){
		visitService.save(visit);
		return "save";
	}
	
	//属性驱动的方式
	//当前页，默认值是1
	private Integer pageCode = 1;
	public void setPageCode(Integer pageCode) {
		if (pageCode == null) {
			pageCode = 1;
		}
		this.pageCode = pageCode;
	}

	//每页显示的数据的条数
	private Integer pageSize = 4;
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	
	public String findByPage(){
		DetachedCriteria criteria = DetachedCriteria.forClass(Visit.class);
		
		Customer c = visit.getCustomer();
		if(c != null && c.getCust_id()!= null){
			criteria.add(Restrictions.eq("customer.cust_id", c.getCust_id()));
		}
		// 获取业务人
		User u = visit.getUser();
		if(u != null && u.getUser_id() != null){
			// 拼接查询的条件
			criteria.add(Restrictions.eq("user.user_id", u.getUser_id()));
		}
		PageBean<Visit> page = visitService.findByPage(pageCode,pageSize,criteria);
		
		//压栈
		ValueStack vs = ActionContext.getContext().getValueStack();
		vs.set("pagevisit", page);
		//从页面模型驱动进来的值，必须手动压栈。如果直接使用mode1无法回显在页面上
		vs.set("v", visit);
		return "page";
	}
	
	
	public String delete(){
		visitService.delete(visit);
		return "delete";
	}
	
	public String initUpdate() {
		//默认压栈???不自己压栈不能回显
		System.out.println(visit.getVisit_id());
		visit = visitService.findById(visit.getVisit_id());
		
		//压栈
		ValueStack vs = ActionContext.getContext().getValueStack();
		vs.set("visit", visit);
		
		return "initUpdate";
	}
	
	public String update(){
		visitService.update(visit);
		return "update";
	}
}
