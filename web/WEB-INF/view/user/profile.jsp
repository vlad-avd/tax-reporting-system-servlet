<%@include file="../template/prefix.jsp"%>

<head>
    <%@include file="../template/metadata.jsp"%>
</head>
<body>
<%@include file="../template/header.jsp"%>
<div class="container">
    <table class="table col-md-4">
        <tbody>
            <tr>
                <th><fmt:message key="messages.user.username"/>:</th>
                <td>${requestScope.user.username}</td>
            </tr>
            <tr>
                <th><fmt:message key="messages.user.password"/>:</th>
                <td>${requestScope.user.password}</td>
            </tr>
            <tr>
                <th><fmt:message key="messages.user.role"/>:</th>
                <td>${requestScope.user.role}</td>
            </tr>
        </tbody>
    </table>
    <div class="form-group mt-3">
        <form action="${pageContext.request.contextPath}/profile/edit">
            <button class="btn btn-primary" type="submit"><fmt:message key="messages.user.edit"/></button>
        </form>
    </div>
</div>
</body>