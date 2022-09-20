<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="current" value="${param.lang}" scope="session"/>

<c:if test="${not empty current}">
    <fmt:setLocale value="${current}" scope="session"/>
</c:if>

<fmt:setBundle basename="message" scope="session"/>
<!DOCTYPE html>

<head>
    <meta charset="UTF-8">
    <title><fmt:message key="client.title"/></title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</head>

<body>

    <%@ include file="menu.jspf" %><br/>

    <div class="container">
        <c:if test="${message ne null}">
            <div class="alert alert-dismissible alert-${type}">
                <button type="button" class="close" data-dismiss="alert" aria-label="close" aria-hidden="true">&times;</button>
                <span>${message}</span>
            </div>
        </c:if>
        <c:remove var="message" scope="session"/>
        <c:remove var="type" scope="session"/>
        <table class="table">
            <thead class="thead-dark">
            <form action="/masterPage/chooseDate" method="get">
                <h4 class="card-title mt-3 text-center"><fmt:message key="master.master"/></h4>

                <tr>
                    <th><fmt:message key="master.tablehead.ChooseWorkDay"/></th>
                    <th><fmt:message key="master.tablehead.ChooseWorkDay"/></th>
                </tr>
                <c:forEach items="${workdays}" var="workday">
                <tr>
                    <td>${workday.workDay}</td>
                    <td><input type="radio" name="workday" value="${workday.workDay}"</td>
                </tr>
                </c:forEach>
                <td ><div><button type="submit" name="name"><fmt:message key="master.button.choose"/></button></div></td>
        </table>
        </form>




    </div>
</body>

</html>