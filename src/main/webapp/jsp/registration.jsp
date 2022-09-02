<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<!DOCTYPE html>
<head>
    <meta charset="utf-8">
    <title><fmt:message key="registration.title"/></title>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</head>

<body clafss="hm-gradient">

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
    <div class="card bg-light">
        <article class="card-body mx-auto" style="max-width: 400px;">
            <h4 class="card-title mt-3 text-center"><fmt:message key="registration.registration"/></h4>
            <br>
            <form action="${pageContext.request.contextPath}/registration" method="post">
                <div class="form-group input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text"> <i class="fa fa-user"></i> </span>
                    </div>
                    <input name="username" class="form-control" placeholder="Username" type="text" required>
                </div>

                <div class="form-group input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text"> <i class="fa fa-lock"></i> </span>
                    </div>
                    <input name="password" class="form-control" placeholder="Password" type="password" required>
                </div>

                <div class="form-group input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text"> <i class="fa fa-lock"></i> </span>
                    </div>
                    <input name="email" class="form-control" placeholder="email" type="email" required>
                </div>

                <c:if test="${role eq 'ADMIN'}">
                    <div class="form-group input-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text"> <i class="fa fa-lock"></i> </span>
                        </div>
                        <select class="form-select" name="role" aria-label="Default select example">
                            <c:forEach items="${roles}" var="role">
                                <option>${role}</option>
                            </c:forEach>
                        </select>
                    </div>
                </c:if>

                <div class="form-group">
                    <button type="submit" class="btn btn-primary btn-block"><fmt:message key="registration.button.create"/></button>
                </div>
<%--                <p class="text-center"><fmt:message key="registration.link.to.login"/><a href="/logIn"><fmt:message key="registration.button.login"/></a></p>--%>
            </form>
        </article>
    </div>
</div>

</body>
</html>