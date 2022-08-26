<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
    <title>Title</title>
    <link href="/css/styles1.css" rel="stylesheet" type="text/css">
</head>
<body>
<jsp:include page='adminEditReservation.jsp'>
    <jsp:param name="workdays" value="${workday}"/>
</jsp:include>

<div class="form-style-2">
    <form method="post" action="/adminPage/edit">
        <table>
        <c:forEach items="${freeslots}" var="freeslot">
            <tr>
                <td>${freeslot}</td>
                <td><input type="radio" name="freeTime" value="${freeslot}"></td>
            </tr>
        </c:forEach>
        </table>
        <input type="submit" value="Change">
    </form>
</div>
</body>
</html>