<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Client List</title>
</head>
<body>
<h1>Client List</h1>
<table border="1">
    <tr>
        <th>ID</th>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Sex</th>
        <th>Age</th>
    </tr>
    <c:forEach var="client" items="${clients}">
        <tr>
            <td>${client.id}</td>
            <td>${client.prenom}</td>
            <td>${client.nom}</td>
            <td>${client.sexe}</td>
            <td>${client.age}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
