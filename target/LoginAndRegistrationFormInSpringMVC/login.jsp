<%--
  Created by IntelliJ IDEA.
  User: Tushar
  Date: 21/06/2020
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8"  %>
<html>
<head>
    <title>Login Page</title>
    <link href="${pageContext.request.contextPath}/resources/style.css" rel="stylesheet" />
</head>
<body>
<form action="loginProcess" method="get">
    <div class="login-box">
        <h1>Login</h1>
        <div class="textbox">
            <i class="fas fa-user"></i>
            <label>
                <input type="text"  placeholder="username" name="username" >
            </label>
        </div>

        <div class="textbox">
            <i class="fas fa-key"></i>
            <label>
                <input type="password" placeholder="password" name="password">
            </label>
        </div>
        <br>
        <input class="btn" name="submit" type="submit" value="Login">
        <br>
        <h5>Are you new user..?</h5>
        <a class="link" href="register.jsp"><h4>Register</h4></a>
    </div>
</form>
</body>
</html>
