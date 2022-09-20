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

<form>

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
    <form method="post">
    <table class="table">
        <thead class="thead-dark">

            <tr>
                <th><fmt:message key="admin.tablehead.master"/></th>
                <th><fmt:message key="admin.tablehead.chooseMaster"/></th>
                <th></th>
            </tr>
        </thead>
        <c:forEach items="${masters}" var="master">
        <tr>
            <td>${master.master.username}</td>
            <td><input type="radio" name="masterName" value="${master.master.username}"></td>
        </tr>
        </c:forEach>
        </form>
</div>
<table class="table">
    <thead class="thead-dark">
           <big><fmt:message key="admin.tablehead.setDay"/></big>
            <p><label for="start"><fmt:message key="admin.tablehead.chooseDay"/></label>
            <input type="date" id="start" name="workDay"
                       min="2022-09-01" max="2022-12-31" required></p>

        <p><label for="appt"><fmt:message key="admin.tablehead.startDay"/></label>
            <input type="time" id="appt" name="startTime"
                       min="09:00 " max="18:00" step="3600" required></p>

             <label for="appt"><fmt:message key="admin.tablehead.endDay"/></label>
            <input type="time" id="appt" name="endTime"
                       min="09:00 " max="18:00" step="3600" required>

        <p><input formmethod="post" type="submit" value=<fmt:message key="admin.tablehead.edit"/>></p>
</table>
</table>
</form>

</div>
</body>

</html>