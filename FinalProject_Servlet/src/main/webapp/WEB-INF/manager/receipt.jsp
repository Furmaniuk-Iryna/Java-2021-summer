<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 22.08.2021
  Time: 18:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
<h1>Receipt</h1>
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
        <th>Price</th>

    </tr>
    </thead>
    <tbody>
        <tr >
            <td><c:out value="${receipts.id}"/></td>
            <td><c:out value="${receipts.user.username}"/></td>
            <td><c:out value="${receipts.type_en}"/></td>
            <td><c:out value="${receipts.weight}"/></td>
            <td><c:out value="${receipts.volume}"/></td>
            <td><c:out value="${receipts.address.address_en}"/></td>
            <td><c:out value="${receipts.dateOfArrival}"/></td>
            <td><c:out value="${price}"/></td>
        </tr>
    </tbody>
</table>
<button class="btn btn-info" type="button">
    <a class="button_a" href="${pageContext.request.contextPath}/manager?page=1">
     Manager page
    </a>
</button>
</body>
</html>
