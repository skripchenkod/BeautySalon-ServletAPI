<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
    <title>Title</title>
    <link href="/css/styles1.css" rel="stylesheet" type="text/css">
</head>
<body>
<jsp:include page='clientPage.jsp'>
    <jsp:param name="procedures" value="${procedures}"/>
</jsp:include>
<div class="form-style-2">
    <div class="form-style-2-heading">

    </div>
    <table>
        <form action="chooseFreeSlots" method="get">
            <tr>
                <th>Master</th>
                <th>Work days</th>
                <th>Work start times</th>
                <th>Work end times</th>
                <th>Choose day</th>
            </tr>
            <p>Procedure name: ${nameProcedure}</p>
            <td><input hidden name="nameProcedure" value="${nameProcedure}"></td>
            <c:forEach items="${schedule}" var="schedule">
                <tr>
                    <td>${schedule.masterId.username}</td>
                    <td>${schedule.workDay}</td>
                    <td>${schedule.startWorkDay}</td>
                    <td>${schedule.endWorkDay}</td>
                    <td><input type="radio" name="date" value="${schedule.workDay}"></td>
                    <td><input hidden name="mastername" value="${schedule.masterId.username}"></td>
                </tr>
            </c:forEach>
            <input type="submit" value="Choose time">

        </form>

    </table>
</div>
</body>
</html>