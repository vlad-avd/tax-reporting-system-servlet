<%@include file="../template/prefix.jsp"%>

<head>
    <%@include file="../template/metadata.jsp"%>
</head>
<body>
<%@include file="../template/header.jsp"%>
<div class="form-group mt-3 container">
    <div class="row">
        <form style="margin-right: 50px" action="${pageContext.request.contextPath}/report/individual-person-report">
            <button class="btn btn-primary" type="submit"><fmt:message key="messages.user.create.individual.person.report"/></button>
        </form>
        <form class="col-md-4" action="${pageContext.request.contextPath}/report/legal-entity-report">
            <button class="btn btn-primary" type="submit"><fmt:message key="messages.user.create.legal.entity.report"/></button>
        </form>
    </div>
    <table class="table table-striped">
        <thead>
        <tr>
        <th>#</th>
        <th><fmt:message key="messages.report.person.type"/></th>
        <th><fmt:message key="messages.report.status"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${requestScope.reports}" var="report" varStatus="reportStat">
            <tr>
                <td>${reportStat.index + requestScope.recordsPerPage * (requestScope.currentPage - 1) + 1}</td>
                <td>${report.personType}</td>
                <td>${report.reportStatus}</td>
                <td><a href="${pageContext.request.contextPath}/report?id=${report.id}"><fmt:message key="messages.report.open"/></a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <nav>
        <ul class="pagination">
            <c:if test="${requestScope.currentPage != 1}">
                <li class="page-item">
                    <a class="page-link" href="${pageContext.request.contextPath}/report?&currentPage=${requestScope.currentPage-1}">Previous</a>
                </li>
            </c:if>

            <c:forEach begin="1" end="${requestScope.noOfPages}" var="i">
                <c:choose>
                    <c:when test="${requestScope.currentPage eq i}">
                        <li class="page-item active"><a class="page-link">
                                ${i} <span class="sr-only">(current)</span></a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="page-item"><a class="page-link"
                                                 href="${pageContext.request.contextPath}/report?currentPage=${i}">${i}</a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>

            <c:if test="${requestScope.currentPage lt requestScope.noOfPages}">
                <li class="page-item"><a class="page-link"
                                         href="${pageContext.request.contextPath}/report?currentPage=${currentPage+1}">Next</a>
                </li>
            </c:if>
        </ul>
    </nav>
</div>
</body>