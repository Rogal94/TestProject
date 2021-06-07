<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table style="border: 1px solid black">
    <tr>
        <th>Firstname</th>
        <th>Lastname</th>
        <th>Age</th>
        <th>Phone</th>
        <th>Delete</th>
    </tr>
<c:forEach items="${userList}" var="user">
    <tr>
        <td>${user.firstName}</td>
        <td>${user.lastName}</td>
        <td>${user.age}</td>
        <td>${user.phoneNo}</td>
        <td><button><a href='<c:url value="/delete/${user.id}"/>'>DELETE</a></button></td>
    </tr>
</c:forEach>
</table>
<p><button><a href='<c:url value="/show"/>'>BACK</a></button></p>
</body>
</html>
