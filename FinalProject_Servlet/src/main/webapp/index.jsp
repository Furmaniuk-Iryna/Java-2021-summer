<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--Теги для работы с базами данных--%>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/sql" %>

<%-- Теги для форматирования и интернационализации информации (i10n и i18n)--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*, java.text.*" %>
<%@ page import="com.example.final_project.model.entity.Tariff" %>
<%@ page import="com.example.final_project.model.service.TariffService" %>
<%@ page import="java.util.concurrent.CopyOnWriteArrayList" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%--    <link rel="stylesheet" href="css/style.css">--%>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <title>Cargo delivery</title>
</head>
<body>
<% String newLocale=request.getParameter("locale")==null?"en":request.getParameter("locale");
request.setAttribute("locale",newLocale);
%>
<fmt:setLocale value="${locale}" />
<fmt:setBundle basename="messages"/>
<%-- <% List<Tariff> tariffs = (List<Tariff>) request.getAttribute("tariffs"); %>--%>
<div class="header_top">
    <div class="locale">
        <div class="d-grid gap-2 d-md-flex justify-content-md-end">
            <button class="btn btn-light" type="button">
                <a class="button_locale" href="${pageContext.request.contextPath}/login"><fmt:message key="login" /></a>
            </button>
            <button class="btn btn-light" type="button">
                <a class="button_locale" href="${pageContext.request.contextPath}/registration"><fmt:message key="registration" /></a>
            </button>
            <button class="btn btn-light" type="button">
                <a class="button_locale" href="${pageContext.request.contextPath}/main"><fmt:message key="website" /></a>
            </button>
            <button class="btn btn-light" type="button"><a class="button_locale" href="?locale=uk">UKR</a>
            </button>

            <button class="btn btn-light" type="button"><a class="button_locale" href="?locale=en">ENG</a>
            </button>
        </div>
    </div>
</div>

</body>
</html>
