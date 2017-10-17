<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>MY POSTS</title>
</head>
<body>
	<h1>My Posts</h1>
	<br>
	<a href="create_post">Create Post</a>
	<br>
	<c:forEach items="${myPosts}" var="a" varStatus="cnt">
		<a href='post_detail/<c:out value="${a.getId()}"/>'><c:out value="${a.getAddress()}"/></a>
		<p><c:out value="${a.getPrice()}"/></p>
		<p><c:out value="${a.getArea()}"/></p>
		<a href='post_update/<c:out value="${a.getId()}"/>'> Edit </a>
		<br>
	</c:forEach>
	<br>
	<a href="${rootPath}">Back</a>
</body>
</html>