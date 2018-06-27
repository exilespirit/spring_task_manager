<%@page contentType="text/html; charset=UTF-8"%>
<%@page session="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="/css/w3.css">
</head>
<body class="w3-light-grey">
<div class="w3-container w3-blue-grey w3-opacity w3-right-align">
        <h1>Task Manager</h1>
    </div>
<form:form method="post" modelAttribute="loginForm">
    <div class="w3-container">
    <c:if test="${param.error != null}">
         <div class="w3-container w3-panel w3-red">
              <p>Invalid username and password.</p>
         </div>
    </c:if>
    <c:if test="${param.logout != null}">
         <div class="w3-container w3-panel w3-blue">
              <p>You have been logged out.</p>
         </div>
    </c:if>

    <div class="w3-margin-top">
        <form:input class="w3-input" path="username" type="text" placeholder="Username"/>
    </div>

    <div class="w3-margin-top">
        <form:input class="w3-input" path="password" type="password" placeholder="Password"/>
    </div>
    </div>

    <div class="w3-bar w3-padding-large w3-padding-24">
    <button class="w3-btn w3-hover-light-blue w3-round-large" type="submit">Login</button>
    <a class="w3-btn w3-hover-light-blue w3-round-large"  href="\">Cancel</a>
    </div>
</form:form>
</body>
</html>