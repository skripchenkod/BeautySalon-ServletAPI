<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<html>
<head>
    <title>Title</title>
    <link href="/css/styles1.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="form-style-2">
    <div class="form-style-2-heading">
        Please Log In!
    </div>
    <form method="post" action="/registration">
        <label for="userName">Username
            <input class="input-field" type="text" id="username" name="username">
        </label>
        <label for="password">Password
            <input class="password-field" type="password" id="password" name="password">
        </label>
        <label for="email">Email
            <input class="text-input" type="password" id="email" name="email">
        </label>
        <input type="submit" value="Registration">
    </form>
</body>
</html>