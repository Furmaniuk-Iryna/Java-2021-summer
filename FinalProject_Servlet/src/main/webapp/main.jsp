<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--Теги для работы с базами данных--%>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/sql" %>

<%-- Теги для форматирования и интернационализации информации (i10n и i18n)--%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
<h1>MAIN</h1>
<%-- <% List<Tariff> tariffs = (List<Tariff>) request.getAttribute("tariffs"); %>--%>
<div class="header_top">
    <div class="locale">
        <div class="d-grid gap-2 d-md-flex justify-content-md-end">
            <button class="btn btn-light" type="button">
                <a class="button_locale" href="${pageContext.request.contextPath}/login">Log in</a>
            </button>
            <button class="btn btn-light" type="button">
                <a class="button_locale" href="${pageContext.request.contextPath}/registration">Registration</a>
            </button>
            <%--            <button class="btn btn-light" type="button"><a class="button_locale" th:href="@{'?locale=uk'}">UKR</a>--%>
            <%--            </button>--%>

            <%--            <button class="btn btn-light" type="button"><a class="button_locale" th:href="@{'?locale=en'}">ENG</a>--%>
            <%--            </button>--%>
        </div>
    </div>
</div>
<div class="tariff">
    <h2 class="tariffs">Tariffs</h2>
    <table class="table table-dark">
        <thead>
        <tr>
            <th>#</th>
            <th>Name</th>
            <th>Weight</th>
            <th>Volume</th>
            <th>Conditions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="tariff" items="${tariffList}">
            <tr>
                <td><c:out value="${tariff.id}"/></td>
                <td><c:out value="${tariff.tariffNameEn}"/></td>
                <td><c:out value="${tariff.tariffForWeight}"/></td>
                <td><c:out value="${tariff.tariffForVolume}"/></td>
                <td><c:out value="${tariff.descriptionEn}"/></td>
            </tr>
        </c:forEach>

        </tbody>
    </table>
</div>

<div class="directions">
    <h2 class="directions">Directions</h2>
    <table class="table table-dark">
        <thead>
        <tr>
            <th>#</th>
            <th>City</th>
            <th>Distance</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="direction" items="${directions}">
            <tr>
                <td><c:out value="${direction.id}"/></td>
                <td><c:out value="${direction.cityEn}"/></td>
                <td><c:out value="${direction.distance}"/></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>