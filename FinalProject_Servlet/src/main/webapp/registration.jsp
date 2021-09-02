<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<br><br>
<c:if test="${sessionScope.error}">
    <div  class="alert alert-primary d-flex align-items-center" role="alert">
        <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="currentColor"
             class="bi bi-exclamation-triangle-fill flex-shrink-0 me-2" viewBox="0 0 16 16" role="img"
             aria-label="Warning:">
            <path d="M8.982 1.566a1.13 1.13 0 0 0-1.96 0L.165 13.233c-.457.778.091 1.767.98 1.767h13.713c.889 0 1.438-.99.98-1.767L8.982 1.566zM8 5c.535 0 .954.462.9.995l-.35 3.507a.552.552 0 0 1-1.1 0L7.1 5.995A.905.905 0 0 1 8 5zm.002 6a1 1 0 1 1 0 2 1 1 0 0 1 0-2z"/>
        </svg>
        <div><fmt:message key="error.data"/> </div>
    </div><br>
</c:if>
<c:if test="${sessionScope.userPresent}">
    <div  class="alert alert-primary d-flex align-items-center" role="alert">
        <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="currentColor"
             class="bi bi-exclamation-triangle-fill flex-shrink-0 me-2" viewBox="0 0 16 16" role="img"
             aria-label="Warning:">
            <path d="M8.982 1.566a1.13 1.13 0 0 0-1.96 0L.165 13.233c-.457.778.091 1.767.98 1.767h13.713c.889 0 1.438-.99.98-1.767L8.982 1.566zM8 5c.535 0 .954.462.9.995l-.35 3.507a.552.552 0 0 1-1.1 0L7.1 5.995A.905.905 0 0 1 8 5zm.002 6a1 1 0 1 1 0 2 1 1 0 0 1 0-2z"/>
        </svg>
        <div><fmt:message key="user.present"/> </div>
    </div><br>
</c:if>

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
