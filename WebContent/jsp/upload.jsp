<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>	<!-- 转码，避免中文乱码 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>文件上传：</h3>
请选择要上传的文件：<br />
<form action="Upload" method="post" enctype="multipart/form-data">
<input type="file" name="file" size="50" />
<br />
<input type="submit" value="上传文件" />

${ result }

<div>
	<c:forEach items="${map}" var = "m">
		${m.key} = ${m.value }
	
	</c:forEach>
</div>
</body>
</html>