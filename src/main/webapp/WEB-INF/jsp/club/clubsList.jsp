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

<c:if test="${not empty errorMessage}">
    <div style="color:red">
            ${errorMessage}
    </div>
</c:if>

<sec:authorize access="hasRole('ADMIN')">
<a href = "/clubs/add">
<button type="button" class="btn btn-primary" id = "addClubBtn">Add club</button>
</a>
</sec:authorize>

<div class="table">

    <table class=table">
        <thead>
        <tr>
            <th scope="col">License no</th>
            <th scope="col">Name</th>
            <th scope="col">Age category</th>
            <th scope="col">NIP</th>
            <th scope="col">Address</th>
            <th scope="col">Web address</th>
            <th scope="col">E-mail</th>
            <th scope="col">Players</th>
            <th scope="col">Coaches</th>
            <sec:authorize access="hasRole('ADMIN')">
            <th scope="col"></th>
            </sec:authorize>
        </tr>
        </thead>
        <c:forEach var="club" items="${clubs}">
            <tr>
                <td>${club.id}</td>
                <td>${club.name}</td>
                <td>${club.ageCategory}</td>
                <td>${club.nip}</td>
                <td>ul. ${club.address.streetName} ${club.address.streetNumber}, ${club.address.zipCode} ${club.address.city}</td>
                <td>${club.webAddress}</td>
                <td>${club.email}</td>
                <td>
                    <a href = "/players/club/${club.id}">
                        <span class ="btn btn-info btn-sm float-right">Players</span>
                    </a>
                </td>
                <td>
                    <a href = "/coaches/club/${club.id}">
                        <span class ="btn btn-info btn-sm float-right">Coaches</span>
                    </a>
                </td>

                <sec:authorize access="hasRole('ADMIN')">
                <td>
                    <a href = "/clubs/update/${club.id}">
                        <span class ="btn btn-info btn-sm float-right">Edit</span>
                    </a>
                    <a href = "/clubs/delete/${club.id}">
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
