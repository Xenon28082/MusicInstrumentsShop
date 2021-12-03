<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="Assets/CSS/VerticalMenu.css">
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
        <div class="ico" style="display: flex; justify-content: space-between; padding-right: 90px; width: 150px">
            <p>
                <%=(String) session.getAttribute("login")%>
            </p>
            <img src="https://img.icons8.com/external-kiranshastry-solid-kiranshastry/64/000000/external-user-interface-kiranshastry-solid-kiranshastry-1.png"/>
            <form action="FrontController" method="get">
                <input type="hidden" name="COMMAND" value="GET_USER_INFO">
                <input type="hidden" name="userLogin" value="${sessionScope.login}">
                <button type="submit">Change profile</button>
            </form>

        </div>
    </div>

</header>


<nav id="menuVertical">
    <ul>
        <li><a class="mainRef" href="FrontController?COMMAND=GET_PRODUCTS&page=0&shift=3">
            <div class="img_n"><img class="try" src="https://img.icons8.com/ios/50/ffffff/progressive-rock.png"/></div>
            <span>Каталог</span></a>
            <ul>
                <li><a href="FrontController?COMMAND=GET_PRODUCTS&TYPE=guitar">Гитары</a></li>
                <li><a href="FrontController?COMMAND=GET_PRODUCTS&TYPE=keys">Клавишные</a></li>
                <li><a href="FrontController?COMMAND=GET_PRODUCTS&TYPE=percussion">Перкуссионные</a></li>
                <li><a href="FrontController?COMMAND=GET_PRODUCTS&TYPE=whistle">Духовые</a></li>
            </ul>
        </li>

        <li><a class="mainRef" href="FrontController?COMMAND=SHOW_BASKET">
            <div class="img_n"><img src="https://img.icons8.com/ios/50/ffffff/shopping-basket.png"/></div>
            <span>Корзина</span></a>
        </li>

        <li><a class="mainRef" href="FrontController?COMMAND=SHOW_ORDER">
            <div class="img_n"><img src="https://img.icons8.com/ios/50/ffffff/shopping-basket.png"/></div>
            <span>Заказ</span></a>
        </li>

        <li><a class="mainRef" href="FrontController?COMMAND=LOGOUT">
            <div class="img_n"><img src="https://img.icons8.com/ios/50/ffffff/exit.png"/></div>
            <span>Выход</span></a>
        </li>

    </ul>
</nav>
<div class="menu">
    <div class="footer">Copyright</div>
</div>
</body>
</html>
