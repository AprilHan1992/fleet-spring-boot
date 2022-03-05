<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>主题切换</title>
    <link rel="stylesheet" href="<spring:theme code="style" />">
</head>

<body>

<div id="app">
    欢迎！！！

    <a href="/index?theme=blue">蓝</a>
    <a href="/index?theme=red">红</a>
    <a href="/index?theme=green">绿</a>
</div>

</body>
</html>
