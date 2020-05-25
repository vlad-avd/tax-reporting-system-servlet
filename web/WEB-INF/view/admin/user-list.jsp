<%@include file="../template/prefix.jsp"%>
<html>
<head>
    <%@include file="../template/metadata.jsp"%>
</head>
<body>
<%@include file="../template/header.jsp"%>
<div class="form-group mt-3 container">
    <table class="table table-striped">
        <thead>
        <tr>
            <th><fmt:message key="messages.user.username"/></th>
            <th><fmt:message key="messages.user.role"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${requestScope.users}" var="user">
        <tr>
            <td>${user.username}</td>
            <td>${user.role.toString()}</td>
            <td><a href="${pageContext.request.contextPath}/user/edit?id=${user.id}"><fmt:message key="messages.user.edit"/></a></td>
            <td><a href="${pageContext.request.contextPath}/user/statistics?id=${user.id}"><fmt:message key="messages.user.statistics"/></a></td>
        </tr>
        </c:forEach>>
        </tbody>
    </table>
    <nav>
        <ul class="pagination">
            <c:if test="${requestScope.currentPage != 1}">
                <li class="page-item">
                    <a class="page-link" href="${pageContext.request.contextPath}/user?&currentPage=${requestScope.currentPage-1}">Previous</a>
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
                                                 href="${pageContext.request.contextPath}/user?currentPage=${i}">${i}</a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>

            <c:if test="${requestScope.currentPage lt requestScope.noOfPages}">
                <li class="page-item"><a class="page-link"
                                         href="${pageContext.request.contextPath}/user?currentPage=${currentPage+1}">Next</a>
                </li>
            </c:if>
        </ul>
    </nav>
</div>
</body>
</html>