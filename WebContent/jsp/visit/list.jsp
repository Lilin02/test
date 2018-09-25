<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<TITLE>客户拜访列表</TITLE> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<LINK href="${pageContext.request.contextPath }/css/Style.css" type=text/css rel=stylesheet>
<LINK href="${pageContext.request.contextPath }/css/Manage.css" type=text/css
	rel=stylesheet>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.4.4.min.js"></script>
<SCRIPT language=javascript>
	function to_page(page){
		if(page){
			$("#page").val(page);
		}
		document.customerForm.submit();
		
	}
	
	$(function(){
		// 发送异步的请求，获取到所有的客户
		var url = "${pageContext.request.contextPath}/customer_findAll.action";
		$.post(url,function(data){
			$(data).each(function(i,n){
				var id = "${v.customer.cust_id}";
				if(id == n.cust_id){
					$("#custId").append("<option value='"+this.cust_id+"' selected>"+this.cust_name+"</option>");
				}else{
					$("#custId").append("<option value='"+this.cust_id+"'>"+this.cust_name+"</option>");
				}
				
			});
		},"json");
		
		var url = "${pageContext.request.contextPath}/user_findAll.action";
		$.post(url,function(data){
			$(data).each(function(i,n){
				var id = "${v.user.user_id}";
				if(id == n.user_id){
					$("#userId").append("<option value='"+this.user_id+"' selected>"+this.user_name+"</option>");
				}else{
					$("#userId").append("<option value='"+this.user_id+"'>"+this.user_name+"</option>");
				}
				
			});
		},"json");
	});
</SCRIPT>

<META content="MSHTML 6.00.2900.3492" name=GENERATOR>
</HEAD>
<BODY>
	<FORM id="customerForm" name="customerForm"
		action="${pageContext.request.contextPath }/visit_findByPage.action"
		method=post>
		
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
					<TD width=15 background="${pageContext.request.contextPath }/images/new_022.jpg"><IMG
						src="${pageContext.request.contextPath }/images/new_022.jpg" border=0></TD>
					<TD vAlign=top width="100%" bgColor=#ffffff>
						<TABLE cellSpacing=0 cellPadding=5 width="100%" border=0>
							<TR>
								<TD class=manageHead>当前位置：客户拜访管理 &gt; 客户拜访列表</TD>
							</TR>
							<TR>
								<TD height=2></TD>
							</TR>
						</TABLE>
						<TABLE borderColor=#cccccc cellSpacing=0 cellPadding=0
							width="100%" align=center border=0>
							<TBODY>
								<TR>
									<TD height=25>
										<TABLE cellSpacing=0 cellPadding=2 border=0>
											<TBODY>
												<TR>
													<TD>客户名称：</TD>
													<TD><select name="customer.cust_id" id="custId">
															<option value="">--请选择--</option>
														</select>
													</TD>
													<TD>业务员：</TD>
													<TD>
														<select name="user.user_id" id="userId">
															<option value="">--请选择--</option>
														</select>
													</TD>
													
													<TD><INPUT class=button id=sButton2 type=submit
														value=" 筛选 " name=sButton2></TD>
												</TR>
											</TBODY>
										</TABLE>
									</TD>
								</TR>
							    
								<TR>
									<TD>
										<TABLE id=grid
											style="BORDER-TOP-WIDTH: 0px; FONT-WEIGHT: normal; BORDER-LEFT-WIDTH: 0px; BORDER-LEFT-COLOR: #cccccc; BORDER-BOTTOM-WIDTH: 0px; BORDER-BOTTOM-COLOR: #cccccc; WIDTH: 100%; BORDER-TOP-COLOR: #cccccc; FONT-STYLE: normal; BACKGROUND-COLOR: #cccccc; BORDER-RIGHT-WIDTH: 0px; TEXT-DECORATION: none; BORDER-RIGHT-COLOR: #cccccc"
											cellSpacing=1 cellPadding=2 rules=all border=0>
											<TBODY>
												<TR
													style="FONT-WEIGHT: bold; FONT-STYLE: normal; BACKGROUND-COLOR: #eeeeee; TEXT-DECORATION: none">
													<TD>客户名称</TD>
													<TD>业务员名称</TD>
													<TD>联系人名称</TD>
													<TD>拜访时间</TD>
													<TD>拜访地方</TD>
													<TD>下次拜访时间</TD>
													<TD>拜访详情</TD>
													<TD>操作</TD>
												</TR>
												<c:forEach items="${pagevisit.beanList}" var="visit">
												<TR
													style="FONT-WEIGHT: normal; FONT-STYLE: normal; BACKGROUND-COLOR: white; TEXT-DECORATION: none">
													<TD>${visit.customer.cust_name }</TD>
													<TD>${visit.user.user_name }</TD>
													<TD>${visit.linkman.lkm_name }</TD>
													<TD>${visit.visit_time }</TD>
													<TD>${visit.visit_addr }</TD>
													<TD>${visit.visit_nexttime }</TD>
													<TD>${visit.visit_detail }</TD>
													
													<TD>
													<a href="${pageContext.request.contextPath }/visit_initUpdate.action?visit_id=${visit.visit_id}">修改</a>
													&nbsp;&nbsp;
													<a href="${pageContext.request.contextPath }/visit_delete.action?visit_id=${visit.visit_id}" onclick="return window.confirm('确定删除吗？')">删除</a>
													</TD>
												</TR>
												
												</c:forEach>

											</TBODY>
										</TABLE>
									</TD>
								</TR>
								
								<TR>
									<TD><SPAN id=pagelink>
											<DIV
												style="LINE-HEIGHT: 20px; HEIGHT: 20px; TEXT-ALIGN: right">
												共[<B>${pagevisit.totalCount}</B>]条记录,共[<B>${pagevisit.totalPage}</B>]页
												,每页显示
												<select name="pageSize">
												
												<option value="4" <c:if test="${pagevisit.pageSize==4 }">selected</c:if>>4</option>
												<option value="5" <c:if test="${pagevisit.pageSize==5 }">selected</c:if>>5</option>
												</select>
												条
												<c:if test="${pagevisit.pageCode > 1 }">
													[<A href="javascript:to_page(${pagevisit.pageCode-1})">前一页</A>]
												</c:if>
												
												<B>${pagevisit.pageCode}</B>
												
												<c:if test="${pagevisit.pageCode < pagevisit.totalPage }">
													[<A href="javascript:to_page(${pagevisit.pageCode+1})">后一页</A>] 
												</c:if>
												
												到
												<input type="text" size="3" id="page" name="pageCode" />
												页
												
												<input type="button" value="Go" onclick="to_page()"/>
											</DIV>
									</SPAN></TD>
								</TR>
							</TBODY>
						</TABLE>
					</TD>
					<TD width=15 background="${pageContext.request.contextPath }/images/new_023.jpg"><IMG
						src="${pageContext.request.contextPath }/images/new_023.jpg" border=0></TD>
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
