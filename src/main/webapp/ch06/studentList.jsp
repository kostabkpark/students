<%--
  Created by IntelliJ IDEA.
  User: KOSTA
  Date: 2024-10-22
  Time: 오전 11:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>학생 목록 조회</title>
</head>
<body>
    <table>
        <tr>
            <th>id</th>
            <th>name</th>
            <th>univ</th>
            <th>birth</th>
            <th>email</th>
        </tr>
        <c:forEach var="s" items="${students}">
            <tr>
                <td>${s.id}</td>
                <td>${s.name}</td>
                <td>${s.univ}</td>
                <td>${s.birth}</td>
                <td>${s.email}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
