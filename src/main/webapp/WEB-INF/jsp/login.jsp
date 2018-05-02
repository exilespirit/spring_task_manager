<%@page contentType="text/html; charset=UTF-8"%>
<%@page session="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE HTML>
<html>
<head>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>
<body>
<div class="w3-container w3-blue-grey w3-opacity w3-right-align">
        <h1>Task Manager</h1>
    </div>
<form:form method="post" modelAttribute="loginForm">
    <label>Username:
        <form:input path="username" type="text" />
    </label>

    <label>Password:
        <form:input path="password" type="password" />
    <button class="w3-btn w3-hover-light-blue w3-round-large" type="submit">Login</button>
    <a class="w3-btn w3-hover-light-blue w3-round-large"  href="\">Cancel</a>
</form:form>
</body>
</html>