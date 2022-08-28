<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
    <form method="get" action="/adminPage/edit">
        <table>
            <p>All ${clientName} reservations: </p>
            <td><input hidden name="clientName" value="${clientName}"></td>
            <tr>
                <th>Start of procedure</th>
                <th>End of procedure</th>
                <th>Name of master</th>
                <th>Name of procedure</th>
                <th>Date of procedure</th>
                <th>Edit reservation</th>
            </tr>
            <c:forEach items="${reservations}" var="reservation">
                <tr>
                    <td>${reservation.start}</td>
                    <td>${reservation.end}</td>

                    <select name="mark">
                        <td>
                            <c:forEach items="${reservations}" var="reservation">
                            <option>
                        <td>${reservation.start}</td>
                        </option>
                        </c:forEach>
                    </select>
                    </td>


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
        <input type="submit" value="Check free time slots">
        <input formmethod="post" formaction="/adminPage/reservation" type="submit" value="Delete reservation">
    </form>
</div>
</body>
</html>