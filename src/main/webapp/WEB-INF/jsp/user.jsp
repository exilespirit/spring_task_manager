<%@page contentType="text/html; charset=UTF-8"%>
<%@page session="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML>
<html>
<head>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>
<body>
<div class="w3-container w3-blue-grey w3-opacity w3-right-align">
        <h1>Task Manager</h1>
    </div>
<form method="post">
    <label>Name:
        <input type="text" name="name"><br />
    </label>

    <label>Username:
        <input type="text" name="pass"><br />
    </label>


    <button type="submit">New Task</button>
</form>

<table border="2">
   <tr>
        <td>Time</td>
        <td>Title</td>
        <td>type</td>
        <td>Description</td>
   </tr>

   <c:forEach  items="${tasks}" var ="task">
           <tr>
               <td><a href="/task?id=${task.id}">${task.time}</a></td>
               <td><a href="/task?id=${task.id}">${task.title}</a></td>
               <td><a href="/task?id=${task.id}">${task.description}</a></td>
               <td><a href="/task?id=${task.id}">${user.name}</a></td>
          </tr>
   </c:forEach>



</body>
</html>