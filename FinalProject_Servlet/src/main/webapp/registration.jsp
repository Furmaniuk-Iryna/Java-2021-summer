<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 22.08.2021
  Time: 18:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%--    <link rel="stylesheet" href="/css/style.css">--%>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <title>Cargo delivery</title>
</head>
<body>
<p>Registration</p>
<form method="POST" action="${pageContext.request.contextPath}/registration" class="form">
    <label for="name">Name </label>
    <input type="text" name="name" id="name"/>
    <br><br>
    <label for="surname">Surname </label>
    <input type="text" name="surname" id="surname"/>
    <br><br>
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
