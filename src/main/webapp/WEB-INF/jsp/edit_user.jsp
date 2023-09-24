<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 24.09.2023
  Time: 19:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Edit a User</title>
</head>
<body>

<form action="main" method="post">
    <input type="hidden" name="command" value="edit_user"/>
    <input type="hidden" name="user_id" value="${sessionScope.user.id}"/><br>
    <div>
        Edit user over here
    </div><br>
    Name: <br><input type="text" name="name" value="${sessionScope.user.name}"/><br>
    Surname: <br><input type="text" name="surname" value="${sessionScope.user.surname}"/><br>
    Age: <br><input type="text" name="age" value="${sessionScope.user.age}"/><br>
    Email: <br><input type="text" name="email" value="${sessionScope.user.email}"/><br><br>
    <input type="submit" value="Apply"/>
</form>
</body>
</html>
