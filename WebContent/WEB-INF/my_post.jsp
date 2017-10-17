<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>MY POSTS</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/css/materialize.min.css">
 <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/js/materialize.min.js"></script>
<script type="text/javascript" src="js/materialize.min.js"></script>
</head>
<body>
	<%@include file="header.jsp" %>
	
	<h1>My Posts</h1>
	<br>
	<a class="btn-floating btn-large waves-effect waves-light red" href="create_post"><i class="material-icons">add</i></a>
	<br><br>
	
	<div>
		<c:forEach items="${myPosts}" var="a" varStatus="cnt">		
			<div class="row">
	        <div class="col s8 m7">
	          <div class="card">
	            <div class="card-image">
	              <img src="https://s3.us-east-2.amazonaws.com/shatyr-images/${a.getImage_url()}">
	              <c:out value="${a.getImage_url()}"/>
	              <span class="card-title"><c:out value="${a.getAddress()}"/></span>
	            </div>
	            <div class="card-content">
	              <p><c:out value="${a.getDescription()}"/></p>
	            </div>
	            <div class="card-action">
	              <a href='post_update/<c:out value="${a.getId()}"/>'> Edit </a>
	            </div>
	          </div>
	        </div>
	      </div>	
		<br>
		</c:forEach>
	</div>
	
	<br>
	<a class="btn-floating btn-large waves-effect waves-light red" href="${rootPath}"><i class="material-icons">arrow_back</i></a>
	<%@include file="footer.jsp" %>
</body>
</html>