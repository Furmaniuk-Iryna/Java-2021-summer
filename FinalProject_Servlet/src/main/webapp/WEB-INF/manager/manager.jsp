<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 22.08.2021
  Time: 18:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<style>
    <%@include file="/css/style.css"%>
</style>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%--    <link rel="stylesheet" href="resources/public/css/style.css">--%>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <title>Cargo delivery</title>
</head>
<body class="manager">
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="messages"/>
<h1>Manager</h1>
<button class="btn btn-light" type="button">
    <a class="button_locale" href="${pageContext.request.contextPath}/logout"> Exit</a>
</button>
<div class="card">
    <h2>Delivery request</h2>
    <table class="table table-dark">
        <thead>
        <tr>
            <th>#</th>
            <th>Username</th>
            <th>Type</th>
            <th>Weight</th>
            <th>Volume</th>
            <th>Address</th>
            <th>Date of arrival</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="request" items="${deliveryRequestList}">
            <tr>
                <td><c:out value="${request.id}"/></td>
                <td><c:out value="${request.user.username}"/></td>
                <td><c:out value="${request.type_en}"/></td>
                <td><c:out value="${request.weight}"/></td>
                <td><c:out value="${request.volume}"/></td>
                <td><c:out value="${request.address.address_en}"/></td>
                <td><c:out value="${request.dateOfArrival}"/></td>
                <td>  <button class="btn btn-light" type="button">
                    <a class="button_a" href="${pageContext.request.contextPath}/receipt?id=${request.getId()}">
                        <fmt:message key="calculate"></fmt:message>
                    </a>
                </button></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <nav>
        <ul class="pagination">
    <c:forEach begin="1" end="${noOfPages}" var="i">
        <c:choose>
            <c:when test="${currentPage eq i}">
                <li class="page-item active"><a class="page-link" href="manager?page=${i}">${i}</a></li>
            </c:when>
            <c:otherwise>
                <li class="page-item"><a class="page-link" href="manager?page=${i}">${i}</a></li>
            </c:otherwise>
        </c:choose>
    </c:forEach>
        </ul>
    </nav>

</div>
<form action="${pageContext.request.contextPath}/directionReport"  class="report">
    <label for="d" >City</label>
    <input type="text" name="city" id="d"/>
    <input type="hidden" name="page" value="1" />
    <input type="submit" class="btn btn-info button_a"/>
</form><br>

<form  action="${pageContext.request.contextPath}/reportByDays"  class="report">
    <label>Day</label>
    <input type="date" name="day" />
    <input type="hidden" name="page" value="1" />
    <input type="submit" class="btn btn-info button_a"/>
</form><br>

</body>
</html>
