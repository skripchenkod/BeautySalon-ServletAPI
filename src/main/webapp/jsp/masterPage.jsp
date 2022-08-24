<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
    <title>Title</title>
    <link href="/css/styles1.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="form-style-2">
    <div class="form-style-2-heading">
        Hello hairdresser!
    </div>
    <table>
        <form action="/masterPage/chooseDate" method="get">

            <tr>
                <th>Choose work day</th>
            </tr>
            <c:forEach items="${workdays}" var="workday">
                <tr>
                    <td>${workday.workDay}</td>
                    <td><input type="radio" name="workday" value="${workday.workDay}"</td>
                </tr>
            </c:forEach>
            <input  type="submit" value="Choose">
    </table>
</div>
</body>
</html>