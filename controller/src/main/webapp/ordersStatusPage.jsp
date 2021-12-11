<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="Assets/CSS/ItemBlock.css">
    <link rel="stylesheet" href="Assets/CSS/VerticalMenu.css">
    <c:set var="locale" value="${sessionScope.get('locale')}"/>
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
<c:set var="userId" value="${requestScope.userId}"/>
<c:set var="test" value="${requestScope.test}"/>
<jsp:include page="Assets/MenuJSPs/AdminMenu.jsp"/>

<div class="blockCont">
    <c:forEach var="finalOrder" items="${test}">

        <div class="info" style="border: green solid 2px; display: flex; justify-content: flex-start">
            <c:out value="User id - ${finalOrder.getUserId()}"/>
            <c:forEach var="item" items="${finalOrder.getProducts()}">
                <div class="info" style="border: green solid 2px">
                    <div><c:out value="${itemNameLabel} - ${item.getName()}"/></div>
                    <div><c:out value="${itemPriceLabel} - ${item.getPrice()}"/></div>
                    <div><c:out value="${itemVendorLabel} - ${item.getVendor()}"/></div>
                </div>
            </c:forEach>
            <form action="FrontController" method="post">
                <input type="hidden" name="orderId" value="${finalOrder.getFinalOrderId()}">
                <input type="hidden" name="COMMAND" value="ACCEPT_FINAL_ORDER">
                <button type="submit">${acceptLabel}</button>
            </form>

            <form action="FrontController" method="post">
                <input type="hidden" name="orderId" value="${finalOrder.getFinalOrderId()}">
                <input type="hidden" name="COMMAND" value="REFUSE_FINAL_ORDER">
                <button type="submit">${refuseLabel}</button>
            </form>
        </div>
    </c:forEach>
</div>
</body>
</html>
