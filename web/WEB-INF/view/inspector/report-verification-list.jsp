<%@include file="../template/prefix.jsp"%>

<head>
    <%@include file="../template/metadata.jsp"%>
</head>
<body>
<%@include file="../template/header.jsp"%>
<div class="form-group mt-3 container">
    <table class="table table-striped">
        <thead>
        <tr>
            <th><fmt:message key="messages.user.id"/></th>
            <th><fmt:message key="messages.report.person.type"/></th>
            <th><fmt:message key="messages.report.created.date"/></th>
            <th><fmt:message key="messages.report.status"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${requestScope.reports}" var="report">
            <tr>
                <td>${report.id}</td>
                <td>${report.personType}</td>
                <td>${report.created}</td>
                <td>${report.reportStatus}</td>
                <td><a href="${pageContext.request.contextPath}/report-verification?id=${report.id}"><fmt:message key="messages.report.open"/></a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>