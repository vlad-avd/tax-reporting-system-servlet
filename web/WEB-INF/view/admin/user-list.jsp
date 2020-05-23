<%@include file="../template/prefix.jsp"%>
<html>
<head>
    <%@include file="../template/metadata.jsp"%>
</head>
<body>
<%@include file="../template/header.jsp"%>
<div class="form-group mt-3 container">
    <table class="table table-striped">
        <thead>
        <tr>
            <th><fmt:message key="messages.user.username"/></th>
            <th><fmt:message key="messages.user.role"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${requestScope.users}" var="user">
        <tr>
            <td>${user.username}</td>
            <td>${user.role.toString()}</td>
            <td><a href="${pageContext.request.contextPath}/user/edit?id=${user.id}"><fmt:message key="messages.user.edit"/></a></td>
            <td><a href="${pageContext.request.contextPath}/user/statistics?id=${user.id}"><fmt:message key="messages.user.statistics"/></a></td>
        </tr>
        </c:forEach>>
        </tbody>
    </table>
</div>
</body>
</html>