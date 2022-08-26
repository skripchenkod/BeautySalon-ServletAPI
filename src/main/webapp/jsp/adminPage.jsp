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
        Hello Admin!
    </div>
    <form method="post">
        <input type="submit" name="editReservation" value="Edit reservation">
    </form>
    <form method="post">
        <input type="submit" name="editSchedule" value="Edit master schedule">
    </form>
</div>
</body>
</html>