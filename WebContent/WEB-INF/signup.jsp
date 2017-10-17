<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Create Account</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/css/materialize.min.css">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/js/materialize.min.js"></script>
<script type="text/javascript" src="js/materialize.min.js"></script>
</head>
<body>
<%@include file="header.jsp" %>
<h1> Sign-up </h1>
	<div class="container">
		<form action="${pageContext.request.contextPath}/signup" method="POST">
		    <label> E-mail</label>
		    <input type="email" name="email" required/>
		
		    <label> Password</label>
		    <input type="password" name="password" required/>
		    
		    <label> Re-Password</label>
		    <input type="password" name="repassword" required/>
		
		    <div class="button-group" 
				    style="display:flex;
				    		   justify-content: center;
				    		   padding: 25px;">
					    	<button class="btn waves-effect waves-light" type="submit" name="action">SignUp
			    				<i class="material-icons right">send</i>
			  			</button>
				    </div>
		</form>
	</div>
	<%@include file="footer.jsp" %>
</body>
</html>