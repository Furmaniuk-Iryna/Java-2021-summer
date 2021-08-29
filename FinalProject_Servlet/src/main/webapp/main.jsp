<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
    <%@include file="/css/style.css"%>
</style>
<html>
<head>
<%--    <meta charset="UTF-8">--%>
<%--    <meta name="viewport" content="width=device-width, initial-scale=1">--%>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <title>Cargo delivery</title>
</head>
<body>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="messages"/>


<%-- <% List<Tariff> tariffs = (List<Tariff>) request.getAttribute("tariffs"); %>--%>
<div class="header_top">
    <div class="locale">
        <div class="d-grid gap-2 d-md-flex justify-content-md-end">
            <button class="btn btn-light" type="button">
                <a class="button_locale" href="${pageContext.request.contextPath}/login"><fmt:message key="login"/></a>
            </button>
            <button class="btn btn-light" type="button">
                <a class="button_locale" href="${pageContext.request.contextPath}/registration"><fmt:message
                        key="registration"/></a>
            </button>
            <button class="btn btn-light" type="button"><a class="button_locale" href="?locale=uk">UKR</a></button>
            <button class="btn btn-light" type="button"><a class="button_locale" href="?locale=en">ENG</a></button>
        </div>
    </div>
</div>

<form action="${pageContext.request.contextPath}/main" class="report">
    <label for="d">City</label>
    <input type="text" name="city" id="d"/>
    <input type="hidden" name="filter" value="true"/>
    <input type="hidden" name="locale" value="${locale}">
    <input type="submit" class="btn btn-info button_a"/>
    <button class="btn btn-info sort_button" type="button">
        <a class="button_a" href="?locale=${locale}&sort=false&filter=false">Reset</a>
    </button>
    <button class="btn btn-info sort_button" type="button">
        <a class="button_a" href="?locale=${locale}&sort=true"><fmt:message key="sort"/></a>
    </button>
</form>

<br><br>
<div class="tariff">
    <h2 class="tariffs"><fmt:message key="tariffs"/></h2>
    <table class="table table-dark">
        <thead>
        <tr>
            <th>#</th>
            <th><fmt:message key="name"/></th>
            <th><fmt:message key="weight"/></th>
            <th><fmt:message key="volume"/></th>
            <th><fmt:message key="conditions"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="tariff" items="${tariffList}">
            <tr>
                <td><c:out value="${tariff.id}"/></td>
                <td>
                    <c:if test="${locale=='en'}"><c:out value="${tariff.tariffNameEn}"/></c:if>
                    <c:if test="${locale=='uk'}"><c:out value="${tariff.tariffNameUk}"/></c:if>
                </td>
                <td><c:out value="${tariff.tariffForWeight}"/></td>
                <td><c:out value="${tariff.tariffForVolume}"/></td>
                <td>
                    <c:if test="${locale=='en'}"><c:out value="${tariff.descriptionEn}"/></c:if>
                    <c:if test="${locale=='uk'}"><c:out value="${tariff.descriptionUk}"/></c:if>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</div>

<div class="directions">
    <h2 class="title"><fmt:message key="directions"/></h2>
    <table class="table table-striped">
        <thead>
        <tr>
            <th>#</th>
            <th><fmt:message key="city"/></th>
            <th><fmt:message key="distance"/></th>
        </tr>
        </thead>
        <tbody>
        <div><c:if test="${sort&&locale=='en'&&!filter}">
            <c:forEach var="direction" items="${sortedDirectionsEnLocale}">
                <tr>
                    <td><c:out value="${direction.id}"/></td>
                    <td>
                        <c:if test="${locale=='en'}">
                            <c:out value="${direction.cityEn}"/>
                        </c:if>
                    <td><c:out value="${direction.distance}"/></td>
                </tr>
            </c:forEach></c:if>
            <c:if test="${sort&&locale=='uk'&&!filter}">
                <c:forEach var="direction" items="${sortedDirectionsUkLocale}">
                    <tr>
                        <td><c:out value="${direction.id}"/></td>
                        <td><c:if test="${locale=='uk'}"><c:out value="${direction.cityUk}"/></c:if></td>
                        <td><c:out value="${direction.distance}"/></td>
                    </tr>
                </c:forEach></c:if>
            <c:if test="${!sort&&!filter}">
                <c:forEach var="direction" items="${directions}">
                    <tr>
                        <td><c:out value="${direction.id}"/></td>
                        <td><c:if test="${locale=='en'}"><c:out value="${direction.cityEn}"/></c:if>
                            <c:if test="${locale=='uk'}"><c:out value="${direction.cityUk}"/></c:if></td>
                        <td><c:out value="${direction.distance}"/></td>
                    </tr>
                </c:forEach></c:if>
            <c:if test="${filter}">
                <tr>
                    <td><c:out value="${filteredDirection.id}"/></td>
                    <td><c:if test="${locale=='en'}"><c:out value="${filteredDirection.cityEn}"/></c:if>
                        <c:if test="${locale=='uk'}"><c:out value="${filteredDirection.cityUk}"/></c:if></td>
                    <td><c:out value="${filteredDirection.distance}"/></td>
                </tr>
            </c:if>
        </div>
        </tbody>
    </table>
</div>

<div class="cost">
    <h2 class="title"><fmt:message key="calculate"/></h2>
    <form action="${pageContext.request.contextPath}/main" class="delivery_cost">
        <input type="hidden" value="true" name="form"/>
        <input type="hidden" value="${sessionScope.locale}" name="locale"/>
        <br>
        <label for="weight"><fmt:message key="weight"/> </label>
        <input type="text" value="${pageContext.request.getParameter("weight")}" name="weight" id="weight"
               placeholder="0"/>
        <br><br>

        <label for="length"><fmt:message key="length"/></label>
        <input type="text" value="${pageContext.request.getParameter("length")}" name="length" id="length"
               placeholder="0"/>
        <br><br>

        <label for="width"><fmt:message key="width"/></label>
        <input type="text" value="${pageContext.request.getParameter("width")}" name="width" id="width"
               placeholder="0"/>
        <br><br>

        <label for="height"><fmt:message key="height"/></label>
        <input type="text" value="${pageContext.request.getParameter("height")}" name="height" id="height"
               placeholder="0"/>
        <br><br>
        <label for="height"><fmt:message key="direction"/></label>

        <select name="route">
            <c:forEach var="direction" items="${directions}">
                <c:choose>
                    <c:when test='${pageContext.request.getParameter("route")!=null && pageContext.request.getParameter("route").equals(direction.cityEn)}'>
                        <option value="${direction.cityEn}" selected>
                            <c:if test="${locale=='en'}"><c:out value="${direction.cityEn}"/></c:if>
                            <c:if test="${locale=='uk'}"><c:out value="${direction.cityUk}"/></c:if>
                        </option>
                    </c:when>

                    <c:otherwise>
                        <option value="${direction.cityEn}">
                            <c:if test="${locale=='en'}"><c:out value="${direction.cityEn}"/></c:if>
                            <c:if test="${locale=='uk'}"><c:out value="${direction.cityUk}"/></c:if>
                        </option>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </select>
        <br><br>
        <input class="btn btn-info button_a" type="submit" value="<fmt:message key="calculate"/>"/>
        <br>
        <p class="cost_result"><c:if test="${sessionScope.cost!=null}"><fmt:message
                key="delivery.costs"/> ${sessionScope.cost}</c:if></p>
    </form>
</div>
</body>
</html>