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
<form:form modelAttribute="taskForm" method="post" class="w3-container">
    <spring:bind path="time">
        <div>
            <form:input class="w3-input ${status.error ? 'w3-border w3-border-red' : ''}" path="time" type="datetime-local" name="time" placeholder="Time"/>
            <form:errors path="time"></form:errors>
        </div>
    </spring:bind>

    <spring:bind path="title">
        <div class="w3-margin-top">
            <form:input class="w3-input ${status.error ? 'w3-border w3-border-red' : ''}" path="title" type="text" name="title" placeholder="Title"/>
            <form:errors path="title"></form:errors>
        </div>
    </spring:bind>

    <spring:bind path="type">
        <div class="w3-margin-top">
            <form:input class="w3-input ${status.error ? 'w3-border w3-border-red' : ''}" path="type" type="text" name="type" placeholder="Type"/>
            <form:errors path="type"></form:errors>
        </div>
    </spring:bind>

    <spring:bind path="description">
        <div class="w3-margin-top">
            <form:textarea class="w3-input ${status.error ? 'w3-border w3-border-red' : ''}" path="description" rows="4" cols="50" placeholder="Description"/>
        </div>
    </spring:bind>

    <div class="w3-bar w3-padding-large w3-padding-24">
        <button type="submit" name="save" class="w3-btn w3-hover-light-blue w3-round-large">Save</button>
        <button type="submit" name="cancel" class="w3-btn w3-hover-light-blue w3-round-large">Cancel</button>
        <div class="w3-btn w3-hover-light-blue w3-round-large" style="visibility:${delButton};" onclick="document.getElementById('id01').style.display='block'">Delete</div>
    </div>

<div id="id01" class="w3-modal">
  <div class="w3-modal-content">
    <div class="w3-container w3-padding-large">

      <p>Are you sure you want to delete task?</p>
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