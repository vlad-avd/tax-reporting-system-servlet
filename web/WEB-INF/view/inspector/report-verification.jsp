<%@ page import="ua.kpi.model.enums.RejectionReason" %>
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
<form action="/check-report/${requestScope.report.id}" method="post">
    <button type="submit" name="reportStatus" value="approve"><fmt:message key="messages.report.approve"/></button>
    <select name="rejectionReason">
        <c:forEach items="${requestScope.rejectionReasons}" var="reason">
            <option value="${reason}">
                    ${reason}
            </option>
        </c:forEach>
    </select>
    <button type="submit" name="reportStatus" value="reject"><fmt:message key="messages.report.reject"/></button>
    <textarea class="form-control" name="comment" rows="3"></textarea>
    <div>
        <button type="submit" name="reportStatus" value="sendToEdit"><fmt:message key="messages.report.send.to.edit"/></button>
    </div>
</form>
</body>