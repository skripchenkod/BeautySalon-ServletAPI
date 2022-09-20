<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="current" value="${param.lang}" scope="session"/>

<c:if test="${not empty current}">
    <fmt:setLocale value="${current}" scope="session"/>
</c:if>

<fmt:setBundle basename="message" scope="session"/>
<!DOCTYPE html>

<head>
    <meta charset="UTF-8">
    <title><fmt:message key="guest.title"/></title>
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
            <tr>
                <th><fmt:message key="guest.tablehead.nameOfProcedure"/></th>
                <th><fmt:message key="guest.tablehead.Description"/></th>
                <th></th>
            </tr>
            </thead>
            <c:forEach items="${procedures}" var="procedure">
                <tr>
                    <td>${procedure.name}</td>
                    <td>${procedure.description}</td>
                </tr>
            </c:forEach>
        </table>
        <table class="table">
            <thead class="thead-dark">
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
                <td ><div><button type="submit" name="name"><fmt:message key="guest.tablehead.sortByName"/></button></div></td>
            </form>
            <form method="post" action="guestPage">
                <td ><div><button type="submit" name="rating"><fmt:message key="guest.tablehead.sortByRating"/></button></div></td>
            </form>
        </table>
    </div>

    <footer role="contentinfo" class="footer">
        <div class="container">
            <span class="text-muted">© Dmytro Skrypchenko, 2022</span>
        </div>
    </footer>

</body>

</html>