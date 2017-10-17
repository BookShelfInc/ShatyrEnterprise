<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${myPosts.getAddress()}</title>
</head>
<body>
	<form action="" method="POST" enctype="multipart/form-data">
			<label>Address</label>
    			<input type="text" value='<c:out value="${myPosts.getAddress()}"/>' name="address"/>
    			<br>
		    <label>Area</label>
		    <input type="text"  value='<c:out value="${myPosts.getArea()}"/>' name="area"/>
			<br>
		    <label>Description</label>
		    <input type="text"  value='<c:out value="${myPosts.getDescription()}"/>' name="description"/>
			<br>
		    <label>Floor</label>
		    <input type="text"  value='<c:out value="${myPosts.getFloor()}"/>' name="floor"/>
		    <br>
		    <label>Type</label>
		    <select name="Points" size="1" id="Points" required>
				<c:forEach items="${houseTypes}" var="a" varStatus="cnt">
					<c:if test = "${a == myPosts.getHouse_type()}">
	    					<option value='<c:out value="${a}"/>' selected><c:out value="${a}"/></option>
	                </c:if>
	                <c:if test = "${a != myPosts.getHouse_type()}">
	    					<option value='<c:out value="${a}"/>'><c:out value="${a}"/></option>
	                </c:if>
				</c:forEach>
			</select>
		    <%-- <input type="text"  value='<c:out value="${myPosts.getHouse_type()}"/>' name="house_type"/> --%>
		    <br>
		    <label>Rooms</label>
		    <input type="text"  value='<c:out value="${myPosts.getNum_rooms()}"/>' name="rooms"/>
		    <br>
		    <label>Phone</label>
		    <input type="text" value='<c:out value="${myPosts.getPhone()}"/>' name="phone"/>
		    <br>
		    <label>Price</label>
		    <input type="text" value='<c:out value="${myPosts.getPrice()}"/>' name="price"/>
		    <br>
		    <label>Year</label>
		    <input type="text" value='<c:out value="${myPosts.getYear()}"/>' name="year"/>
		    <br>
		    <label>Image</label>
	    			<input type="file" name="photo" />
	    		<br>
		    
		    <input type="submit" value="SUBMIT">
	</form>
</body>
</html>