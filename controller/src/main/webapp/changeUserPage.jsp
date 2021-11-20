<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
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

        <li><a class="mainRef" href="FrontController?COMMAND=GET_VENDORS">
            <div class="img_n"><img src="https://img.icons8.com/ios/50/ffffff/plus--v1.png"/></div>
            <span>Добавить новый товар</span></a>
        </li>

        <li><a class="mainRef" href="FrontController?COMMAND=SHOW_BASKET">
            <div class="img_n"><img src="https://img.icons8.com/ios/50/000000/shopping-basket.png"/></div>
            <span>Изменить роль пользователя</span></a>
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
<c:set var="error" value="${param.message}"/>
<c:set var="foundUser" value="${requestScope.foundUser}"/>
<div class="blockCont" style="display: flex; justify-content: flex-start; flex-wrap: wrap;">
    <form method="post" action="FrontController?COMMAND=GET_USER_INFO">
        <input name="userLogin" placeholder="User Login"/>
        <button type="submit">Find</button>
    </form>
    <c:if test="${foundUser != null}">
        <form method="post" action="FrontController?COMMAND=UPDATE_USER">
            <div>
                <input type="hidden" name="userId" value="${foundUser.getId()}"/>
                <input type="hidden" name="userLastRole" value="${foundUser.getRole()}"/>
                <input name="userLogin" value="${foundUser.getLogin()}"/>
                <input name="userFirstname" value="${foundUser.getName()}"/>
                <input name="userLastname" value="${foundUser.getLastname()}"/>
                <select name="userRole">
                    <option value="1">Administrator</option>
                    <option value="2">Customer</option>
                </select>
<%--                <input type="number" min="1" max="2" name="userRole" value="${foundUser.getRole()}"/>--%>
                <button type="submit">Change</button>
            </div>
        </form>
    </c:if>
    <c:if test="${error == 'fieldsMustBeFulfilled'}">
        <p style="color: red">All fields must be fulfilled</p>
    </c:if>
    <c:if test="${error == 'mustBeADir'}">
        <p style="color: red">Only director can update admin role</p>
    </c:if>
    <c:if test="${error == 'Cant change yourself'}">
        <p style="color: red">Only director can update admin role</p>
    </c:if>
</div>
</body>