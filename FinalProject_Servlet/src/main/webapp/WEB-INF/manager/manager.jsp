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
<body class="manager">
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="messages"/>
<div>
<button class="btn btn-light left_button" type="button">
    <a class="button_locale" href="${pageContext.request.contextPath}/logout"><fmt:message key="main.page"/></a>
</button>
<button class="btn btn-light right_button" type="button"><a class="button_locale" href="?locale=uk">UKR</a></button>
<button class="btn btn-light right_button" type="button"><a class="button_locale" href="?locale=en">ENG</a></button>
</div>
<br>
<br>
<div class="card">
    <table class="table table-striped">
        <thead>
        <tr>
            <th>#</th>
            <th><fmt:message key="username"/></th>
            <th><fmt:message key="type"/></th>
            <th><fmt:message key="weight"/></th>
            <th><fmt:message key="volume"/></th>
            <th><fmt:message key="address"/></th>
            <th><fmt:message key="date.of.arrival"/> </th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="request" items="${deliveryRequestList}">
            <tr>
                <td><c:out value="${request.id}"/></td>
                <td><c:out value="${request.user.username}"/></td>
                <td>
                    <c:if test="${sessionScope.locale=='en'}"><c:out value="${request.type_en}"/></c:if>
                    <c:if test="${sessionScope.locale=='uk'}"><c:out value="${request.type_uk}"/></c:if>
                </td>
                <td><c:out value="${request.weight}"/></td>
                <td><c:out value="${request.volume}"/></td>
                <td>
                    <c:if test="${sessionScope.locale=='en'}"><c:out value="${request.address.address_en}"/></c:if>
                    <c:if test="${sessionScope.locale=='uk'}"><c:out value="${request.address.address_uk}"/></c:if>
                </td>
                <td><c:out value="${request.dateOfArrival}"/></td>
                <td>  <button class="btn btn-info" type="button">
                    <a class="button_a" href="${pageContext.request.contextPath}/receipt?id=${request.getId()}">
                        <fmt:message key="create.receipt"/>
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
    <label for="city" ><fmt:message key="city"/> </label>
    <input type="text" name="city" id="city"/>
    <input type="hidden" name="page" value="1" />
    <input type="submit" class="btn btn-info button_a" value="<fmt:message key="direction.report"/> "/>
</form>
<br>
<form  action="${pageContext.request.contextPath}/reportByDays"  class="report">
    <label for="day"><fmt:message key="date.of.arrival"/> </label>
    <input type="date" name="day" id="day"/>
    <input type="hidden" name="page" value="1" />
    <input type="submit" class="btn btn-info button_a" value="<fmt:message key="report.by.days"/> "/>
</form><br>

</body>
</html>
