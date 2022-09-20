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
        <form action="/clientPage/chooseFreeSlots" method="get">
            <tr>
                <th><fmt:message key="client.tablehead.Master"/></th>
                <th><fmt:message key="client.tablehead.workDays"/></th>
                <th><fmt:message key="client.tablehead.WorkStartTimes"/></th>
                <th><fmt:message key="client.tablehead.WorkEndTimes"/></th>
                <th><fmt:message key="client.tablehead.ChooseDay"/></th>
                <th></th>
            </tr>
            <p><fmt:message key="client.tablehead.ProcedureName"/>${nameProcedure}</p>
            <td><input hidden name="nameProcedure" value="${nameProcedure}"></td>
        </thead>
        <c:forEach items="${schedule}" var="schedule">
            <tr>
                <td>${schedule.masterId.username}</td>
                <td>${schedule.workDay}</td>
                <td>${schedule.startWorkDay}</td>
                <td>${schedule.endWorkDay}</td>
                <td>
                    <div>
                        <button type="radio" class="radio-button" name="date" value="${schedule.workDay}"></button>
                    </div>
                </td>
                <td><input hidden name="mastername" value="${schedule.masterId.username}"></td>
            </tr>
        </c:forEach>
    </table>
    </form>
    <form action="/clientPage" method="get">
        <button type="submit" class="btn btn-primary btn-block"><fmt:message key="client.button.back"/></button>
    </form>


</div>
</body>

</html>