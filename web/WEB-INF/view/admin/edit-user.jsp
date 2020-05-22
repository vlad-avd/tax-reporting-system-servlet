<%@include file="../template/prefix.jsp"%>

<head>
    <%@include file="../template/metadata.jsp"%>
</head>
<body>
<%@include file="../template/header.jsp"%>
<div class="container">
    <form action="${pageContext.request.contextPath}/user/edit?id=${requestScope.user.id}" method="post">
        <div class="row">
            <fmt:message key="messages.account.new.username"/>:
            <input type="text" name="username" value="${requestScope.user.username}">
        </div>
        <div class="row">
            <fmt:message key="messages.account.new.password"/>:
            <input type="text" name="password" value="${requestScope.user.password}">
        </div>
        <div class="row">
            <fmt:message key="messages.user.password.confirmation"/>:
            <input type="text" name="password-confirmation" value="${requestScope.user.password}">
        </div>
        <div class="row">
            <fmt:message key="messages.user.role"/>:
            <c:forEach items="${requestScope.roles}" var="role">
                <label><input type="radio" name="role" value="${role}" ${role==requestScope.user.role?"checked":""}>${role}</label>
            </c:forEach>
        </div>
        <div>
            <button class="btn btn-primary" type="submit"><fmt:message key="messages.user.save"/></button>
        </div>
    </form>
    <form action="${pageContext.request.contextPath}/user">
        <button class="btn btn-primary" type="submit"><fmt:message key="messages.user.cancel"/></button>
    </form>
</div>
</body>