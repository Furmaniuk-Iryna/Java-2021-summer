<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<style>
    <%@include file="/css/style.css"%>
</style>
<html>
<head>
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
<br>
<p class="registration_title"><fmt:message key="registration"/> </p>
<form method="POST" action="${pageContext.request.contextPath}/registration" class="form">
    <label for="name"><fmt:message key="name"/> </label>
    <input type="text" name="name" id="name"/>
    <br><br>
    <label for="surname"><fmt:message key="surname"/> </label>
    <input type="text" name="surname" id="surname"/>
    <br><br>
    <label for="username"><fmt:message key="username"/> </label>
    <input type="text" name="username" id="username"/>
    <br><br>
    <label for="password"><fmt:message key="password"/> </label>
    <input type="password" name="password"  id="password"/>
    <br><br>
    <input class="btn btn-info button_a" type="submit" value="<fmt:message key="registration"/>">
    <button class="btn btn-light" type="button">
        <a class="button_locale" href="${pageContext.request.contextPath}/main" ><fmt:message key="main.page"/> </a>
    </button>
</form>
</body>
</html>
