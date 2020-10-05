<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
    <!-- Bootstrap CSS -->
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>

<style>
    .row{
        display: flex;
        align-items: center;
        justify-content: center;
    }
</style>

</head>

<body>
<jsp:include page="../navbar.jsp" />

<div class="container-fluid">
    <div class="row">
        <form method="post" action="/login">

            <c:if test = "${error == 'error'}">
                <div  style="color:red">Please try again!</div>
            </c:if>

            <h3 class="h3 mb-3 font-weight-normal">Login</h3>

            <label for="username" class="sr-only">User:</label>
            <input required class="form-control" placeholder="User name" autofocus name="username" type="text" id="username">
            <label for="password" class="sr-only">Password:</label>
            <input required class="form-control" placeholder="Password" name="password" type="password" value="" id="password">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

            <br>
            <button class="btn btn-lg btn-primary btn-block" type="submit">Log in</button>
        </form>
    </div>

    <a href = "/clubs/" class = "row">Continue as a guest</a>

</div>


</body>
</html>
