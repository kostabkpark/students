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
    <title>학생 상세정보 조회</title>
</head>
<body>
 <h2>학생 상세 정보</h2>
 <ul>
     <li>${student.id}</li>
     <li>${student.name}</li>
     <li>${student.univ}</li>
     <li>${student.birth}</li>
     <li>${student.email}</li>
 </ul>
</body>
</html>
