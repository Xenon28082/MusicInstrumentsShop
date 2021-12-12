<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="Assets/CSS/ItemBlock.css">
    <link rel="stylesheet" href="Assets/CSS/VerticalMenu.css">
</head>
<body>
<jsp:include page="Assets/MenuJSPs/UserMenu.jsp"/>
<c:set var="finalOrders" value="${requestScope.finalOrders}"/>
<div class="blockCont">
    <c:forEach var="finalOrder" items="${finalOrders}">
        <div class="info" style="border: #2ecc71 solid 2px">
            <c:if test="${finalOrder.isAccepted() && !finalOrder.isRefused()}">
                <div style="color: #2ecc71">Accepted</div>
            </c:if>
            <c:if test="${!finalOrder.isAccepted() && finalOrder.isRefused()}">
                <div style="color: red">Refused</div>
            </c:if>
            <c:if test="${!finalOrder.isAccepted() && !finalOrder.isRefused()}">
                <div style="color: yellow">Processing</div>
            </c:if>
            <c:forEach var="order" items="${finalOrder.getProducts()}">

                <c:out value="${order.getProduct().getName()}"/>
                <c:out value="${order.getProduct().getPrice()}"/>
                <c:out value="${order.getProduct().getType()}"/>
                <c:out value="${order.getProduct().getVendor()}"/>

            </c:forEach>
        </div>
    </c:forEach>
</div>
</body>
</html>
