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
    <fmt:message bundle="${loc}" key="amountLabel" var="amountLabel"/>
    <fmt:message bundle="${loc}" key="deleteLabel" var="deleteLabel"/>
    <fmt:message bundle="${loc}" key="addToOrderLabel" var="addToOrderLabel"/>
    <fmt:message bundle="${loc}" key="deleteFromOrderLabel" var="deleteFromOrderLabel"/>
</head>
<body>

<%
    if (session.getAttribute("id") == null) {
        String redirectURL = "index.jsp";
        response.sendRedirect(redirectURL);
    }
%>


<jsp:include page="Assets/MenuJSPs/UserMenu.jsp"/>


<c:set var="orders" value="${sessionScope.reservations}"/>
<c:set var="message" value="${param.message}"/>

<div class="blockCont" style="display: flex; justify-content: flex-start; flex-wrap: wrap;">
    <c:if test="${message == 'basketEmpty'}">
        <div style="border: red solid 2px">
            Basket is Empty
        </div>
    </c:if>
    <c:forEach var="order" items="${orders}">

        <div class="product">
            <div class="image">
                <img src="http://placehold.it/170x220">
            </div>

            <div class="info">
                <div><c:out value="${itemNameLabel} - ${order.getProduct().getName()}"/></div>
                <div><c:out value="${itemPriceLabel} - ${order.getProduct().getPrice()}"/></div>
                <div><c:out value="${itemVendorLabel} - ${order.getProduct().getVendor()}"/></div>
                <div><c:out value="${amountLabel} - ${order.getAmount()}"/></div>


                <form method="post" action="FrontController">
                    <input type="hidden" name="COMMAND" value="DELETE_FROM_BASKET"/>
                    <input type="hidden" name="orderId" value="${order.getOrderId()}"/>
                    <input type="hidden" name="productId" value="${order.getProduct().getId()}"/>
                    <input type="number" name="productAmount" min="1" value="1" required>
                    <button class="addToCart" type="submit">${deleteLabel}</button>
                </form>
                <c:if test="${order.getAmount() != 0}">
                    <form method="post" id="reserveForm" action="FrontController">
                        <input type="hidden" name="COMMAND" value="RESERVATE_PRODUCT">
                        <c:if test="${order.isReserved()}">
                            <input disabled type="checkbox" checked/>
                            <input type="hidden" name="isreserved" value="delete">
                            <input type="hidden" name="orderId" value="${order.getOrderId()}">
                            <button type="submit">${deleteFromOrderLabel}</button>
                        </c:if>
                        <c:if test="${!order.isReserved()}">
                            <input disabled type="checkbox"/>
                            <input type="hidden" name="isreserved" value="add">
                            <input type="hidden" name="orderId" value="${order.getOrderId()}">
                            <button type="submit">${addToOrderLabel}</button>
                        </c:if>
                    </form>
                </c:if>
                <c:if test="${order.getAmount() == 0}">
                    <div style="color: red"> You can't order this product.</div>
                    <div style="color: red">It's out of stock</div>
                </c:if>
            </div>


        </div>
    </c:forEach>

</div>

<div class="blockCont">
    <c:if test="${message == 'failed'}">
        <div style="border: red solid 2px">
            You can't delete less than 1 item
        </div>
    </c:if>
    <c:if test="${message == 'more'}">
        <div style="border: red solid 2px">
            You can't delete more then you have in basket
        </div>
    </c:if>
</div>


</body>
</html>
