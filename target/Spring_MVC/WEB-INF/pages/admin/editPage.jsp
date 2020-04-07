<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: FOX
  Date: 21.03.2020
  Time: 21:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <c:if test="${empty user.name}">
        <title>Add</title>
    </c:if>
    <c:if test="${!empty user.name}">
        <title>Edit</title>
    </c:if>

</head>
<body>
<c:if test="${empty user.id}">
    <h2>Add user</h2>
</c:if>
<c:if test="${!empty user.name}">
    <h2>Edit user</h2>
</c:if>

<c:if test="${empty user.name}">
    <c:url value="/admin/add" var="var"/>
</c:if>
<c:if test="${!empty user.name}">
    <c:url value="/admin/edit" var="var"/>
</c:if>

<b>${error}</b>

    <form action="${var}" method="POST">



        <c:if test="${!empty user.name}">
            <input type="hidden" name="id" value="${user.id}">
        </c:if>


        <label for="name">name</label>
        <input type="text" name="name" id="name" value="${user.name}">

        <label for="password">password</label>
        <input type="text" name="password" id="password" value="${user.password}">

        <label for="email">email</label>
        <input type="text" name="email" id="email" value="${user.email}">

        <c:if test="${empty user.id}">
            <input type="hidden" name="checkboxRole" value="1"/>
            <label for="role1">ROLE_USER</label>
            <input type="checkbox" name="checkboxRole" id="role1" value="ROLE_USER">
            <label for="role2">ROLE_ADMIN</label>
            <input type="checkbox" name="checkboxRole" id="role2" value="ROLE_ADMIN">
        </c:if>

        <c:if test="${!empty user.name}">
            <input type="hidden" name="checkboxRole" value="1"/>
            <label for="role1">ROLE_USER</label>
            <input type="checkbox" name="checkboxRole" id="role1" value="ROLE_USER"
<%--            <c:if test="${checkedRoles.indexOf('ROLE_USER') != -1}">--%>
<%--                   checked--%>
<%--            </c:if>--%>
            >
            <label for="role2">ROLE_ADMIN</label>
            <input type="checkbox" name="checkboxRole" id="role2" value="ROLE_ADMIN"
<%--            <c:if test="${checkedRoles.indexOf('ROLE_ADMIN') != -1}">--%>
<%--                   checked--%>
<%--            </c:if>--%>
            >

        </c:if>


        <c:if test="${empty user.id}">
            <input  type="submit" value="add user">
        </c:if>
        <c:if test="${!empty user.id}">
            <input  type="submit" value="edit user">
        </c:if>


    </form>
    <a href="<c:url value="/perform_logout" />">Logout</a>
</body>
</html>
