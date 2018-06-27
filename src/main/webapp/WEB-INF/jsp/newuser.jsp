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
    <p><a href="/logout" class="w3-btn w3-hover-light-blue w3-round-large" style="visibility:${delButton};">Logout</a></p>
</div>

<form:form method="post" modelAttribute="newUserForm" class="w3-container">
    <spring:bind path="name">
        <div>
            <form:input class="w3-input ${status.error ? 'w3-border w3-border-red' : ''}" type="text" path="name" placeholder="Name"/>
            <form:errors path="name"></form:errors>
        </div>
    </spring:bind>

    <spring:bind path="username">
        <div class="w3-margin-top">
            <form:input class="w3-input ${status.error ? 'w3-border w3-border-red' : ''}" type="${usernameField}" path="username" placeholder="Username"/>
            <form:errors path="username"></form:errors>
        </div>
    </spring:bind>

    <spring:bind path="password">
        <div class="w3-margin-top">
            <form:input class="w3-input ${status.error ? 'w3-border w3-border-red' : ''}" type="${usernameField}" path="password" placeholder="Password"/>
            <form:errors path="password"></form:errors>
        </div>
    </spring:bind>

    <spring:bind path="email">
        <div class="w3-margin-top">
            <form:input class="w3-input ${status.error ? 'w3-border w3-border-red' : ''}" type="text" path="email" placeholder="Email"/>
            <form:errors path="email"></form:errors>
        </div>
    </spring:bind>

    <div class="w3-bar w3-padding-large w3-padding-24">
        <button type="submit" name="save" class="w3-btn w3-hover-light-blue w3-round-large">Save</button>
        <button type="submit" name="cancel" class="w3-btn w3-hover-light-blue w3-round-large">Cancel</button>
        <div class="w3-btn w3-hover-light-blue w3-round-large" style="visibility:${delButton};" onclick="document.getElementById('id01').style.display='block'">Delete</div>
        <button type="submit" name="block" style="visibility:${blockButton};" class="w3-btn w3-hover-light-blue w3-round-large">${isBlocked}</button>
        <a class="w3-btn w3-hover-light-blue w3-round-large" style="visibility:${blockButton};"  href="/adminpanel/resetpassword?id=${id}">Change password</a>
    </div>


<div id="id01" class="w3-modal">
  <div class="w3-modal-content">
    <div class="w3-container w3-padding-large">

      <p>Are you sure you want to delete user?</p>
    </div>
    <div class="w3-blue-grey w3-opacity w3-right-align w3-bar w3-padding-large">
            <button type="submit" name="delete" class="w3-btn w3-hover-light-blue w3-round-large">Ok</button>
            <div onclick="document.getElementById('id01').style.display='none'" class="w3-btn w3-hover-light-blue w3-round-large">Cancel</div>
          </div>
  </div>
</div>
</form:form>
</body>
</html>