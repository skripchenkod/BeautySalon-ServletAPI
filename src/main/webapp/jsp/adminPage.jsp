<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<!DOCTYPE html>

<head>
    <meta charset="UTF-8">
    <title><fmt:message key="client.title"/></title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css"
          integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</head>

<body>

<%@ include file="menu.jspf" %>
<br/>

<div class="container">
    <c:if test="${message ne null}">
        <div class="alert alert-dismissible alert-${type}">
            <button type="button" class="close" data-dismiss="alert" aria-label="close" aria-hidden="true">&times;
            </button>
            <span>${message}</span>
        </div>
    </c:if>
    <c:remove var="message" scope="session"/>
    <c:remove var="type" scope="session"/>
    <table class="table">
        <thead class="thead-dark">
        <form method="post">
            <button type="submit" name="editReservation" class="btn btn-primary btn-block"><fmt:message
                    key="admin.button.EditReservation"/></button>
        </form>

        <form method="post">
            <button type="submit" name="showComments" class="btn btn-primary btn-block"><fmt:message
                    key="admin.button.ShowComments"/></button>
        </form>

        <form method="post">
            <button type="submit" name="editSchedule" class="btn btn-primary btn-block"><fmt:message
                    key="admin.button.editSchedule"/></button>
        </form>

        <form method="post">
            <button type="submit" name="registration" class="btn btn-primary btn-block"><fmt:message
                    key="admin.button.registration"/></button>
        </form>
    </table>
</div>
</body>

</html>