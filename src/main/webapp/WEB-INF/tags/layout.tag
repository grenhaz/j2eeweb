<%@tag description="Main Layout" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="ui" uri="http://www.obarcia.com/tags" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@attribute name="title" required="false" type="java.lang.String" %>
<%@attribute name="lang" required="false" type="java.lang.String" %>
<%@attribute name="bootstrap" required="false" type="java.lang.Boolean" %>
<%@attribute name="fontawesome" required="false" type="java.lang.Boolean" %>
<c:set var="lang" value="${(empty lang) ? 'es' : lang}" />
<c:set var="title" value="${(empty title) ? 'No title' : title}" />
<c:set var="base" value="${pageContext.getServletContext().getContextPath()}" />
<c:set value="${pageContext.request.remoteUser}" var="username" />
<!DOCTYPE html>
<html lang="${lang}">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>${title}</title>
        <link href="https://fonts.googleapis.com/css?family=Roboto:400,100,100italic,300,300italic,400italic,500,700,700italic,500italic,900,900italic|Roboto+Condensed:400,300,300italic,400italic,700,700italic|Roboto+Slab:400,100,300,700" rel="stylesheet" type="text/css">
        <link href="${base}/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">
        <link href="${base}/resources/fontawesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
        <link href="${base}/resources/css/site.css" rel="stylesheet" type="text/css">
        
        <script src="${base}/resources/jquery/jquery.min.js"></script>
        <script src="${base}/resources/bootstrap/js/bootstrap.min.js"></script>
        <script src="${base}/resources/bootbox/bootbox.min.js"></script>
        <script src="${base}/resources/js/site.js"></script>
    </head>
    <body>
        <ui:navbar classCss="navbar-fixed">
            <ui:navitem title="Home" url="${base}" />
            <ui:navitem title="Blog" url="${base}/blog" />
            <ui:navitem title="Contact" url="${base}/contact" />
            <sec:authorize access="!isAuthenticated()">
                <ui:navitem title="Login" url="${base}/login" />
            </sec:authorize>
            <sec:authorize access="hasRole('ROLE_ADMIN')">
                <ui:navitem title="Admin" url="${base}/admin/index" />
            </sec:authorize>
            <sec:authorize access="isAuthenticated()">
                <ui:navitem title="Logout [${username}]" url="${base}/logout" />
            </sec:authorize>
        </ui:navbar>

        <div class="container">
            <div class="wrap">
                <jsp:doBody />
            </div>
        </div>

        <jsp:include page="/WEB-INF/jsp/static/footer.jsp" />

        </footer>
    </body>
</html>