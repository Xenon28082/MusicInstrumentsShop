
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>

<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="Assets/CSS/ItemBlock.css">
    <link rel="stylesheet" href="Assets/CSS/VerticalMenu.css">
</head>
<body>
<jsp:include page="Assets/MenuJSPs/UserMenu.jsp"/>
<c:set var="order" value="${sessionScope.orders}"/>
<c:set var="message" value="${param.message}"/>
<div class="blockCont" style="display: flex; justify-content: flex-start; flex-wrap: wrap;">
    <c:forEach var="product" items="${order}">
        <div class="product">
            <div class="image">
                <img src="http://placehold.it/170x220">
            </div>
            <div class="info">
                <p>Order id - ${product.getOrderId()}</p>
                <p>User id - ${product.getUserId()}</p>
                <p>Product id - ${product.getProductId()}</p>
                <p>Amount - ${product.getAmount()}</p>
            </div>
        </div>
    </c:forEach>
    <c:if test="${message == 'noOrder'}">
        <div style="color: red">
            There is no products in your order
        </div>
    </c:if>
</div>

<c:if test="${order != null}">
    <div class="blockCont">
        <c:out value="${ids}"/>
        <form action="FrontController" method="post">
            <input type="hidden" name="COMMAND" value="CLOSE_ORDER">
            <button type="submit">Close order</button>
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
</div>
</body>
</html>
