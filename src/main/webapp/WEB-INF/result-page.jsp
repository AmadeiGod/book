
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="nameInfo" scope="request" type="book.book.DTO.NameinfDTO" xmlns:jsp="http://java.sun.com/JSP/Page"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<h1 align="center">Data Binding in Spring MVC with Example</h1>
<hr />
<p>First Name is: ${nameInfo.firstName}</p>
<p>Last Name is: ${nameInfo.lastName}</p>
</body>
</html>