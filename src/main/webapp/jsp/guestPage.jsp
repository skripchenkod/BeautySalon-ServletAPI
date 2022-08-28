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
        Hello Guest!
    </div>
    <table>
        <tr>
            <th>Name of procedure</th>
            <th>Description</th>
        </tr>
        <c:forEach items="${procedures}" var="procedure">
            <tr>
                <td>${procedure.name}</td>
                <td>${procedure.description}</td>
            </tr>
        </c:forEach>
    </table>

    <table>
        <tr>
            <th>Name of master</th>
            <th>Rating</th>
        </tr>
        <c:forEach items="${masters}" var="master">
            <tr>
                <td><form method="post" action="guestPage">
                    <input type="submit" value="${master.master.username}" name="master">
                    </form>
                </td>
                <td>${master.ratingMark}</td>
            </tr>
        </c:forEach>
    </table>

    <table>
    <form method="post" action="guestPage">
        <input type="submit" value="sort by name" name="name">
    </form>
    <form method="post" action="guestPage">
        <input type="submit" value="sort by rating" name="rating">
    </form>
    </table>
    <table>
    </table>
    </table>

    <div class="form-style-2-heading">
    </div>
    <tr class="form-style-2-heading">
        Please log in or Registration!
    </tr>

    <form action="logIn">
        <input formaction="/logIn" type="submit" value="Log in">
        <input formaction="/registration" type="submit" value="Registration">
    </form>
</div>
</body>
</html>