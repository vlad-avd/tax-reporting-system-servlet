<%@include file="../template/prefix.jsp"%>

<head>
    <%@include file="../template/metadata.jsp"%>
</head>
<body>
<%@include file="../template/header.jsp"%>

<form action="${pageContext.request.contextPath}/registration" method="post">
    <div class="form-group row">
        <label class="col-sm-2 col-form-label"><fmt:message key="messages.user.username"/>: </label>
        <div class="col-sm-6">
            <input type="text" name="username" class="form-control" placeholder=<fmt:message key="messages.user.username"/>/>
        </div>
    </div>
    <div class="form-group row">
        <label class="col-sm-2 col-form-label"> <fmt:message key="messages.user.password"/>: </label>
        <div class="col-sm-6">
            <input type="password" name="password" class="form-control" placeholder=<fmt:message key="messages.user.password"/>/>
        </div>
    </div>
    <div class="form-group row">
        <label class="col-sm-2 col-form-label"> <fmt:message key="messages.user.password.confirmation"/>: </label>
        <div class="col-sm-6">
            <input type="password" name="password-confirmation" class="form-control" placeholder=<fmt:message key="messages.user.password.confirmation"/>/>
        </div>
    </div>
    <%--    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>--%>
    <%--    <#if !isRegisterForm>--%>
    <%--    <a href="/registration"><fmt:message key="messages.account.add"/></a>--%>
    <%--</#if>--%>
    <button class="btn btn-primary" type="submit"><fmt:message key="messages.account.sign.in"/></button>
</form>
</body>
</html>
