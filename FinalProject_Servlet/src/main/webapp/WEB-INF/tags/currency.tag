<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="messages"/>

<c:if test="${sessionScope.locale=='en'}"><% double cost = (Double) session.getAttribute("cost");%>
    <fmt:message key="result"/> <%=Math.ceil(cost/27)+"$"%> </c:if>