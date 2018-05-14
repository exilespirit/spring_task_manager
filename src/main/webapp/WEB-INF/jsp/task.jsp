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
<form:form modelAttribute="taskForm" method="post">
    <label>Title:
        <form:input class="w3-input" path="title" type="text" name="title" value="${task.title}" />
    </label>

    <label>Type:
        <form:input class="w3-input" path="type" type="text" name="type" value="${task.type}" />
    </label>

    <label>Time:
        <form:input class="w3-input" path="time" type="text" name="time" value="${task.time}" />
    </label>
     <form:textarea path="description" rows="4" cols="50" />


    <button type="submit" name="save" class="w3-btn w3-hover-light-blue w3-round-large">Save</button>
    <button type="submit" name="delete" class="w3-btn w3-hover-light-blue w3-round-large">Delete</button>
    <button type="submit" name="cancel" class="w3-btn w3-hover-light-blue w3-round-large">Cancel</button>
</form:form>
</body>
</html>