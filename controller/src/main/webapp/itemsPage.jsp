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

<header>
    <div class="head" style="display: flex; justify-content: space-between">
        <div class="ico">
            <a href="#">
                <img src="Assets/images/test.png">
            </a>
        </div>
        <div class="ico" style="display: flex; justify-content: space-between; padding-right: 30px; width: 150px">
            <img src="https://img.icons8.com/external-kiranshastry-solid-kiranshastry/64/000000/external-user-interface-kiranshastry-solid-kiranshastry-1.png"/>
            <p>
                <%=(String) session.getAttribute("login")%>
            </p>
        </div>
    </div>
</header>


<nav id="menuVertical">
    <ul>
        <li><a class="mainRef" href="FrontController?COMMAND=GET_PRODUCTS">
            <div class="img_n"><img class="try" src="https://img.icons8.com/ios/50/000000/progressive-rock.png"/></div>
            <span>Каталог</span></a>
            <ul>
                <li><a href="FrontController?COMMAND=GET_PRODUCTS&TYPE=guitar">Гитары</a></li>
                <li><a href="FrontController?COMMAND=GET_PRODUCTS&TYPE=keys">Клавишные</a></li>
                <li><a href="FrontController?COMMAND=GET_PRODUCTS&TYPE=percussion">Перкуссионные</a></li>
                <li><a href="FrontController?COMMAND=GET_PRODUCTS&TYPE=whistle">Духовые</a></li>
            </ul>
        </li>

        <li><a class="mainRef" href="FrontController?COMMAND=SHOW_BASKET">
            <div class="img_n"><img src="https://img.icons8.com/ios/50/000000/shopping-basket.png"/></div>
            <span>Корзина</span></a>
        </li>

        <li><a class="mainRef" href="#3">
            <div class="img_n"><img src="https://img.icons8.com/ios/50/000000/like--v2.png"/></div>
            <span>Понравившиеся</span></a>
        </li>

        <li><a class="mainRef" href="FrontController?COMMAND=LOGOUT">
            <div class="img_n"><img src="https://img.icons8.com/ios/50/000000/exit.png"/></div>
            <span>Выход</span></a>
        </li>

    </ul>
</nav>
<div class="menu">
    <div class="footer">Copyright</div>
</div>

<c:set var="items" value="${requestScope.items}"/>
<c:set var="type" value="${requestScope.type}"/>
<c:set var="userRole" value="${sessionScope.role}"/>

<div class="blockCont" style="display: flex; justify-content: flex-start; flex-wrap: wrap;">
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
                <c:if test="${userRole == 2}">
                    <form method="post" action="FrontController">
                        <input type="hidden" name="TYPE" value="${type}"/>
                        <input type="hidden" name="COMMAND" value="ADD_TO_BASKET"/>
                        <input type="hidden" name="productId" value="${fn:escapeXml(item.getId())}"/>
                        <input type="number" name="productAmount" min="1" value="1">
                        <button class="addToCart" type="submit"><img
                                src="https://img.icons8.com/material-outlined/24/ffffff/plus--v1.png"/></button>
                    </form>
                </c:if>
            </div>

        </div>
    </c:forEach>

</div>
</body>

</html>
