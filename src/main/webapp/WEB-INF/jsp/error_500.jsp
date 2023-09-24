<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 23.09.2023
  Time: 19:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error_500</title>
</head>
<body>
<div>
    <h4>
        Request: ${pageContext.errorData.requestURI}
        Failed
    </h4>
    <br>
    <h4>
       Code: ${pageContext.errorData.statusCode}
    </h4>
    <br>
    <h4>
        Error
        ${sessionScope.errorMessage}
    </h4>
    <br>
</div>
</body>
</html>
