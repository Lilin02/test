package com.itheima.interceptor;

import org.apache.struts2.ServletActionContext;

import com.itheima.domain.User;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

public class LoginInterceptor  extends MethodFilterInterceptor{

	private static final long serialVersionUID = -7581962614029125646L;

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
		
		if( user != null ){
			//放行
			System.out.println(user.getUser_name());
			return invocation.invoke();
		}
		return "login";
	}

}
