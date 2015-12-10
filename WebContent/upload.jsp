<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>	<!-- 转码，避免中文乱码 -->
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
</body>
</html>