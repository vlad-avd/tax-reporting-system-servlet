<%@include file="../template/prefix.jsp"%>

<head>
    <%@include file="../template/metadata.jsp"%>
</head>
<body>
<%@include file="../template/header.jsp"%>
<div>
    <table class="table">
        <tbody>
        <c:if test="${requestScope.report.personType.toString() == 'INDIVIDUAL_PERSON'}">
        <tr>
            <th><fmt:message key="messages.individual.person.report.full.name"/>:</th>
            <td>${requestScope.report.fullName}</td>
        </tr>
        <tr>
            <th><fmt:message key="messages.individual.person.report.workplace"/>:</th>
            <td>${requestScope.report.workplace}</td>
        </tr>
        <tr>
            <th><fmt:message key="messages.individual.person.report.salary"/>:</th>
            <td>${requestScope.report.salary}</td>
        </tr>
        </c:if>
        <c:if test="${requestScope.report.personType.toString() == 'LEGAL_ENTITY'}">
        <tr>
            <th><fmt:message key="messages.legal.entity.report.company.name"/>:</th>
            <td>${requestScope.report.companyName}</td>
        </tr>
        <tr>
            <th><fmt:message key="messages.legal.entity.report.financial.turnover"/>:</th>
            <td>${requestScope.report.financialTurnover}</td>
        </tr>
        </c:if>
        <tr>
            <th><fmt:message key="messages.report.person.type"/>:</th>
            <td>${requestScope.report.personType}</td>
        </tr>
        <tr>
            <th><fmt:message key="messages.report.status"/>:</th>
            <td>${requestScope.report.reportStatus}</td>
        </tr>
        </tbody>
    </table>
</div>
<div class="form-group mt-3">
    <form action="/user/replace-inspector/${requestScope.report.id}">
        <button class="btn btn-primary" type="submit"><fmt:message key="messages.user.replace.inspector"/></button>
    </form>
</div>
<div class="form-group mt-3">
    <form action="${pageContext.request.contextPath}/report/individual-person-report">
        <button class="btn btn-primary" type="submit"><fmt:message key="messages.user.create.individual.person.report"/></button>
    </form>
</div>
<div class="form-group">
    <form action="${pageContext.request.contextPath}/report/legal-entity-report">
        <button class="btn btn-primary" type="submit"><fmt:message key="messages.user.create.legal.entity.report"/></button>
    </form>
</div>
</body>