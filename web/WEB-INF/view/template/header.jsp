<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="${pageContext.request.contextPath}/home"><fmt:message key="messages.app.name"/></a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="${pageContext.request.contextPath}/home"><fmt:message key="messages.navbar.home"/></a>
            </li>
            <c:if test="${sessionScope.username != null}">
            <li class="nav-item active">
                <a class="nav-link" href="${pageContext.request.contextPath}/report"> <fmt:message key="messages.navbar.user.reports"/></a>
            </li>
            </c:if>
            <c:if test="${sessionScope.role == 'ROLE_INSPECTOR' or sessionScope.role == 'ROLE_ADMIN'}">
            <li class="nav-item active">
                <a class="nav-link" href="${pageContext.request.contextPath}/report-verification"><fmt:message key="messages.navbar.inspector.reports"/></a>
            </li>
            </c:if>

            <c:if test="${sessionScope.role == 'ROLE_ADMIN'}">
            <li class="nav-item active">
                <a class="nav-link" href="${pageContext.request.contextPath}/user"><fmt:message key="messages.navbar.users"/></a>
            </li>
            </c:if>
        </ul>
    </div>

    <c:if test="${sessionScope.username != null}">
    <div class="navbar-text mr-3">${sessionScope.username}</div>
    <form action="${pageContext.request.contextPath}/logout" method="post">
<%--        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>--%>
        <button class="btn btn-primary" type="submit"><fmt:message key="messages.account.logout"/></button>
    </form>
    </c:if>


<!--<span><@spring.message "messages.lang.change"/></span>
-->

<a href="?lang=en" style="margin-left: 10px; padding-right: 5px;">
    <fmt:message key="messages.lang.en"/>
</a>
|
<a href="?lang=ru" style="padding-left: 5px;">
    <fmt:message key="messages.lang.ru"/>
</a>
</nav>
