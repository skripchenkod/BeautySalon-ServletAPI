<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
    <link href="/css/styles1.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="form-style-2">
    <div class="form-style-2-heading">
        <p>Hello ${clientName} !</p>
    </div>
    <form method="post" action="/commentPage">
        <tr>
            <th>Input comment text</th>
        </tr>
        <input name="commentText">
        <p>
            Choose mark: <select name="mark">
            <c:forEach items="${marks}" var="mark">
                <option>
                    <tr>
                        <td>${mark}</td>
                    </tr>
                </option>
            </c:forEach>
        </p>
        <input type="submit" name="commentText" value="Send">
        </select>
    </form>

</div>
</body>
</html>