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
        <form method="post">
            <tr>
                <th>Free time slots</th>
            </tr>
            <c:forEach items="${freeSlots}" var="slots">
                <tr>
                    <td><input type="submit" name="freeslots" value="${slots}"></td>
                </tr>
            </c:forEach>
        </form>
    </table>
</div>
</body>
</html>