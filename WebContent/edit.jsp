<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
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
</STYLE>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.11.3.min.js"></script>

<script>
	
	function check(){
		//提交表单时 需要进行一定的校验    如果不一致 不让提交表单
		//校验旧密码是否跟 session中密码一致
		//获取用户输入的旧密码
		var oldPassword = $("#oldPassword").val();
		var sessionPwd = "${existUser.user_password}";
		//alert("1:"+oldPassword);
		if (oldPassword.trim() == "") {
			$("#oldPassword").addClass("error");
		}
		if (oldPassword.trim() != "") {
			//将旧密码加密
			//编写请求路径
			var url = "${pageContext.request.contextPath}/user_MD5Password.action";
			//编写请求参数
			var parameter = {"oldPassword" : oldPassword};
 			$.post(url, parameter, function(date) {
 				if (date != sessionPwd) {
					
					alert("旧密码输入错误");
					$("#oldPassword").addClass("error");
				}
			});		
		} else {
			$("#oldPassword").removeClass("error");
		}			
	}
	
	function checkForm(){
		
		//判断新密码不能为null  两次新密码必须一致
		var password = $("#password").val();//新密码
		if (password.length == 0) { //密码的长度一定要大于0 
			alert("新密码不可以为空");
			$("#Password").addClass("error");
		}else{
			$("#Password").removeClass("error");
		}
		//确认密码
		var rePassword = $("#rePassword").val();//新密码
		if (password != rePassword) {
			alert("两次新密码必须一致");
			$("#rePassword").addClass("error");
		}else{
			$("#rePassword").removeClass("error");
		}
		//获取error的数量，如果数量  > 0,说明存在错误，不能提交表单
		if($(".error").size() > 0){
			return false;
		}
	}
</script>


<META content="MSHTML 6.00.6000.16809" name=GENERATOR></HEAD>
<BODY>
<FORM id=form1 name=form1 action="${pageContext.request.contextPath}/user_update.action" onsubmit="return checkForm()" method=post>

<input type="hidden" name="user_id" value="${existUser.user_id }"/>
<input type="hidden" name="user_state" value="1"/>
		
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
    <TD style="HEIGHT: 105px"><IMG src="images/login_1.gif" border=0></TD>
  </TR>
  <TR>
    <TD background=images/timg3.jpg height=300>
      <TABLE height=300 cellPadding=0 width=900 border=0>
        <TBODY>
        <TR>
          <TD colSpan=2 height=100></TD></TR>
        <TR>
          <TD width=330></TD>
          <TD>
            <TABLE cellSpacing=0 cellPadding=2 border=0>
              <TBODY>
              
              <TR>
                <TD style="HEIGHT: 28px" width=80>用户名称：</TD>
                <TD style="HEIGHT: 28px" width=150>
                	<INPUT id="username" style="WIDTH: 130px" name="user_name" value="${existUser.user_name}" readonly="readonly">
                </TD>
              </TR>
              
              <TR>
                <TD style="HEIGHT: 28px" width=80>登录名称：</TD>
                <TD style="HEIGHT: 28px" width=150>
                	<INPUT id="usercode" style="WIDTH: 130px" name="user_code" value="${existUser.user_code}" readonly="readonly">
                </TD>
              </TR>
              
              <TR>
                <TD style="HEIGHT: 28px" width=80>旧密码：</TD>
                <TD style="HEIGHT: 28px" width=150>
                	<INPUT id="oldPassword"  style="WIDTH: 130px" name="oldPassword" onblur="check()">
                </TD>
              </TR>
         
              <TR>
                <TD style="HEIGHT: 28px">新密码：</TD>
                <TD style="HEIGHT: 28px">
                	<INPUT id="password" style="WIDTH: 130px" type=password name="user_password">
                </TD>
               
              </TR>
              <TR>
                <TD style="HEIGHT: 28px">确认密码：</TD>
                <TD style="HEIGHT: 28px">
                	<INPUT id="rePassword" style="WIDTH: 130px" type=password name="rePassword" >
                </TD>
              </TR>
            
              
               <TR>
                <TD style="HEIGHT: 28px" width=230 colspan="2">
                	<font id="errorMsg" color="red"></font>
                </TD>
              </TR>
              <TR>
                <TD></TD>
                <TD></TD>
                <TD>
                	<INPUT id=btn 
                  style="HEIGHT: 28px; BORDER-TOP-WIDTH: 0px; BORDER-LEFT-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; BORDER-RIGHT-WIDTH: 0px" 
                  type=image src="images/yes.png" name=btn> 
              	</TD>
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
    <TD>
    	<IMG src="images/login_3.jpg" border=0>
    </TD>
   </TR>
  </TBODY>
 </TABLE>
</DIV>
</DIV>

</FORM>
</BODY>
</HTML>

