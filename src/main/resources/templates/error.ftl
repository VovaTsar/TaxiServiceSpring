<!DOCTYPE HTML>
<html xmlns:th="http://www.thymeleaf.org" th:with="lang=${locale.language}" th:lang="${lang}">
<head th:fragment="header">
    <title th:text='#{title}'></title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>
<body>
<div th:include="header"></div>
<h1>Something went wrong! </h1>
<h2>Our Engineers are on it</h2>
<a href="/">Go Home</a>
<p th:text="'Error_status: ' + ${error_status}">
    <!--<p th:text="'Error_message: ' + ${error_message}">-->
</body>
</html>