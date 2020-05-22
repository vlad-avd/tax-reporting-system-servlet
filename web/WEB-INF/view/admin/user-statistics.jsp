<%@include file="../template/prefix.jsp"%>

<head>
    <%@include file="../template/metadata.jsp"%>
</head>
<body>
<%@include file="../template/header.jsp"%>
<div class="container">
    <div class="form-group mt-3">
        <div class="row">
            <div class="col-4">
                <table class="table">
                    <tbody>
                    <tr>
                        <th><fmt:message key="messages.user.id"/>:</th>
                        <td>${requestScope.user.id}</td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <div class="col-4">
                <table class="table">
                    <tbody>
                        <tr>
                            <th><fmt:message key="messages.user.username"/>:</th>
                            <td>${requestScope.user.username}</td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <div class="col-4">
                <table class="table">
                    <tbody>
                    <tr>
                        <th><fmt:message key="messages.reports.number"/>:</th>
                        <td>${requestScope.statistics.reportsNumber}</td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
        <c:if test="${requestScope.statistics.reportsNumber != 0}">
        <div class="row">
            <div class="col-6">
                <table class="table">
                    <tbody>
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
                    </tbody>
                </table>
            </div>
            <div class="col-6">
                <table class="table">
                    <tbody>
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
                    </tbody>
                </table>
            </div>
        </div>
        </c:if>
        <table class="table">
            <thead>
            <tr>
                <th><fmt:message key="messages.report.id"/></th>
                <th><fmt:message key="messages.report.person.type"/></th>
                <th><fmt:message key="messages.report.status"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${requestScope.reports}" var="report">
                <tr>
                    <td>${report.id}</td>
                    <td>${report.personType}</td>
                    <td>${report.reportStatus}</td>
                    <td><a href="${pageContext.request.contextPath}/report?id=${report.id}"><fmt:message key="messages.report.open"/></a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <form action="${pageContext.request.contextPath}/report/individual-person-report">
            <button class="btn btn-primary" type="submit"><fmt:message key="messages.user.create.individual.person.report"/></button>
        </form>
    </div>
</div>
</body>