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
<body class="user">
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="messages"/>
<div>
<button class="btn btn-light right_button" type="button"><a class="button_locale" href="?locale=uk">UKR</a></button>
<button class="btn btn-light right_button" type="button"><a class="button_locale" href="?locale=en">ENG</a></button>
<button disabled class="btn btn-light right_button" ><fmt:message key="balance"/> - ${balance}</button>
<button class="btn btn-light left_button" type="button">
    <a class="button_locale" href="${pageContext.request.contextPath}/logout"><fmt:message key="main.page"/> </a>
</button>
<button class="btn btn-light left_button" type="button">
    <a class="button_locale" href="${pageContext.request.contextPath}/delivery-request"><fmt:message key="create.delivery.request"/></a>
</button>
</div>
<br>
<div>
<c:if test="${paid=='fail'}">
<div  class="alert alert-primary d-flex align-items-center" role="alert">
    <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="currentColor"
         class="bi bi-exclamation-triangle-fill flex-shrink-0 me-2" viewBox="0 0 16 16" role="img"
         aria-label="Warning:">
        <path d="M8.982 1.566a1.13 1.13 0 0 0-1.96 0L.165 13.233c-.457.778.091 1.767.98 1.767h13.713c.889 0 1.438-.99.98-1.767L8.982 1.566zM8 5c.535 0 .954.462.9.995l-.35 3.507a.552.552 0 0 1-1.1 0L7.1 5.995A.905.905 0 0 1 8 5zm.002 6a1 1 0 1 1 0 2 1 1 0 0 1 0-2z"/>
    </svg>
    <div><fmt:message key="fail.pay"/> </div>
</div>
</c:if>
<c:if test="${paid=='successful'}">
<div class="alert alert-primary d-flex align-items-center" role="alert">
    <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="currentColor" class="bi bi-exclamation-triangle-fill flex-shrink-0 me-2" viewBox="0 0 16 16" role="img" aria-label="Warning:">
        <path d="M8.982 1.566a1.13 1.13 0 0 0-1.96 0L.165 13.233c-.457.778.091 1.767.98 1.767h13.713c.889 0 1.438-.99.98-1.767L8.982 1.566zM8 5c.535 0 .954.462.9.995l-.35 3.507a.552.552 0 0 1-1.1 0L7.1 5.995A.905.905 0 0 1 8 5zm.002 6a1 1 0 1 1 0 2 1 1 0 0 1 0-2z"/>
    </svg>
    <div><fmt:message key="successful.pay"/> </div>
</div>
</c:if>
</div>
<br><br>
<div class="card">
    <table class="table table-striped">
        <thead>
        <tr>
            <th>#</th>
            <th><fmt:message key="type"/></th>
            <th><fmt:message key="weight"/></th>
            <th><fmt:message key="volume"/></th>
            <th><fmt:message key="address"/></th>
            <th><fmt:message key="date.of.arrival"/> </th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="request" items="${requests}">
            <tr>
                <td><c:out value="${request.id}"/></td>
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
                <td>
                    <c:forEach var="receipt" items="${receipts}">
                        <c:if test="${receipt.deliveryRequest.id==request.id}">
                            <c:out value="${receipt.price}"/>
                            <c:if test="${receipt.status!='paid'}">
                                <button class="btn btn-info" type="button">
                                    <a class="button_a" href="${pageContext.request.contextPath}/user?page=${currentPage}&receipt=${request.id}">Pay</a>
                                </button>
                            </c:if>
                        </c:if>
                    </c:forEach>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <nav>
        <ul class="pagination">
            <c:forEach begin="1" end="${noOfPages}" var="i">
                <c:choose>
                    <c:when test="${currentPage eq i}">
                        <li class="page-item active"><a class="page-link" href="user?page=${i}">${i}</a></li>
                    </c:when>
                    <c:otherwise>
                        <li class="page-item"><a class="page-link" href="user?page=${i}">${i}</a></li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </ul>
    </nav>
</div>
<form action="${pageContext.request.contextPath}/user" class="recharge">
    <input type="hidden" name="page" value="${currentPage}"/>
    <input type="hidden" name="recharge" value="true"/>
    <select class="form-select" name="sum">
        <option value="1">1</option>
        <option value="5">5</option>
        <option value="10">10</option>
        <option value="50">50</option>
        <option value="100">100</option>
        <option value="500">500</option>
        <option value="1000">1000</option>
    </select>
    <input type="submit" class="btn btn-info button_a" value="<fmt:message key="replenish.balance"/> "/>
</form>
</body>
</html>
