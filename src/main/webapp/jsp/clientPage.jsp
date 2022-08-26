<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
    <title>Title</title>
    <link href="/css/styles1.css" rel="stylesheet" type="text/css">
</head>
<body>
<jsp:include page='logOut.jsp'>
    <jsp:param name="workdays" value="${workday}"/>
</jsp:include>
<div class="form-style-2">
    <div class="form-style-2-heading">
        Hello Client!
    </div>
    <table>
        <form action="/clientPage/chooseDate" method="get">
        <tr>
            <th>Name of procedure</th>
            <th>Description</th>
            <th>Duration</th>
            <th>Choose procedure</th>
        </tr>
        <c:forEach items="${procedures}" var="procedure">
            <tr>
                <td>${procedure.name}</td>
                <td>${procedure.description}</td>
                <td>${procedure.durationHours}</td>
                <td><input type="radio" name="procedureName" value="${procedure.name}"></td>
            </tr>
        </c:forEach>
    </table>
            <input type="submit" value="chek free data" name="fg">
        </form>
        </form>

    </table>

<%--    <c:forEach items="${schedule}" var="schedule">--%>
<%--    <tr>--%>
<%--        <input type="submit" value="${master.master.username}" name="master">--%>
<%--    </tr>--%>




<%--    <div class="day">--%>
<%--        <div class="datelabel"><strong>Friday</strong><br/>August 23</div>--%>
<%--        <div class="timeslot">9:00am</div>--%>
<%--        <div class="timeslot">9:30am</div>--%>
<%--        <div class="timeslot">10:00am</div>--%>
<%--    </div>--%>





<%--    <div class="form-style-2-heading">--%>
<%--    </div>--%>
<%--<form>--%>
<%--    <big>Sign up on procedure!!!</big>--%>
<%--    <label for="start">Start date:</label>--%>
<%--    <input type="date" id="start" name="trip-start"--%>
<%--           min="2022-01-01" max="2022-12-31">--%>

<%--    <label for="appt">Choose a time for your meeting:</label>--%>
<%--    <input type="time" id="appt" name="appt"--%>
<%--           min=" " max="18:00" step="1800" required>--%>
<%--    <small>Office hours are 9am to 5pm</small>--%>

<%--    <input type="submit" value="sign up">--%>
<%--</form>--%>


</div>
</body>
</html>