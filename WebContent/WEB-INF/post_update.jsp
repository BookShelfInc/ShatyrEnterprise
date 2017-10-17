<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>${myPosts.getAddress()}</title>
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
	<br><br><br>
	<div class="container">
		<form action="" method="POST">
			<div class="input-field col s12">
			    <label>Address</label>
    				<input type="text" value='<c:out value="${myPosts.getAddress()}"/>' name="address"/>
	  		</div>
	  		
	  		<div class="input-field col s12">
			    <label>Area</label>
		    		<input type="text"  value='<c:out value="${myPosts.getArea()}"/>' name="area"/>
	  		</div>
	  		
	  		<div class="input-field col s12">
			    <label>Description</label>
		    <input type="text"  value='<c:out value="${myPosts.getDescription()}"/>' name="description"/>
	  		</div>
	  		<br>
	  		
	  		<div class="input-field col s12">
				<label>Floor</label>
		    		<input type="text"  value='<c:out value="${myPosts.getFloor()}"/>' name="floor"/>
	  		</div>
	  		<br>
	  			
	  		
	  		<div class="input-field col s12">
			    <select name="Points" size="1" id="Points" required>
			    		<option value="" disabled selected>Choose your option</option>
					<c:forEach items="${houseTypes}" var="a" varStatus="cnt">
						<c:if test = "${a == myPosts.getHouse_type()}">
		    					<option value='<c:out value="${a}"/>' selected><c:out value="${a}"/></option>
		                </c:if>
		                <c:if test = "${a != myPosts.getHouse_type()}">
		    					<option value='<c:out value="${a}"/>'><c:out value="${a}"/></option>
		                </c:if>
					</c:forEach>
				</select>
				<label>House type</label>
  			</div>
  			
  			<div class="input-field col s12">
		    		<label>Rooms</label>
		   		<input type="text"  value='<c:out value="${myPosts.getNum_rooms()}"/>' name="rooms"/>
  			</div>
  			<br>
  			
  			<div class="input-field col s12">
		    		<label>Phone</label>
		    		<input type="text" value='<c:out value="${myPosts.getPhone()}"/>' name="phone"/>
  			</div>
  			<br>
  			
  			<div class="input-field col s12">
			    <label>Price</label>
		    		<input type="text" value='<c:out value="${myPosts.getPrice()}"/>' name="price"/>
  			</div>
  			<br>
  	
  			<div class="input-field col s12">
		   		<label>Year</label>
		    		<input type="text" value='<c:out value="${myPosts.getYear()}"/>' name="year"/>
  			</div>
  			<br>
  	
  			<div class="input-field col s12">
			    <label>Image</label>
    				<input type="file" name="photo" />
  			</div>
  			<br>
  			
  			<div class="button-group" 
		    style="display:flex;
		    		   justify-content: center;
		    		   padding: 25px;">
			    	<button class="btn waves-effect waves-light" type="submit">Submit
	    				<i class="material-icons right">send</i>
	  			</button>
		    </div>
		    
		    <div class="button-group" 
		    style="display:flex;
		    		   justify-content: center;
		    		   padding: 25px;">
		    <button class="btn waves-effect waves-light" type="submit" href="my_post">Cancel
	    				<i class="material-icons right">cancel</i>
	  			</button>
	  		</div>
  		</form>
	</div>
	
	<a href="my_post">Cancel</a>
	<br><br><br>
	<%@include file="footer.jsp" %>
</body>
</html>