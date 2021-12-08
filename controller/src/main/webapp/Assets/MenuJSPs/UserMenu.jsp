<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="Assets/CSS/VerticalMenu.css">
    <c:set var="locale" value="${sessionScope.get('locale')}"/>
    <c:if test="${locale == null}">
        <fmt:setLocale value="en"/>
    </c:if>
    <c:if test="${locale != null}">
        <fmt:setLocale value="${locale}"/>
    </c:if>
    <fmt:setBundle basename="localization.local" var="loc"/>
    <fmt:message bundle="${loc}" key="catalogLabel" var="catalogLabel"/>
    <fmt:message bundle="${loc}" key="basketLabel" var="basketLabel"/>
    <fmt:message bundle="${loc}" key="orderLabel" var="orderLabel"/>
    <fmt:message bundle="${loc}" key="exitLabel" var="exitLabel"/>
    <fmt:message bundle="${loc}" key="changeProfileLabel" var="changeProfileLabel"/>
    <fmt:message bundle="${loc}" key="guitarLabel" var="guitarLabel"/>
    <fmt:message bundle="${loc}" key="keysLabel" var="keysLabel"/>
    <fmt:message bundle="${loc}" key="perkLabel" var="perkLabel"/>
    <fmt:message bundle="${loc}" key="whistLabel" var="whistLabel"/>
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
            <a href="userPage.jsp">
                <img src="Assets/images/test.png">
            </a>
        </div>
        <div class="ico">
            <form action="FrontController">
                <input type="hidden" name="COMMAND" value="CHANGE_LANG"/>
                <select name="localeSelect">
                    <option value="ru">RU</option>
                    <option value="en">EN</option>
                </select>
                <input type="hidden" name="pathBack" value="userPage.jsp"/>
                <p></p>
                <button type="submit">${changeLocaleLable}</button>
            </form>
        </div>
        <div class="ico" style="display: flex; justify-content: space-between; padding-right: 90px; width: 150px">
            <p>
                <%=(String) session.getAttribute("login")%>
            </p>
            <img src="https://img.icons8.com/external-kiranshastry-solid-kiranshastry/64/000000/external-user-interface-kiranshastry-solid-kiranshastry-1.png"/>
            <form action="FrontController" method="get">
                <input type="hidden" name="COMMAND" value="GET_USER_INFO">
                <input type="hidden" name="userLogin" value="${sessionScope.login}">
                <button type="submit">${changeProfileLabel}</button>
            </form>

        </div>
    </div>

</header>


<nav id="menuVertical">
    <ul>
        <li><a class="mainRef" href="FrontController?COMMAND=GET_PRODUCTS&page=0&shift=3">
            <div class="img_n"><img class="try" src="https://img.icons8.com/ios/50/ffffff/progressive-rock.png"/></div>
            <span>${catalogLabel}</span></a>
            <ul>
                <li><a href="FrontController?COMMAND=GET_PRODUCTS&TYPE=guitar">${guitarLabel}</a></li>
                <li><a href="FrontController?COMMAND=GET_PRODUCTS&TYPE=keys">${keysLabel}</a></li>
                <li><a href="FrontController?COMMAND=GET_PRODUCTS&TYPE=percussion">${perkLabel}</a></li>
                <li><a href="FrontController?COMMAND=GET_PRODUCTS&TYPE=whistle">${whistLabel}</a></li>
            </ul>
        </li>

        <li><a class="mainRef" href="FrontController?COMMAND=SHOW_BASKET">
            <div class="img_n"><img src="https://img.icons8.com/ios/50/ffffff/shopping-basket.png"/></div>
            <span>${basketLabel}</span></a>
        </li>

        <li><a class="mainRef" href="FrontController?COMMAND=SHOW_ORDER">
            <div class="img_n"><img src="https://img.icons8.com/ios/50/ffffff/shopping-basket.png"/></div>
            <span>${orderLabel}</span></a>
        </li>

        <li><a class="mainRef" href="FrontController?COMMAND=LOGOUT">
            <div class="img_n"><img src="https://img.icons8.com/ios/50/ffffff/exit.png"/></div>
            <span>${exitLabel}</span></a>
        </li>

    </ul>
</nav>
<div class="menu">
    <div class="footer">Copyright Xenon28082</div>
</div>
</body>
</html>
