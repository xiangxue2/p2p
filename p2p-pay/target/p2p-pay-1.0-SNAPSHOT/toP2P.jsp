<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/12/3 0003
  Time: 18:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>疯狂加载中...</title>
</head>
<body>
<form action="${pay_p2p_return_url}" method="post">
    <input type="hidden" name="${signVerified}" value="${signVerified}"/>
    <c:choose>
        <c:when test="${not empty params}">
            <c:forEach items="${params}" var="paramMap">
                <input type="hidden" name="${paramMap.key}" VALUE="${paramMap.value}"/>
            </c:forEach>
        </c:when>
    </c:choose>
</form>
<script>document.forms[0].submit()</script>

</body>
</html>
