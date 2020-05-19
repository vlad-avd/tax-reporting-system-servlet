<%@include file="../template/prefix.jsp"%>

<head>
    <%@include file="../template/metadata.jsp"%>
</head>
<body>
<%@include file="../template/header.jsp"%>
<div class="container mt-5">
    List of users
    <table>
        <thead>
        <tr>
            <th><fmt:message key="messages.user.username"/></th>
            <th><fmt:message key="messages.user.role"/></th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${requestScope.users}" var="user">
        <tr>
            <td>${user.username}</td>
            <td>${user.role.toString()}</td>
            <td><a href="/user/${user.id}"><fmt:message key="messages.user.edit"/></a></td>
        </tr>
        </c:forEach>>
        </tbody>
    </table>
</div>
</body>