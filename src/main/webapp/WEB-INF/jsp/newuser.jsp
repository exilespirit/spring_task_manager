<%@page contentType="text/html; charset=UTF-8"%>
<%@page session="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE HTML>
<html>
<head>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>
<body class="w3-light-grey">
<div class="w3-container w3-blue-grey w3-opacity w3-right-align">
        <h1>Task Manager</h1>
    </div>
<form:form method="post" modelAttribute="newUserForm">
    <label>Name:
        <form:input class="w3-input" type="text" path="name" />
    </label>

    <label>Username:
        <form:input class="w3-input" type="text" path="username" />
    </label>

    <label>Password:
        <form:input class="w3-input" type="password" path="password" />
    </label>
    <button type="submit" name="save" class="w3-btn w3-hover-light-blue w3-round-large">Save</button>
    <button type="submit" name="cancel" class="w3-btn w3-hover-light-blue w3-round-large">Cancel</button>
    <button type="submit" name="delete" class="w3-btn w3-hover-light-blue w3-round-large">Delete</button>
</form:form>
</body>
</html>