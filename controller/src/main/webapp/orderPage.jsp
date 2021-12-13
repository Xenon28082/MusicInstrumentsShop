<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>

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
    <fmt:message bundle="${loc}" key="allOrdersLabel" var="allOrdersLabel"/>
    <fmt:message bundle="${loc}" key="orderIdLabel" var="orderIdLabel"/>
    <fmt:message bundle="${loc}" key="userIdLabel" var="userIdLabel"/>
    <fmt:message bundle="${loc}" key="productNameLabel" var="productNameLabel"/>
    <fmt:message bundle="${loc}" key="productPriceLabel" var="productPriceLabel"/>
    <fmt:message bundle="${loc}" key="productStockLabel" var="productStockLabel"/>
    <fmt:message bundle="${loc}" key="productTypeLabel" var="productTypeLabel"/>
    <fmt:message bundle="${loc}" key="productVendorLabel" var="productVendorLabel"/>
    <fmt:message bundle="${loc}" key="amountLabel" var="amountLabel"/>
    <fmt:message bundle="${loc}" key="payAndCloseLabel" var="payAndCloseLabel"/>
</head>
<body>
<jsp:include page="Assets/MenuJSPs/UserMenu.jsp"/>
<c:set var="orders" value="${sessionScope.orders}"/>
<c:set var="message" value="${param.message}"/>
<c:set var="finalPrice" value="${requestScope.orderPrice}"/>
<c:set var="isReserved" value="${requestScope.isreserved}"/>
<div class="blockCont" style="display: flex; justify-content: flex-start; flex-wrap: wrap;">
    <c:forEach var="order" items="${orders}">
        <c:if test="${isReserved}">
            <div class="bigProduct" style="border: #2ecc71 solid 2px">
                <div class="image">
                    <img src="http://placehold.it/170x220">
                </div>
                <div class="info">
                    <p>${orderIdLabel} - ${order.getOrderId()}</p>
                    <p>${userIdLabel} - ${order.getUserId()}</p>
                    <p>${productNameLabel} - ${order.getProduct().getName()}</p>
                    <p>${productPriceLabel} - ${order.getProduct().getPrice()}</p>
                    <p>${productTypeLabel} - ${order.getProduct().getType()}</p>
                    <p>${productVendorLabel} - ${order.getProduct().getVendor()}</p>
                    <p>${amountLabel} - ${order.getAmount()}</p>
                </div>
            </div>
        </c:if>
        <c:if test="${!isReserved}">
            <div class="bigProduct" style="border: red solid 2px">
                <div class="image">
                    <img src="http://placehold.it/170x220">
                </div>
                <div class="info">
                    <p>${orderIdLabel} - ${order.getOrderId()}</p>
                    <p>${userIdLabel} - ${order.getUserId()}</p>
                    <p>${productNameLabel} - ${order.getProduct().getName()}</p>
                    <p>${productPriceLabel} - ${order.getProduct().getPrice()}</p>
                    <p>${productTypeLabel} - ${order.getProduct().getType()}</p>
                    <p>${productVendorLabel} - ${order.getProduct().getVendor()}</p>
                    <p>${amountLabel} - ${order.getAmount()}</p>
                </div>
            </div>
        </c:if>
    </c:forEach>

    <c:if test="${message == 'noOrder'}">
        <div style="color: red">
            There is no products in your order
        </div>
    </c:if>
</div>

<c:if test="${orders != null}">
    <div class="blockCont">
        <c:out value="Final price: ${finalPrice}"/>
        <p></p>
        <c:out value="${ids}"/>
        <form action="FrontController" method="post">
            <input type="hidden" name="COMMAND" value="CLOSE_ORDER">
            <input type="hidden" name="finalPrice" value="${finalPrice}">
            <input type="number" name="paid" min = "1" max="999999" required>
            <button type="submit">${payAndCloseLabel}</button>
        </form>
    </div>
</c:if>
<div class="blockCont">
    <c:if test="${message == 'failed'}">
        <div style="color: red">Failed to register order</div>
    </c:if>
    <c:if test="${message == 'success'}">
        <div style="color: green">Order was successfully registered</div>
    </c:if>
    <c:if test="${message == 'exists'}">
        <div style="color: red">Such order already registered</div>
    </c:if>
    <c:if test="${message == 'negative'}">
        <div style="color: red">Entered value must be positive</div>
    </c:if>
    <c:if test="${message == 'empty'}">
        <div style="color: red">All fields must be fulfilled</div>
    </c:if>
    <c:if test="${message == 'notequal'}">
        <div style="color: red">Your paid value is not equal to he price of your order</div>
    </c:if>
</div>
</body>
</html>
