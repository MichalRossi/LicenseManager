<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<html>
<head>

    <!-- Bootstrap CSS -->
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
</head>

<body>
<jsp:include page="../navbar.jsp" />

<sec:authorize access="hasRole('ADMIN')">
<a href = "/players/add">
    <button type="button" class="btn btn-primary" id = "addPlayerBtn">Add player</button>
</a>
</sec:authorize>

<div class="table">

    <table class=table">
        <thead>
        <tr>
            <th scope="col">License no</th>
            <th scope="col">First name</th>
            <th scope="col">Last name</th>
            <th scope="col">Gender</th>
            <th scope="col">Age category</th>
            <th scope="col">Valid</th>
            <th scope="col">Club</th>
            <sec:authorize access="hasRole('ADMIN')">
            <th scope="col"></th>
            </sec:authorize>

        </tr>
        </thead>
        <c:forEach var="player" items="${players}">
            <tr>
                <td>${player.id}</td>
                <td>${player.firstName}</td>
                <td>${player.lastName}</td>
                <td>${player.gender}</td>
                <td>${player.ageCategory}</td>
                <td>${player.valid}</td>
                <td>
                    <c:forEach var="club" items="${player.clubs}" varStatus="clubList">
                        <c:if test="${clubList.last}">
                            ${club.name}
                        </c:if>
                    </c:forEach>
                </td>
                <sec:authorize access="hasRole('ADMIN')">
                <td>
                    <a href = "/players/update/${player.id}">
                        <span class ="btn btn-info btn-sm float-right">Edit</span>
                    </a>
                    <a href = "/players/delete/${player.id}">
                        <span class ="btn btn-danger btn-sm float-right">Delete</span>
                    </a>
                </td>
                </sec:authorize>
            </tr>
        </c:forEach>
    </table>

</div>
</body>
</html>
