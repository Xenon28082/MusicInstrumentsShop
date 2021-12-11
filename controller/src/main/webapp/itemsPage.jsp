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
    <fmt:message bundle="${loc}" key="deleteSome" var="deleteSome"/>
    <fmt:message bundle="${loc}" key="deleteAll" var="deleteAll"/>
    <fmt:message bundle="${loc}" key="addSome" var="addSome"/>
</head>
<body>

<%
    if (session.getAttribute("id") == null) {
        String redirectURL = "index.jsp";
        response.sendRedirect(redirectURL);
    }
%>

<c:set var="items" value="${requestScope.items}"/>
<c:set var="count" value="${requestScope.count}"/>
<c:set var="type" value="${requestScope.type}"/>
<c:set var="userRole" value="${sessionScope.role}"/>
<c:set var="message" value="${param.message}"/>
<c:set var="shift" value="3"/>

<c:if test="${userRole == 2}">
    <jsp:include page="Assets/MenuJSPs/UserMenu.jsp"/>
</c:if>
<c:if test="${userRole == 1 || userRole == 3}">
    <jsp:include page="Assets/MenuJSPs/AdminMenu.jsp"/>
</c:if>


<div class="blockCont" style="display: flex; justify-content: flex-start; flex-wrap: wrap;">

    <c:forEach var="item" items="${items}">
        <div class="product">
            <div class="image">
                <img src="http://placehold.it/170x220">
            </div>

            <div class="info">
                <div><c:out value="${itemNameLabel} - ${item.getName()}"/></div>
                <div><c:out value="${itemPriceLabel} - ${item.getPrice()}"/></div>
                <div><c:out value="${itemPriceLabel} - ${item.getVendor()}"/></div>
                <div><c:out value="${itemStock} - ${item.getStock()}"/></div>
                <c:if test="${userRole == 1 || userRole == 3}">
                    <form method="post" action="FrontController">
                        <input type="hidden" name="COMMAND" value="DELETE_SOME"/>
                        <input type="hidden" name="productId" value="${item.getId()}">
                        <input type="number" name="deleteValue" value="0" required>
                        <button type="submit">${deleteSome}</button>
                    </form>
                    <form method="post" action="FrontController">
                        <input type="hidden" name="COMMAND" value="DELETE_SOME"/>
                        <input type="hidden" name="productId" value="${item.getId()}">
                        <input type="hidden" name="deleteValue" value="${item.getStock()}">
                        <button type="submit">${deleteAll}</button>
                    </form>
                    <form method="post" action="FrontController">
                        <input type="hidden" name="COMMAND" value="ADD_SOME"/>
                        <input type="hidden" name="productId" value="${item.getId()}">
                        <input id="1" type="number" name="addValue" value="0" required>
                        <button type="submit">${addSome}</button>
                    </form>

                </c:if>
                <c:if test="${userRole == 2}">
                    <form method="post" action="FrontController">
                        <input type="hidden" name="TYPE" value="${type}"/>
                        <input type="hidden" name="COMMAND" value="ADD_TO_BASKET"/>
                        <input type="hidden" name="productId" value="${item.getId()}"/>
                        <input type="number" name="productAmount" min="1" value="1" required>
                        <button class="addToCart" type="submit"><img
                                src="https://img.icons8.com/material-outlined/24/ffffff/plus--v1.png"/></button>
                    </form>
                </c:if>
            </div>

        </div>
    </c:forEach>
</div>
<c:if test="${type == null}">
    <div class="blockCont" style="display: flex; justify-content: space-around">
        <c:forEach begin="1" end="${Math.ceil(count / shift)}" var="i">
            <form action="FrontController">
                <input type="hidden" name="COMMAND" value="GET_PRODUCTS"/>
                <input type="hidden" name="shift" value="${shift}"/>
                <input type="hidden" name="page" value="${i - 1}"/>
                <button>${i}</button>
            </form>
        </c:forEach>
    </div>
</c:if>
<div class="blockCont">
    <c:if test="${message == 'noProducts'}">
        <div style="border: red solid 2px">
            There is no products
        </div>
    </c:if>
    <c:set var="message" value="${param.message}"/>

    <c:if test="${message=='negative'}">
        <div style="border: red solid 2px"><c:out value="You can't insert negative values in input fields"/></div>
    </c:if>
    <c:if test="${message=='more'}">
        <div style="border: red solid 2px"><c:out value="You can't delete more then is on stock"/></div>
    </c:if>

    <c:if test="${message=='less'}">
        <div style="border: red solid 2px"><c:out value="You can't add to basket more then is on stock"/></div>
    </c:if>
</div>
</body>
</html>
