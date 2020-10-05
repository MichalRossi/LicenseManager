<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <!-- Bootstrap CSS -->
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>

</head>
<body>

<nav class="navbar navbar-expand-xl navbar-light bg-light nav-shadow" id="navigationBar">
<ul class="navbar-nav mr-auto">
    <li class="nav-item active">
        <a class="dropdown-item" href="/clubs/">Clubs</a>
    </li>
    <li class="nav-item active">
        <a class="dropdown-item" href="/coaches/">Coaches</a>
    </li>
    <li class="nav-item active">
        <a class="dropdown-item" href="/players/">Players</a>
    </li>
    <li class="nav-item active">
        <a class="dropdown-item" href="/seasons/">Seasons</a>
    </li>
</ul>
    <sec:authorize access="isAuthenticated()">
    <ul class="navbar-nav justify-content-end">
        <li class="nav-item active">
            <form method="post" action="<c:url value="/logout"/>" >
                <button type="submit" class="btn btn-outline-success my-2 my-sm-0">Log off</button>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form>
        </li>
    </ul>
    </sec:authorize>

    <sec:authorize access="!isAuthenticated()">
        <ul class="navbar-nav justify-content-end">
            <li class="nav-item active">
                    <button  class="btn btn-outline-success my-2 my-sm-0" onclick='location.href="/login"' type="button">Log in</button>
            </li>
        </ul>
    </sec:authorize>


</nav>
</body>