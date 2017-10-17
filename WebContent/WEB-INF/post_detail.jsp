<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${post.getAddress()}</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/css/materialize.min.css">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/js/materialize.min.js"></script>
<script type="text/javascript" src="js/materialize.min.js"></script>
</head>
<body>
	<%@include file="header.jsp" %>

	<h1>${post.getAddress()}</h1>
	<p>${post.getArea()}</p>
	<p>${post.getHouse_type()}</p>
	<p>${post.getNum_rooms()}</p>
	<p>${post.getPrice()}</p>
	<p>${post.getDescription()}</p>
	<p>${post.getPhone()}</p>
	<p>${post.getImage_url()}</p>
	
	<c:if test = "${post.getImage_url() != null}">
		<img src="https://s3.us-east-2.amazonaws.com/shatyr-images/${post.getImage_url()}" 
				alt="houseImage" style="width:304px;height:228px;">
	</c:if>
	
	<%@include file="footer.jsp" %>
</body>
</html>