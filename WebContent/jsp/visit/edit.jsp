<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<TITLE>修改客户拜访</TITLE> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<LINK href="${pageContext.request.contextPath }/css/Style.css" type=text/css rel=stylesheet>
<LINK href="${pageContext.request.contextPath }/css/Manage.css" type=text/css
	rel=stylesheet>
<!-- 引入datapicker插件 -->
<link rel="stylesheet" href="${pageContext.request.contextPath }/jquery/jquery.datepick.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath }/jquery/jquery-1.4.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/jquery/jquery.datepick.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/jquery/jquery.datepick-zh-CN.js"></script>
	
<script type="text/javascript">
$(function(){
	
	// 发送异步的请求，获取到所有的客户
	var url = "${pageContext.request.contextPath}/customer_findAll.action";
	$.post(url,function(data){
		var id = "${visit.customer.cust_id}";
		$(data).each(function(i,n){
			if(id == n.cust_id){
				$("#customerId").append("<option value='"+this.cust_id+"' selected>"+this.cust_name+"</option>");
			}else{
				$("#customerId").append("<option value='"+this.cust_id+"'>"+this.cust_name+"</option>");
			}
			
		});
	},"json");
	
	var url = "${pageContext.request.contextPath}/user_findAll.action";
	$.post(url,function(data){
		var id = "${visit.user.user_id}";
		$(data).each(function(i,n){
			if(id == n.user_id){
				$("#userId").append("<option value='"+this.user_id+"' selected>"+this.user_name+"</option>");
			}else{
				$("#userId").append("<option value='"+this.user_id+"'>"+this.user_name+"</option>");
			}
				
		});
	},"json");
	
	var url = "${pageContext.request.contextPath}/linkman_findByCustid.action";
	var custid = "${visit.customer.cust_id}";
	var params = {"customer.cust_id":custid};
	$.post(url,params,function(data){
		var id = "${visit.linkman.lkm_id}";
		
		$(data).each(function(i,n){
			if(id == n.lkm_id){
				$("#lkmNameId").append("<option value='"+this.lkm_id+"' selected>"+this.lkm_name+"</option>")
			}else{
				$("#lkmNameId").append("<option value='"+this.lkm_id+"'>"+this.lkm_name+"</option>")
			}
			
		});
	},"json");
});

$(function(){
	//使用class属性处理  'yy-mm-dd' 设置格式"yyyy/mm/dd"
	$('#visit_time').datepick({dateFormat: 'yy-mm-dd'}); 
	$('#visit_nexttime').datepick({dateFormat: 'yy-mm-dd'}); 
});

function changeCustomer(customerEle){
	$("#lkmNameId").html("");
	
	var url = "${pageContext.request.contextPath}/linkman_findByCustid.action";
	var params = {"customer.cust_id":customerEle.value};
	$.post(url,params,function(data){
		$(data).each(function(){
			$("#lkmNameId").append("<option value='"+this.lkm_id+"'>"+this.lkm_name+"</option>")
		});
	},"json");
}

</script>


<META content="MSHTML 6.00.2900.3492" name=GENERATOR>
</HEAD>
<BODY>
	<FORM id=form1 name=form1
		action="${pageContext.request.contextPath }/visit_update.action"
		method=post>
		<input type="hidden" name="visit_id" value="${visit.visit_id }"/>

		<TABLE cellSpacing=0 cellPadding=0 width="98%" border=0>
			<TBODY>
				<TR>
					<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_019.jpg"
						border=0></TD>
					<TD width="100%" background="${pageContext.request.contextPath }/images/new_020.jpg"
						 height=20></TD>
					<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_021.jpg"
						border=0></TD>
				</TR>
			</TBODY>
		</TABLE>
		<TABLE cellSpacing=0 cellPadding=0 width="98%" border=0>
			<TBODY>
				<TR>
					<TD width=15 background=${pageContext.request.contextPath }/images/new_022.jpg><IMG
						src="${pageContext.request.contextPath }/images/new_022.jpg" border=0></TD>
					<TD vAlign=top width="100%" bgColor=#ffffff>
						<TABLE cellSpacing=0 cellPadding=5 width="100%" border=0>
							<TR>
								<TD class=manageHead>当前位置：客户拜访管理 &gt; 新增客户拜访</TD>
							</TR>
							<TR>
								<TD height=2></TD>
							</TR>
						</TABLE>
						<TABLE cellSpacing=0 cellPadding=5  border=0>
							
							<TR>
								<td>客户名称：</td>
								<td>
								<select  name="customer.cust_id" id="customerId" onchange="changeCustomer(this)"  style="WIDTH: 182px;height:20px">
									<option  value="0">请选择客户</option>
								</select>
								</td>
								<td>联系人名称：</td>
								<td>
								<select  name="linkman.lkm_id" id="lkmNameId" style="WIDTH: 182px;height:20px"></select>
								</td>
								<td>业务员：</td>
								<td>
								<select  name="user.user_id" id="userId" style="WIDTH: 182px;height:20px"></select>
								</td>
							</TR>
							<TR>
								<td>拜访时间 ：</td>
								<td>
								<INPUT class=textbox id="visit_time"
														style="WIDTH: 180px" maxLength=50 name="visit_time" value="${visit.visit_time }">
								</td>
								<td>拜访地点 ：</td>
								<td>
								<INPUT class=textbox id=sChannel2
														style="WIDTH: 180px" maxLength=50 name="visit_addr" value="${visit.visit_addr }">
								</td>
								<td>下次拜访时间  ：</td>
								<td>
								<INPUT class=textbox id="visit_nexttime"
														style="WIDTH: 180px" maxLength=50 name="visit_nexttime" value="${visit.visit_nexttime }">
								</td>
							</TR>
							<TR>
								<td>拜访详情 ：</td>
								<td>
								<textarea rows="5" cols="25"  name="visit_detail" >${visit.visit_detail }</textarea>
								</td>
							
							</TR>
							<tr>
								<td rowspan=2>
								<INPUT class=button id=sButton2 type=submit
														value="保存 " name=sButton2>
								</td>
							</tr>
						</TABLE>
						
						
					</TD>
					<TD width=15 background="${pageContext.request.contextPath }/images/new_023.jpg">
					<IMG src="${pageContext.request.contextPath }/images/new_023.jpg" border=0></TD>
				</TR>
			</TBODY>
		</TABLE>
		<TABLE cellSpacing=0 cellPadding=0 width="98%" border=0>
			<TBODY>
				<TR>
					<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_024.jpg"
						border=0></TD>
					<TD align=middle width="100%"
						background="${pageContext.request.contextPath }/images/new_025.jpg" height=15></TD>
					<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_026.jpg"
						border=0></TD>
				</TR>
			</TBODY>
		</TABLE>
	</FORM>
</BODY>
</HTML>
