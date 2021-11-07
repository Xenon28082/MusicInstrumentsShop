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

<%--<header>--%>
<%--    <div class="head" style="display: flex; justify-content: space-between">--%>
<%--        <div class="ico">--%>
<%--            <a href="#">--%>
<%--                <img src="Assets/images/test.png">--%>
<%--            </a>--%>
<%--        </div>--%>
<%--        <div class="ico" style="display: flex; justify-content: space-between; padding-right: 30px; width: 150px">--%>
<%--            <img src="https://img.icons8.com/external-kiranshastry-solid-kiranshastry/64/000000/external-user-interface-kiranshastry-solid-kiranshastry-1.png"/>--%>
<%--            <p>--%>
<%--                <%=(String) session.getAttribute("login")%>--%>
<%--            </p>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--</header>--%>


<%--<nav id="menuVertical">--%>
<%--    <ul>--%>
<%--        <li><a class="mainRef" href="FrontController?COMMAND=GET_PRODUCTS">--%>
<%--            <div class="img_n"><img class="try" src="https://img.icons8.com/ios/50/000000/progressive-rock.png"/></div>--%>
<%--            <span>Каталог</span></a>--%>
<%--            <ul>--%>
<%--                <li><a href="FrontController?COMMAND=GET_PRODUCTS&TYPE=guitar">Гитары</a></li>--%>
<%--                <li><a href="FrontController?COMMAND=GET_PRODUCTS&TYPE=keys">Клавишные</a></li>--%>
<%--                <li><a href="FrontController?COMMAND=GET_PRODUCTS&TYPE=percussion">Перкуссионные</a></li>--%>
<%--                <li><a href="FrontController?COMMAND=GET_PRODUCTS&TYPE=whistle">Духовые</a></li>--%>
<%--            </ul>--%>
<%--        </li>--%>

<%--        <li><a class="mainRef" href="FrontController?COMMAND=SHOW_BASKET">--%>
<%--            <div class="img_n"><img src="https://img.icons8.com/ios/50/000000/shopping-basket.png"/></div>--%>
<%--            <span>Корзина</span></a>--%>
<%--        </li>--%>

<%--        <li><a class="mainRef" href="FrontController?COMMAND=ADD_NEW_USER">--%>
<%--            <div class="img_n"><img src="https://img.icons8.com/ios/50/ffffff/plus--v1.png"/></div>--%>
<%--            <span>Добавить новый товар</span></a>--%>
<%--        </li>--%>

<%--        <li><a class="mainRef" href="#4">--%>
<%--            <div class="img_n"><img src="https://img.icons8.com/ios/50/000000/exit.png"/></div>--%>
<%--            <span>Выход</span></a>--%>
<%--        </li>--%>

<%--    </ul>--%>
<%--</nav>--%>
<%--<div class="menu">--%>
<%--    <div class="footer">Copyright</div>--%>
<%--</div>--%>

<c:set var="vendors" value="${requestScope.vendors}"/>
<div style="border: red solid 3px">

    <form method="post" action="FrontController?COMMAND=ADD_NEW_ITEM">
        <input type="text" name="productName" placeholder="Product name" autocomplete="off">
        <p></p>
        <input type="number" name="productPrice" placeholder="Product price" autocomplete="off">
        <p></p>
        <input type="number" name="productStock" placeholder="Product stock" autocomplete="off">
        <p></p>
        <input type="text" name="productType" placeholder="Product type" autocomplete="off">
        <p></p>
        <%--        <input type="number" name="productVendor" placeholder="Product vendor" autocomplete="off">--%>
        <select name="vendorSelect">
            <c:forEach var="vendor" items="${vendors}">
                <option value="${vendor.getName()}">${vendor.getName()}</option>
            </c:forEach>
        </select>
        <p></p>
        <button type="submit">Add new item</button>
    </form>


</div>
</body>
