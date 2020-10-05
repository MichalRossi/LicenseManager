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

</head>

<body>
<jsp:include page="../navbar.jsp" />

<c:if test="${not empty errorMessage}">
    <div style="color:red">
            ${errorMessage}
    </div>
</c:if>

<div id="addClubForm">

<form:form method="post" modelAttribute="club">



    <div class="form-group">
    <label for="nameId">Name:</label>
    <form:input class="form-control" type="text" path="name" id="nameId"/>
    <form:errors path="name" element="div" cssStyle="color: red"/>
    </div>

    <div class="form-row">
        <div class="form-group col-md-6">
        <label for="streetId">Street name:</label>
        <form:input class="form-control" type="text" path="address.streetName" id="streetId"/>
        </div>

        <div class="form-group col-md-6">
            <label for="streetNumberId">Street number:</label>
            <form:input class="form-control" type="text" path="address.streetNumber" id="streetNumberId"/>
        </div>
    </div>

    <div class="form-row">
        <div class="form-group col-md-6">
            <label for="zipCodeId">Zip code:</label>
            <form:input class="form-control" type="text" path="address.zipCode" id="zipCodeId"/>
        </div>

        <div class="form-group col-md-6">
            <label for="cityId">City name:</label>
            <form:input class="form-control" type="text" path="address.city" id="cityId"/>
        </div>
    </div>

    <div class="form-row">
        <div class="form-group col-md-6">
            <label for="emailId">Email:</label>
            <form:input class="form-control" type="text" path="email" id="emailId"/>
            <form:errors path="email" element="div" cssStyle="color: red"/>

        </div>

        <div class="form-group col-md-6">
            <label for="webAddress">Web address:</label>
            <form:input class="form-control" type="text" path="webAddress" id="webAddressId"/>
            <form:errors path="webAddress" element="div"/>

        </div>
    </div>

    <div class="form-row">
        <div class="form-group col-md-6">
            <label for="phoneNumberId">Phone number:</label>
            <form:input class="form-control" type="text" path="phoneNumber" id="phoneNumberId"/>
            <form:errors path="phoneNumber" element="div" cssStyle="color: red"/>
        </div>

        <div class="form-group col-md-6">
            <label for="nipId">NIP:</label>
            <form:input class="form-control" type="text" path="nip" id="nipId"/>
            <form:errors path="nip" element="div" cssStyle="color: red"/>
        </div>
    </div>

    <div class="form-group">
        <label for="ageCategoryId">Age Category:</label>
        <form:select path="ageCategory" class="form-control" items="${ageCategories}"  id="ageCategoryId"/>
    </div>

    <input type="submit" value="Save">
</form:form>


</div>
</body>
</html>
