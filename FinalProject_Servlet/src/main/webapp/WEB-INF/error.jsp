<%@ page language="java" isErrorPage="true"  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, java.text.*" %>
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
<body>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="messages"/>

<button class="btn btn-light left_button" type="button">
    <a class="button_locale" href="${pageContext.request.contextPath}/main"><fmt:message key="main.page"/></a>
</button>
<button class="btn btn-light right_button" type="button"><a class="button_locale" href="${pageContext.request.contextPath}/exception?locale=uk">UKR</a></button>
<button class="btn btn-light right_button" type="button"><a class="button_locale" href="${pageContext.request.contextPath}/exception?locale=en">ENG</a></button>
<p class="exception-title"><fmt:message key="error"/></p><br/>
<br>


</body>
</html>