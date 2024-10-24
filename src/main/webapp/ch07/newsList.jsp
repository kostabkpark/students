<%--<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>--%>
<%@ page contentType="text/html;" language="java" isELIgnored="false" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>뉴스 목록</title>
</head>
<body>
    <ul>
        <c:forEach var="n" varStatus="i" items="${news}">
            <li> [${i.count}]
                <a href="/news?action=info&aid=${n.aid}">${n.title}</a>
                    ${n.date}
            </li>
        </c:forEach>
    </ul>
</body>
</html>
