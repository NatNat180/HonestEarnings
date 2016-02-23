<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="eng">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/bootstrap.css"/>
<link rel="stylesheet" type="text/css" href="css/style.css"/>
<script src="js/jquery-1.11.3.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<title>Honest Earnings</title>
</head>
<body>
<c:import url="/WEB-INF/header.html"/>
<section class="push-down-little">
	<form method="post" action="register-user">
		<div id="left-of-input">User Name:</div><input type="text" name="userName" value="" id="text-input" class="form-control" required/>
		<br />
		<br />
		<div id="left-of-input">Password:</div><input type="password" name="password" value="" id="text-input" class="form-control" required/>
		<br />
		<br />
		<br />
		&nbsp;
		<br />
		<input type="submit" value="Register" id="right"/>
	</form>
	<br />
	<br />
	<div id="right">Already Registered? <a href="/">Login</a></div>
</section>
</body>
</html>