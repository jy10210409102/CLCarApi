<%@ page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8"%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	 <form action="<%=request.getContextPath() %>/user/addUser" method="post">
		name<input type="text" name="userName"><br />
		password<input type="password" name="passWord" /><br />
		email<input type="email" name="email" /><br />
		<input type="submit" value="submit" /><br />
	</form>
</body>
</html>