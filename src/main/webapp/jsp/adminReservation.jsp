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
    <title><fmt:message key="client.title"/></title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css"
          integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</head>

<body>

<%@ include file="menu.jspf" %>
<br/>

<div class="container">
    <c:if test="${message ne null}">
        <div class="alert alert-dismissible alert-${type}">
            <button type="button" class="close" data-dismiss="alert" aria-label="close" aria-hidden="true">&times;
            </button>
            <span>${message}</span>
        </div>
    </c:if>
    <c:remove var="message" scope="session"/>
    <c:remove var="type" scope="session"/>
    <table class="table">
        <thead class="thead-dark">
        <form action="/adminPage/edit" method="get">
                <p>All ${clientName} reservations: </p>
                <td><input hidden name="clientName" value="${clientName}"></td>
            <tr>
                <th><fmt:message key="admin.tablehead.StartOfProcedure"/></th>
                <th><fmt:message key="admin.tablehead.EndOfProcedure"/></th>
                <th><fmt:message key="admin.tablehead.NameOfMaster"/></th>
                <th><fmt:message key="admin.tablehead.NameOfProcedure"/></th>
                <th><fmt:message key="admin.tablehead.DateOfProcedure"/></th>
                <th><fmt:message key="admin.button.EditReservation"/></th>
                <th></th>
            </tr>
        </thead>
        <c:forEach items="${reservations}" var="reservation">
            <tr>
                <td>${reservation.start}</td>
                <td>${reservation.end}</td>
                <td>${reservation.beautyMaster.username}</td>
                <td>${reservation.procedure.name}</td>
                <td>${reservation.data}</td>
                <td><input type="radio" name="reservationId" value="${reservation.id}"></td>
                <td><input hidden name="mastername" value="${reservation.beautyMaster.username}"></td>
                <td><input hidden name="date" value="${reservation.data}"></td>
                <td><input hidden name="procedureName" value="${reservation.procedure.name}"></td>
            </tr>
        </c:forEach>
            </table>
            <button type="submit" class="btn btn-primary btn-block"><fmt:message key="admin.button.www"/></button>
             <button type="submit" formmethod="post" formaction="/adminPage/reservation" class="btn btn-primary btn-block"><fmt:message key="admin.text.delete"/></button>

    </form>
    </table>


</div>
</body>

</html>