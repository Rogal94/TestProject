<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<p><button><a href='<c:url value="/show/list"/>'>SHOW ALL</a></button></p>
<p><button><a href='<c:url value="/show/list/sorted"/>'>SORT BY AGE</a></button></p>
<form method="post">
    Find user:
    <input type="text" placeholder="Last name" name="lastName"/>
    <button type="submit">search</button>
</form>
<p>Number of users: ${count}</p>
<p>The oldest one with phone number: ${theOldest.firstName} ${theOldest.lastName} ${theOldest.birthDate} ${theOldest.phoneNo}</p>
<p><button><a href='<c:url value="/delete/all"/>'>DELETE ALL USERS</a></button></p>
<p><button><a href='<c:url value="/"/>'>BACK</a></button></p>
</body>
</html>
