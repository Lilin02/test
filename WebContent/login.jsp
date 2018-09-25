<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Frameset//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/frameset.dtd">
<HTML xmlns="http://www.w3.org/1999/xhtml">
<HEAD>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<STYLE type=text/css>
BODY {
	FONT-SIZE: 12px; COLOR: #ffffff; FONT-FAMILY: 宋体
}
TD {
	FONT-SIZE: 12px; COLOR: #ffffff; FONT-FAMILY: 宋体
}
a:hover{color: red}
</STYLE>

<META content="MSHTML 6.00.6000.16809" name=GENERATOR>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.11.3.min.js"></script>
<script type="text/javascript">
function change(){
	var img1=document.getElementById("checkImg");
	img1.src="${pageContext.request.contextPath}/user_doImg.action?i="+Math.random(); 
}

//校验验证码
function checky(){
	var yan = $("#checkcode").val();
	//alert("输入的验证码是： "+yan);
	var url = "${pageContext.request.contextPath}/user_checkYan.action";
	var parameter={"identify":yan};
	$.post(url,parameter,function(data){
		if (data == "success"){
			$("#checkcode").removeClass("error");
			//alert("验证码正确！！");
		}else{
			//动态的加载 class 属性
			alert("验证码出错！！");
		}
	});
}

function checkForm(){
	
	checky();
	
	//获取error的数量，如果数量  > 0,说明存在错误，不能提交表单
	if($(".error").size() > 0){
		return false;
	}
}
</script>
</HEAD>
<BODY>
<FORM id=form1 name=form1 action="${pageContext.request.contextPath }/user_login.action" method="post" onsubmit="return checkForm()" target="_parent">

<DIV id=UpdatePanel1>
<DIV id=div1 
style="LEFT: 0px; POSITION: absolute; TOP: 0px; BACKGROUND-COLOR: #0066ff"></DIV>
<DIV id=div2 
style="LEFT: 0px; POSITION: absolute; TOP: 0px; BACKGROUND-COLOR: #0066ff"></DIV>


<DIV>&nbsp;&nbsp; </DIV>
<DIV>
<TABLE cellSpacing=0 cellPadding=0 width=900 align=center border=0>
  <TBODY>
  <TR>
    <TD style="HEIGHT: 105px"><IMG src="images/login_1.gif" 
  border=0></TD></TR>
  <TR>
    <TD background=images/login_2.jpg height=300>
      <TABLE height=300 cellPadding=0 width=900 border=0>
        <TBODY>
        <TR>
          <TD colSpan=2 height=35></TD></TR>
        <TR>
          <TD width=360></TD>
          <TD>
            <TABLE cellSpacing=0 cellPadding=2 border=0>
              <TBODY>
              <TR>
                <TD style="HEIGHT: 28px" width=80>登 录 名：</TD>
                <TD style="HEIGHT: 28px" width=150><INPUT id=txtName style="WIDTH: 130px" name="user_code"></TD>
                <TD style="HEIGHT: 28px" width=370><SPAN 
                  id=RequiredFieldValidator3 
                  style="FONT-WEIGHT: bold; VISIBILITY: hidden; COLOR: white">请输入登录名</SPAN></TD></TR>
              <TR>
                <TD style="HEIGHT: 28px">登录密码：</TD>
                <TD style="HEIGHT: 28px"><INPUT id=txtPwd style="WIDTH: 130px" type=password name="user_password"></TD>
                <TD style="HEIGHT: 28px"><SPAN id=RequiredFieldValidator4 
                  style="FONT-WEIGHT: bold; VISIBILITY: hidden; COLOR: white">请输入密码</SPAN></TD></TR>
              <tr>
				<TR>
					<TD style="HEIGHT: 28px" width=80>验 证 码：</TD>
				
				<td>
					<span class="fieldSet">
						<input type="text" id="checkcode" name="checkcode" class="text captcha error" onblur="checky()" style="WIDTH: 130px"
								maxlength="4" autocomplete="off">
					</span>
				</td>
				<td>
					<span class="fieldSet">
						<img id="checkImg" class="captchaImage" src="${pageContext.request.contextPath}/user_doImg.action" 
								onclick="change()" title="点击更换验证码">
					</span>
				</td></TR>
			</tr>
              
              
              <!--     
              <TR>
                <TD style="HEIGHT: 28px">验证码：</TD>
                <TD style="HEIGHT: 28px"><INPUT id=txtcode 
                  style="WIDTH: 130px" name=txtcode></TD>
                <TD style="HEIGHT: 28px">&nbsp;</TD></TR>
              --> 
              
              <TR>
                <TD style="HEIGHT: 18px"></TD>
                <TD style="HEIGHT: 18px"></TD>
                <TD style="HEIGHT: 18px"></TD></TR>
              <TR>
                <TD></TD>
                <TD><INPUT id=btn 
                  style="BORDER-TOP-WIDTH: 0px; BORDER-LEFT-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; BORDER-RIGHT-WIDTH: 0px" 
                  type=image src="images/login_button.gif" name=btn> 
              	</TD>
              	<td><a href="${pageContext.request.contextPath }/regist.jsp" style="color: white" >注册</a></td>
              </TR></TBODY></TABLE></TD></TR></TBODY></TABLE></TD></TR>
  <TR>
    <TD><IMG src="images/login_3.jpg" 
border=0></TD></TR></TBODY></TABLE></DIV></DIV>


</FORM></BODY></HTML>


</body>
</html>