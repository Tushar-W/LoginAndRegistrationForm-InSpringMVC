
<%--
  Created by IntelliJ IDEA.
  User: Tushar
  Date: 08/06/2020
  Time: 7:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>

<html>
<head>
    <title>Welcome Page</title>
    <link href="${pageContext.request.contextPath}/resources/style.css" rel="stylesheet" />
</head>
<body>
<div class="login-box">
    <h2>User Details :</h2>
    <br>
    <h3>Name : <%=session.getAttribute("username") %></h3>
    <h3>Email : <%=session.getAttribute("email") %></h3>
    <h3>Reg.Date : <%=session.getAttribute("date") %></h3>
</div>
</body>
</html>
