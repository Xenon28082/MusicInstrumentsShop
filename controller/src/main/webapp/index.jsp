<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="Assets/CSS/FallenBox.css">
</head>
<body>
<c:set var="error" value="${param.message}"/>
<div class="box">

    <form id="LoginForm" action="FrontController" method="post">
        <h1>Login</h1>
        <input type="hidden" name="COMMAND" value="FIND_USER"/>
        <input type="text" name="userloginLog" placeholder="Username" autocomplete="off">
        <input type="password" name="passwordLog" placeholder="Password" autocomplete="off">
        <input type="submit" placeholder="Login" value="Sign in">
    </form>

    <form id="RegForm" action="FrontController" method="post" style="display: none;">
        <h1>Registration</h1>
        <input type="hidden" name="COMMAND" value="CREATE_NEW_USER"/>
        <input type="text" name="userlogin" placeholder="Login" autocomplete="off">
        <input type="text" name="username" placeholder="Name" autocomplete="off">
        <input type="text" name="userlastname" placeholder="Lastname" autocomplete="off">
        <input type="password" name="password" placeholder="Password" autocomplete="off">
        <input type="password" name="checkPassword" placeholder="Repeat password" autocomplete="off">
        <input type="submit" placeholder="Login" value="Sign up">
    </form>

    <button id="GoToLogin"
            onclick="document.getElementById('LoginForm').style.display='block'; document.getElementById('RegForm').style.display='none';;document.getElementById('GoToLogin').style.display='none'; document.getElementById('GoToReg').style.display='block';"
            style="display:none;">
        Sign in
    </button>

    <button id="GoToReg"
            onclick=" document.getElementById('LoginForm').style.display='none'; document.getElementById('RegForm').style.display='block';document.getElementById('GoToReg').style.display='none'; document.getElementById('GoToLogin').style.display='block';">
        Sign up
    </button>
    <c:if test="${error == 'noSuchUser'}">
        <p style="color: red">There is no such user</p>
    </c:if>
    <c:if test="${error == 'loginExists'}">
        <p style="color: red">Login exists</p>
    </c:if>
    <c:if test="${error == 'passwordsnotmatch'}">
        <p style="color: red">Entered passwords don't match</p>
    </c:if>
    <c:if test="${error == 'failedToRegister'}">
        <p style="color: red">Failed to register</p>
    </c:if>
</div>

</body>
</html>
