<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Frameset//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/frameset.dtd">
<HTML xmlns="http://www.w3.org/1999/xhtml">
<HEAD>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<STYLE type=text/css>
BODY {
	FONT-SIZE: 12px;
	COLOR: #ffffff;
	FONT-FAMILY: 宋体
}

TD {
	FONT-SIZE: 12px;
	COLOR: #ffffff;
	FONT-FAMILY: 宋体
}

.error {
	color: red;
}
</STYLE>
<META content="MSHTML 6.00.6000.16809" name=GENERATOR>

<!-- 引入JQ -->
<script type="text/javascript" src="${ pageContext.request.contextPath }/js/jquery-1.11.3.min.js"></script>

<script type="text/javascript">
	//校验登录名
	function checkCode(){
		//获取用户输入的登录名
		var code = $("#user_code").val();
		//alert(code);
		//进行判断,说明没有输入登录名
		if(code.trim() == ""){
			
			//动态的加载 class 属性
			$("#codeId").addClass("error");
			//给出提示
			$("#codeId").html("登录名不能为空");
		}else{
			//若登录名不为空，ajax请求，验证
			//编写请求路径
			var url="${pageContext.request.contextPath}/user_checkCode.action";
			//编写请求参数
			var parameter={"user_code":code};
			$.post(url,parameter,function(data){
				//操作data，进行判断
				if( data=="no"){
					$("#codeId").addClass("error");
					$("#codeId").html("该用户已存在");
				}else{
					$("#codeId").removeClass("error");
					$("#codeId").html("可以注册");
				}
			})
		}
	}
	
	function checkForm(){
		//先让校验名称的方法线执行以下
		checkCode();
		
		var password = $("#user_password").val();
		if(password.trim() == ""){
			
			//动态的加载 class 属性
			$("#password").addClass("error");
			//给出提示
			$("#password").html("登录密码不能为空");
		}else{
			$("#password").removeClass("error");
			$("#password").html("");
		}
	
		var name = $("#user_name").val();
		//alert(code);
		//进行判断,说明没有输入用户名
		if(name.trim() == ""){
			
			//动态的加载 class 属性
			$("#username").addClass("error");
			//给出提示
			$("#username").html("用户姓名不能为空");
		}else{
			$("#username").removeClass("error");
			$("#username").html("");
		}
		alert($(".error").size());
		//获取error的数量，如果数量  > 0,说明存在错误，不能提交表单
		if($(".error").size() > 0){
			return false;
		}
	}
</script>

</HEAD>

<BODY>

	<FORM id=form1 name=form1
		action="${ pageContext.request.contextPath }/user_regist.action"
		onsubmit="return checkForm()" method=post>

		<DIV id=UpdatePanel1>
			<DIV id=div1
				style="LEFT: 0px; POSITION: absolute; TOP: 0px; BACKGROUND-COLOR: #0066ff"></DIV>
			<DIV id=div2
				style="LEFT: 0px; POSITION: absolute; TOP: 0px; BACKGROUND-COLOR: #0066ff"></DIV>


			<DIV>&nbsp;&nbsp;</DIV>
			<DIV>
				<TABLE cellSpacing=0 cellPadding=0 width=900 align=center border=0>
					<TBODY>
						<TR>
							<TD style="HEIGHT: 105px"><IMG src="images/login_1.gif"
								border=0></TD>
						</TR>
						<TR>
							<TD background=images/timg2.jpg height=300>
								<TABLE height=300 cellPadding=0 width=900 border=0>
									<TBODY>
										<TR>
											<TD colSpan=2 height=50></TD>
										</TR>
										<TR>
											<TD width=360></TD>
											<TD>
												<TABLE cellSpacing=0 cellPadding=2 border=0>
													<TBODY>
														<TR>
															<TD style="HEIGHT: 28px" width=80>登 录 名：</TD>

															<TD style="HEIGHT: 28px" width=150><INPUT
																id="user_code" style="WIDTH: 130px" name="user_code"
																onblur="checkCode()"></TD>
															<TD style="HEIGHT: 28px" width=370><SPAN id="codeId"
																style="FONT-WEIGHT: bold;"></SPAN></TD>
														</TR>

														<TR>
															<TD style="HEIGHT: 28px">登录密码：</TD>
															<TD style="HEIGHT: 28px"><INPUT id="user_password"
																style="WIDTH: 130px" type=password name="user_password"
																onblur="checkCode()"></TD>
															<TD style="HEIGHT: 28px" width=370><SPAN id="password"
																style="FONT-WEIGHT: bold;"></SPAN></TD>
														</TR>

														<TR>
															<TD style="HEIGHT: 28px">用户姓名：</TD>
															<TD style="HEIGHT: 28px"><INPUT id="user_name"
																style="WIDTH: 130px" type="text" name="user_name"
																onblur="checkCode()"></TD>
															<TD style="HEIGHT: 28px" width=370><SPAN id="username"
																style="FONT-WEIGHT: bold;"></SPAN></TD>
														</TR>

														<TR>
															<TD style="HEIGHT: 18px"></TD>
															<TD style="HEIGHT: 18px"></TD>
															<TD style="HEIGHT: 18px"></TD>
														</TR>
														<TR>
															<TD></TD>
															<TD><INPUT id=btn 
                  												style="BORDER-TOP-WIDTH: 0px; BORDER-LEFT-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; BORDER-RIGHT-WIDTH: 0px" 
                  												type=image src="images/register_button.jpg" name=btn> </TD>
														</TR>
													</TBODY>
												</TABLE>
											</TD>
										</TR>
									</TBODY>
								</TABLE>
							</TD>
						</TR>
						<TR>
							<TD><IMG src="images/login_3.jpg" border=0></TD>
						</TR>
					</TBODY>
				</TABLE>
			</DIV>
		</DIV>


	</FORM>
</BODY>
</HTML>


</body>
</html>