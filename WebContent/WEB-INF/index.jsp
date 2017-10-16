<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ShatyrEnterprise</title>
</head>
<body>

<h1>Shatyr Enterprise LLC</h1>

<c:if test="${authUser != null}">
	<h3>Hello <c:out value="${authUser.getEmail()}"/></h3>
	<a href="signout/">Sign-Out</a>
	<a href="create_post">Create Post</a>
</c:if>

<h1><c:out value="${val}"/></h1>

<c:if test="${authUser == null}">
	<h3>Hello</h3>
	<a href="login/">Sign-In</a>
	<a href="signup/">Sign-up</a>
</c:if>

<br>
<br>
<h1>Posts:</h1>
<c:forEach items="${allPosts}" var="a" varStatus="cnt">
	<a href='post_detail/<c:out value="${a.getId()}"/>'><c:out value="${a.getAddress()}"/></a>
	<p><c:out value="${a.getPrice()}"/></p>
	<br>
</c:forEach>

</body>
</html>