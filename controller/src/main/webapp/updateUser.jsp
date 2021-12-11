<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="Assets/CSS/ItemBlock.css">
    <link rel="stylesheet" href="Assets/CSS/VerticalMenu.css">
    <c:set var="locale" value="${sessionScope.get('locale')}"/>
    <c:if test="${locale == null}">

        <fmt:setLocale value="en"/>
    </c:if>
    <c:if test="${locale != null}">
        <fmt:setLocale value="${locale}"/>
    </c:if>
    <fmt:setBundle basename="localization.local" var="loc"/>
    <fmt:message bundle="${loc}" key="updateUserLogin" var="updateUserLogin"/>
    <fmt:message bundle="${loc}" key="updateUserPassword" var="updateUserPassword"/>
    <fmt:message bundle="${loc}" key="userLoginPlaceholder" var="userLoginPlaceholder"/>
    <fmt:message bundle="${loc}" key="passwordPlaceholder" var="passwordPlaceholder"/>
    <fmt:message bundle="${loc}" key="userNamePlaceholder" var="userNamePlaceholder"/>
    <fmt:message bundle="${loc}" key="userLastnamePlaceholder" var="userLastnamePlaceholder"/>
    <fmt:message bundle="${loc}" key="repeatPasswordPlaceholder" var="repeatPasswordPlaceholder"/>
    <fmt:message bundle="${loc}" key="signInPlaceholder" var="signInPlaceholder"/>
    <fmt:message bundle="${loc}" key="signUpPlaceholder" var="signUpPlaceholder"/>
    <fmt:message bundle="${loc}" key="changeLocaleLable" var="changeLocaleLable"/>
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
        <button type="submit">${updateUserLogin}</button>
    </form>
    <h1>Update password</h1>
    <form method="post" action="FrontController">
        <input type="hidden" name="COMMAND" value="UPDATE_USER_PASSWORD">
        <input type="hidden" name="userId" value="${sessionScope.id}">
        <input name="newPassword" required>
        <input name="repeatNewPassword" required>
        <button type="submit">${updateUserPassword}</button>
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
