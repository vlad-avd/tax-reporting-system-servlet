<%@include file="../template/prefix.jsp"%>

<head>
    <%@include file="../template/metadata.jsp"%>
</head>
<body>
<%@include file="../template/header.jsp"%>
<div class="container">
    <form action="${pageContext.request.contextPath}/report/edit?id=${requestScope.report.id}&update=true" method="post">
        <c:if test="${requestScope.report.personType.toString() == 'INDIVIDUAL_PERSON'}">
        <div class="form-group row">
            <label class="col-sm-3 col-form-label"><strong> <fmt:message key="messages.individual.person.report.full.name"/>:</strong></label>
            <div class="col-sm-4">
                <input type="text" name="fullName" class="form-control" value="${requestScope.report.fullName}">
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-3 col-form-label"><strong> <fmt:message key="messages.individual.person.report.workplace"/>:</strong></label>
            <div class="col-sm-4">
                <input type="text" name="workplace" class="form-control" value="${requestScope.report.workplace}">
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-3 col-form-label"><strong> <fmt:message key="messages.individual.person.report.salary"/>:</strong></label>
            <div class="col-sm-4">
                <input type="text" name="salary" class="form-control" value="${requestScope.report.salary}">
            </div>
        </div>
        </c:if>
        <c:if test="${requestScope.report.personType.toString() == 'LEGAL_ENTITY'}">
        <div class="form-group row">
            <label class="col-sm-3 col-form-label"><strong> <fmt:message key="messages.legal.entity.report.company.name"/>:</strong></label>
            <div class="col-sm-4">
                <input type="text" name="companyName" class="form-control" value="${requestScope.report.companyName}" readonly>
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-3 col-form-label"><strong> <fmt:message key="messages.legal.entity.report.financial.turnover"/>:</strong></label>
            <div class="col-sm-4">
                <input type="text" name="financialTurnover" class="form-control" value="${requestScope.report.financialTurnover}" readonly>
            </div>
        </div>
        </c:if>
        <div>
            <button class="btn btn-primary" type="submit"><fmt:message key="messages.report.save.changes"/></button>
        </div>
    </form>
    <form action="${pageContext.request.contextPath}/report/">
        <button class="btn btn-primary" type="submit"><fmt:message key="messages.user.cancel"/></button>
    </form>
</div>
</body>