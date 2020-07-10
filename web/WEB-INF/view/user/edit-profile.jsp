<%@include file="../template/prefix.jsp"%>

<head>
    <%@include file="../template/metadata.jsp"%>
</head>
<body>
<%@include file="../template/header.jsp"%>
<div class="container">
    <form action="${pageContext.request.contextPath}/profile/edit" method="post">
        <div class="form-group row">
            <label class="col-sm-3 col-form-label"><strong> <fmt:message key="messages.account.new.username"/>:</strong></label>
            <div class="col-sm-4">
                <input type="text" name="username" class="form-control" value="${requestScope.user.username}">
            </div>
        </div>
        <c:if test="${requestScope.usernameIsValid == false}">
            <div class="alert alert-danger"><p><fmt:message key="messages.invalid.username"/></p></div>
        </c:if>
        <div class="form-group row">
            <label class="col-sm-3 col-form-label"><strong> <fmt:message key="messages.account.new.password"/>:</strong></label>
            <div class="col-sm-4">
                <input type="text" name="password" class="form-control" value="${requestScope.user.password}">
            </div>
        </div>
        <c:if test="${requestScope.passwordIsValid == false}">
            <div class="alert alert-danger"><p><fmt:message key="messages.invalid.password"/></p></div>
        </c:if>
        <div class="form-group row">
            <label class="col-sm-3 col-form-label"><strong> <fmt:message key="messages.user.password.confirmation"/>:</strong></label>
            <div class="col-sm-4">
                <input type="text" name="password-confirmation" class="form-control" value="${requestScope.user.password}">
            </div>
        </div>
        <c:if test="${requestScope.passwordMismatch == false}">
            <div class="alert alert-danger"><p><fmt:message key="messages.password.mismatch"/></p></div>
        </c:if>
        <div class="form-group row">
            <label class="col-sm-3 col-form-label"><strong> <fmt:message key="messages.user.role"/>:</strong></label>
            <div class="col-sm-4">
                <input type="text" name="password-confirmation" class="form-control" value="${requestScope.user.role}" readonly>
            </div>
        </div>
        <div>
            <button class="btn btn-primary" type="submit"><fmt:message key="messages.user.save"/></button>
        </div>
    </form>
    <form action="${pageContext.request.contextPath}/profile">
        <button class="btn btn-primary" type="submit"><fmt:message key="messages.user.cancel"/></button>
    </form>
</div>
</body>