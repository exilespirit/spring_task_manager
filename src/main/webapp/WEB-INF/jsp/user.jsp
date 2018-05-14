<%@page contentType="text/html; charset=UTF-8"%>
<%@page session="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML>
<html>
<head>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>
<body class="w3-light-grey">
<div class="w3-container w3-blue-grey w3-opacity w3-right-align">
        <h1>Task Manager</h1>
    </div>

    <div >
           <h1>Welcome, ${user.name}</h1>
    </div>
    <div class="w3-bar w3-padding-large w3-padding-24">
        <button onclick="location.href='/user/update?id='+${user.id}" class="w3-btn w3-hover-light-blue w3-round-large">Update user</button>
        <button onclick="/user/delete" class="w3-btn w3-hover-light-blue w3-round-large">Delete user</button>
    </div>
    <div >
               <h1>Tasks</h1>
        </div>


<table class="w3-table-all w3-hoverable" >
   <tr>
        <td>Time</td>
        <td>Title</td>
        <td>type</td>
        <td>Description</td>
   </tr>

   <c:forEach  items="${tasks}" var ="task">
           <tr>
               <td><a href="/task?id=${task.id}&uid=${user.id}">${task.time}</a></td>
               <td><a href="/task?id=${task.id}&uid=${user.id}">${task.title}</a></td>
               <td><a href="/task?id=${task.id}&uid=${user.id}">${task.description}</a></td>
               <td><a href="/task?id=${task.id}&uid=${user.id}">${user.name}</a></td>
          </tr>
   </c:forEach>
</table>
    <a href="/task/new?uid=${user.id}" class="w3-btn w3-hover-light-blue w3-round-large">New Task</button>
</form>
</body>
</html>