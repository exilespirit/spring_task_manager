<%@page contentType="text/html; charset=UTF-8"%>
<%@page session="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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

<div class="w3-container">
   <h1>Welcome, Administrator</h1>
</div>

<div  class="w3-container">
     <h1>Users</h1>
</div>

<table class="w3-table-all w3-hoverable" >
   <tr>
        <td>Name</td>
        <td>Username</td>
        <td>Status</td>
   </tr>
   <c:forEach  items="${users}" var ="user">
        <tr>
             <td><a href="/adminpanel/update?id=${user.id}">${user.name}</a></td>
             <td><a href="/adminpanel/update?id=${user.id}">${user.username}</a></td>
             <td><a href="/adminpanel/update?id=${user.id}">${user.role}</a></td>
        </tr>
   </c:forEach>
</table>
</body>
</html>