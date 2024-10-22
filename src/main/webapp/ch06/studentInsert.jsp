<%--
  Created by IntelliJ IDEA.
  User: KOSTA
  Date: 2024-10-22
  Time: 오전 11:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
    isELIgnored="false"
%>

<html>
<head>
    <title>학생정보 입력</title>
</head>
<body>
    <form action="/students?action=insert" method="post">
        <input type="text" name="name">
        <input type="text" name="univ">
        <input type="date" name="birth">
        <input type="email" name="email">
        <input type="submit" value="등록">
    </form>
</body>
</html>
