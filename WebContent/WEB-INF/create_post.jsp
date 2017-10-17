<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Create Post</title>
</head>
<body>
	<form action="" method="POST"> <!-- enctype="multipart/form-data"> -->
			<label>Address</label>
    			<input type="text" name="address" required/>
    			<br>
		    <label>Area</label>
		    <input type="text" name="area" required/>
			<br>
		    <label>Description</label>
		    <input type="text" name="description" required/>
			<br>
		    <label>Floor</label>
		    <input type="text" name="floor" required/>
		    <br>
		    <label>Type</label>
		    <select name="Points" size="1" id="Points" required>
				<c:forEach items="${houseTypes}" var="a" varStatus="cnt">
					<option value='<c:out value="${a}"/>'><c:out value="${a}"/></option>w
				</c:forEach>
			</select>
		    <br>
		    <label>Rooms</label>
		    <input type="text" name="rooms" required/>
		    <br>
		    <label>Phone</label>
		    <input type="text" name="phone" required/>
		    <br>
		    <label>Price</label>
		    <input type="text" name="price" required/>
		    <br>
		    <label>Year</label>
		    <input type="text" name="year" required/>
		    <br>
		    
		    <!-- <label>Image</label>
	    		<input type="file" name="image" />
	    		<br> -->
		    
		    <input type="submit" value="SUBMIT">
	</form>
	<a href="my_post">Cancel</a>
	
</body>
</html>