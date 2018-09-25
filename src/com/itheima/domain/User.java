package com.itheima.domain;

import java.util.HashSet;
import java.util.Set;

import com.alibaba.fastjson.annotation.JSONField;

public class User {

	/*
	   
	  struct2中action类中方法的返回值
	  setcontentType和setcharacterencoding的区别:都是用于response的编码设置，setcontentType自带设置文档内容的类型
	      注册模块：1.使用ajax判断是否存在相同的登录名；2.加密密码；3.通过判断是否ajax判断中是否存在错误，来确定，注册按钮点击是否有效。
	      客户列表：分页查询，多条件查询，list.jsp页面中使用ajax请求，在页面一加载成功就加载所有请求,通过下拉框表示
	     添加客户：在普通添加之上加一个功能(文件上传)
	      
	      数据字典表 :方便扩展维护
	     延迟加载的问题：在web.xml 中配置
	  structs2的文件上传
	      异步加载，多条件的查询，并且做到数据回显
	    fastJson:1.只加载有值得属性（如果是对象，json转换成字符串，如果是集合，json转换成数组）
	    		 2.默认情况下，fastJson禁止循环引用  ALI出品	
	    		 	循环引用：集合重复添加同一个对象，list.add(对象1),list.add(对象1)，第二个调用第一个的引用
	    		  	死循环：加注解：   @JsonField(serialize=false) 对指定的属性不转换成Json
	    文件上传：表单form标签需要添加一个属性(enctype="multipart/form-data")	，name属性必须要写
	    	文件上传有大小限制(默认是 2M)：浏览器提示404(拦截器发现错误，发送错误信息到action类中，返回input，而Struts.xml文件中没有配置)，后台提示500.
	    	解决方案：在Struts.xml中配置新的大小
	    	设置上传文件的类型(Struts.xml中配置)
	    
	    网页(.jsp)，页面加载初始化函数中$(function(){})中的函数顺序影响到网页中不同下拉框的排序，上下顺序要一致，不然会出现值相同，代码实现(add.jsp)
	  
	  
	  `user_id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '用户id',
	  `user_code` varchar(32) NOT NULL COMMENT '用户账号',
	  `user_name` varchar(64) NOT NULL COMMENT '用户名称',
	  `user_password` varchar(32) NOT NULL COMMENT '用户密码',
	  `user_state` char(1) NOT NULL COMMENT '1:正常,0:暂停',
	 */
	
	private Long user_id;			//主键，用户id
	private String user_code;		//登录账号
	private String user_name;		//用户名称
	private String user_password;	//密码（保存的时候，需要加密处理）
	private String user_state;		//用户的状态	1:正常,0:暂停
	@JSONField(serialize=false)
	private Set<Visit> visits = new HashSet<>();
	
	public Set<Visit> getVisits() {
		return visits;
	}
	public void setVisits(Set<Visit> visits) {
		this.visits = visits;
	}
	public Long getUser_id() {
		return user_id;
	}
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	public String getUser_code() {
		return user_code;
	}
	public void setUser_code(String user_code) {
		this.user_code = user_code;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_password() {
		return user_password;
	}
	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}
	public String getUser_state() {
		return user_state;
	}
	public void setUser_state(String user_state) {
		this.user_state = user_state;
	}
	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", user_code=" + user_code + ", user_name=" + user_name + ", user_password="
				+ user_password + ", user_state=" + user_state + "]";
	}
	
}
