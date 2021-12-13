<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="Assets/CSS/ItemBlock.css">
    <link rel="stylesheet" href="Assets/CSS/VerticalMenu.css">
    <c:set var="locale" value="${sessionScope.get('loc')}"/>
    <c:if test="${locale == null}">

        <fmt:setLocale value="en"/>
    </c:if>
    <c:if test="${locale != null}">
        <fmt:setLocale value="${locale}"/>
    </c:if>
    <fmt:setBundle basename="localization.local" var="loc"/>
    <fmt:message bundle="${loc}" key="itemNameLabel" var="itemNameLabel"/>
    <fmt:message bundle="${loc}" key="itemPriceLabel" var="itemPriceLabel"/>
    <fmt:message bundle="${loc}" key="itemVendorLabel" var="itemVendorLabel"/>
    <fmt:message bundle="${loc}" key="itemStock" var="itemStock"/>
    <fmt:message bundle="${loc}" key="acceptLabel" var="acceptLabel"/>
    <fmt:message bundle="${loc}" key="refuseLabel" var="refuseLabel"/>
</head>
<body>
<c:set var="finalOrders" value="${requestScope.finalOrders}"/>
<c:set var="message" value="${param.message}"/>
<jsp:include page="Assets/MenuJSPs/AdminMenu.jsp"/>

<div class="blockCont">
    <c:forEach var="finalOrder" items="${finalOrders}">
        <div class="info" style="border: green solid 2px; display: flex; justify-content: flex-start">
            <c:out value="User id - ${finalOrder.get(0).getUserId()}"/>
            <c:forEach var="item" items="${finalOrder}">
                <div class="info" style="border: green solid 2px">
                    <div><c:out value="${itemNameLabel} - ${item.getProduct().getName()}"/></div>
                    <div><c:out value="${itemPriceLabel} - ${item.getProduct().getPrice()}"/></div>
                    <div><c:out value="${itemVendorLabel} - ${item.getProduct().getVendor()}"/></div>
                </div>
            </c:forEach>
            <form action="FrontController" method="post">
                <input type="hidden" name="orderId" value="${finalOrder.get(0).getOrderId()}">
                <input type="hidden" name="COMMAND" value="UPDATE_FINAL_ORDER">
                <button type="submit">${acceptLabel}</button>
            </form>

        </div>
    </c:forEach>
</div>
<div class="blockCont">
    <c:if test="${message == 'failed'}">
        <div style="border: red solid 2px">
            Failed to close order
        </div>
    </c:if>
    <c:if test="${message == 'success'}">
        <div style="border: green solid 2px">
            Order successfully closed
        </div>
    </c:if>
    <c:if test="${message == 'noorders'}">
        <div style="border: green solid 2px">
            All orders are closed, there is no new orders.
        </div>
    </c:if>
</div>
</body>
</html>
