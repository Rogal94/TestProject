<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="post" enctype="multipart/form-data">
    Upload CSV file:
    <input type="file" name="file" />
    <input type="submit" value="Upload" />
</form>
<p><button><a href='<c:url value="/"/>'>BACK</a></button></p>
</body>
</html>

