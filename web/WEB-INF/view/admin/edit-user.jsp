<%@include file="../template/prefix.jsp"%>

<head>
    <%@include file="../template/metadata.jsp"%>
</head>
<body>
<%@include file="../template/header.jsp"%>
<div class="container">
    <form action="${pageContext.request.contextPath}/user/edit?id=${requestScope.user.id}" method="post">
        <div class="form-group row">
            <label class="col-sm-3 col-form-label"><strong> <fmt:message key="messages.account.new.username"/>:</strong></label>
            <div class="col-sm-4">
                <input type="text" name="username" class="form-control" value="${requestScope.user.username}">
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-3 col-form-label"><strong> <fmt:message key="messages.account.new.password"/>:</strong></label>
            <div class="col-sm-4">
                <input type="text" name="password" class="form-control" value="${requestScope.user.password}">
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-3 col-form-label"><strong> <fmt:message key="messages.user.password.confirmation"/>:</strong></label>
            <div class="col-sm-4">
                <input type="text" name="passwordConfirmation" class="form-control" value="${requestScope.user.password}">
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-3 col-form-label"><strong> <fmt:message key="messages.user.role"/>:</strong></label>
            <div class="col-sm-8">
                <c:forEach items="${requestScope.roles}" var="role">
                    <c:if test="${!role.toString().equals('ROLE_GUEST')}">
                        <label class="form-check-label" style="margin-right: 30px">
                            <input class="form-check-input radio-inline" type="radio" name="role" value="${role}" ${role==requestScope.user.role?"checked":""}>${role}
                        </label>
                    </c:if>
                </c:forEach>
            </div>
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