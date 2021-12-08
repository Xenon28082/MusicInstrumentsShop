<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <title>Title</title>
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
    <fmt:message bundle="${loc}" key="addNewItemLabel" var="addNewItemLabel"/>
    <fmt:message bundle="${loc}" key="guitarLabel" var="guitarLabel"/>
    <fmt:message bundle="${loc}" key="keysLabel" var="keysLabel"/>
    <fmt:message bundle="${loc}" key="perkLabel" var="perkLabel"/>
    <fmt:message bundle="${loc}" key="whistLabel" var="whistLabel"/>
    <fmt:message bundle="${loc}" key="changeUserRoleLabel" var="changeUserRoleLabel"/>
    <fmt:message bundle="${loc}" key="changeOrderStatusLabel" var="changeOrderStatusLabel"/>
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
            <a href="adminPage.jsp">
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
                <input type="hidden" name="pathBack" value="adminPage.jsp"/>
                <p></p>
                <button type="submit">${changeLocaleLable}</button>
            </form>
        </div>
        <div class="ico" style="display: flex; justify-content: space-between; padding-right: 30px; width: 150px">
            <img src="https://img.icons8.com/external-kiranshastry-solid-kiranshastry/64/ffffff/external-user-interface-kiranshastry-solid-kiranshastry-1.png"/>
            <p>
                <%=(String) session.getAttribute("login")%>
            </p>
        </div>
    </div>
</header>


<nav id="menuVertical">
    <ul>
        <li><a class="mainRef" href="FrontController?COMMAND=GET_PRODUCTS&page=0&shift=3">
            <div class="img_n"><img src="https://img.icons8.com/ios/50/ffffff/refresh-folder.png"/></div>
            <span>${catalogLabel}</span></a>
            <ul>
                <li><a href="FrontController?COMMAND=GET_PRODUCTS&TYPE=guitar">${guitarLabel}</a></li>
                <li><a href="FrontController?COMMAND=GET_PRODUCTS&TYPE=keys">${keysLabel}</a></li>
                <li><a href="FrontController?COMMAND=GET_PRODUCTS&TYPE=percussion">${perkLabel}</a></li>
                <li><a href="FrontController?COMMAND=GET_PRODUCTS&TYPE=whistle">${whistLabel}</a></li>
            </ul>
        </li>

        <li><a class="mainRef" href="FrontController?COMMAND=GET_VENDORS">
            <div class="img_n"><img src="https://img.icons8.com/ios/50/ffffff/plus--v1.png"/></div>
            <span>${addNewItemLabel}</span></a>
        </li>

        <li><a class="mainRef" href="changeUserPage.jsp">
            <div class="img_n"><img src="https://img.icons8.com/ios/50/ffffff/lifecycle--v1.png"/></div>
            <span>${changeUserRoleLabel}</span></a>
        </li>

        <li><a class="mainRef" href="FrontController?COMMAND=GET_ORDERS">
            <div class="img_n"><img src="https://img.icons8.com/ios/50/ffffff/lifecycle--v1.png"/></div>
            <span>${changeOrderStatusLabel}</span></a>
        </li>

        <li><a class="mainRef" href="FrontController?COMMAND=LOGOUT">
            <div class="img_n"><img src="https://img.icons8.com/ios/50/ffffff/exit.png"/></div>
            <span>${exitLabel}</span></a>
        </li>

    </ul>
</nav>
<div class="menu">
    <div class="footer">Copyright xenon28082</div>
</div>
</body>
</html>
