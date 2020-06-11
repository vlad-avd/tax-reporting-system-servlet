<%@include file="../template/prefix.jsp"%>
<html>
<head>
    <%@include file="../template/metadata.jsp"%>
</head>
<body>
<%@include file="../template/header.jsp"%>
<div class="container">
<form action="${pageContext.request.contextPath}/report/individual-person-report" method="post">
    <div class="form-group row">
        <label class="col-sm-2 col-form-label"> <fmt:message key="messages.individual.person.report.full.name"/> : </label>
        <div class="col-sm-4">
            <input type="text" name="fullName" class="form-control" placeholder=<fmt:message key="messages.individual.person.report.full.name"/>>
        </div>
    </div>
    <c:if test="${requestScope.isFullNameValid == false}">
        <div class="alert alert-danger"><p><fmt:message key="messages.incorrect.input"/></p></div>
    </c:if>
    <div class="form-group row">
        <label class="col-sm-2 col-form-label"> <fmt:message key="messages.individual.person.report.workplace"/> : </label>
        <div class="col-sm-4">
            <input type="text" name="workplace" class="form-control" placeholder=<fmt:message key="messages.individual.person.report.workplace"/>>
        </div>
    </div>
    <c:if test="${requestScope.isWorkplaceValid == false}">
        <div class="alert alert-danger"><p><fmt:message key="messages.incorrect.input"/></p></div>
    </c:if>
    <div class="form-group row">
        <label class="col-sm-2 col-form-label"> <fmt:message key="messages.individual.person.report.salary"/>: </label>
        <div class="col-sm-4">
            <input type="text" name="salary" class="form-control" placeholder=<fmt:message key="messages.individual.person.report.salary"/>>
        </div>
    </div>
    <c:if test="${requestScope.isSalaryValid == false}">
        <div class="alert alert-danger"><p><fmt:message key="messages.incorrect.input"/></p></div>
    </c:if>
<%--    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>--%>
    <button class="btn btn-primary" type="submit"><fmt:message key="messages.report.submit"/></button>
</form>
</div>
</body>
</html>
