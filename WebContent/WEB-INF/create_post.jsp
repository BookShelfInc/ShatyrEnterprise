<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Create Post</title>
</head>
<body>
	<form action="/create_post" method="POST">
			<label>Address</label>
    			<input type="text" name="address"/>
    			<br>
		    <label>Area</label>
		    <input type="text" name="area"/>
			<br>
		    <label>Description</label>
		    <input type="text" name="description"/>
			<br>
		    <label>Floor</label>
		    <input type="text" name="floor"/>
		    <br>
		    <label>Type</label>
		    <input type="text" name="house_type"/>
		    <br>
		    <label>Rooms</label>
		    <input type="text" name="rooms"/>
		    <br>
		    <label>Phone</label>
		    <input type="text" name="phone"/>
		    <br>
		    <label>Price</label>
		    <input type="text" name="price"/>
		    <br>
		    <label>Year</label>
		    <input type="text" name="year"/>
	</form>
</body>
</html>