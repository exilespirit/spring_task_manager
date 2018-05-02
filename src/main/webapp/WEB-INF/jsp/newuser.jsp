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
<form:form method="post" modelAttribute="newUserForm">
    <label>Name:
        <form:input type="text" path="name" />
    </label>

    <label>Username:
        <form:input type="text" path="username" />
    </label>

    <label>Password:
        <form:input type="password" path="password" />
    </label>
    <button type="submit" class="w3-btn w3-hover-light-blue w3-round-large">Save</button>
</form:form>
</body>
</html>