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

    <%
    if (session.getAttribute("id") == null) {
        String redirectURL = "index.jsp";
        response.sendRedirect(redirectURL);
    }
%>

<c:set var="items" value="${requestScope.items}"/>
<c:set var="type" value="${requestScope.type}"/>
<c:set var="userRole" value="${sessionScope.role}"/>
<c:set var="error" value="${param.message}"/>

<c:if test="${userRole == 2}">
    <jsp:include page="Assets/MenuJSPs/UserMenu.jsp"/>
</c:if>
<c:if test="${userRole == 1 || userRole == 3}">
        <jsp:include page="Assets/MenuJSPs/AdminMenu.jsp"/>
</c:if>


<div class="blockCont" style="display: flex; justify-content: flex-start; flex-wrap: wrap;">
    <c:if test="${error == 'noProducts'}">
        <div style="border: red solid 2px">
            There is no products
        </div>
    </c:if>
    <c:set var="message" value="${param.message}"/>

    <c:if test="${message=='negative'}">
        <div style="border: red solid 2px"><c:out value="You can't insert negative values in input fields"/></div>
    </c:if>
    <c:forEach var="item" items="${items}">
        <div class="product">
            <div class="image">
                <img src="http://placehold.it/170x220">
            </div>

            <div class="info">
                <div><c:out value="ItemName - ${item.getName()}"/></div>
                <div><c:out value="ItemPrice - ${item.getPrice()}"/></div>
                <div><c:out value="ItemVendor - ${item.getVendor()}"/></div>
                <div><c:out value="InStock - ${item.getStock()}"/></div>
                <c:if test="${userRole == 1 || userRole == 3}">
                    <form method="post" action="FrontController">
                        <input type="hidden" name="COMMAND" value="DELETE_SOME"/>
                        <input type="hidden" name="productId" value="${item.getId()}">
                        <input type="number" name="deleteValue" value="0" required>
                        <button type="submit">Delete Some</button>
                    </form>
                    <form method="post" action="FrontController">
                        <input type="hidden" name="COMMAND" value="DELETE_SOME"/>
                        <input type="hidden" name="productId" value="${item.getId()}">
                        <input type="hidden" name="deleteValue" value="${item.getStock()}">
                        <button type="submit">Delete all</button>
                    </form>
                    <form method="post" action="FrontController">
                        <input type="hidden" name="COMMAND" value="ADD_SOME"/>
                        <input type="hidden" name="productId" value="${item.getId()}">
                        <input id="1" type="number" name="addValue" value="0" required>
                        <button type="submit">Add some</button>
                    </form>

                </c:if>
                <c:if test="${userRole == 2}">
                    <form method="post" action="FrontController">
                        <input type="hidden" name="TYPE" value="${type}"/>
                        <input type="hidden" name="COMMAND" value="ADD_TO_BASKET"/>
                        <input type="hidden" name="productId" value="${item.getId()}"/>
                        <input type="number" name="productAmount" value="1" required>
                        <button class="addToCart" type="submit"><img
                                src="https://img.icons8.com/material-outlined/24/ffffff/plus--v1.png"/></button>
                    </form>
                </c:if>
            </div>

        </div>
    </c:forEach>

</div>


</html>
