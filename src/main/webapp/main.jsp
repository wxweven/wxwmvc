<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<script type="text/javascript">
<!--
function reloadVerifyCode(){
	document.getElementById('verifyCodeImage').setAttribute('src', '${pageContext.request.contextPath}/mydemo/getVerifyCodeImage');
}
//-->
</script>

普通用户可访问
<a href="${pageContext.request.contextPath}/user/getUserInfo" target="_blank"> 用户信息页面 </a>
<br/>
<br/>
管理员可访问
<a href="${pageContext.request.contextPath}/user/listUser.jsp" target="_blank">用户列表页面</a>
<br/>
<br/>
<a href="<${pageContext.request.contextPath}/user/logout" target="_blank">Logout</a>


</body>
</html>