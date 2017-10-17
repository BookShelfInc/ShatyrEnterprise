<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ShatyrEnterprise</title>
<style>
	.row {
		display:flex;
		width: 100%;
		justify-content: center;
	}
</style>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/css/materialize.min.css">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/js/materialize.min.js"></script>
<script type="text/javascript" src="js/materialize.min.js"></script>
<script>
  $(document).ready(function() {
    $('select').material_select();
  });
</script>
</head>
<body>
	<%@include file="header.jsp" %>
	
	<c:if test="${authUser != null}">
		<a href="my_post" class="left" style="padding: 15px;">My Posts</a>
		<a href="signout/" class="right" style="padding: 15px;">Sign-Out</a>
		<br><br><brs><h3>Hello <c:out value="${authUser.getEmail()}"/></h3>
	</c:if>

	<c:if test="${authUser == null}">
		<a href="login/" class="right" style="padding: 15px;">Sign-In</a>
		<a href="signup/" class="right" style="padding: 15px;">Sign-Up</a>
		<h3>Hello</h3>
	</c:if>

	<br>
	<br>
	
	<div class="container">
		<h3>Filter:</h3>
		<form action="${pageContext.request.contextPath}/" method="POST">
		 <div class="input-field col s12">
		    <label> Area </label>
	    		<input type="number" name="area"/>
  		</div>
	    <br>
		
		<div class="input-field col s12">
		    <label> Price</label>
	    		<input type="number" name="price"/>
  		</div>
	    <br>
	    
	    <div class="input-field col s12">
		    <label> Number of rooms </label>
	    		<input type="number" name="num_rooms"/>
  		</div>    
	    <br>
	    
  		<div class="input-field col s12">
		    <select name="Points" size="1" id="Points">
			    <option value="" disabled selected>Choose your option</option>
				<c:forEach items="${houseTypes}" var="a" varStatus="cnt">
					<option value='<c:out value="${a}"/>'><c:out value="${a}"/></option>
				</c:forEach>
			</select>
			<label>House type</label>
  		</div>
  		
		<br>
		<div class="input-field col s12">
		    <label> Floor</label>
	    		<input type="number" name="floor"/>
  		</div>
	    <br>
		
		    <input type="submit" name="pageName" value="Filter"/>
  		</div>
	</form>			
	</div>


	<br>
	
	<div class="container">
	<h3>Order by:</h3>
		<form action="${pageContext.request.contextPath}/" method="POST">
			<select name="Points" size="1" id="Points">
				<c:forEach items="${ordersList}" var="a" varStatus="cnt">
					<option value='<c:out value="${a}"/>'><c:out value="${a}"/></option>
				</c:forEach>
			</select>
	
		    <input type="submit" name="pageName" value="Order"/>
		</form>
	</div>

	<br>
	<br>
	<h1>Posts:</h1>
	
	<div>
		<c:forEach items="${allPosts}" var="a" varStatus="cnt">		
			<div class="row">
	        <div class="col s8 m7">
	          <div class="card">
	            <div class="card-image">
	              <img src="https://s3.us-east-2.amazonaws.com/shatyr-images/${a.getImage_url()}">
	              <span class="card-title"><c:out value="${a.getAddress()}"/></span>
	            </div>
	            <div class="card-content">
	              <p><c:out value="${a.getDescription()}"/></p>
	            </div>
	            <div class="card-action">
	              <a href='post_detail/<c:out value="${a.getId()}"/>'>Go to post</a>
	            </div>
	          </div>
	        </div>
	      </div>	
		<br>
		</c:forEach>
	</div>
	
	<%@include file="footer.jsp" %>
</body>
</html>