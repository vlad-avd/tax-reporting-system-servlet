<%@include file="../template/prefix.jsp"%>

<head>
    <%@include file="../template/metadata.jsp"%>
</head>
<body>
<%@include file="../template/header.jsp"%>
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