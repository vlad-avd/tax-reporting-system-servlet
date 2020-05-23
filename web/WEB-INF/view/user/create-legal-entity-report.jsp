<%@include file="../template/prefix.jsp"%>
<html>
<head>
    <%@include file="../template/metadata.jsp"%>
</head>
<body>
<%@include file="../template/header.jsp"%>
<div class="container">
<form action="${pageContext.request.contextPath}/report/legal-entity-report" method="post">
    <div class="form-group row">
        <label class="col-sm-3 col-form-label"> <fmt:message key="messages.legal.entity.report.company.name"/> : </label>
        <div class="col-sm-4">
            <input type="text" name="companyName" class="form-control" placeholder=<fmt:message key="messages.legal.entity.report.company.name"/>>
        </div>
    </div>
    <div class="form-group row">
        <label class="col-sm-3 col-form-label"> <fmt:message key="messages.legal.entity.report.financial.turnover"/> : </label>
        <div class="col-sm-4">
            <input type="text" name="financialTurnover" class="form-control" placeholder=<fmt:message key="messages.legal.entity.report.financial.turnover"/>>
        </div>
    </div>
    <button class="btn btn-primary" type="submit"><fmt:message key="messages.report.submit"/></button>
</form>
</div>
</body>
</html>