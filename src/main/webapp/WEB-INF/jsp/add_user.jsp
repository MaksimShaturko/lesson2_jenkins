<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 24.09.2023
  Time: 15:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Add User</title>
</head>
<body>


<form action="main" method="post">
    <input type="hidden" name="command" value="add_user"/><br>
    <div>
        Enter user data
    </div>
    <input type="text" name="name" value="Enter username"/><br>
    <input type="text" name="surname" value="Enter surname"/><br>
    <input type="text" name="age" value="Enter age"/>
    <input type="text" name="email" value="Enter email"/>
    <input type="submit"
           value="Add a user"/>
</form>
</body>
</html>
