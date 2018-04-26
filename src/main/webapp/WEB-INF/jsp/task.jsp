<!DOCTYPE HTML>
<html>
<head>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>
<body>
<form method="post">
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



    <button type="submit">Update</button>
    <button type="submit">Cancel</button>
</form>
</body>
</html>