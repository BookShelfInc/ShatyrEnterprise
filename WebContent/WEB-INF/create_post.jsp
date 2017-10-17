<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Create Post</title>
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

	<div class="container">
		<form action="" method="POST" enctype="multipart/form-data">
			<div class="input-field col s12">
			    <label>Address</label>
	   			<input type="text" name="address" required/>
	  		</div>
	  		
	  		<div class="input-field col s12">
			    <label>Area</label>
	    			<input type="text" name="area" required/>
	  		</div>
	  		
	  		<div class="input-field col s12">
			    <label>Description</label>
		   	    <input type="text" name="description" required/>
	  		</div>
	  		<br>
	  		
	  		<div class="input-field col s12">
				<label>Floor</label>
		    		<input type="text" name="floor" required/>
	  		</div>
	  		<br>
	  		
	  		<div class="input-field col s12">
			    <select name="Points" size="1" id="Points" required>
			    		<option value="" disabled selected>Choose your option</option>
					<c:forEach items="${houseTypes}" var="a" varStatus="cnt">
						<option value='<c:out value="${a}"/>'><c:out value="${a}"/></option>w
					</c:forEach>
				</select>
				<label>House type</label>
  			</div>
  			
  			<div class="input-field col s12">
		    		<label>Rooms</label>
	    			<input type="text" name="rooms" required/>
  			</div>
  			<br>
  			
  			<div class="input-field col s12">
		    		<label>Phone</label>
	    			<input type="text" name="phone" required/>
  			</div>
  			<br>
  			
  			<div class="input-field col s12">
			    <label> Price</label>
		    		<input type="number" name="price"/>
  			</div>
  			<br>
  	
  			<div class="input-field col s12">
		   		<label>Year</label>
	   		    <input type="text" name="year" required/>
  			</div>
  			<br>
  	
  			<div class="input-field col s12">
			    <label>Image</label>
    				<input type="file" name="photo" />
  			</div>
  			<br>
  			
  			<input type="submit" value="Submit"/>
  			<!-- <input class="btn-floating btn-large waves-effect waves-light red" type="submit"><i class="material-icons">check_circle</i></input> -->
  		</form>
	</div>	
	
	<%@include file="footer.jsp" %>
</body>
</html>