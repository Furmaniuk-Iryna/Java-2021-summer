<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>
    <%@include file="/css/style.css"%>
</style>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <title>Cargo delivery</title>
</head>
<body class="registration">

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="messages"/>
<button class="btn btn-light right_button" type="button"><a class="button_locale" href="?locale=uk">UKR</a></button>
<button class="btn btn-light right_button" type="button"><a class="button_locale" href="?locale=en">ENG</a></button>
<br><br>
<h1 class="registration_title"><fmt:message key="login.text"/> </h1>
<form method="POST" action="${pageContext.request.contextPath}/login" class="form">
    <br><br>
    <label for="username"><fmt:message key="username"/> </label>
    <input type="text" name="username" id="username"/>
    <br><br>
    <label for="password"><fmt:message key="password" /></label>
    <input type="password" name="password"  id="password"/>
    <br><br>
    <input class="btn btn-info button_a" type="submit" value="<fmt:message key="login.text"/>">
    <button class="btn btn-light" type="button">
        <a class="button_locale" href="${pageContext.request.contextPath}/main" ><fmt:message key="main.page" /></a>
    </button>
</form>
</body>
</html>
