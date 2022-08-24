<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
    <title>Title</title>
    <link href="/css/styles1.css" rel="stylesheet" type="text/css">
</head>
<body>
<jsp:include page='masterPage.jsp'>
    <jsp:param name="workdays" value="${workday}"/>
</jsp:include>
<div class="form-style-2">
    <div class="form-style-2-heading">
        Hello hairdresser!
    </div>
    <table>
        <form method="get">
            <tr>
            <th>Free time slots</th>
        </tr>
        <c:forEach items="${freeSlots}" var="freeslots">
            <tr>
                <td>${freeslots}</td>
            </tr>
        </c:forEach>
        </form>
        <form action="/masterPage/chooseDate" method="post">
            <tr>
                <th>Busy time slots</th>
                <th>Marks the execution </th>
            </tr>
            <c:forEach items="${busySlots}" var="busyslots">
            <tr>
                <td>${busyslots}</td>
                <td><input type="radio" name="busyslots" value="${busyslots}"></td>
            </tr>
            </c:forEach>
            <input  type="submit" value="Done">
        </form>
    </table>
</div>
</body>
</html>