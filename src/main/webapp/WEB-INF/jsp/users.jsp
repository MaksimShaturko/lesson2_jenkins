<%--
  Created by IntelliJ IDEA.
  User: Maksim Shaturko
  Date: 23.09.2023
  Time: 19:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Users</title>
</head>
<body>
<div>
    <table border="1">
        <tr>
            <td>Id</td>
            <td>Name</td>
            <td>Surname</td>
            <td>Age</td>
            <td>Email</td>
            <td></td>
        </tr>
        <c:forEach items="${sessionScope.listOfUsers}" var="user">
            <tr>
                <td>${user.id}</td>
                <td>${user.name}</td>
                <td>${user.surname}</td>
                <td>${user.age}</td>
                <td>${user.email}</td>
                <form action="main" method="post">
                    <input type="hidden" name="command" value="delete_user"/>
                    <input type="hidden" name="user_id" value="${user.id}"/>
                    <td><input type="submit" value="Delete user"/></td>
                </form>

            </tr>
        </c:forEach>
    </table>
</div>
<br>
<div>
    <button onclick="window.location.href='main?command=go_to_add_user_page';">
        Add a user
    </button>
</div>
<br>
<a href="main?command=get_all_users">List of all users </a></p>
</body>
</html>
