<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Create Account</title>
</head>
<body>

<h1> Sign-up </h1>
<form action="${pageContext.request.contextPath}/signup" method="POST">
    <label> E-mail</label>
    <input type="email" name="email" required/>

    <label> Password</label>
    <input type="password" name="password" required/>
    
    <label> Re-Password</label>
    <input type="password" name="repassword" required/>

    <input type="submit" value="Submit"/>
</form>

</body>
</html>