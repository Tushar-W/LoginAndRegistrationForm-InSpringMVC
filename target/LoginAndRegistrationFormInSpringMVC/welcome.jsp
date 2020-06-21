<%--
  Created by IntelliJ IDEA.
  User: Tushar
  Date: 08/06/2020
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<% Object user = session.getAttribute("user"); %>
<html>
<head>
    <title>Welcome Page</title>
</head>
<body>
<h2>Welcome to  the World...!!!</h2>
<div>
    <%=user%>>
</div>
</body>
</html>
