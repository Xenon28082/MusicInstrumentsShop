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
                    <div><c:out value="ItemName - ${item.getName()}"/></div>
                    <div><c:out value="ItemPrice - ${item.getPrice()}"/></div>
                    <div><c:out value="ItemVendor - ${item.getVendor()}"/></div>
                </div>
            </c:forEach>
            <form action="FrontController" method="post">
                <input type="hidden" name="orderId" value="${finalOrder.getFinalOrderId()}">
                <input type="hidden" name="COMMAND" value="ACCEPT_FINAL_ORDER">
                <button type="submit">Accept</button>
            </form>

            <form action="FrontController" method="post">
                <input type="hidden" name="orderId" value="${finalOrder.getFinalOrderId()}">
                <input type="hidden" name="COMMAND" value="REFUSE_FINAL_ORDER">
                <button type="submit">Refuse</button>
            </form>
        </div>
    </c:forEach>
</div>
</body>
</html>
