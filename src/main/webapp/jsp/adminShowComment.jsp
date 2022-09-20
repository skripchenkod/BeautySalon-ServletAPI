<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="current" value="${param.lang}" scope="session"/>

<c:if test="${not empty current}">
    <fmt:setLocale value="${current}" scope="session"/>
</c:if>

<fmt:setBundle basename="message" scope="session"/>v
<!DOCTYPE html>

<head>
    <meta charset="UTF-8">
    <title><fmt:message key="client.title"/></title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</head>

<body>

<%@ include file="menu.jspf" %><br/>

<div class="container">
    <c:if test="${message ne null}">
        <div class="alert alert-dismissible alert-${type}">
            <button type="button" class="close" data-dismiss="alert" aria-label="close" aria-hidden="true">&times;</button>
            <span>${message}</span>
        </div>
    </c:if>
    <c:remove var="message" scope="session"/>
    <c:remove var="type" scope="session"/>
    <table class="table">
        <thead class="thead-dark">
        <form method="get">
            <tr>
                <th><fmt:message key="admin.tablehead.commentText"/></th>
                <th><fmt:message key="admin.tablehead.commentMark"/></th>
                <th><fmt:message key="admin.tablehead.commentDate"/></th>
                <th><fmt:message key="admin.tablehead.client"/></th>
                <th><fmt:message key="admin.tablehead.master"/></th>
                <th></th>
            </tr>
        </thead>
        <c:forEach items="${comments}" var="comment">
            <tr>
                <td>${comment.commentText}</td>
                <td>${comment.serviceMark}</td>
                <td>${comment.commentDate}</td>
                <td>${comment.commentator.username}</td>
                <td>${comment.master.username}</td>
            </tr>
        </c:forEach>
    </table>
    </form>
</div>
</body>

</html>