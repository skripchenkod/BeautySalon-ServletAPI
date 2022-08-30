<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
    <link href="/css/styles1.css" rel="stylesheet" type="text/css">
</head>
<body>
<%@ include file="logOut.jsp" %><br/>
<div class="form-style-2">
    <div class="form-style-2-heading">
        Hello Admin!
    </div>
    <form method="get" action="/adminPage/reservation">
        <table>
            <tr>
                <th>Name of master and their client</th>
            </tr>

            <tr>
                <c:forEach items="${masters}" var="entry">
            <tr>
                <td>${entry.key.username}:</td>
            </tr>
            <tr>
                <form method="get" action="/adminPage/reservation">
                    <td>
                        <select name="client">
                            <c:forEach items="${entry.value}" var="entry">
                            <option>
                                    ${entry.username}
                            </option>
                            </c:forEach>
                    <td><input type="submit" value="search"></td>
                    </select>
                    </td>
                </form>
            </tr>
            </c:forEach>

            </tr>


        </table>
    </form>
</div>
</body>
</html>