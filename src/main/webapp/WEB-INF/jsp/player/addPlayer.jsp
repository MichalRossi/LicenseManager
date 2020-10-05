<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

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

<div id="addPlayerForm">

    <form:form method="post" modelAttribute="player">

        <div class="form-row">
            <div class="form-group col-md-6">
                <label for="firstNameId">First name:</label>
                <form:input class="form-control" type="text" path="firstName" id="firstNameId"/>
                <form:errors path="firstName" element="div" cssStyle="color: red"/>
            </div>
            <div class="form-group col-md-6">
                <label for="lastNameId">First name:</label>
                <form:input class="form-control" type="text" path="lastName" id="lastNameId"/>
                <form:errors path="lastName" element="div" cssStyle="color: red"/>
            </div>
        </div>

        <div class="form-row">
            <div class="form-group col-md-6">
                <label for="clubsId">Club:</label>
                <form:select path="clubs" class="form-control" items="${clubs}" itemLabel="name" itemValue="id" id="clubsId" multiple="false"/>
            </div>

            <div class="form-group col-md-6" style = "max-width: 100px">
                <label for="genderId">Gender</label>
                <form:select path="gender" class="form-control" items="${genders}"  id="genderId"/>
            </div>

            <div class="form-group col-md-6" style = "max-width: 100px">
                <label for="validId">Valid</label>
                <form:checkbox class="form-control"  path="valid" id="validId"/>
            </div>

            <div class="form-group col-md-6" style = "max-width: 200px">
                <label for="birthDateId">Date of birth:</label>
                <form:input class="form-control" type="date" path="dateOfBirth" id="birthDateId"/>
            </div>
        </div>

        <input type="submit" value="Save">
    </form:form>


</div>
</body>
</html>
