<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 22.08.2021
  Time: 18:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
<form method="POST" action="${pageContext.request.contextPath}/delivery-request-save" class="form_request">


    <select name="typeEn">
        <option value="Cargo">Cargo</option>
        <option value="Pallets">Pallets</option>
    </select>
    <br>
    <label for="weight">Weight </label>
    <input type="text" name="weight" id="weight"/>
    <br><br>
    <label for="length">Length</label>
    <input type="text" name="length" id="length"/>
    <br><br>


    <label for="width">Width</label>
    <input type="text" name="width" id="width"/>
    <br><br>

    <label for="height">Height</label>
    <input type="text" name="height" id="height"/>
    <br><br>

    <label for="city">Address</label>
    <select class="form-select" aria-label="Default select example" id="city" name="address">
 <c:forEach var="address" items="${addresses}">
     <option value="${address.id}"><c:out value="${address.address_en}"/></option>
 </c:forEach>
    </select>
    <br><br>
    <input class="btn btn-info button_a" type="submit"/>
    <button class="btn btn-light" type="button">
        <a class="button_locale" href="${pageContext.request.contextPath}/user">User</a>
    </button>
</form>

</body>
</html>
