<%@tag description="Articles list" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="ui" uri="http://www.obarcia.com/tags" %>
<%@attribute name="base" required="true" rtexprvalue="true" %>
<%@attribute name="articles" required="true" type="org.obarcia.demo.models.ListPagination" rtexprvalue="true" %>
<div>
    <button onclick="getArticles('${base}/articles/all');">TODOS</button>
    <button onclick="getArticles('${base}/articles/new');">NOTICIAS</button>
    <button onclick="getArticles('${base}/articles/review');">ANÁLISIS</button>
    <button onclick="getArticles('${base}/articles/guide');">GUÍAS</button>
    <button onclick="getArticles('${base}/articles/video');">VIDEOS</button>
    <button onclick="getArticles('${base}/articles/special');">ESPECIALES</button>
</div>
<c:if test="${not empty articles.records}">
    <c:forEach var="article" items="${articles.records}">
        <ui:article position="left" article="${article}" />
    </c:forEach>

    <ui:pagination url="${articles.urlBase}" current="${articles.current}" pages="${articles.pages}" />
</c:if>
<c:if test="${empty articles.records}">
    <div class="articles-empty"><spring:message code="articles.empty" /></div>
</c:if>