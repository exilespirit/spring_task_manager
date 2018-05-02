<%@page contentType="text/html; charset=UTF-8"%>
<%@page session="true"%>

<!DOCTYPE HTML>
<html>
<head>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>
<body>
<div class="w3-container w3-blue-grey w3-opacity w3-right-align">
        <h1>Task Manager</h1>
    </div>
<form action="saveTask" method="post" modelAttribute="task">
    <label>Title:
        <input type="text" name="title" value="${task.title}"><br />
    </label>

    <label>Type:
        <input type="text" name="type" value="${task.type}"><br />
    </label>

    <label>Time:
        <input type="text" name="time" value="${task.time}"><br />
    </label>
     <textarea rows="4" cols="50">
     ${task.type}
    </textarea>



    <button type="submit" class="w3-btn w3-hover-light-blue w3-round-large">Update</button>
    <button class="w3-btn w3-hover-light-blue w3-round-large">Cancel</button>
</form>
</body>
</html>