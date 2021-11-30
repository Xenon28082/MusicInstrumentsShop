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

<c:set var="message" value="${param.message}"/>

<jsp:include page="Assets/MenuJSPs/AdminMenu.jsp"/>


<c:set var="vendors" value="${requestScope.vendors}"/>
<div class="blockCont" style="display: flex; justify-content: flex-start; flex-wrap: wrap;">
    <form method="post" action="FrontController?COMMAND=ADD_NEW_ITEM">
        <input type="text" name="productName" placeholder="Product name" autocomplete="off" required>
        <p></p>
        <input type="number" name="productPrice" placeholder="Product price" autocomplete="off" required>
        <p></p>
        <input type="number" name="productStock" placeholder="Product stock" autocomplete="off" required>
        <p></p>
        <select name="productType">
            <option value="guitar">guitar</option>
            <option value="percussion">percussion</option>
            <option value="keys">keys</option>
            <option value="whistle">whistle</option>
            <option></option>
        </select>
        <p></p>
        <select name="vendorSelect">
            <c:forEach var="vendor" items="${vendors}">
                <option value="${vendor.getName()}">${vendor.getName()}</option>
            </c:forEach>
        </select>
        <p></p>
        <button type="submit">Add new item</button>
    </form>

    <c:if test="${message == 'negative'}">
        <div style="border: red solid 2px">All number fields must be positive</div>
    </c:if>

    <c:if test="${message == 'empty'}">
        <div style="border: red solid 2px">All fields must be fulfilled</div>
    </c:if>
</div>
</body>
