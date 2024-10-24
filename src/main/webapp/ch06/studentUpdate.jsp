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
    <title>학생정보 수정</title>
</head>
<body>
    <h2>학생정보 수정</h2>
    <form action="/students?action=update&id=${s.id}" method="post">
        이름<input type="text" name="name" value="${s.name}" readonly>
        학교<input type="text" name="univ" value="${s.univ}">
        생일<input type="date" name="birth" value="${s.birth}" readonly>
        이메일<input type="email" name="email" value="${s.email}">
        <input type="submit" value="수정">
    </form>
</body>
</html>
