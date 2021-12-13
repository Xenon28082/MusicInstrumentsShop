<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="Assets/CSS/ItemBlock.css">
    <link rel="stylesheet" href="Assets/CSS/VerticalMenu.css">
    <c:set var="locale" value="${sessionScope.get('loc')}"/>
    <c:if test="${locale == null}">

        <fmt:setLocale value="en"/>
    </c:if>
    <c:if test="${locale != null}">
        <fmt:setLocale value="${locale}"/>
    </c:if>
    <fmt:setBundle basename="localization.local" var="loc"/>
    <fmt:message bundle="${loc}" key="addNewItemLabel" var="addNewItemLabel"/>
    <fmt:message bundle="${loc}" key="productNameLabel" var="productNameLabel"/>
    <fmt:message bundle="${loc}" key="productPriceLabel" var="productPriceLabel"/>
    <fmt:message bundle="${loc}" key="productStockLabel" var="productStockLabel"/>
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

<c:set var="message" value="${param.message}"/>

<jsp:include page="Assets/MenuJSPs/AdminMenu.jsp"/>


<c:set var="vendors" value="${requestScope.vendors}"/>
<div class="blockCont" style="display: flex; justify-content: flex-start; flex-wrap: wrap;">
    <form method="post" action="FrontController?COMMAND=ADD_NEW_ITEM">
        <input type="text" name="productName" placeholder="${productNameLabel}" autocomplete="off" required>
        <p></p>
        <input type="number" name="productPrice" placeholder="${productPriceLabel}" min="1" max="999999"
               autocomplete="off" required>
        <p></p>
        <input type="number" name="productStock" placeholder="${productStockLabel}" min="1" max="999999"
               autocomplete="off" required>
        <p></p>
        <select name="productType">
            <option value="guitar">${guitarLabel}</option>
            <option value="percussion">${perkLabel}</option>
            <option value="keys">${keysLabel}</option>
            <option value="whistle">${whistLabel}</option>
            <option></option>
        </select>
        <p></p>
        <select name="vendorSelect">
            <c:forEach var="vendor" items="${vendors}">
                <option value="${vendor.getName()}">${vendor.getName()}</option>
            </c:forEach>
        </select>
        <p></p>
        <button type="submit">${addNewItemLabel}</button>
    </form>
</div>
<div class="blockCont">
    <c:if test="${message == 'negative'}">
        <div style="border: red solid 2px">All number fields must be positive</div>
    </c:if>

    <c:if test="${message == 'empty'}">
        <div style="border: red solid 2px">All fields must be fulfilled</div>
    </c:if>
    <c:if test="${message == 'success'}">
        <div style="border: red solid 2px">Product has been added successfully</div>
    </c:if>
</div>
</body>
