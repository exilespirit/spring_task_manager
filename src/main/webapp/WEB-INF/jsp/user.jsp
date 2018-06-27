<%@page contentType="text/html; charset=UTF-8"%>
<%@page session="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE HTML>
<html>
<head>
    <link rel="stylesheet" href="/css/w3.css">
    <link rel="stylesheet" href="/css/notification.css">
    <script src="/webjars/jquery/jquery.min.js"></script>
    <script src="/webjars/sockjs-client/sockjs.min.js"></script>
    <script src="/webjars/stomp-websocket/stomp.min.js"></script>
    <script src="/test.js"></script>
</head>

<body class="w3-light-grey" >

<div class="w3-container w3-blue-grey w3-opacity w3-right-align">
    <h1>Task Manager</h1>
</div>

<div class="w3-container w3-opacity w3-right-align">
    <p><a href="/logout" class="w3-btn w3-hover-light-blue w3-round-large">Logout</a></p>
</div>

<div class="w3-container">
   <h1>Welcome, ${user.name}</h1>
</div>

<div class="w3-bar w3-padding-large w3-padding-24">
    <button onclick="location.href='/user/update?id='+${user.id}"
            class="w3-btn w3-hover-light-blue w3-round-large">Update user info</button>
    <button onclick="location.href='/resetpassword?id='+${user.id}"
                class="w3-btn w3-hover-light-blue w3-round-large">Change password</button>
</div>

<div  class="w3-container">
     <h1>Tasks</h1>
</div>

<table class="w3-table-all w3-hoverable " style="width=100%;">
   <tr>
        <td>Time</td>
        <td>Title</td>
        <td>type</td>
        <td>Description</td>
   </tr>
   <c:forEach  items="${tasks}" var ="task">
        <tr>
             <td style="max-width: 0; white-space: nowrap;"><a href="/task?id=${task.id}&uid=${user.id}">${formatter.format(task.time)}</a></td>
             <td style="max-width: 0; overflow: hidden; text-overflow: ellipsis; white-space: nowrap;"><a href="/task?id=${task.id}&uid=${user.id}">${task.title}</a></td>
             <td style="max-width: 0; overflow: hidden; text-overflow: ellipsis; white-space: nowrap;"><a href="/task?id=${task.id}&uid=${user.id}">${task.type}</a></td>
             <td style="max-width: 0; overflow: hidden; text-overflow: ellipsis; white-space: nowrap;"><a href="/task?id=${task.id}&uid=${user.id}">${task.description}</a></td>
        </tr>
   </c:forEach>
</table>

<div class="w3-bar w3-padding-large w3-padding-24">
    <a href="/task/new?uid=${user.id}" class="w3-btn w3-hover-light-blue w3-round-large">New Task</a>
</div>
</form>

<div id="notification" class="w3-modal" display="block">
  <div class="w3-modal-content">
    <div id="list" class="w3-container">

    </div>
    <div class="w3-margin-top w3-blue-grey w3-opacity w3-right-align w3-bar w3-padding-large">
          <div onclick="document.getElementById('notification').style.display='none'"
                    class="w3-btn w3-hover-light-blue w3-round-large">Close</div>
          </div>
  </div>
</div>

</body>
</html>