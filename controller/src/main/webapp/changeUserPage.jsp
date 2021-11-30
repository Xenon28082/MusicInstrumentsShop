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
<jsp:include page="Assets/MenuJSPs/AdminMenu.jsp"/>

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
                <input disabled name="userLogin" value="${foundUser.getLogin()}" required/>
                <input disabled name="userFirstname" value="${foundUser.getName()}" required/>
                <input disabled name="userLastname" value="${foundUser.getLastname()}" required/>
                <select name="userRole">
                    <option value="1">Administrator</option>
                    <option value="2">Customer</option>
                </select>
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
    <c:if test="${error == 'Cant change dir'}">
        <p style="color: red">can't get director info</p>
    </c:if>
    <c:if test="${error == 'noUser'}">
        <p style="color: red">There is no user with such login</p>
    </c:if>
    <c:if test="${error == 'updated'}">
        <p style="color: green">Updated</p>
    </c:if>
</div>
</body>