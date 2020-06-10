<%@include file="../template/prefix.jsp"%>

<head>
    <%@include file="../template/metadata.jsp"%>
</head>
<body>
<%@include file="../template/header.jsp"%>
<div class="container">
    <form action="${pageContext.request.contextPath}/registration" method="post">
        <div class="form-group row">
            <label class="col-sm-2 col-form-label"><fmt:message key="messages.user.username"/>: </label>
            <div class="col-sm-6">
                <input type="text" name="username" class="form-control" placeholder=<fmt:message key="messages.user.username"/>>
            </div>
        </div>
        <c:if test="${requestScope.usernameIsValid == false}">
            <div class="alert alert-info"><p><fmt:message key="messages.invalid.username"/></p></div>
        </c:if>
        <c:if test="${requestScope.isUserExists == true}">
            <div class="alert alert-info"><p><fmt:message key="messages.user.already.exists"/></p></div>
        </c:if>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label"> <fmt:message key="messages.user.password"/>: </label>
            <div class="col-sm-6">
                <input type="password" name="password" class="form-control" placeholder=<fmt:message key="messages.user.password"/>>
            </div>
        </div>
        <c:if test="${requestScope.passwordIsValid == false}">
            <div class="alert alert-info"><p><fmt:message key="messages.invalid.password"/></p></div>
        </c:if>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label"> <fmt:message key="messages.user.password.confirmation"/>: </label>
            <div class="col-sm-6">
                <input type="password" name="passwordConfirmation" class="form-control" placeholder=<fmt:message key="messages.user.password.confirmation"/>>
            </div>
        </div>
        <c:if test="${requestScope.passwordMismatch == false}">
            <div class="alert alert-info"><p><fmt:message key="messages.password.mismatch"/></p></div>
        </c:if>
        <button class="btn btn-primary" type="submit"><fmt:message key="messages.account.add"/></button>
    </form>
</div>
</body>
</html>
