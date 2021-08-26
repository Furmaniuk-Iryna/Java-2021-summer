<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- Теги для форматирования и интернационализации информации --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
<%--<fmt:setBundle basename="resourses.messages"  var="BundleContent"/>--%>
<fmt:setLocale value="${locale}"/>
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
                        <button class="btn btn-light" type="button"><a class="button_locale" href="?locale=uk">UKR</a>
                        </button>

                        <button class="btn btn-light" type="button"><a class="button_locale" href="?locale=en">ENG</a>
                        </button>
        </div>
    </div>
</div>
<div class="tariff">
    <h2 class="tariffs"><fmt:message key="tariffs" /></h2>
    <table class="table table-striped">
        <thead>
        <tr>
            <th>#</th>
            <th><fmt:message key="name" /></th>
            <th><fmt:message key="weight" /></th>
            <th><fmt:message key="volume" /></th>
            <th><fmt:message key="conditions" /></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="tariff" items="${tariffList}">
            </td>
                <td><c:out value="${tariff.id}"/></td>
                <td><c:if test="${locale=='en'}"><c:out value="${tariff.tariffNameEn}"/></c:if>
               <c:if test="${locale=='uk'}"><c:out value="${tariff.tariffNameUk}"/></c:if></td>
                <td><c:out value="${tariff.tariffForWeight}"/></td>
                <td><c:out value="${tariff.tariffForVolume}"/></td>
                <td><c:if test="${locale=='en'}"><c:out value="${tariff.descriptionEn}"/></c:if>
            <c:if test="${locale=='uk'}"><c:out value="${tariff.descriptionUk}"/></c:if></td>
            </tr>
        </c:forEach>

        </tbody>
    </table>
</div>
<br><br>
<div class="directions">
    <h2 class="directions"><fmt:message key="directions" /></h2>
    <table class="table table-striped">
        <thead>
        <tr>
            <th>#</th>
            <th><fmt:message key="city" /></th>
            <th><fmt:message key="distance" /></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="direction" items="${directions}">
            <tr>
                <td><c:out value="${direction.id}"/></td>
                <td><c:if test="${locale=='en'}"><c:out value="${direction.cityEn}"/></c:if>
                <c:if test="${locale=='uk'}"><c:out value="${direction.cityUk}"/></c:if></td>
                <td><c:out value="${direction.distance}"/></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>