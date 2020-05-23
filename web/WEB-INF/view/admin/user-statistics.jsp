<%@include file="../template/prefix.jsp"%>

<head>
    <%@include file="../template/metadata.jsp"%>
</head>
<body>
<%@include file="../template/header.jsp"%>
<div class="container">
    <div class="row">
        <table class="table table-striped col-md-5" style="margin-right: 100px">
            <tbody>
            <tr>
                <th><fmt:message key="messages.user.username"/>:</th>
                <td>${requestScope.user.username}</td>
            </tr>
            <c:if test="${requestScope.statistics.reportsNumber != 0}">
            <tr>
                <th><fmt:message key="messages.on.verifying.reports.number"/>:</th>
                <td>${requestScope.statistics.onVerifyingReportNumber}</td>
            </tr>
            <tr>
                <th><fmt:message key="messages.first.report.date"/>:</th>
                <td>${requestScope.statistics.firstReportDate}</td>
            </tr>
            <tr>
                <th><fmt:message key="messages.last.report.date"/>:</th>
                <td>${requestScope.statistics.lastReportDate}</td>
            </tr>
            </c:if>
            </tbody>
        </table>
        <table class="table table-striped col-md-5">
            <tbody>
            <tr>
                <th><fmt:message key="messages.reports.number"/>:</th>
                <td>${requestScope.statistics.reportsNumber}</td>
            </tr>
            <c:if test="${requestScope.statistics.reportsNumber != 0}">
            <tr>
                <th><fmt:message key="messages.approved.reports.number"/>:</th>
                <td>${requestScope.statistics.approvedReportNumber}</td>
            </tr>
            <tr>
                <th><fmt:message key="messages.rejected.reports.number"/>:</th>
                <td>${requestScope.statistics.rejectedReportNumber}</td>
            </tr>
            <tr>
                <th><fmt:message key="messages.need.to.edit.reports.number"/>:</th>
                <td>${requestScope.statistics.needToEditReportNumber}</td>
            </tr>
            </c:if>
            </tbody>
        </table>
    </div>
    <form action="${pageContext.request.contextPath}/report/individual-person-report">
        <button class="btn btn-primary" type="submit"><fmt:message key="messages.user.create.individual.person.report"/></button>
    </form>
</div>
</body>