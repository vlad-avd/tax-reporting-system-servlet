<%@include file="../template/prefix.jsp"%>

<head>
    <%@include file="../template/metadata.jsp"%>
</head>
<body>
<%@include file="../template/header.jsp"%>
<div class="container">
    <div>
        <table class="table table-striped col-md-8">
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
    <form action="${pageContext.request.contextPath}/report-verification/verification-result?id=${requestScope.report.id}" method="post">
        <div class="row container" style="margin-bottom: 20px">
            <button class="btn btn-success" type="submit" name="reportStatus" value="approve"><fmt:message key="messages.report.approve"/></button>
            <button style="margin: 0px 50px" class="btn btn-danger" type="submit" name="reportStatus" value="reject"><fmt:message key="messages.report.reject"/></button>
            <select class="form-control col-md-4" name="rejectionReason">
                <option selected value>- - - - -</option>
                <c:forEach items="${requestScope.rejectionReasons}" var="reason">
                    <option value="${reason}">
                            ${reason}
                    </option>
                </c:forEach>
            </select>
        </div>
        <div class="row container">
            <button class="btn btn-warning" style="margin-right: 40px" type="submit" name="reportStatus" value="sendToEdit"><fmt:message key="messages.report.send.to.edit"/></button>
            <textarea class="form-control col-md-4 " name="comment" rows="4"></textarea>
        </div>
    </form>
</div>
</body>