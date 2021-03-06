<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
<body class="delivery_request">
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="messages"/>
<button class="btn btn-light" type="button"><a class="button_locale" href="?locale=uk">UKR</a></button>
<button class="btn btn-light" type="button"><a class="button_locale" href="?locale=en">ENG</a></button>
<br>

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

<c:if test="${sessionScope.save}">
    <div  class="alert alert-primary d-flex align-items-center" role="alert">
        <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="currentColor"
             class="bi bi-exclamation-triangle-fill flex-shrink-0 me-2" viewBox="0 0 16 16" role="img"
             aria-label="Warning:">
            <path d="M8.982 1.566a1.13 1.13 0 0 0-1.96 0L.165 13.233c-.457.778.091 1.767.98 1.767h13.713c.889 0 1.438-.99.98-1.767L8.982 1.566zM8 5c.535 0 .954.462.9.995l-.35 3.507a.552.552 0 0 1-1.1 0L7.1 5.995A.905.905 0 0 1 8 5zm.002 6a1 1 0 1 1 0 2 1 1 0 0 1 0-2z"/>
        </svg>
        <div><fmt:message key="save.request"/> </div>
    </div><br>
</c:if>

<form method="POST" action="${pageContext.request.contextPath}/delivery-request" class="form_request">

    <label for="type"><fmt:message key="type"/></label>
    <select class="form-select" name="typeEn" id="type">
        <option value="Cargo"><fmt:message key="cargo"/></option>
        <option value="Pallets"><fmt:message key="pallets"/></option>
    </select>
    <br>
    <label for="weight"> <fmt:message key="weight"/></label>
    <input type="text" name="weight" id="weight"/>
    <br><br>
    <label for="length"><fmt:message key="length"/></label>
    <input type="text" name="length" id="length"/>
    <br><br>


    <label for="width"><fmt:message key="width"/></label>
    <input type="text" name="width" id="width"/>
    <br><br>

    <label for="height"><fmt:message key="height"/></label>
    <input type="text" name="height" id="height"/>
    <br><br>

    <label for="city"><fmt:message key="address"/></label>
    <select class="form-select" id="city" name="address">
        <c:forEach var="address" items="${addresses}">
            <option value="${address.id}">
                <c:if test="${sessionScope.locale=='en'}"><c:out value="${address.address_en}"/></c:if>
                <c:if test="${sessionScope.locale=='uk'}"><c:out value="${address.address_uk}"/></c:if>
            </option>
        </c:forEach>
    </select>
    <br><br>
    <input class="btn btn-info button_a" type="submit" value="<fmt:message key="create.delivery.request"/>"/>
    <button class="btn btn-light" type="button">
        <a class="button_locale" href="${pageContext.request.contextPath}/user"><fmt:message key="user.page"/></a>
    </button>
</form>

</body>
</html>
