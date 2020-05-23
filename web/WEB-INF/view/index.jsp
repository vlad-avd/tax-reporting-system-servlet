<%@include file="template/prefix.jsp"%>
<html>
  <head>
    <%@include file="template/metadata.jsp"%>
  </head>
  <body>
  <%@include file="template/header.jsp"%>
    <div class="container">
      <h3><fmt:message key="messages.task.description.title"/></h3>
      <fmt:message key="messages.app.description"/>
      <c:if test="${sessionScope.username == null}">
      <div class="container">
          <div class="row" style="margin-top: 20px">
            <form style="margin-right: 20px" action="${pageContext.request.contextPath}/login">
              <button class="btn btn-primary" type="submit"><fmt:message key="messages.account.sign.in"/></button>
            </form>
            <form action="${pageContext.request.contextPath}/registration">
              <button class="btn btn-primary" type="submit"><fmt:message key="messages.account.add"/></button>
            </form>
        </div>
        </c:if>
      </div>
    </div>
  </body>
</html>