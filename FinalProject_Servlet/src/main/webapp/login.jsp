<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 22.08.2021
  Time: 17:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>
    <%@include file="/css/style.css"%>
</style>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%--    <link rel="stylesheet" href="/css/style.css">--%>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <title>Cargo delivery</title>
</head>
<body class="registration">
<form method="POST" action="${pageContext.request.contextPath}/login" class="form">
    <label for="username">Username </label>
    <input type="text" name="username" id="username"/>
    <br><br>
    <label for="password">Password </label>
    <input type="password" name="password"  id="password"/>
    <br><br>
    <input class="btn btn-info button_a" type="submit">
    <button class="btn btn-light" type="button">
        <a class="button_locale" href="${pageContext.request.contextPath}/main" > Exit</a>
    </button>
</form>
</body>
</html>
