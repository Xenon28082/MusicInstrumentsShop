<%--
  Created by IntelliJ IDEA.
  User: savva
  Date: 30.11.2021
  Time: 16:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
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
            <span>Каталог</span></a>
            <ul>
                <li><a href="FrontController?COMMAND=GET_PRODUCTS&TYPE=guitar">Гитары</a></li>
                <li><a href="FrontController?COMMAND=GET_PRODUCTS&TYPE=keys">Клавишные</a></li>
                <li><a href="FrontController?COMMAND=GET_PRODUCTS&TYPE=percussion">Перкуссионные</a></li>
                <li><a href="FrontController?COMMAND=GET_PRODUCTS&TYPE=whistle">Духовые</a></li>
            </ul>
        </li>

        <li><a class="mainRef" href="FrontController?COMMAND=GET_VENDORS">
            <div class="img_n"><img src="https://img.icons8.com/ios/50/ffffff/plus--v1.png"/></div>
            <span>Добавить новый товар</span></a>
        </li>

        <li><a class="mainRef" href="changeUserPage.jsp">
            <div class="img_n"><img src="https://img.icons8.com/ios/50/ffffff/lifecycle--v1.png"/></div>
            <span>Изменить роль пользователя</span></a>
        </li>

        <li><a class="mainRef" href="FrontController?COMMAND=GET_ORDERS">
            <div class="img_n"><img src="https://img.icons8.com/ios/50/ffffff/lifecycle--v1.png"/></div>
            <span>Изменить статус заказа</span></a>
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
