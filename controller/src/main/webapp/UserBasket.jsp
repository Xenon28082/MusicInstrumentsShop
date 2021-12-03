<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="Assets/CSS/ItemBlock.css">
    <link rel="stylesheet" href="Assets/CSS/VerticalMenu.css">
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
<c:set var="error" value="${param.message}"/>

<div class="blockCont" style="display: flex; justify-content: flex-start; flex-wrap: wrap;">
    <c:if test="${error == 'basketEmpty'}">
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
                <div><c:out value="orderId - ${order.getOrderId()}"/></div>
                <div><c:out value="ProdName - ${order.getProduct().getName()}"/></div>
                <div><c:out value="ProdPrice - ${order.getProduct().getPrice()}"/></div>
                <div><c:out value="ProdVendor - ${order.getProduct().getVendor()}"/></div>
                <div><c:out value="ProdId - ${order.getProduct().getId()}"/></div>
                <div><c:out value="Amount - ${order.getAmount()}"/></div>


                <form method="post" action="FrontController">
                    <input type="hidden" name="COMMAND" value="DELETE_FROM_BASKET"/>
                    <input type="hidden" name="orderId" value="${fn:escapeXml(order.getOrderId())}"/>
                    <input type="hidden" name="productId" value="${fn:escapeXml(order.getProduct().getId())}"/>
                    <input type="number" name="productAmount" value="0" required>
                    <button class="addToCart" type="submit">DELETE</button>
                </form>
                <c:if test="${order.getAmount() != 0}">
                    <form method="post" id="reserveForm" action="FrontController">
                        <input type="hidden" name="COMMAND" value="RESERVATE_PRODUCT">
                        <c:if test="${order.isReserved()}">
                            <input disabled type="checkbox" checked/>
                            <input type="hidden" name="isreserved" value="delete">
                            <input type="hidden" name="orderId" value="${order.getOrderId()}">
                            <button type="submit">Delete from reserve</button>
                        </c:if>
                        <c:if test="${!order.isReserved()}">
                            <input disabled type="checkbox"/>
                            <input type="hidden" name="isreserved" value="add">
                            <input type="hidden" name="orderId" value="${order.getOrderId()}">
                            <button type="submit">Add to reserve</button>
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
    <c:if test="${error == 'failed'}">
        <div style="border: red solid 2px">
            You can't delete less than 1 item
        </div>
    </c:if>
</div>


</body>
</html>
