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
        <input type="submit" name="" placeholder="Login">
    </form>

    <form id="RegForm" action="FrontController" method="post" style="display: none;">
        <h1>Registration</h1>
        <input type="hidden" name="COMMAND" value="CREATE_NEW_USER"/>
        <input type="text" name="userlogin" placeholder="Userlogin" autocomplete="off">
        <input type="text" name="username" placeholder="Username" autocomplete="off">
        <input type="text" name="userlastname" placeholder="Userlastname" autocomplete="off">
        <input type="password" name="password" placeholder="password" autocomplete="off">
        <input type="submit" placeholder="Login">
    </form>

    <button id="GoToLogin"
            onclick="document.getElementById('LoginForm').style.display='block'; document.getElementById('RegForm').style.display='none';;document.getElementById('GoToLogin').style.display='none'; document.getElementById('GoToReg').style.display='block';"
            style="display:none;"> Go to loginisation
    </button>

    <button id="GoToReg"
            onclick=" document.getElementById('LoginForm').style.display='none'; document.getElementById('RegForm').style.display='block';document.getElementById('GoToReg').style.display='none'; document.getElementById('GoToLogin').style.display='block';">
        Go to registration
    </button>
    <c:if test="${error == 'noSuchUser'}">
        <p style="color: red">There is no such user</p>
    </c:if>
    <c:if test="${error == 'loginExists'}">
        <p style="color: red">Login exists</p>
    </c:if>
</div>

</body>
</html>


<%--<!DOCTYPE html>--%>
<%--<html lang="en">--%>
<%--<head>--%>
<%--    <meta charset="UTF-8">--%>
<%--    <title>Title</title>--%>
<%--&lt;%&ndash;    <link rel="stylesheet" href="Assets/CSS/FallenBox.css">&ndash;%&gt;--%>
<%--</head>--%>
<%--<body>--%>
<%--&lt;%&ndash;<jsp:include page="Assets/HTML/EnterForm.html"/>&ndash;%&gt;--%>
<%--&lt;%&ndash;<div style="position: absolute; top: 120vh; width: 1000px; height: 50px; border: 3px solid red; color: red">&ndash;%&gt;--%>
<%--    <c:if test="${error == 'noSuchUser'}">--%>
<%--        <p style="color: red">There is no such user</p>--%>
<%--    </c:if>--%>
<%--    <c:if test="${error == 'loginExists'}">--%>
<%--        <p style="color: red">Login exists</p>--%>
<%--    </c:if>--%>
<%--&lt;%&ndash;</div>&ndash;%&gt;--%>
<%--</body>--%>
<%--</html>--%>

<%--<html>--%>
<%--<body>--%>


<%--<jsp:include page="Assets/HTML/EnterForm.html"/>--%>

<%--<p>Registration form</p>--%>
<%----%>
<%--<form method="post" action="FrontController">--%>
<%--    <input type="hidden" name="COMMAND" value="CREATE_NEW_USER"/>--%>
<%--    Login: <input name="userlogin"/>--%>
<%--    <br/>--%>
<%--    Name: <input name="username"/>--%>
<%--    <br/>--%>
<%--    Lastname: <input name="userlastname"/>--%>
<%--    <br/>--%>
<%--    Password: <input name="password"/>--%>
<%--    <br/>--%>
<%--    <button type="submit">Submit</button>--%>
<%--</form>--%>
<%----%>
<%--<p>Login form</p>--%>
<%----%>
<%--<form method="get" action="FrontController">--%>
<%--    <input type="hidden" name="COMMAND" value="FIND_USER"/>--%>
<%--    Login: <input name="userloginLog"/>--%>
<%--    <br/>--%>
<%--    Password: <input name="passwordLog"/>--%>
<%--    <br/>--%>
<%--    <button type="submit">Submit</button>--%>
<%--</form>--%>
<%----%>
<%--<form method="get" action="FrontController">--%>
<%--    <input type="hidden" name="COMMAND" value="GET_PRODUCTS"/>--%>
<%--    <button type="submit">Go to items page</button>--%>
<%--</form>--%>
<%----%>
<%----%>
<%--<br/>--%>


<%--<c:set var="test" value="${sessionScope.test}"/>--%>

<%--<c:out value="${test}"/>--%>


<p>
</p>

<%--<c:set var="q" value="qwer"/>--%>

<%--<c:out value="${q}"/>--%>

</body>
</html>