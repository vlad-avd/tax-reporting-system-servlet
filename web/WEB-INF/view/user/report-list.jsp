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
    <div class="row" style="margin-top: 20px" >
        <form style="margin-right: 20px" action="${pageContext.request.contextPath}/report/individual-person-report">
            <button class="btn btn-primary" type="submit"><fmt:message key="messages.user.create.individual.person.report"/></button>
        </form>
        <form action="${pageContext.request.contextPath}/report/legal-entity-report">
            <button class="btn btn-primary" type="submit"><fmt:message key="messages.user.create.legal.entity.report"/></button>
        </form>
    </div>
</div>
</body>