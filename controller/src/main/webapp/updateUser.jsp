<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="Assets/CSS/ItemBlock.css">
    <link rel="stylesheet" href="Assets/CSS/VerticalMenu.css">
</head>
<body>

<jsp:include page="Assets/MenuJSPs/UserMenu.jsp"/>

<c:set var="foundUser" value="${requestScope.foundUser}"/>
<c:set var="message" value="${param.message}"/>
<c:out value="login - ${foundUser.getLogin()}"/>
<c:out value="name - ${foundUser.getName()}"/>
<c:out value="lastname - ${foundUser.getLastname()}"/>
<div class="blockCont">
    <h1>Update login</h1>
    <form method="post" action="FrontController">
        <input type="hidden" name="COMMAND" value="UPDATE_USER_LOGIN">
        <input type="hidden" name="userId" value="${sessionScope.id}">
        <input name="newLogin" required>
        <button type="submit">Update login</button>
    </form>
    <h1>Update password</h1>
    <form method="post" action="FrontController">
        <input type="hidden" name="COMMAND" value="UPDATE_USER_PASSWORD">
        <input type="hidden" name="userId" value="${sessionScope.id}">
        <input name="newPassword" required>
        <input name="repeatNewPassword" required>
        <button type="submit">Update password</button>
    </form>
    <c:if test="${message == 'success'}">
        <p style="color: green">Successful update</p>
    </c:if>
    <c:if test="${message == 'error'}">
        <p style="color: red">Update failed</p>
    </c:if>
    <c:if test="${message == 'empty'}">
        <p style="color: red">Fields must be fulfilled</p>
    </c:if>
    <c:if test="${message == 'notEqual'}">
        <p style="color: red">Passwords must be equal</p>
    </c:if>
</div>
</body>
</html>
