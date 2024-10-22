<%@ page contentType="text/html;charset=UTF-8" language="java"
    isELIgnored="false"
%>

<html>
<head>
    <title>학생정보 수정</title>
</head>
<body>
    <form action="/students?action=update&id=${s.id}" method="post">
        이름<input type="text" name="name" value="${s.name}" readonly><br>
        학교<input type="text" name="univ" value="${s.univ}" ><br>
        생일<input type="date" name="birth" value="${s.birth}" readonly><br>
        이메일<input type="email" name="email" value="${s.email}" ><br>
        <input type="submit" value="수정">
    </form>
</body>
</html>
