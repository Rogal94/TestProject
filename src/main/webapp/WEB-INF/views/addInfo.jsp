<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<p>Upload complete</p><br>
<p>Users added: ${info[0]}</p>
<p>Wrong phone: ${info[1]}</p><br>
<p>Users not added: ${info[2] + info[3] + info[4]}</p>
<p>Null lines: ${info[2]}</p>
<p>The same phone: ${info[3]}</p>
<p>Wrong date: ${info[4]}</p>
<p><button><a href='<c:url value="/"/>'>BACK</a></button></p>
</body>
</html>
