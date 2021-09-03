<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
<body>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="messages"/>
<%--<button class="btn btn-light right_button" type="button"><a class="button_locale" href="?locale=uk">UKR</a></button>--%>
<%--<button class="btn btn-light right_button" type="button"><a class="button_locale" href="?locale=en">ENG</a></button>--%>
<table class="table table-dark">
    <thead>
    <tr>
        <th>#</th>
        <th><fmt:message key="username"/></th>
        <th><fmt:message key="type"/></th>
        <th><fmt:message key="weight"/></th>
        <th><fmt:message key="volume"/></th>
        <th><fmt:message key="address"/></th>
        <th><fmt:message key="date.of.arrival"/> </th>
        <th><fmt:message key="price"/></th>

    </tr>
    </thead>
    <tbody>
        <tr>
            <td><c:out value="${receipts.id}"/></td>
            <td><c:out value="${receipts.user.username}"/></td>
            <td>
                <c:if test="${sessionScope.locale=='en'}"><c:out value="${receipts.type_en}"/></c:if>
                <c:if test="${sessionScope.locale=='uk'}"><c:out value="${receipts.type_uk}"/></c:if>
            </td>
            <td><c:out value="${receipts.weight}"/></td>
            <td><c:out value="${receipts.volume}"/></td>
            <td>
                <c:if test="${sessionScope.locale=='en'}"><c:out value="${receipts.address.address_en}"/></c:if>
                <c:if test="${sessionScope.locale=='uk'}"><c:out value="${receipts.address.address_uk}"/></c:if>
            </td>
            <td><c:out value="${receipts.dateOfArrival}"/></td>
            <td><c:out value="${price}"/></td>
        </tr>
    </tbody>
</table><br>
<button class="btn btn-info" type="button">
    <a class="button_a" href="${pageContext.request.contextPath}/manager?page=1">
<fmt:message key="manager.page"/>
    </a>
</button>
</body>
</html>
