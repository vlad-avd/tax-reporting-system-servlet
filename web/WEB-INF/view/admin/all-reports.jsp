<%@include file="../template/prefix.jsp"%>

<head>
    <%@include file="../template/metadata.jsp"%>
</head>
<body>
<%@include file="../template/header.jsp"%>
<div class="form-group mt-3 container">
    <div class="row">
        <div id="filter-panel" class="filter-panel">
            <div class="panel panel-default">
                <div class="panel-body">
                    <form class="form-inline" role="form" method="post">
                        <div class="form-group" style="margin-right: 30px">
                            <label class="filter-col" style="margin-right:10px;"><fmt:message key="messages.filters.sort.by.date"/>:</label>
                            <select name="sortByDate" class="form-control">
                                <option ${requestScope.sortByDate == 'fromNewestToOldest' ? 'selected="selected"' : ''} value="fromNewestToOldest"><fmt:message key="messages.filters.sort.from.newest.to.oldest"/></option>
                                <option ${requestScope.sortByDate == 'fromOldestToNewest' ? 'selected="selected"' : ''} value="fromOldestToNewest"><fmt:message key="messages.filters.sort.from.oldest.to.newest"/></option>
                            </select>
                        </div> <!-- form group [rows] -->
                        <div class="form-group" style="margin-right: 30px">
                            <label class="filter-col" style="margin-right:10px;"><fmt:message key="messages.filters.sort.by.report.status"/>:</label>
                            <select name="sortByReportStatus" class="form-control">
                                <option ${requestScope.sortByReportStatus == 'all' ? 'selected="selected"' : ''}  value="all"><fmt:message key="messages.filters.sort.all"/></option>
                                <option ${requestScope.sortByReportStatus == 'onVerifying' ? 'selected="selected"' : ''} value="onVerifying"><fmt:message key="messages.filters.sort.on.verifying"/></option>
                                <option ${requestScope.sortByReportStatus == 'needToEdit' ? 'selected="selected"' : ''} value="needToEdit"><fmt:message key="messages.filters.sort.need.to.edit"/></option>
                                <option ${requestScope.sortByReportStatus == 'approved' ? 'selected="selected"' : ''} value="approved"><fmt:message key="messages.filters.sort.approved"/></option>
                                <option ${requestScope.sortByReportStatus == 'rejected' ? 'selected="selected"' : ''} value="rejected"><fmt:message key="messages.filters.sort.rejected"/></option>
                            </select>
                        </div> <!-- form group [order by] -->
                        <div class="form-group">
                            <button type="submit" class="btn btn-info filter-col">
                                <fmt:message key="messages.filters.apply"/>
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
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
    <nav>
        <ul class="pagination">
            <c:if test="${requestScope.currentPage != 1}">
                <li class="page-item">
                    <form action="${pageContext.request.contextPath}/report/all?&currentPage=${requestScope.currentPage-1}" method="post">
                        <input type="hidden" name="sortByDate" value="${requestScope.sortByDate}">
                        <input type="hidden" name="sortByReportStatus" value="${requestScope.sortByReportStatus}">
                        <button class="page-link" type="submit">Previous</button>
                    </form>
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
                        <li class="page-item">
                            <form action="${pageContext.request.contextPath}/report/all?&currentPage=${i}" method="post">
                                <input type="hidden" name="sortByDate" value="${requestScope.sortByDate}">
                                <input type="hidden" name="sortByReportStatus" value="${requestScope.sortByReportStatus}">
                                <button class="page-link" type="submit">${i}</button>
                            </form>
                        </li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>

            <c:if test="${requestScope.currentPage lt requestScope.noOfPages}">
                <li class="page-item">
                    <form action="${pageContext.request.contextPath}/report/all?currentPage=${currentPage+1}" method="post">
                        <input type="hidden" name="sortByDate" value="${requestScope.sortByDate}">
                        <input type="hidden" name="sortByReportStatus" value="${requestScope.sortByReportStatus}">
                        <button class="page-link" type="submit">Next</button>
                    </form>
                </li>
            </c:if>
        </ul>
    </nav>
</div>
</body>