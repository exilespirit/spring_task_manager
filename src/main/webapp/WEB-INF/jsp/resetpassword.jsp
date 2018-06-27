<%@page contentType="text/html; charset=UTF-8"%>
<%@page session="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<!DOCTYPE HTML>
<html>
<head>
    <link rel="stylesheet" href="/css/w3.css">
</head>
<body class="w3-light-grey">
<div class="w3-container w3-blue-grey w3-opacity w3-right-align">
        <h1>Task Manager</h1>
    </div>
<div class="w3-container w3-opacity w3-right-align">
    <p><a href="/logout" class="w3-btn w3-hover-light-blue w3-round-large">Logout</a></p>
</div>
<form:form method="post" class="w3-container" modelAttribute="passForm">

    <spring:bind path="password">
        <div class="w3-margin-top">
            <form:input class="w3-input ${status.error ? 'w3-border w3-border-red' : ''}" type="${oldPassField}" path="password" placeholder="Password"/>
            <form:errors path="password"></form:errors>
        </div>
    </spring:bind>

    <div class="w3-margin-top">
         <input class="w3-input ${newPassError ? 'w3-border w3-border-red' : ''}" type="password" name="newpassword" placeholder="New password"/>
         <span style="visibility:${newPassError};">${newPassMessage}</span>
    </div>
    <div class="w3-margin-top">
         <input class="w3-input ${confirmationError ? 'w3-border w3-border-red' : ''}" type="password" name="confirmation" placeholder="Confirmation"/>
         <span style="visibility:${confirmationError};">${confirmationMessage}</span>
    </div>

    <div class="w3-bar w3-padding-large w3-padding-24">
        <button type="submit" name="save" class="w3-btn w3-hover-light-blue w3-round-large">Save</button>
        <button type="submit" name="cancel" class="w3-btn w3-hover-light-blue w3-round-large">Cancel</button>
    </div>
</form:form>
</body>
</html>