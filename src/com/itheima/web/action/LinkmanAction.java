package com.itheima.web.action;


import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.itheima.domain.Customer;
import com.itheima.domain.Linkman;
import com.itheima.domain.PageBean;
import com.itheima.domain.User;
import com.itheima.service.LinkmanService;
import com.itheima.utils.FastJsonUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.ValueStack;


public class LinkmanAction extends ActionSupport implements ModelDriven<Linkman>{

	private static final long serialVersionUID = 1626345516401516168L;
	
	private Linkman linkman = new Linkman();
	@Override
	public Linkman getModel() {
		// TODO 自动生成的方法存根
		return linkman;
	}
	private LinkmanService linkmanservice;
	public void setLinkmanservice(LinkmanService linkmanservice) {
		this.linkmanservice = linkmanservice;
	}
		
	public String initAddUI(){
		return "add";
	}
	/*
	 * 保存联系人
	 */
	public String savelinkman(){	
		System.out.println(linkman);
		linkmanservice.save(linkman);
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
		DetachedCriteria criteria = DetachedCriteria.forClass(Linkman.class);
		
		String link_name = linkman.getLkm_name();
		if(link_name != null && !link_name.trim().isEmpty()){
			criteria.add(Restrictions.like("lkm_name", "%"+link_name+"%"));
		}
		// 获取客户
		Customer c = linkman.getCustomer();
		if(c != null && c.getCust_id() != null){
			// 拼接查询的条件
			criteria.add(Restrictions.eq("customer.cust_id", c.getCust_id()));
		}
		PageBean<Linkman> page = linkmanservice.findByPage(pageCode,pageSize,criteria);
		
		//压栈
		ValueStack vs = ActionContext.getContext().getValueStack();
		vs.set("page1", page);
		//从页面模型驱动进来的值，必须手动压栈。如果直接使用mode1无法回显在页面上
		vs.set("lk", linkman);
		return "page";
	}
	
	public String delete(){
		linkmanservice.delete(linkman);
		return "delete";
	}
	
	public String initUpdate() {
		//默认压栈???不自己压栈不能回显
		System.out.println(linkman.getLkm_id());
		linkman = linkmanservice.findById(linkman.getLkm_id());
		
		//压栈
		ValueStack vs = ActionContext.getContext().getValueStack();
		vs.set("linkman", linkman);
		
		System.out.println(linkman);
		return "initUpdate";
	}
	
	public String update(){
		linkmanservice.update(linkman);
		return "update";
	}
	
	
	public String findByCustid(){
		System.out.println(linkman.getCustomer().getCust_id());
		List<Linkman> list = linkmanservice.findByCustid(linkman.getCustomer().getCust_id());
		// 转换成json
		String jsonString = FastJsonUtil.toJSONString(list);
		HttpServletResponse response = ServletActionContext.getResponse();
		FastJsonUtil.write_json(response, jsonString);
		
		return null;
	}
}
