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
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%--    <link rel="stylesheet" href="resources/public/css/style.css">--%>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <title>Cargo delivery</title>
</head>
<body>
<table class="table table-dark">
    <thead>
    <tr>
        <th>#</th>
        <th>Username</th>
        <th><fmt:message key="type"/></th>
        <th><fmt:message key="weight"/></th>
        <th><fmt:message key="volume"/></th>
        <th><fmt:message key="distance"/></th>
        <th>Date of arrival</th>

    </tr>
    </thead>
    <tbody>
    <c:forEach var="request" items="${reportByDays}">
        <tr>
            <td><c:out value="${request.id}"/></td>
            <td><c:out value="${request.user.username}"/></td>
            <td><c:out value="${request.type_en}"/></td>
            <td><c:out value="${request.weight}"/></td>
            <td><c:out value="${request.volume}"/></td>
            <td><c:out value="${request.address.address_en}"/></td>
            <td><c:out value="${request.dateOfArrival}"/></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<nav>
    <ul class="pagination">
        <c:forEach begin="1" end="${noOfPages}" var="i">
            <c:choose>
                <c:when test="${currentPage eq i}">
                    <li class="page-item active"><a class="page-link" href="reportByDays?day=${requestScope.reportByDays.get(1).dateOfArrival}&page=${i}">${i}</a></li>
                </c:when>
                <c:otherwise>
                    <li class="page-item"><a class="page-link" href="reportByDays?day=${requestScope.reportByDays.get(1).dateOfArrival}&page=${i}">${i}</a></li>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </ul>
</nav>
</body>
</html>
