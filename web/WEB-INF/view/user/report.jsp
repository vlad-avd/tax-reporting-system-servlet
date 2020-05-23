<%@include file="../template/prefix.jsp"%>

<head>
    <%@include file="../template/metadata.jsp"%>
</head>
<body>
<%@include file="../template/header.jsp"%>
<div>
    <table class="table table-striped">
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
<c:if test="${requestScope.report.reportStatus == 'NEED_TO_EDIT' or requestScope.report.reportStatus == 'REJECTED'}">
<div class="form-group mt-3">
    <form action="${pageContext.request.contextPath}/report/replace-inspector?id=${requestScope.report.id}" method="post">
        <button class="btn btn-primary" type="submit"><fmt:message key="messages.user.replace.inspector"/></button>
    </form>
</div>
</c:if>
</body>