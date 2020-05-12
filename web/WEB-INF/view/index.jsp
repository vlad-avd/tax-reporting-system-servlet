<%@include file="template/prefix.jsp"%>
<html>
  <head>
    <%@include file="template/metadata.jsp"%>
  </head>
  <body>
    <%@include file="template/header.jsp"%>
    <h3>Task Description</h3>
    <fmt:message key="messages.app.description"/>
    <a class="mr-2" href="${pageContext.request.contextPath}/login"><fmt:message key="messages.account.sign.in"/></a>
    <a href="${pageContext.request.contextPath}/registration"><fmt:message key="messages.account.add"/></a>
    Web Application Context Path = ${pageContext.request.contextPath}
  </body>
</html>