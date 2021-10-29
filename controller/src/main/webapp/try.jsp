<%--
  Created by IntelliJ IDEA.
  User: savva
  Date: 25.10.2021
  Time: 21:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
    <title>Title</title>
</head>
<body>
    <c:out value="${requestScope.items}"/>

    <c:set var="buf" value="${requestScope.items}"/>

    <c:out value="${buf}"/>
</body>
</html>
