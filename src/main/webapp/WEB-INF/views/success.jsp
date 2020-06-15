<%--
  Created by IntelliJ IDEA.
  User: Su
  Date: 2020/6/14
  Time: 18:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"  isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h3>查询成功</h3>
    <c:forEach items="${users}" var="user">
        ${user.name}
    </c:forEach>

</body>
</html>
