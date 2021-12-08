<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="Assets/CSS/FallenBox.css">

    <c:set var="locale" value="${sessionScope.get('locale')}"/>
    <c:if test="${locale == null}">

        <fmt:setLocale value="en"/>
    </c:if>
    <c:if test="${locale != null}">
        <fmt:setLocale value="${locale}"/>
    </c:if>
    <fmt:setBundle basename="localization.local" var="loc"/>
    <fmt:message bundle="${loc}" key="registerLabel" var="registerLabel"/>
    <fmt:message bundle="${loc}" key="loginLabel" var="loginLabel"/>
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

<c:set var="message" value="${param.message}"/>
<div class="box">
    <form id="LoginForm" action="FrontController" method="post">
        <h1>${loginLabel}</h1>
        <input type="hidden" name="COMMAND" value="FIND_USER"/>
        <input type="hidden" id="reciever" name="locale"/>
        <input type="text" name="userloginLog" placeholder="${userLoginPlaceholder}" autocomplete="off" required>
        <input type="password" name="passwordLog" placeholder="${passwordPlaceholder}" autocomplete="off" required>
        <input type="submit" placeholder="Login" value="${signInPlaceholder}">
    </form>

    <form id="RegForm" action="FrontController" method="post" style="display: none;">
        <h1>${registerLabel}</h1>
        <input type="hidden" name="COMMAND" value="CREATE_NEW_USER"/>
        <input type="text" name="userlogin" placeholder="${userLoginPlaceholder}" autocomplete="off" required>
        <input type="text" name="username" placeholder="${userNamePlaceholder}" autocomplete="off" required>
        <input type="text" name="userlastname" placeholder="${userLastnamePlaceholder}" autocomplete="off" required>
        <input type="password" name="password" placeholder="${passwordPlaceholder}" autocomplete="off" required>
        <input type="password" name="checkPassword" placeholder="${repeatPasswordPlaceholder}" autocomplete="off"
               required>
        <input type="submit" placeholder="Login" value="${signUpPlaceholder}">
    </form>

    <button id="GoToLogin"
            onclick="document.getElementById('LoginForm').style.display='block'; document.getElementById('RegForm').style.display='none';;document.getElementById('GoToLogin').style.display='none'; document.getElementById('GoToReg').style.display='block';"
            style="display:none;">
        ${signInPlaceholder}
    </button>

    <button id="GoToReg"
            onclick=" document.getElementById('LoginForm').style.display='none'; document.getElementById('RegForm').style.display='block';document.getElementById('GoToReg').style.display='none'; document.getElementById('GoToLogin').style.display='block';">
        ${signUpPlaceholder}
    </button>
    <c:if test="${message == 'noSuchUser'}">
        <p style="color: red">There is no such user</p>
    </c:if>
    <c:if test="${message == 'loginExists'}">
        <p style="color: red">Login exists</p>
    </c:if>
    <c:if test="${message == 'passwordsnotmatch'}">
        <p style="color: red">Entered passwords don't match</p>
    </c:if>
    <c:if test="${message == 'failedToRegister'}">
        <p style="color: red">Failed to register</p>
    </c:if>
    <c:if test="${message == 'empty'}">
        <p style="color: red">All fields must be fulfilled</p>
    </c:if>
    <c:if test="${message == 'fatal'}">
        <p style="color: red">An error has been occurred, you've been disconnected</p>
    </c:if>
    <c:if test="${message == 'success'}">
        <p style="color: green">New user has been created</p>
    </c:if>
</div>
<form action="FrontController">
    <input type="hidden" name="COMMAND" value="CHANGE_LANG"/>
    <select id="sender" name="localeSelect" onchange="passparam()">
        <option value="ru">RU</option>
        <option value="en">EN</option>
    </select>
    <input type="hidden" name="pathBack" value="index.jsp"/>
    <p></p>
    <button type="submit" onclick="passparam()">${changeLocaleLable}</button>
</form>
<script type="text/javascript">
    function passparam(){
        let senderElem = document.getElementById("sender");
        let recieverElement = document.getElementById("reciever");
        recieverElement.value = senderElem.value;
    }
</script>
</body>
</html>
