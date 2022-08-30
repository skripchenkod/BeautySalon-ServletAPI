<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
    <form method="post" action="${pageContext.request.contextPath}/logIn">
        <label for="email">email
            <input class="input-field" type="text" id="email" name="email">
        </label>
        <label for="password">Password
            <input class="password-field" type="password" id="password" name="password">
        </label>
        <input type="submit" value="Log in">
    </form>
    <div class="footer">
        <div class="copyright">
            <small><jsp:include page='mytags.jsp'>
                <jsp:param name="workdays" value="${workday}"/>
            </jsp:include>
            </small>
        </div>
    </div>
</body>
</html>