<%@tag description="Main Layout" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="ui" uri="http://www.obarcia.com/tags" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@attribute name="title" required="false" type="java.lang.String" %>
<%@attribute name="lang" required="false" type="java.lang.String" %>
<%@attribute name="classCss" required="false" type="java.lang.String" %>
<%@attribute name="bootstrap" required="false" type="java.lang.Boolean" %>
<%@attribute name="fontawesome" required="false" type="java.lang.Boolean" %>
<%@attribute name="tag" required="true" rtexprvalue="true" %>
<c:set var="lang" value="${(empty lang) ? 'es' : lang}" />
<c:set var="title" value="${(empty title) ? 'No title' : title}" />
<!DOCTYPE html>
<html lang="${lang}">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>${title}</title>
        <link href="https://fonts.googleapis.com/css?family=Roboto:400,100,100italic,300,300italic,400italic,500,700,700italic,500italic,900,900italic|Roboto+Condensed:400,300,300italic,400italic,700,700italic|Roboto+Slab:400,100,300,700" rel="stylesheet" type="text/css">
        <link href="<c:url value="/resources/bootstrap/css/bootstrap.min.css" />" rel="stylesheet" type="text/css">
        <link href="<c:url value="/resources/fontawesome/css/font-awesome.min.css" />" rel="stylesheet" type="text/css">
        <link href="<c:url value="/resources/css/site.css" />" rel="stylesheet" type="text/css">
        
        <script>var __BASE = '<c:url value="/" />';</script>
        <script src="<c:url value="/resources/jquery/jquery.min.js" />"></script>
        <script src="<c:url value="/resources/bootstrap/js/bootstrap.min.js" />"></script>
        <script src="<c:url value="/resources/bootbox/bootbox.min.js" />"></script>
        <script src="<c:url value="/resources/js/site.js" />"></script>
    </head>
    <body class="${classCss}">
        <ui:header tag="${tag}" username="${pageContext.request.remoteUser}" />

        <div class="container">
            <div class="wrap">
                <div class="wrap-container">
                    <jsp:doBody />
                </div>
                    
                <ui:footer />
            </div>
        </div>
    </body>
</html>